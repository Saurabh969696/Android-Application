package com.example.home;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MusicPlayerActivity extends AppCompatActivity {

    MediaPlayer music;
    SeekBar seekBar, volumeControl;
    TextView currentTime, totalTime;
    Handler handler = new Handler();
    AudioManager audioManager;
    Button back;

    // Playlist and song index
    int[] playlist = {R.raw.song1, R.raw.song2, R.raw.song3};
    int currentSongIndex = 0;

    private static final String CHANNEL_ID = "music_notification_channel";
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        back=findViewById(R.id.button15);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate back to MainActivity
                Intent intent = new Intent(MusicPlayerActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Optional: Close BMI activity
            }
        });



        // Ask for notification permission for Android 13 and above
        requestNotificationPermission();

        // Initialize MediaPlayer with the first song in the playlist
        music = MediaPlayer.create(this, playlist[currentSongIndex]);

        // Initialize SeekBar and volume control
        initializePlayer();

        // Update SeekBar and current time every second
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (music != null && music.isPlaying()) {
                    seekBar.setProgress(music.getCurrentPosition());
                    currentTime.setText(formatTime(music.getCurrentPosition()));
                }
                handler.postDelayed(this, 1000);
            }
        }, 1000);

        // Create notification channel
        createNotificationChannel();
    }

    private void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (!(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Initialize SeekBar and TextViews
    private void initializePlayer() {
        // Initialize SeekBar for music progress
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(music.getDuration());

        // Initialize TextViews for current and total time
        currentTime = findViewById(R.id.currentTime);
        totalTime = findViewById(R.id.totalTime);
        totalTime.setText(formatTime(music.getDuration()));

        // Handle SeekBar for music progress
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    music.seekTo(progress);
                    currentTime.setText(formatTime(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Initialize volume control SeekBar
        volumeControl = findViewById(R.id.volumeSeekBar);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        volumeControl.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeControl.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

        // Handle volume control changes
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    // Play music
    public void musicplay(View v) {
        if (music != null && !music.isPlaying()) {
            music.start();
            createNotification();
        }
    }

    // Pause music
    public void musicpause(View v) {
        if (music != null && music.isPlaying()) {
            music.pause();
            createNotification();
        }
    }

    // Stop music and reset the player
    public void musicstop(View v) {
        if (music != null) {
            music.stop();
            music = MediaPlayer.create(this, playlist[currentSongIndex]);
            seekBar.setProgress(0); // Reset SeekBar to 0
            currentTime.setText(formatTime(0)); // Reset current time to 0
            createNotification();
        }
    }

    // Play next song (loop back to first song if at the end)
    public void playNextSong(View v) {
        if (currentSongIndex < playlist.length - 1) {
            currentSongIndex++;
        } else {
            currentSongIndex = 0; // Loop back to the first song
        }
        playSong();
    }

    // Play previous song (loop back to last song if at the beginning)
    public void playPreviousSong(View v) {
        if (currentSongIndex > 0) {
            currentSongIndex--;
        } else {
            currentSongIndex = playlist.length - 1; // Loop back to the last song
        }
        playSong();
    }

    // Play the song at the current index
    private void playSong() {
        if (music != null) {
            music.stop();
            music.release();
        }
        music = MediaPlayer.create(this, playlist[currentSongIndex]);
        music.start();
        seekBar.setMax(music.getDuration());
        totalTime.setText(formatTime(music.getDuration()));
        currentTime.setText(formatTime(0));
        createNotification();
    }

    // Format milliseconds to mm:ss
    private String formatTime(int ms) {
        int minutes = ms / 1000 / 60;
        int seconds = (ms / 1000) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    // Create notification with music controls
    private void createNotification() {
        // Check if notification permission is granted
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Notification permission is required to show notifications", Toast.LENGTH_SHORT).show();
                return; // Exit if permission is not granted
            }
        }

        NotificationManagerCompat notificationManager1= NotificationManagerCompat.from(this);

        // Next song action
        Intent playIntent = new Intent(this, NotificationReceiver.class);
        playIntent.setAction("ACTION_PLAY");
        PendingIntent playPendingIntent = PendingIntent.getBroadcast(this, 0, playIntent, PendingIntent.FLAG_IMMUTABLE);

        Intent pauseIntent = new Intent(this, NotificationReceiver.class);
        pauseIntent.setAction("ACTION_PAUSE");
        PendingIntent pausePendingIntent = PendingIntent.getBroadcast(this, 1, pauseIntent, PendingIntent.FLAG_IMMUTABLE);

        Intent nextIntent = new Intent(this, NotificationReceiver.class);
        nextIntent.setAction("ACTION_NEXT");
        PendingIntent nextPendingIntent = PendingIntent.getBroadcast(this, 2, nextIntent, PendingIntent.FLAG_IMMUTABLE);

        Intent previousIntent = new Intent(this, NotificationReceiver.class);
        previousIntent.setAction("ACTION_PREVIOUS");
        PendingIntent previousPendingIntent = PendingIntent.getBroadcast(this, 3, previousIntent, PendingIntent.FLAG_IMMUTABLE);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.music) // Ensure ic_music_note exists in res/drawable
                .setContentTitle("Playing Music")
                .setContentText("Your music is playing")
                .addAction(R.drawable.start, "Play", playPendingIntent)
                .addAction(R.drawable.pause, "Pause", pausePendingIntent)
                .addAction(R.drawable.next, "Next", nextPendingIntent)
                .addAction(R.drawable.back, "Previous", previousPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    // Create a notification channel
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Music Channel";
            String description = "Channel for music controls";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}

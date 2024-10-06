package com.example.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        MusicPlayerActivity mainActivity = (MusicPlayerActivity) context;


        if (action != null) {
            switch (action) {
                case "NEXT_SONG":
                    mainActivity.playNextSong(null);
                    break;
                case "PREVIOUS_SONG":
                    mainActivity.playPreviousSong(null);
                    break;
                case "PLAY_PAUSE":
                    if (mainActivity.music.isPlaying()) {
                        mainActivity.musicpause(null);
                    } else {
                        mainActivity.musicplay(null);
                    }
                    break;
            }
        }
    }
}


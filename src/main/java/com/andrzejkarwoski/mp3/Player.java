package com.andrzejkarwoski.mp3;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Player {
    private SongCollection songCollection;

    private Media media;
    private MediaPlayer mediaPlayer;

    public SongCollection getSongCollection() {
        return songCollection;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
    public Player(){
        this.songCollection = new SongCollection();
    }

    public void play() {
        if(mediaPlayer != null && (mediaPlayer.getStatus() == MediaPlayer.Status.READY || mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED)) {
            mediaPlayer.play();
        }
    }

    public void stop() {
        if(mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
        }
    }

    public double getLoadedSongLength() {
        if(media != null) {
            return media.getDuration().toSeconds();
        } else {
            return 0;
        }
    }

    public void setVolume(double volume) {
        if(mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }

    public void loadSong(int index) {
        if(mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.stop();
        }
        Song mp3s = songCollection.getSongList().get(index);
        media = new Media(new File(mp3s.getFilePath()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }
}

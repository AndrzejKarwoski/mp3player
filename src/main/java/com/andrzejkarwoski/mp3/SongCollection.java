package com.andrzejkarwoski.mp3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SongCollection {
    private ObservableList<Song> songList;

    public ObservableList<Song> getSongList(){
        return songList;
    }
    public void addSong(Song song){
        songList.add(song);
    }

    public void addSongs(ObservableList<Song> songs) {
        songList.addAll(songs);
    }

    public void clear(){
        songList.clear();
    }

    public SongCollection(){
        this.songList = FXCollections.observableArrayList();
    }
}

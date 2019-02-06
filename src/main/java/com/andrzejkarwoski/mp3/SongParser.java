package com.andrzejkarwoski.mp3;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;

public class SongParser {
    public Song createMp3Song(File file) {
        Song result = new Song();
        Mp3File mp3File = null;
        try {
            mp3File = new Mp3File(file);
        } catch (IOException | UnsupportedTagException | InvalidDataException e) {
            e.printStackTrace();
        }
        result.setFilePath(file.getAbsolutePath());
        result.setTitle(mp3File.getId3v1Tag().getTitle());
        result.setAuthor(mp3File.getId3v1Tag().getArtist());
        return result;
    }

    public ObservableList<Song> createMp3Songs(File dir) {
        if(!dir.isDirectory()) {
            return null;
        }

        ObservableList<Song> result = FXCollections.observableArrayList();
        File[] files = dir.listFiles();
        for(File f: files) {
            String fileExtension = f.getName().substring(f.getName().lastIndexOf(".") + 1);
            if(fileExtension.equals("mp3"))
                result.add(createMp3Song(f));
        }

        return result;
    }
}

package com.andrzejkarwoski.mp3;

public class Song {
    private String title;
    private String author;
    private String filePath;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title= title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author=author;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public Song(String title, String author) {
        this.title = title;
        this.author = author;
    }
    public Song(){}

    @Override
    public String toString() {
        return "Mp3Song [title=" + title + ", author=" + author + "]";
    }
}

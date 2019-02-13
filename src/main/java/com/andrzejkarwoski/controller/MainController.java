package com.andrzejkarwoski.controller;

import com.andrzejkarwoski.mp3.Player;
import com.andrzejkarwoski.mp3.Song;
import com.andrzejkarwoski.mp3.SongParser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private ContentPaneController contentPaneController;
    @FXML
    private ControlPaneController controlPaneController;
    @FXML
    private MenuPaneController menuPaneController;

    private Player player;
    private SongParser songParser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        player = new Player();
        songParser = new SongParser();
        configureControlPaneAction();
        configureVolume();
        configureTable();
        configureMenu();
    }

    private void configureMenu() {
        MenuItem openFile = menuPaneController.getFileMenuItem();
        MenuItem openDir = menuPaneController.getDirMenuItem();

        openFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fc = new FileChooser();
                fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Mp3", "*.mp3"));
                File file = fc.showOpenDialog(new Stage());
                player.getSongCollection().clear();
                player.getSongCollection().addSong(songParser.createMp3Song(file));
            }
        });

        openDir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DirectoryChooser dc = new DirectoryChooser();
                File dir = dc.showDialog(new Stage());
                player.getSongCollection().clear();
                player.getSongCollection().addSongs(songParser.createMp3Songs(dir));
            }
        });
    }

    private void configureTable() {
        TableView<Song> contentTable = contentPaneController.getContentTable();
        contentTable.setItems(player.getSongCollection().getSongList());
        contentTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    player.loadSong(contentTable.getSelectionModel().getSelectedIndex());
                    configureProgressBar();
                    controlPaneController.getPlayButton().setSelected(true);
                }
            }
        });
    }

    private void configureVolume(){
        Slider volumeSlider = controlPaneController.getVolumeSlider();
        double minVolume = 0;
        double maxVolume = 1;

        volumeSlider.setMin(minVolume);
        volumeSlider.setMax(maxVolume);
        volumeSlider.setValue(maxVolume);
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                player.setVolume(t1.doubleValue());
            }
        });
    }

    private void configureControlPaneAction(){
        TableView<Song> contentTable = contentPaneController.getContentTable();
        ToggleButton playButton = controlPaneController.getPlayButton();
        Button prevButton = controlPaneController.getPrevButton();
        Button nextButton = controlPaneController.getNextButton();

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (playButton.isSelected()) {
                    player.play();
                } else {
                    player.stop();
                }
            }
        });

        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                contentTable.getSelectionModel().select(contentTable.getSelectionModel().getSelectedIndex()+1);
                player.loadSong(contentTable.getSelectionModel().getSelectedIndex());
                configureProgressBar();
            }
        });

        prevButton.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                contentTable.getSelectionModel().select(contentTable.getSelectionModel().getSelectedIndex() - 1);
                player.loadSong(contentTable.getSelectionModel().getSelectedIndex());
                configureProgressBar();
            }
        }));
    }

    private void configureProgressBar() {
        Slider songSlider = controlPaneController.getSongSlider();
        player.getMediaPlayer().setOnReady(new Runnable() {
            @Override
            public void run() {
                songSlider.setMax(player.getLoadedSongLength());
            }
        });
        player.getMediaPlayer().currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> arg, Duration oldVal, Duration newVal) {
                songSlider.setValue(newVal.toSeconds());
            }
        });
        songSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(songSlider.isValueChanging()) {
                    player.getMediaPlayer().seek(Duration.seconds(newValue.doubleValue()));
                }

            }
        });
    }
    }
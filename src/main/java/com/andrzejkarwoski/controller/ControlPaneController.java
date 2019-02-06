package com.andrzejkarwoski.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlPaneController implements Initializable {
    @FXML
    private Button nextButton;
    @FXML
    private Button prevButton;

    @FXML
    private ToggleButton playPauseButton;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Slider progressSongSlider;

    public Button getPrevButton() {
        return prevButton;
    }

    public Button getNextButton() {
        return nextButton;
    }

    public ToggleButton getPlayButton() {
        return playPauseButton;
    }

    public Slider getVolumeSlider() {
        return volumeSlider;
    }

    public Slider getSongSlider() {
        return progressSongSlider;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}

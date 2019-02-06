package com.andrzejkarwoski.controller;

import com.andrzejkarwoski.mp3.Song;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

    public class ContentPaneController implements Initializable {

    @FXML
    private TableView<Song> contentTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureTable();
    }

    private void configureTable() {
        TableColumn<Song,String> titleColumn = new TableColumn<Song,String>("Tytuł");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Song, String> authorColumn = new TableColumn<Song, String>("Autor");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        contentTable.getColumns().add(titleColumn);
        contentTable.getColumns().add(authorColumn);
    }

    public TableView<Song> getContentTable(){
        return contentTable;
    }
}

package junk;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import junk.controls.RecordForm;
import javafx.scene.control.Alert.AlertType;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML public MenuItem menuItemEnableEdit, menuItemDisableEdit, menuItemShow, menuItemHide, menuItemExit;
    @FXML public MenuBar menuBar;
    @FXML public Label lableName, lableAlbum, lableMediaType, lableGenre, lableComposer, lablePrice;
    @FXML public TextField tfName, tfAlbum, tfMediaType, tfGenre, tfComposer, tfPrice;
    @FXML public VBox vBox;
    @FXML public Button btnRefresh, btnMakeEmpty, btnAdd, btnEdit, btnDelete;
    @FXML TableView<Songs> tableView;
    @FXML TableColumn<Songs,String> c1, c2, c3, c4, c5;
    @FXML TableColumn<Songs,Float> c6;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // recForm();
        eventHandler();
        createSongsTable();
    }
    @FXML private void closeWindowAction(){
        Platform.exit();
}
    private void recForm(){
        lableName.setText("Name");
        lableName.setText("Album");
        lableName.setText("Media type");
        lableGenre.setText("Gender");
        lableComposer.setText("Composer");
        lablePrice.setText("Price");


        btnAdd.setText("Add");
        btnEdit.setText("Edit");
        btnMakeEmpty.setText("Make Empty Fields");
        btnDelete.setText("Delete");
        btnRefresh.setText("Refresh");

    }
    private void eventHandler(){

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showSongDetails(newValue);
            btnEdit.setOnAction(event -> {
                String name = "", album = "", mediaType = "", genre = "", composer = "";
                float price = 0f;

                if (! tfName.getText().isEmpty())
                    name =  tfName.getText();
                if (! tfAlbum.getText().isEmpty())
                    album =  tfAlbum.getText();
                if (! tfMediaType.getText().isEmpty())
                    mediaType =  tfMediaType.getText();
                if (! tfGenre.getText().isEmpty())
                    genre =  tfGenre.getText();
                if (! tfComposer.getText().isEmpty())
                    composer =  tfComposer.getText();
                if (! tfPrice.getText().isEmpty())
                    if (RecordForm. validationCheck( tfPrice.getText()))
                        price = Float.parseFloat(  tfPrice.getText());

                DataNode.editDB(name, album, mediaType, genre, composer, price, newValue.getName(), newValue.getAlbum(), newValue.getComposer(), newValue.getMediaType(), newValue.getGenre(), newValue.getPrice());
                clearFields();
                //createSongsTable();
            });
            btnDelete.setOnAction(event -> {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                //alert.setHeaderText("Look, a Confirmation Dialog");
                alert.setContentText("Ar tikrai nori ištrinti įrašą?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    DataNode. deleteFromDB(newValue.getName(), newValue.getAlbum(), newValue.getMediaType(), newValue.getGenre(), newValue.getComposer());
                    clearFields();
                }


            });
        });
        menuItemDisableEdit.setOnAction(event -> toggleButtons(true));
        menuItemEnableEdit.setOnAction(event -> toggleButtons(false));
        menuItemHide.setOnAction(event -> {
            //vBox.getChildren().remove(vBox);
            vBox.setVisible(false);

        });
        menuItemShow.setOnAction(event -> {
            vBox.setVisible(true);
        });
        btnMakeEmpty.setOnAction(event -> {
            clearFields();
        });
        btnAdd.setOnAction((value) ->{
            String name = "", album = "", mediaType = "", genre = "", composer = "";
            float price = 0;

            if (!tfName.getText().isEmpty())
                name = tfName.getText();
            if (!tfAlbum.getText().isEmpty())
                album =  tfAlbum.getText();
            if (! tfMediaType.getText().isEmpty())
                mediaType =  tfMediaType.getText();
            if (! tfGenre.getText().isEmpty())
                genre =  tfGenre.getText();
            if (! tfComposer.getText().isEmpty())
                composer =  tfComposer.getText();
            if (! tfPrice.getText().isEmpty())
                if (RecordForm. validationCheck( tfPrice.getText()))
                    price = Integer.parseInt(  tfPrice.getText());

            DataNode. addDataToDB(name, album, mediaType, genre, composer, price);
            //table_songs.setVisible(false);
            //     createSongsTable();
            //tableView.refresh();
            clearFields();
        });
        btnRefresh.setOnAction(event -> {
            tableView.refresh();
            DataNode dataNode = new DataNode();

            dataNode.getdata("songs");
            // ObservableList list = dataNode.getSongsObservableList();
            tableView.setItems(dataNode.getSongsObservableList());
        });
    }
    private void createSongsTable(){
        DataNode dataNode = new DataNode();

        c1.setText("Song name");
        c1.setCellValueFactory(new PropertyValueFactory<>("name"));
        c1.setMinWidth(Region.USE_PREF_SIZE);

        c2.setText("Album");
        c2.setCellValueFactory(new PropertyValueFactory<>("album"));
        c2.setMinWidth(Region.USE_PREF_SIZE);

        c3.setText("Media type");
        c3.setCellValueFactory(new PropertyValueFactory<>("mediaType"));
        c3.setMinWidth(Region.USE_PREF_SIZE);

        c4.setText("Genre");
        c4.setCellValueFactory(new PropertyValueFactory<>("genre"));
        c4.setMinWidth(Region.USE_PREF_SIZE);

        c5.setText("Composer");
        c5.setCellValueFactory(new PropertyValueFactory<>("composer"));
        c5.setMinWidth(Region.USE_PREF_SIZE);

        c6.setText("Price");
        c6.setCellValueFactory(new PropertyValueFactory<>("price"));
        c6.setMinWidth(Region.USE_PREF_SIZE);

        dataNode.getdata("songs");
        tableView.setItems(dataNode.getSongsObservableList());
        System.out.println("Table populated");

        tableView.setMinWidth(800);


    }
    private void clearFields(){
        tfName.clear();
        tfAlbum.clear();
        tfMediaType.clear();
        tfGenre.clear();
        tfComposer.clear();
        tfPrice.clear();
    }
    private void showSongDetails(Songs song){
        if (song != null)
        {
            tfName.setText(song.getName());
            tfAlbum.setText(song.getAlbum());
            tfMediaType.setText(song.getMediaType());
            tfGenre.setText(song.getGenre());
            tfComposer.setText(song.getComposer());
            tfPrice.setText(song.getPrice().toString());
        } else {
            tfName.setText(null);
            tfAlbum.setText(null);
            tfMediaType.setText(null);
            tfGenre.setText(null);
            tfComposer.setText(null);
            tfPrice.setText(null);
        }
    }
    private void toggleButtons(Boolean state){
        tfName.setDisable(state);
        tfAlbum.setDisable(state);
        tfMediaType.setDisable(state);
        tfComposer.setDisable(state);
        tfGenre.setDisable(state);
        tfPrice.setDisable(state);

        btnAdd.setDisable(state);
        btnEdit.setDisable(state);
        btnMakeEmpty.setDisable(state);
        btnDelete.setDisable(state);
        btnRefresh.setDisable(state);

    }
}
package junk;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import junk.controls.RecordForm;
import junk.controls.StatusBar;


public class FormView extends BorderPane {
    GridPane	rec_form;
    StatusBar sts_bar;
    MenuBar menu_bar;
    MenuItem	mit_file, mit_dbase, mit_exit;
    TableView<Songs> table_songs;
    MenuItem menuItem_enable_edit, menuItem_disable_edit, menuItem_enable_ViewForm, menuItem_disable_ViewForm;
    VBox vBox;

    FormView(){
        initForm();
    }
    private void initForm(){
        this.setPadding(new Insets(10,10,10,10));
        // this.sts_bar = new StatusBar();


        createMenuBar();
        createSongsTable();
        createRecForm();
        setEventHandlers();
    }
    private void createRecForm(){
        vBox = new VBox(10);
        {
            rec_form = new GridPane();
            {
                rec_form.setAlignment(Pos.TOP_LEFT);
                rec_form.setHgap(20);
                rec_form.setVgap(10);
                rec_form.setPadding(new Insets(25, 25, 25, 25));

                RecordForm. name = new Label("Name");
                RecordForm.	album = new Label("Album");
                RecordForm. mediaType = new Label("Media Type");
                RecordForm. genre = new Label("Genre");
                RecordForm. composer = new Label("Composer");
                RecordForm. price = new Label("Price");

                RecordForm. tf_Name = new TextField();
                RecordForm. tf_Album = new TextField();
                RecordForm. tf_MediaType = new TextField();
                RecordForm. tf_Genre = new TextField();
                RecordForm. tf_Composer = new TextField();
                RecordForm. tf_Prise = new TextField();
               // RecordForm. tf_Prise.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));

                rec_form.add(RecordForm. name,		0, 0);
                rec_form.add(RecordForm. album,		0, 1);
                rec_form.add(RecordForm. mediaType,		0, 2);
                rec_form.add(RecordForm. genre,	0, 3);
                rec_form.add(RecordForm. composer,	0, 4);
                rec_form.add(RecordForm. price,  0, 5);

                rec_form.add(RecordForm. tf_Name,		1, 0, 1, 1);
                rec_form.add(RecordForm. tf_Album,		1, 1, 1, 1);
                rec_form.add(RecordForm. tf_MediaType,		1, 2, 1, 1);
                rec_form.add(RecordForm. tf_Genre,	1, 3, 1, 1);
                rec_form.add(RecordForm. tf_Composer,	1, 4, 1, 1);
                rec_form.add(RecordForm. tf_Prise, 1,5, 1, 1);

                rec_form.setMinWidth(300);
            }
            vBox.getChildren().add(rec_form);

            HBox btn_bar = new HBox(10);
            {
                RecordForm. btn_add  = new Button("Add");
                RecordForm. btn_edit = new Button("Edit");
                RecordForm. btn_MakeEmpty = new Button("Make Empty Fields");
                RecordForm. btn_Delete = new Button("Delete");
                RecordForm. btn_Reftesh = new Button("Refresh");


                btn_bar.getChildren().addAll(RecordForm. btn_Reftesh ,RecordForm. btn_MakeEmpty, RecordForm. btn_add, RecordForm. btn_edit, RecordForm. btn_Delete);
                btn_bar.setAlignment(Pos.TOP_RIGHT);
            }
            vBox.getChildren().addAll(new Separator(), btn_bar);
        }
        this.setCenter(vBox);
    }
    private void createSongsTable(){
        DataNode dataNode = new DataNode();
        table_songs = new TableView<>();
        {
            //table_songs.setItems(model.getSongData());
            TableColumn<Songs, String> col_name = new TableColumn<>("Song name");
            col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_name.setMinWidth(Region.USE_PREF_SIZE);

            TableColumn<Songs, String> col_album = new TableColumn<>("Album");
            col_album.setCellValueFactory(new PropertyValueFactory<>("album"));
            col_album.setMinWidth(Region.USE_PREF_SIZE);

            TableColumn<Songs, String> col_mediaType = new TableColumn<>("Media type");
            col_mediaType.setCellValueFactory(new PropertyValueFactory<>("mediaType"));
            col_mediaType.setMinWidth(Region.USE_PREF_SIZE);

            TableColumn<Songs, String> col_genre = new TableColumn<>("Genre");
            col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
            col_genre.setMinWidth(Region.USE_PREF_SIZE);

            TableColumn<Songs, String> col_composer = new TableColumn<>("Composer");
            col_composer.setCellValueFactory(new PropertyValueFactory<>("composer"));
            col_composer.setMinWidth(Region.USE_PREF_SIZE);

            TableColumn<Songs, Float> col_price = new TableColumn<>("Price");
            col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
            col_price.setMinWidth(Region.USE_PREF_SIZE);

            table_songs.getColumns().addAll(col_name, col_album, col_mediaType, col_genre, col_composer, col_price);
            dataNode.getdata("songs");
            table_songs.setItems(dataNode.getSongsObservableList());

        }
       /* try {
            RecordForm. btn_Reftesh.setOnAction(event -> {
                table_songs.refresh();
            });
        }catch (NullPointerException e){
            e.printStackTrace();
        }*/

        table_songs.setMinWidth(800);
        //table_songs.setMinWidth(BorderPane.USE_PREF_SIZE);
        this.setLeft(table_songs);

    }
    private void createMenuBar() {
        menu_bar = new MenuBar();
        {
            Menu menu_file = new Menu("File");
            {
                mit_file = new MenuItem("Open Text File");
                mit_dbase = new MenuItem("Open Data Base");
                mit_exit = new MenuItem("Exit");

                menu_file.getItems().addAll
                        (
                                //mit_dbase,
                                new SeparatorMenuItem(),
                                mit_exit
                        );
                menu_bar.getMenus().add(menu_file);
            }
            Menu menu_edit = new Menu("Edit");
            {
                menuItem_enable_edit = new MenuItem("Enable edit");
                menuItem_disable_edit = new MenuItem("Disable edit");
                menuItem_enable_ViewForm = new MenuItem("Show ViewForm");
                menuItem_disable_ViewForm = new MenuItem("Hide ViewForm");
                menu_edit.getItems().addAll(menuItem_enable_edit, menuItem_disable_edit, menuItem_enable_ViewForm, menuItem_disable_ViewForm);
                menu_bar.getMenus().add(menu_edit);
            }
            this.setTop(menu_bar);
        }
    }
    private void toggleButtons(Boolean state){
        RecordForm. tf_Name.setDisable(state);
        RecordForm. tf_Album.setDisable(state);
        RecordForm. tf_MediaType.setDisable(state);
        RecordForm. tf_Composer.setDisable(state);
        RecordForm. tf_Genre.setDisable(state);
        RecordForm. tf_Prise.setDisable(state);

        RecordForm. btn_add.setDisable(state);
        RecordForm. btn_edit.setDisable(state);
        RecordForm. btn_MakeEmpty.setDisable(state);
        RecordForm. btn_Delete.setDisable(state);
        RecordForm. btn_Reftesh.setDisable(state);

    }
    private void setEventHandlers(){
        mit_exit.setOnAction(event -> {
            Window window  = getScene().getWindow();
            if (window instanceof Stage)
                ((Stage) window).close();
        });
        table_songs.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showSongDetails(newValue);
            RecordForm.btn_edit.setOnAction(event -> {
                String name = "", album = "", mediaType = "", genre = "", composer = "";
                float price = 0f;

                if (!RecordForm. tf_Name.getText().isEmpty())
                    name = RecordForm. tf_Name.getText();
                if (!RecordForm. tf_Album.getText().isEmpty())
                    album = RecordForm. tf_Album.getText();
                if (!RecordForm. tf_MediaType.getText().isEmpty())
                    mediaType = RecordForm. tf_MediaType.getText();
                if (!RecordForm. tf_Genre.getText().isEmpty())
                    genre = RecordForm. tf_Genre.getText();
                if (!RecordForm. tf_Composer.getText().isEmpty())
                    composer = RecordForm. tf_Composer.getText();
                if (!RecordForm. tf_Prise.getText().isEmpty())
                    if (RecordForm. validationCheck(RecordForm. tf_Prise.getText()))
                        price = Float.parseFloat( RecordForm. tf_Prise.getText());

                DataNode.editDB(name, album, mediaType, genre, composer, price, newValue.getName(), newValue.getAlbum(), newValue.getComposer(), newValue.getMediaType(), newValue.getGenre(), newValue.getPrice());
                clearFields();
                //createSongsTable();
            });
            RecordForm. btn_Delete.setOnAction(event -> {
                DataNode. deleteFromDB(newValue.getName(), newValue.getAlbum(), newValue.getMediaType(), newValue.getGenre(), newValue.getComposer());
                clearFields();

            });
        });
        menuItem_disable_edit.setOnAction(event -> toggleButtons(true));
        menuItem_enable_edit.setOnAction(event -> toggleButtons(false));
        menuItem_disable_ViewForm.setOnAction(event -> {
            //vBox.getChildren().remove(vBox);
            vBox.setVisible(false);

        });
        menuItem_enable_ViewForm.setOnAction(event -> {
            vBox.setVisible(true);
        });
        RecordForm. btn_MakeEmpty.setOnAction(event -> {
            clearFields();
        });
        RecordForm.btn_add.setOnAction((value) ->{
            String name = "", album = "", mediaType = "", genre = "", composer = "";
            float price = 0;

            if (!RecordForm. tf_Name.getText().isEmpty())
                name = RecordForm. tf_Name.getText();
            if (!RecordForm. tf_Album.getText().isEmpty())
                album = RecordForm. tf_Album.getText();
            if (!RecordForm. tf_MediaType.getText().isEmpty())
                mediaType = RecordForm. tf_MediaType.getText();
            if (!RecordForm. tf_Genre.getText().isEmpty())
                genre = RecordForm. tf_Genre.getText();
            if (!RecordForm. tf_Composer.getText().isEmpty())
                composer = RecordForm. tf_Composer.getText();
            if (!RecordForm. tf_Prise.getText().isEmpty())
                if (RecordForm. validationCheck(RecordForm. tf_Prise.getText()))
                    price = Integer.parseInt( RecordForm. tf_Prise.getText());

            DataNode. addDataToDB(name, album, mediaType, genre, composer, price);
            //table_songs.setVisible(false);
       //     createSongsTable();
            table_songs.refresh();
            clearFields();
        });
        RecordForm. btn_Reftesh.setOnAction(event -> {
            table_songs.refresh();
            DataNode dataNode = new DataNode();
            dataNode.getdata("songs");
            table_songs.setItems(dataNode.getSongsObservableList());
        });
    }
    private void clearFields(){
        RecordForm. tf_Name.clear();
        RecordForm. tf_Album.clear();
        RecordForm. tf_MediaType.clear();
        RecordForm. tf_Genre.clear();
        RecordForm. tf_Composer.clear();
        RecordForm. tf_Prise.clear();
    }
    private void showSongDetails(Songs song){
        if (song != null)
        {
            RecordForm. tf_Name.setText(song.getName());
            RecordForm. tf_Album.setText(song.getAlbum());
            RecordForm. tf_MediaType.setText(song.getMediaType());
            RecordForm. tf_Genre.setText(song.getGenre());
            RecordForm. tf_Composer.setText(song.getComposer());
            RecordForm. tf_Prise.setText(song.getPrice().toString());
        } else {
            RecordForm. tf_Name.setText(null);
            RecordForm. tf_Album.setText(null);
            RecordForm. tf_MediaType.setText(null);
            RecordForm. tf_Genre.setText(null);
            RecordForm. tf_Composer.setText(null);
            RecordForm. tf_Prise.setText(null);
        }
    }
}

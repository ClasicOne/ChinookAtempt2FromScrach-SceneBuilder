package junk;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    DataNode dataNode = null;
    @Override
    public void init() throws Exception {
        super.init();
        this.dataNode = new DataNode();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        new FirstStage();
        new SecondStage();
//        //FormView root= new FormView();
//        primaryStage.setTitle("Songs db SceneBuilder crap");
//        primaryStage.setScene(new Scene(root, Region.USE_PREF_SIZE, Region.USE_PREF_SIZE));
//        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

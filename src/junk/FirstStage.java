package junk;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class FirstStage extends Stage{
    FirstStage()throws Exception{
        this.setTitle("Songs db SceneBuilder ");
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        this.setScene(new Scene(root, Region.USE_PREF_SIZE, Region.USE_PREF_SIZE));
        this.show();
    }

}

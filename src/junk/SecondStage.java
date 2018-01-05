package junk;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;


public class SecondStage extends Stage{
    public SecondStage(){
        FormView root= new FormView();
        this.setTitle("Songs db");
        this.setScene(new Scene(root, Region.USE_PREF_SIZE, Region.USE_PREF_SIZE));
        this.show();
    }
}

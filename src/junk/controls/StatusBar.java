package junk.controls;

import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StatusBar extends AnchorPane {

    private Label the_message;
    private Label the_datetxt;
    private Separator the_separator;

    //----- Constructors

    public StatusBar()
    {
        initStatusBar();
    }

    public StatusBar(String init_msg)
    {
        initStatusBar(init_msg);
    }

    private void initStatusBar()
    {
        initStatusBar("Ready...");
    }

    private void initStatusBar (String message){
        this.the_message = new Label();
        this.the_datetxt = new Label();
        this.the_separator = new Separator();

        the_separator.setOrientation(Orientation.VERTICAL);
        the_separator.setMaxHeight(20);

        AnchorPane.setLeftAnchor(the_message, 5.0);
        AnchorPane.setRightAnchor(the_datetxt, 5.0);
        AnchorPane.setRightAnchor(the_separator, 200.0);

        this.getChildren().clear();
        this.getChildren().addAll(the_message, the_separator, the_datetxt);
    }

    public void setMessage(String text){
        this.the_message.setText(text);
        this.setDate();
    }

    public void setDate (){
        String DT_FORMAT = "yyyy.MM.dd [(HH.mm.ss)]";
        this.the_datetxt.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DT_FORMAT)));
    }
}

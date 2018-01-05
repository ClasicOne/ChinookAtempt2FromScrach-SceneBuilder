package junk.controls;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class RecordForm extends VBox{

    public static Label name, album, composer, genre, mediaType, price;
    public static TextField tf_Name, tf_Album, tf_Composer, tf_Genre, tf_MediaType, tf_Prise;
    public static Button		btn_add, btn_edit, btn_MakeEmpty, btn_Delete, btn_Reftesh;

    public static boolean validationCheck(String s){
        boolean valid = false;
        try {
            Float.parseFloat(s);
            valid = true;
        }catch (NumberFormatException e){
            System.out.println("This is not number");
        }
        return valid;
    }
}

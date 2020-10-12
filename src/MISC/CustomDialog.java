package MISC;

import Menu.Screen;
import Menu.main_menu;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

public class CustomDialog {

    private Stage owner;
    private String title;
    private String message;
    private String button;
    //private Circle pattern;


    public CustomDialog(Stage owner, String title, String message, String button, ImagePattern imgx)
    {
        Circle c = new Circle(50);
        c.setFill(imgx);
        this.owner=owner;
        this.title=title;
        this.message=message;
        this.button=button;
        settingUP((Node) c);
    }
    public CustomDialog(Stage owner, String title, String message, String button, String url)
    {
        ImageView img = new ImageView(new Image(url,150,150,true,false));
        this.owner=owner;
        this.title=title;
        this.message=message;
        this.button=button;
        settingUP((Node) img);
    }

    public void settingUP(Node n)
    {
        Label label=new Label(message);
        label.setTextFill(Color.WHITE);
        Button ok=new Button(button);
        new ButtonsStyling(ok,20);
        VBox layout2=new VBox();
        layout2.setAlignment(Pos.CENTER);
        layout2.getChildren().addAll(label,n,ok);
        Stage stage=new Stage();
        stage.setTitle(title);
        ok.setOnAction(event -> {
            stage.close();
        });
        Scene scene =new Scene(layout2,Screen.getNotiWidth(),Screen.getNotiHeight());
        stage.setScene(scene);
        new CustomDialogsSetting(stage,owner,layout2);
        ok.requestFocus();
    }
}

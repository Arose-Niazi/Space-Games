package MISC;

import Menu.Screen;
import Menu.main_menu;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SignInNotifiction {
    public SignInNotifiction(Stage owner)
    {
        ImageView img = new ImageView(new Image("Images/crash.png",150,150,true,false));
        Label label=new Label("Hello? Please identify yourself.\nThe space station can not recognize you. ");
        label.setTextFill(Color.WHITE);
        Button ok=new Button("Sign In");
        new ButtonsStyling(ok,20);
        VBox layout2=new VBox();
        layout2.setAlignment(Pos.CENTER);
        layout2.getChildren().addAll(label,img,ok);

        Stage stage=new Stage();
        stage.setTitle("LOGIN ERROR!");
        ok.setOnAction(event -> {
            stage.close();
            main_menu.getMain().SignIn();
        });
        Scene scene =new Scene(layout2,Screen.getNotiWidth(),Screen.getNotiHeight());
        stage.setScene(scene);
        new CustomDialogsSetting(stage,owner,layout2);
        ok.requestFocus();
        ok.setDefaultButton(true);

    }
}

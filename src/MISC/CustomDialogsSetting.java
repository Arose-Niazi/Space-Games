package MISC;

import Menu.Screen;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CustomDialogsSetting {

    public CustomDialogsSetting(Stage stage, Stage owner, VBox box)
    {
        /*BackgroundImage b = new BackgroundImage(new Image("Images/radar.jpg", Screen.getNotiWidth(),Screen.getNotiHeight(),true,false), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);*/

        box.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0),CornerRadii.EMPTY, Insets.EMPTY)));
        box.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        stage.initOwner(owner);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();

    }
}

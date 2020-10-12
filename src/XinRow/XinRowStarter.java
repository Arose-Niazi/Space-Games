package XinRow;

import Games.XinRowGame;
import MISC.ButtonsStyling;
import MISC.StageDisplay;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Menu.*;

public class XinRowStarter implements XinRowGame {
    private Label noPlayersL,noInRowL;
    private ChoiceBox<Integer> noPlayers;
    private ChoiceBox<Integer> noInRow;
    private Button start,back;
    private GridPane layout;

    public XinRowStarter(){
        noPlayers=new ChoiceBox<Integer>();
        noInRow=new ChoiceBox<Integer>();

        noPlayersL=new Label("Players:");
        noInRowL=new Label("In Rows:");

        noPlayers.getItems().removeAll(noPlayers.getItems());
        noPlayers.getItems().addAll(2,3,4,5,6,7,8,9,10);
        noPlayers.getSelectionModel().select(0);

        noInRow.getItems().removeAll(noInRow.getItems());
        noInRow.getItems().addAll(2,3,4,5,6,7);
        noInRow.getSelectionModel().select(2);

        noPlayersL.setTextFill(Color.WHITE);
        noInRowL.setTextFill(Color.WHITE);

        start=new Button("Start");
        back=new Button("Back");

        layout=new GridPane();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setVgap(10);
        layout.setHgap(10);

        int i=0;
        for(; i<Screen.getSceneWidth(); i+=75) layout.getColumnConstraints().add(new ColumnConstraints(75));
        int k=0;
        for(; k<Screen.getFullScreenHeight(); k+=50) layout.getRowConstraints().add(new RowConstraints(50));
        i/=75;
        k/=50;
        layout.setConstraints(noPlayersL,i/2 - 2,k/2 - 10);
        layout.setConstraints(noPlayers,i/2 - 1,k/2 - 10);
        layout.setConstraints(noInRowL,i/2 - 2,k/2 - 9);
        layout.setConstraints(noInRow,i/2 - 1,k/2 - 9);
        layout.setConstraints(start,i/2 - 2,k/2 - 8);
        layout.setConstraints(back,i/2 - 1,k/2 - 8);

        new ButtonsStyling(back,14);
        new ButtonsStyling(start,14);

        layout.getChildren().addAll(noPlayersL,noPlayers,noInRowL,noInRow,start,back);

        back.setOnAction(event -> {
            new GameSelect();
        });

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new XinRow(noPlayers.getValue(),noInRow.getValue());
            }
        });

        BackgroundImage b = new BackgroundImage(new Image("Images/space.jpg", Screen.getWidth(), Screen.getFullScreenHeight(),true,false), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        layout.setBackground(new Background(b));

        Stage stage = main_menu.getMain().getStage();
        new StageDisplay(layout,"In Row Selector");
    }
}

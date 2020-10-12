package Menu;

import Blackjack.BlackJack;
import BrickBreaker.BrickBreaker;
import MISC.ButtonsStyling;
import MISC.StageDisplay;
import TickCross.TickCross;
import XinRow.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import G2048.g2048;

public class GameSelect {
    private Button xinrow,tickcross,blackjack,brickbreaker, g2048,back;
    private GridPane layout;
    private Stage stage;

    public GameSelect(){
        xinrow = new Button("X in row");
        blackjack = new Button("Blackjack");
        tickcross = new Button("Tick cross");
        brickbreaker = new Button("Brick breaker");
        g2048 = new Button("2048");
        back = new Button("Back");

        layout=new GridPane();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setVgap(30);
        layout.setHgap(10);

        int i=0;
        for(; i<Screen.getSceneWidth(); i+=170) layout.getColumnConstraints().add(new ColumnConstraints(170));
        int k=0;
        for(; k<Screen.getFullScreenHeight(); k+=70) layout.getRowConstraints().add(new RowConstraints(70));
        i/=120;
        k/=70;
        k+=3;
        layout.setConstraints(xinrow,i/2 - 3,k/2 - 10);
        layout.setConstraints(blackjack,i/2 - 3,k/2 - 9);
        layout.setConstraints(tickcross,i/2 - 3,k/2 - 8);
        layout.setConstraints(brickbreaker,i/2 - 3,k/2 - 7);
        layout.setConstraints(g2048,i/2 - 3,k/2 - 6);
        layout.setConstraints(back,i/2 - 3,k/2 - 5);

        new ButtonsStyling(xinrow,20);
        new ButtonsStyling(blackjack,20);
        new ButtonsStyling(tickcross,20);
        new ButtonsStyling(brickbreaker,20);
        new ButtonsStyling(g2048,20);
        new ButtonsStyling(back,20);

        xinrow.setOnAction(event -> {
            new XinRowStarter();
        });

        blackjack.setOnAction(event -> {
            new BlackJack();
        });

        brickbreaker.setOnAction(event -> {
            new BrickBreaker();
        });

        g2048.setOnAction(event -> {
            new g2048();
        });

        tickcross.setOnAction(event -> {
            new TickCross();
        });

        layout.getChildren().addAll(xinrow,blackjack,tickcross,brickbreaker, g2048,back);

        back.setOnAction(event -> {
            main_menu.getMain().showMainMenu();
        });

        BackgroundImage b = new BackgroundImage(new Image("Images/space.jpg", Screen.getWidth(), Screen.getFullScreenHeight(),true,false), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        layout.setBackground(new Background(b));

        stage = main_menu.getMain().getStage();
        new StageDisplay(layout,"Game Select");
    }
}

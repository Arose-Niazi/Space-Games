package Menu;

import Blackjack.BlackJack;
import BrickBreaker.BrickBreaker;
import G2048.g2048;
import MISC.ButtonsStyling;
import MISC.StageDisplay;
import Menu.Screen;
import Menu.main_menu;
import Player.TopPlayers;
import TickCross.TickCross;
import XinRow.XinRowStarter;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Stats {
    private Button xinrow,tickcross,blackjack,brickbreaker, g2048,back;
    private GridPane layout;
    private Stage stage;

    public Stats(){
        TopPlayers topPlayers = new TopPlayers();
        xinrow = new Button("X in row = " + topPlayers.xInRow());
        blackjack = new Button("Blackjack = " + topPlayers.blackJack());
        tickcross = new Button("Tick cross = "+ topPlayers.tickCross()  );
        brickbreaker = new Button("Brick breaker = "+ topPlayers.brickBreaker());
        g2048 = new Button("2048 = "+ topPlayers.g2048());
        back = new Button("Back");

        layout=new GridPane();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setVgap(30);
        layout.setHgap(10);

        int i=0;

        int k=0;
        for(; k< Screen.getFullScreenHeight(); k+=70) layout.getRowConstraints().add(new RowConstraints(70));
        k/=70;
        k+=3;
        layout.setConstraints(xinrow,i,k/2 - 10);
        layout.setConstraints(blackjack,i,k/2 - 9);
        layout.setConstraints(tickcross, i,k/2 - 8);
        layout.setConstraints(brickbreaker,i,k/2 - 7);
        layout.setConstraints(g2048,i,k/2 - 6);
        layout.setConstraints(back,i,k/2 - 5);

        new ButtonsStyling(xinrow,20);
        new ButtonsStyling(blackjack,20);
        new ButtonsStyling(tickcross,20);
        new ButtonsStyling(brickbreaker,20);
        new ButtonsStyling(g2048,20);
        new ButtonsStyling(back,20);


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
        new StageDisplay(layout,"Stats");
    }
}

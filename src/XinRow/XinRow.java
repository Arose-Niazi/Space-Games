package XinRow;

import Games.XinRowGame;
import MISC.ButtonsStyling;
import MISC.StageDisplay;
import Menu.GameSelect;
import Menu.Screen;
import Menu.main_menu;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import Player.*;

import java.util.ArrayList;

public class XinRow implements XinRowGame {

    static Group root;
    private static AnchorPane layout;
    static boolean canSelectNext;
    static Player currentPlayer;
    static Working onGoingGame;
    static boolean gameStarted;
    static XinRow cMain;

    private static ArrayList<Label> playerLabels = new ArrayList<>();
    private static ArrayList<Circle> playerCircles = new ArrayList<>();
    private static int cpCounter=0;

    private BackGroundGrid grid;
    private Button startButton,backButton;
    private int playersMax;
    private int toWin;
    private Stage stage;


    static {
        canSelectNext=false;
        gameStarted=false;
    }

    public XinRow(int playersMax, int toWin)
    {
        cMain=this;
        this.playersMax=playersMax;
        this.toWin=toWin;
        if(layout == null)
            start();
        else
            new StageDisplay(layout,toWin + " In Row");
    }

    public void start(){

        root = new Group();
        stage = main_menu.getMain().getStage();

        grid = new BackGroundGrid();

        try {
            root.getChildren().add(grid);
            root.getChildren().addAll(grid.spaces);
            root.getChildren().addAll(grid.clickableCricles);
        }
        catch (Exception e)
        {

        }
        startButton= new Button("PLAY!");
        new ButtonsStyling(startButton,25);
        startButton.setLayoutX(Screen.getWidth() - 900);
        startButton.setLayoutY(Screen.getHeight() - 75);
        startButton.setOnAction(e -> {
            startGame();
        });

        backButton= new Button("GAME SELECT");
        new ButtonsStyling(backButton,25);
        backButton.setLayoutX(Screen.getWidth() - 200);
        backButton.setLayoutY(Screen.getHeight() - 75);
        backButton.setOnAction(e -> {
            new GameSelect();
        });

        root.getChildren().add(startButton);
        root.getChildren().add(backButton);

        layout = new AnchorPane();
        BackgroundImage b = new BackgroundImage(new Image("Images/space.jpg", Menu.Screen.getWidth(), Menu.Screen.getFullScreenHeight(),true,false), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        layout.setBackground(new Background(b));
        layout.getChildren().add(root);



        new StageDisplay(layout,toWin + " In Row");


    }

    public void startGame()
    {
        root.getChildren().removeAll(BackGroundGrid.allPlacedCircles);
        BackGroundGrid.resetGrid();
        gameStarted=canSelectNext=true;
        startButton.setDisable(true);
        onGoingGame = new Working(this,playersMax,toWin);
    }

    public void endGame()
    {
        gameStarted=canSelectNext=false;
        startButton.setDisable(false);
    }

    public void addPlayersData(Player p)
    {

        playerLabels.add(new Label(p.getName()));
        playerLabels.get(cpCounter).setLayoutX(5);
        playerLabels.get(cpCounter).setLayoutY(10 + 80*cpCounter);
        playerLabels.get(cpCounter).setFont(Font.font("Ariel", FontWeight.EXTRA_BOLD,10.0));
        playerLabels.get(cpCounter).setTextFill(Color.WHITE);
        playerCircles.add( new Circle(20 ,20 + 80*cpCounter,40));
        playerCircles.get(cpCounter).setFill(p.getPlanet());
        root.getChildren().addAll(playerCircles.get(cpCounter),playerLabels.get(cpCounter));
        cpCounter++;
    }

    public void resetPlayers()
    {
        root.getChildren().removeAll(playerLabels);
        root.getChildren().removeAll(playerCircles);
        cpCounter=0;
    }

    public Stage getStage() {
        return stage;
    }
}

package TickCross;

import Games.TickCrossGame;
import MISC.ButtonsStyling;
import MISC.CustomDialog;
import MISC.StageDisplay;
import Menu.GameSelect;
import Menu.Screen;
import Menu.main_menu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TickCross implements TickCrossGame {

    private Label playerOneLabel, playerTwoLabel;
    private int emptyBoxChecker, playerOnePoints, playerTwoPoints;
    private int movesCounter=0;
    private TextField[][] boxes;
    private Pane inner;

    private Button backButton;

    public TickCross(){

        playerOnePoints =main_menu.getMain().getPlayer().getWins(this);
        playerTwoPoints =0;
        boxes=new TextField[3][3];
        inner=new Pane();

        playerOneLabel =new Label(main_menu.getMain().getPlayer().getName()+ "(✖): " + playerOnePoints);
        playerTwoLabel =new Label("Player 2(✔): "+ playerTwoPoints);
        playerOneLabel.setTextFill(Color.WHITE);
        playerTwoLabel.setTextFill(Color.WHITE);
        playerOneLabel.setLayoutY((Screen.getSceneHeight()/2) + (53*2) + 10);
        playerTwoLabel.setLayoutY((Screen.getSceneHeight()/2) + (53*2) + 25);
        playerOneLabel.setLayoutX(Screen.getScreenCenter() - 53);
        playerTwoLabel.setLayoutX(Screen.getScreenCenter() - 53);


        Reset();

        backButton= new Button("GAME SELECT");
        new ButtonsStyling(backButton,25);
        backButton.setLayoutX(Screen.getWidth() - 200);
        backButton.setLayoutY(Screen.getHeight() - 75);
        backButton.setOnAction(e -> {
            new GameSelect();
        });

        BackgroundImage b = new BackgroundImage(new Image("Images/space.jpg", Menu.Screen.getWidth(), Menu.Screen.getFullScreenHeight(),true,false), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        inner.setBackground(new Background(b));

        inner.getChildren().addAll(playerOneLabel, playerTwoLabel,backButton);

        new StageDisplay(inner,"Tick Cross");


    }
    public String action(){
        if(movesCounter % 2==0){
            return "✔";
        }
        else {
            return "✖";
        }
    }

    public void function(){
        Node fo =main_menu.getMain().getScene().getFocusOwner();


        if(fo instanceof TextField){
            if(((TextField)fo).getText().isEmpty()){
            ((TextField)fo).setText(action());
            movesCounter++;
            if(movesCounter  == 1)
            {
                main_menu.getMain().getPlayer().addGame(this);
            }
            check();}
        }
    }
    public void check(){
        if(!boxes[0][0].getText().isEmpty() && boxes[0][0].getText().equals(boxes[1][1].getText()) && boxes[1][1].getText().equals(boxes[2][2].getText())){
            won();
            Reset();
        }
        if(!boxes[0][1].getText().isEmpty() && boxes[0][1].getText().equals(boxes[1][1].getText()) && boxes[1][1].getText().equals(boxes[2][1].getText())){
            won();
            Reset();
        }
        if(!boxes[0][2].getText().isEmpty() && boxes[0][2].getText().equals(boxes[1][1].getText()) && boxes[1][1].getText().equals(boxes[2][0].getText())){
            won();
            Reset();
        }
        if(!boxes[0][1].getText().isEmpty() && boxes[0][0].getText().equals(boxes[0][1].getText()) && boxes[0][1].getText().equals(boxes[0][2].getText())){
            won();
            Reset();
        }
        if(!boxes[1][1].getText().isEmpty() && boxes[1][0].getText().equals(boxes[1][1].getText()) && boxes[1][1].getText().equals(boxes[1][2].getText())){
            won();
            Reset();
        }
        if(!boxes[2][1].getText().isEmpty() && boxes[2][0].getText().equals(boxes[2][1].getText()) && boxes[2][1].getText().equals(boxes[2][2].getText())){
            won();
            Reset();
        }
        if(!boxes[0][0].getText().isEmpty() && boxes[0][0].getText().equals(boxes[1][0].getText()) && boxes[1][0].getText().equals(boxes[2][0].getText())){
            won();
            Reset();
        }
        if(!boxes[0][1].getText().isEmpty() && boxes[0][1].getText().equals(boxes[1][1].getText()) && boxes[1][1].getText().equals(boxes[2][1].getText())){
            won();
            Reset();
        }
        if(!boxes[0][2].getText().isEmpty() && boxes[0][2].getText().equals(boxes[1][2].getText()) && boxes[1][2].getText().equals(boxes[2][2].getText())){
            won();
            Reset();

        }
        emptyBoxChecker--;
        if (emptyBoxChecker ==0){
            new CustomDialog(main_menu.getMain().getStage(), "Game Over!","NO ONE THE GAME","OK","Images/explode.png");

            Reset();
        }

    }
    public void won(){
        if(movesCounter % 2==0 && movesCounter >1){
            System.out.println(movesCounter);
            playerOnePoints++;
            main_menu.getMain().getPlayer().addWin(this);
            new CustomDialog(main_menu.getMain().getStage(), "Game Over!",main_menu.getMain().getPlayer().getName()+" WON THE GAME","OK","Images/ship.png");
        }
        else {
            playerTwoPoints++;
            new CustomDialog(main_menu.getMain().getStage(), "Game Over!","PLAYER 2 WON THE GAME","OK","Images/ship.png");
            main_menu.getMain().getPlayer().addLose(this);
        }
        playerOneLabel.setText(main_menu.getMain().getPlayer().getName()+"(✖): "+ playerOnePoints);
        playerTwoLabel.setText("Player 2 (✔): "+ playerTwoPoints);

    }


    public void Reset(){

        movesCounter =0;
        emptyBoxChecker =10;
       if(boxes[0][0] != null)
       {
           for(int a=0;a<3;a++) {
               for (int b = 0; b < 3; b++) {
                   boxes[a][b].clear();
               }
           }
           return;
       }

        movesCounter =0;
        emptyBoxChecker =10;
        //initializing textFields
        for(int a=0;a<3;a++){
            for(int b=0;b<3;b++){
                boxes[a][b]=new TextField();
                boxes[a][b].setPrefSize(200,200);
                boxes[a][b].setFont(Font.font(15));
                boxes[a][b].setEditable(false);
                boxes[a][b].setOnMouseClicked(event -> function());
                boxes[a][b].setStyle("-fx-opacity: 0.3;");
                boxes[a][b].setAlignment(Pos.CENTER);
            }}

        //Inner
        for(int i=0; i<3; i++)
        {
            for(int k=0; k<3; k++)
            {
                boxes[i][k].setLayoutX((Screen.getScreenCenter() - 53) + 53*k);
                boxes[i][k].setLayoutY(((Screen.getSceneHeight() / 2) - 53 ) + 53*i);
                boxes[i][k].setMinSize(50,50);
                boxes[i][k].setMaxSize(50,50);
            }

        }
        inner.setPadding(new Insets(0,0,0,0));

        inner.getChildren().addAll(boxes[0][0],boxes[0][1],boxes[0][2],boxes[1][0],boxes[1][1],boxes[1][2],boxes[2][0],boxes[2][1],boxes[2][2]);

    }
}

package G2048;

import Games.G2048Game;
import MISC.ButtonsStyling;
import MISC.CustomDialog;
import MISC.StageDisplay;
import Menu.GameSelect;
import Menu.Screen;
import Menu.main_menu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Random;


public class g2048 implements G2048Game {
   // KeyEvent event =new KeyEvent;
    private Label scoreLabel =new Label("Score: ");
    private TextField scoreText=new TextField();
    private int added=0;
    private int[] emptyR=new int[16];
    private int[] emptyC=new int[16];
    private TextField[][] text = new TextField[4][4];
    private Button right=new Button("▶");
    private Button reset=new Button("⟲");
    private Button left=new Button("◀");
    private Button up=new Button("▲");
    private Button down =new Button("▼");
    private Button backButton;

    public g2048() {
        //scoreText.editableProperty().set(false);
        scoreText.setDisable(true);
        scoreText.setMaxSize(100,50);
        scoreText.setMinSize(100,50);
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setFont(new Font(20));

        new ButtonsStyling(left,25);
        new ButtonsStyling(right,25);
        new ButtonsStyling(up,25);
        new ButtonsStyling(down,25);

        new ButtonsStyling(reset,20);

        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 4; b++) {
                text[a][b] = new TextField();
                text[a][b].editableProperty().set(false);
                text[a][b].setMinSize(100, 100);
                text[a][b].setMaxSize(100, 100);
                text[a][b].setAlignment(Pos.CENTER);

            }
        }
        //Inner layout
        GridPane inner = new GridPane();
        inner.setPadding(new Insets(5,5,5,5));
        GridPane.setConstraints(text[0][0], 0, 0);
        GridPane.setConstraints(text[0][1], 1, 0);
        GridPane.setConstraints(text[0][2], 2, 0);
        GridPane.setConstraints(text[0][3], 3, 0);
        GridPane.setConstraints(text[1][0], 0, 1);
        GridPane.setConstraints(text[1][1], 1, 1);
        GridPane.setConstraints(text[1][2], 2, 1);
        GridPane.setConstraints(text[1][3], 3, 1);
        GridPane.setConstraints(text[2][0], 0, 2);
        GridPane.setConstraints(text[2][1], 1, 2);
        GridPane.setConstraints(text[2][2], 2, 2);
        GridPane.setConstraints(text[2][3], 3, 2);
        GridPane.setConstraints(text[3][0], 0, 3);
        GridPane.setConstraints(text[3][1], 1, 3);
        GridPane.setConstraints(text[3][2], 2, 3);
        GridPane.setConstraints(text[3][3], 3, 3);
        inner.getChildren().addAll(text[0][0], text[0][1], text[0][2], text[0][3], text[1][0], text[1][1], text[1][2], text[1][3],
                text[2][0], text[2][1], text[2][2], text[2][3], text[3][0], text[3][1], text[3][2], text[3][3]);
        inner.setVgap(5);
        inner.setHgap(5);

        backButton= new Button("GAME SELECT");
        new ButtonsStyling(backButton,25);
        backButton.setLayoutX(25);
        backButton.setLayoutY(600);
        backButton.setOnAction(e -> {
            new GameSelect();
        });

        //Set Bottom layout
        HBox lower=new HBox();
        lower.getChildren().addAll(backButton, new Label(), new Label(), left, right, up, down);
        lower.setAlignment(Pos.CENTER);


        //Upper layout
        HBox upper=new HBox();
        upper.getChildren().addAll(scoreLabel,scoreText,reset);
        upper.setAlignment(Pos.CENTER);
        //reset.setMinSize(40,40);
        //reset.setMaxSize(40,40);


        //Outer layout
        BorderPane outer = new BorderPane();
        outer.setCenter(inner);
        outer.setTop(upper);
        outer.setBottom(lower);



        //Function Calling
        Starting();
        outer.requestFocus();
        right.setOnAction(event -> {
            sortHorizontally(3,0,-1);
            addHorizontally(3,0,-1);
            add();
            colorCheck();
            outer.requestFocus();
        });
        left.setOnAction(event -> {
            sortHorizontally(0,3,1);
            addHorizontally(0,3,1);
            add();
            colorCheck();
            outer.requestFocus();
        });
        up.setOnAction(event -> {
            sortUpAndDown(0,3,1);
            addVertically(0,3,1);
            add();
            colorCheck();
            outer.requestFocus();
            //sortVertically(0,3,1);
        });
        down.setOnAction(event -> {
            sortUpAndDown(3,0,-1);
            addVertically(3,0,-1);
            add();
            colorCheck();
            outer.requestFocus();
        });
        reset.setOnAction(event -> resetText());
        //Just chechking





        BackgroundImage bg = new BackgroundImage(new Image("Images/space.jpg", Screen.getWidth(), Screen.getFullScreenHeight(),true,false), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        outer.setBackground(new Background(bg));
        //lower.getChildren().add(backButton);

        new StageDisplay(outer,"2048");


        outer.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case RIGHT:sortHorizontally(3,0,-1);
                    addHorizontally(3,0,-1);
                    add();
                    colorCheck();
                    break;
                case UP:sortUpAndDown(0,3,1);
                    addVertically(0,3,1);
                    add();
                    colorCheck();
                    break;
                case LEFT:sortHorizontally(0,3,1);
                    addHorizontally(0,3,1);
                    add();
                    colorCheck();
                    break;
                case DOWN:sortUpAndDown(3,0,-1);
                    addVertically(3,0,-1);
                    add();
                    colorCheck();
            }
            outer.requestFocus();
        });
        outer.requestFocus();


    }


    //Returns either 2 or 4 in String format
    String getRandom() {
        Random random = new Random();
        int a;
        a = random.nextInt();
        if (a % 2 == 0) {
            return "2";
        } else return "4";
        //return Integer.toString(a);
    }


    //Used to intialize first two random cells for the starting of game
    void Starting() {
        scoreText.setText("0");
        Random random = new Random();
        int a = random.nextInt(4);
        int b = random.nextInt(4);
        text[a][b].setText(getRandom());

        a = random.nextInt(4);
        b = random.nextInt(4);
        text[a][b].setText(getRandom());
        colorCheck();
        main_menu.getMain().getPlayer().addGame(this);



    }


    //When right key is pressed
    void right() {
        //add();
        //sort();
    }


    //Sorts to right
    void sortHorizontally(int ColumnStart,int Columnend,int addingFactor){
        for(int row=0;row<=3;row++) {
            for (int y = 0; y <= 3; y++) {
                for (int column = ColumnStart; column !=Columnend; column=column+addingFactor){
                    if (text[row][column].getText().isEmpty()) {
                        text[row][column].setText(text[row][column + addingFactor].getText());
                        text[row][column + addingFactor].clear();}
                    }
            }
        }
    }

 /*   void sortVertically(int RowStart,int RowEnd,int addingFactor){
        for(int column=0;column<=3;column++) {
            for (int y = 0; y <= 3; y++) {
                for (int row = RowStart; row !=RowEnd; row=column+addingFactor){
                    if (text[row][column].getText().isEmpty()) {
                        text[row][column].setText(text[row + addingFactor][column].getText());
                        text[row + addingFactor][column].clear();}
                }
            }
        }
    }*/
 void sortUpAndDown(int rowStart,int rowEnd,int addingFactor){
     for (int column=0;column<=3;column++){
         for (int y=0;y<=3;y++){
             for (int row=rowStart;row!=rowEnd;row=row+addingFactor){
                 if(text[row][column].getText().isEmpty()){
                     text[row][column].setText(text[row+addingFactor][column].getText());
                     text[row+addingFactor][column].clear();
                 }
             }
         }
     }
 }

    void add(){
        //addHorizontally();
        check();
        if(added==0){
            text[emptyR[0]][emptyC[0]].setText(getRandom());
        }
        scoreText.setText(Integer.toString(Integer.parseInt(scoreText.getText())+(added*10)));
        added=0;
    }


    void check(){
        int x=0;
        for(int column=0;column<=3;column++){
            for(int row=0;row<=3;row++){
                if(text[row][column].getText().isEmpty()){
                    emptyR[x]=row;
                    emptyC[x]=column;
                    x++;
                }
            }
        }
        if (x==0){
            main_menu.getMain().getPlayer().addLose(this);
            new CustomDialog(main_menu.getMain().getStage(), "Game Over!","YOU LOST THE GAME","OK","Images/crash.png");
            main_menu.getMain().getPlayer().addPoints(this,Integer.parseInt(scoreText.getText()));
            resetText();
        }

        for(int column=0;column<=3;column++){
            for(int row=0;row<=3;row++){
                if(text[row][column].getText().equals("2048")){
                    main_menu.getMain().getPlayer().addWin(this);
                    main_menu.getMain().getPlayer().addPoints(this,Integer.parseInt(scoreText.getText()));
                    new CustomDialog(main_menu.getMain().getStage(), "Game Over!","YOU WON THE GAME","OK","Images/ship.png");

                    resetText();
                }
            }
        }
        colorCheck();
    }

    void resetText(){
            for(int column=0;column<=3;column++){
                for(int row=0;row<=3;row++){
                    text[row][column].clear();
                }
            }
            Starting();
    }
    void addHorizontally(int columnStart,int columnEnd,int factor){
        for(int row=0;row<=3;row++) {
            for (int column = columnStart; column !=columnEnd; column=column+factor) {
                if (text[row][column].getText().equals(text[row][column +factor].getText())) {
                    if (!text[row][column +factor].getText().isEmpty()) {
                        int a = Integer.parseInt(text[row][column].getText());
                        int b = Integer.parseInt(text[row][column +factor].getText());
                        b = b + a;
                        text[row][column].setText(Integer.toString(b));
                        text[row][column +factor].clear();
                        added++;
                        sortHorizontally(columnStart,columnEnd,factor);
                    }
                }
            }
        }
    }

    void addVertically(int rowStart,int rowEnd,int factor){
        for(int column=0;column<=3;column++) {
            for (int row = rowStart; row !=rowEnd; row=row+factor) {
                if (text[row][column].getText().equals(text[row +factor][column].getText())) {
                    if (!text[row +factor][column].getText().isEmpty()) {
                        int a = Integer.parseInt(text[row][column].getText());
                        int b = Integer.parseInt(text[row +factor][column].getText());
                        b = b + a;
                        text[row][column].setText(Integer.toString(b));
                        text[row +factor][column].clear();
                        added++;
                        sortUpAndDown(rowStart,rowEnd,factor);
                    }
                }
            }
        }


    }
    void colorCheck(){
        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 4; b++) {
                if(!text[a][b].getText().isEmpty()){
                int x=Integer.parseInt(text[a][b].getText());
                switch (x){
                    case 2: text[a][b].setStyle("-fx-background-color: #FFA500;" +
                            "-fx-text-fill: #FFFFFF;" +
                            "-fx-font-size: 25pt");break;
                    case 4: text[a][b].setStyle("-fx-background-color: #006600;" +
                            "-fx-text-fill: #FFFFFF;" +
                            "-fx-font-size: 25pt");break;
                    case 8: text[a][b].setStyle("-fx-background-color: #000099;" +
                            "-fx-text-fill: #FFFFFF;" +
                            "-fx-font-size: 25pt");break;
                    case 16: text[a][b].setStyle("-fx-background-color: #FF007F;" +
                            "-fx-text-fill: #FFFFFF;" +
                            "-fx-font-size: 25pt");break;
                    case 32: text[a][b].setStyle("-fx-background-color: #FFD700;" +
                            "-fx-text-fill: #FFFFFF;" +
                            "-fx-font-size: 25pt");break;
                    case 64: text[a][b].setStyle("-fx-background-color:  #FF3333;" +
                            "-fx-text-fill: #FFFFFF;" +
                            "-fx-font-size: 25pt");break;
                    case 128: text[a][b].setStyle("-fx-background-color: #00FFFF;" +
                            "-fx-text-fill: #FFFFFF;" +
                            "-fx-font-size: 25pt");break;
                    case 256: text[a][b].setStyle("-fx-background-color: #808080;" +
                            "-fx-text-fill: #FFFFFF;" +
                            "-fx-font-size: 22pt");break;
                    case 512: text[a][b].setStyle("-fx-background-color: #000000;" +
                            "-fx-text-fill: #FFFFFF;" +
                            "-fx-font-size: 22pt");break;
                    case 1024: text[a][b].setStyle("-fx-background-color: #F1F;" +
                            "-fx-text-fill: #FFFFFF;" +
                            "-fx-font-size: 20pt");break;
                    case 2048: text[a][b].setStyle("-fx-background-color: #FFF123;" +
                            "-fx-text-fill: #FFFFFF;" +
                            "-fx-font-size: 20pt");break;
                    default:text[a][b].setStyle("-fx-background-color: #383838;" +
                            "-fx-text-fill: #FFFFFF;" +
                            "-fx-font-size: 25pt");break;
                }


                }else{text[a][b].setStyle("-fx-background-color: #383838;" +
                        "-fx-text-fill: #FFFFFF;" +
                        "-fx-font-size: 25pt"); }
            }
            }
        }

    }



    //adds numbers to right
   /*  ||
   int adding() {
        int a;
        int b;
        int c = 2;

        for (int x = c; x >= 0; x--) {
            if (!text[0][x].getText().isEmpty()) {
                a = Integer.parseInt(text[0][3].getText());
                b = Integer.parseInt(text[0][x].getText());
                text[0][3].setText(Integer.toString(a + b));
                return x;
            } else {
                if (!text[0][3 - 2].getText().isEmpty()) {
                    a = Integer.parseInt(text[0][3].getText());
                    b = Integer.parseInt(text[0][3 - 2].getText());
                    text[0][3].setText(Integer.toString(a + b));
                } else {
                    if (!text[0][3 - 3].getText().isEmpty()) {
                        a = Integer.parseInt(text[0][3].getText());
                        b = Integer.parseInt(text[0][3 - 3].getText());
                        text[0][3].setText(Integer.toString(a + b));
                    }
                }
            }
        }
    return 0;}

    void shifting(int x){
        for(int y=x+1;y>=1;y--){
            text[0][y].setText(text[0][y-1].getText());
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}*/
//Step 10:Desighn grid       DONE
//Step 20:Generate random number function(2 and 4 only)     DONE
//Step 30:Initialize some boxes(only 2 random boxes)        DONE
//Step 40:Make functions{Function explaination:Sort for three times than add same numbers sort again}
//Step 45:winning check and reset function*/ DONE
//Step 47:Add Score method
//Step 50:add Buttons
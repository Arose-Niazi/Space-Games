package BrickBreaker;

import Games.BrickBreakerGame;
import MISC.ButtonsStyling;
import MISC.StageDisplay;
import Menu.GameSelect;
import Menu.Screen;
import javafx.animation.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import Menu.main_menu;

public class BrickBreaker implements BrickBreakerGame {

    private Label score;
    private AnchorPane layout;
    private Canvas canvas;
    private int settingA;
    private int settingB;
    private Brick brick;
    private ArrayList<Brick> b;
    private Pad rect;
    private Ball b1;
    private Button backButton;


    public BrickBreaker()

    {
        settingA=0;
        settingB=540;
        score = new Label();
        score.setTextFill(Color.WHITE);
        canvas=new Canvas(Screen.getSceneWidth(),Screen.getFullScreenHeight());
        brick=new Brick(100,200);
        b=new ArrayList<>();
        rect=new Pad();
        b1=new Ball();

        Image border=new Image("Images/red.jpg");//,540,10,false,false);

        score.setLayoutY(540+5);
        score.setLayoutX(540+10);

        SettingBricks();

        ImageView lowerImageView=new ImageView();
        lowerImageView.setImage(border);
        lowerImageView.setFitHeight(10);
        lowerImageView.setFitWidth(settingB);
        lowerImageView.setX(settingA);
        lowerImageView.setY(settingA);


        border=new Image("Images/fire.jpg");
        ImageView upperImageView=new ImageView();
        upperImageView.setImage(border);
        upperImageView.setFitHeight(15);
        upperImageView.setFitWidth(settingB+8);
        upperImageView.setX(settingA);
        upperImageView.setY(settingB);


        border=new Image("Images/red.jpg");
        ImageView rightImageView=new ImageView();
        rightImageView.setImage(border);
        rightImageView.setFitHeight(settingB);
        rightImageView.setFitWidth(10);
        rightImageView.setX(settingB);
        rightImageView.setY(settingA);

        ImageView leftImageView=new ImageView();
        leftImageView.setImage(border);
        leftImageView.setFitHeight(settingB);
        leftImageView.setFitWidth(2);
        leftImageView.setX(settingA);
        leftImageView.setY(settingA);

        backButton= new Button("GAME SELECT");
        new ButtonsStyling(backButton,25);
        backButton.setLayoutX(25);
        backButton.setLayoutY(600);
        backButton.setOnAction(e -> {
            new GameSelect();
        });



        layout = new AnchorPane();

        layout.getChildren().addAll(canvas,score,upperImageView,lowerImageView,leftImageView,rightImageView);//
        layout.getChildren().add(backButton);

        BackgroundImage bg = new BackgroundImage(new Image("Images/space.jpg", Screen.getWidth(), Screen.getFullScreenHeight(),true,false), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        layout.setBackground(new Background(bg));

        rect.setPosition(500,500);
        b1.setPosition(100,100);

        GraphicsContext gc=canvas.getGraphicsContext2D();

        Image img=new Image("Images/space.jpg");
        ImageView imageView=new ImageView(img);
        imageView.setStyle("-fx-opacity: 0.5");

        new AnimationTimer(){
            public void handle(long now){


                gc.drawImage(img, settingA,settingA,Screen.getSceneWidth(),Screen.getFullScreenHeight());
                rect.render(gc);
                for(Brick c:b){
                    c.render(gc);
                }
                if(b.size()==0){
                    gameOver(gc,"You won");
                    main_menu.getMain().getPlayer().addWin(this);
                    stop();
                }
                b1.move(rect,b);
                b1.render(gc);
                score.setText("Score : " + b1.getScore());
                if(b1.getPositionY()>settingB){
                gameOver(gc,"Game Over");
                    main_menu.getMain().getPlayer().addLose(this);
                    main_menu.getMain().getPlayer().addPoints(this,b1.getScore());
                    stop();

                }

                canvas.setOnKeyPressed(event -> {
                    switch (event.getCode()){
                        case LEFT:rect.move(-3);break;
                        case RIGHT:rect.move(3);break;
                        case DOWN:
                            if(rect.getDownFactor()<5){
                                rect.setPositionY(rect.getPositionY()+5);
                                rect.setDownFactor(rect.getDownFactor()+1);}
                                break;
                    }
                });
                canvas.setOnKeyReleased(event -> {
                    if(event.getCode()==KeyCode.DOWN){
                        for(int a=0;a<rect.getDownFactor();a++){
                            rect.setPositionY(rect.getPositionY()-(5));
                            if(b1.intersects(rect)){
                                b1.setY(b1.getY()*(-rect.getDownFactor()));
                                b1.setPositionY(settingB-100);
                            }
                        }
                        rect.setDownFactor(0);

                    }
                });
                canvas.requestFocus();

            }

        }.start();

        new StageDisplay(layout,"Brick Breaker");
    }

    public void gameOver(GraphicsContext gc,String s){
        main_menu.getMain().getPlayer().addGame(this);
        Font font=new Font(45);
        Label restart=new Label("⟲");
        restart.setTextFill(Color.PAPAYAWHIP);
        restart.setFont(font);
        restart.setLayoutX(250);
        restart.setLayoutY(280);
        layout.getChildren().add(restart);
        restart.setOnMouseClicked(event -> new BrickBreaker());



        font=new Font(25);
        gc.setFont(font);
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.PAPAYAWHIP);
        gc.setLineWidth(2);
        gc.fillText(s,210,200);
        gc.strokeText(s,210,200);
        gc.fillText(score.getText(),210,250);
        gc.strokeText(score.getText(),210,250);

    }

    public void SettingBricks(){

        b.add(new Brick(settingA+3,10));
        b.add(new Brick(settingA+58,10));
        b.add(new Brick(settingA+113,10));
        b.add(new Brick(settingA+168,10));
        b.add(new Brick(settingA+223,10));
        b.add(new Brick(settingA+278,10));
        b.add(new Brick(settingA+333,10));
        b.add(new Brick(settingA+388,10));
        b.add(new Brick(settingA+443,10));
        b.add(new Brick(settingA+498,10));
        b.add(new Brick(settingA+3,35));
        b.add(new Brick(settingA+58,35));
        b.add(new Brick(settingA+113,35));
        b.add(new Brick(settingA+168,35));
        b.add(new Brick(settingA+223,35));
        b.add(new Brick(settingA+278,35));
        b.add(new Brick(settingA+333,35));
        b.add(new Brick(settingA+388,35));
        b.add(new Brick(settingA+443,35));
        b.add(new Brick(settingA+498,35));
        b1.setScore(0);

    }
}




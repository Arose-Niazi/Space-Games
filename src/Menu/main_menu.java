package Menu;

import MISC.SignInNotifiction;
import MISC.StageDisplay;
import Player.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import Player.SignIn;
import javafx.stage.StageStyle;

public class main_menu extends Application{
    private Label newGame,signIn,stats,playerN,exitL;

    private Group group;
    private Canvas canvas;
    private Stage stage;
    private Player player = null;

    private static main_menu main;
    private static Scene scene;


    @Override
    public void start(Stage primaryStage){
        main = this;
        new MySQLConnection();

        stage =primaryStage;
        playerN=new Label();
        newGame=new Label("New Game");
        signIn=new Label("Sign In");
        stats=new Label("Statics");
        exitL=new Label("Exit");

        newGame.setTextFill(Color.WHITESMOKE);
        signIn.setTextFill(Color.WHITESMOKE);
        stats.setTextFill(Color.WHITESMOKE);
        playerN.setTextFill(Color.WHITESMOKE);
        exitL.setTextFill(Color.WHITESMOKE);
        newGame.setAlignment(Pos.CENTER);
        signIn.setAlignment(Pos.CENTER);
        stats.setAlignment(Pos.CENTER);
        exitL.setAlignment(Pos.CENTER);

        newGame.setLayoutY(200);
        newGame.setLayoutX(Screen.getSceneWidth() - 400);
        signIn.setLayoutY(300);
        signIn.setLayoutX(Screen.getSceneWidth() - 400);
        stats.setLayoutY(400);
        stats.setLayoutX(Screen.getSceneWidth() - 400);
        exitL.setLayoutY(500);
        exitL.setLayoutX(Screen.getSceneWidth() - 400);
        playerN.setLayoutY(30);
        playerN.setLayoutX(70);


        newGame.setOnMouseEntered(event -> newGame.setTextFill(Color.STEELBLUE));
        newGame.setOnMouseExited(event -> newGame.setTextFill(Color.WHITESMOKE));

        stats.setOnMouseEntered(event -> stats.setTextFill(Color.STEELBLUE));
        stats.setOnMouseExited(event -> stats.setTextFill(Color.WHITESMOKE));

        signIn.setOnMouseEntered(event -> signIn.setTextFill(Color.STEELBLUE));
        signIn.setOnMouseExited(event -> signIn.setTextFill(Color.WHITESMOKE));

        exitL.setOnMouseEntered(event -> exitL.setTextFill(Color.STEELBLUE));
        exitL.setOnMouseExited(event -> exitL.setTextFill(Color.WHITESMOKE));

        newGame.setOnMouseClicked(e -> NewGame());
        signIn.setOnMouseClicked(e -> SignIn());
        stats.setOnMouseClicked(e -> Stats());
        exitL.setOnMouseClicked(e -> closeGame());

        if(player != null) signIn.setDisable(true);

        playerN.setTextFill(Color.WHITE);
        playerN.setStyle("-fx-font-size: 15");
        Font font=new Font("Times New Roman",30);
        newGame.setFont(font);
        newGame.setStyle("-fx-font-size: 30");
        signIn.setStyle("-fx-font-size: 30 ");
        stats.setStyle("-fx-font-size: 30");
        exitL.setStyle("-fx-font-size: 30");


        group=new Group();
        canvas=new Canvas(Screen.getSceneWidth(),Screen.getFullScreenHeight());
        group.getChildren().addAll(canvas,newGame,signIn,stats,exitL,playerN);
        scene =new Scene(group);

        GraphicsContext gc=canvas.getGraphicsContext2D();
        final long startTime=System.nanoTime();

        //Image player=new Image("Images/SilverBall1.JPG",50,50,false,false);
        Image img=new Image("Images/space.jpg",Screen.getWidth(),Screen.getFullScreenHeight(),true,false);
        Image Jupiter=new Image("Images/Jupiter.jpg",50,50,false,false);
        Image mars=new Image("Images/Venus.jpg",50,50,false,false);
        Image venus=new Image("Images/realVenus.jpg",50,50,false,false);
        Image earth=new Image("Images/ship.png",50,50,false,false);
        Image sun=new Image("Images/question.png",110,110,false,false);

        gc.drawImage(img,0,0);
        /*new AnimationTimer(){

            @Override
            public void handle(long now) {
                gc.drawImage(img,0,0);

                double t=(now-startTime)/1000000000.0;
                double x=200+128*Math.cos(t);
                double y=200+128*Math.sin(t);
                gc.drawImage(earth,x,y);

                gc.drawImage(sun,196,196);
            }
        }.start();*/

        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        new StageDisplay(group,"Main Menu");
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void NewGame()
    {
       if(player == null)
       {
           new SignInNotifiction(stage);
           return;
       }
       new GameSelect();
    }

    public void SignIn()
    {
        new SignIn(stage);
    }

    private void Stats()
    {
        new Stats();
    }

    private void closeGame()
    {
        stage.close();
        MySQLConnection.getCurrentObj().endConnection();
        System.exit(0);
    }

    public void showMainMenu()
    {
        if(player != null) signIn.setDisable(true);
        new StageDisplay(group,"Main Menu");
    }

    public static main_menu getMain() {
        return main;
    }

    public void setUpPlayer(Player p)
    {
        player=p;
        playerN.setText(p.getName());

        Circle pc = new Circle(40,40, 30);
        pc.setFill(player.getPlanet());
        group.getChildren().add(pc);
    }

    public Stage getStage() {
        return stage;
    }

    public Player getPlayer() {
        return player;
    }

    public Scene getScene() {return scene;}
}

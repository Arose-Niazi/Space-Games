package Blackjack;

import Games.BlackJackGame;
import MISC.ButtonsStyling;
import MISC.StageDisplay;
import Menu.GameSelect;
import Menu.Screen;
import Menu.main_menu;
import Player.Player;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import static Blackjack.Cards.CARDSPOSSIBLE;
import static Blackjack.Cards.DECK;
import static Blackjack.CardsData.BACK;
import java.util.Random;

public class BlackJack implements BlackJackGame {


    private static Group root;
    private static AnchorPane layout;

    private Working currentGame;

    private ImageView dealerCards[] = new ImageView[CARDSPOSSIBLE.value];
    private ImageView playerCards[] = new ImageView[CARDSPOSSIBLE.value];

    private Button startButton,standButton,backButton;

    private Player player;

    private static boolean gameExists;

    static {
        gameExists=false;
    }


    public BlackJack()
    {
        if(!gameExists)
            start();
        else
        {
            new StageDisplay(layout,"Blackjack");
        }

    }
    private void start() {
        player = main_menu.getMain().getPlayer();
        gameExists =true;
        try {
            root = new Group();
            Random rand = new Random();

            Text dealerName = new Text();
            dealerName.setFont(Font.font("Ariel", FontWeight.BOLD,15));
            dealerName.setX(20);
            dealerName.setY(40);
            dealerName.setText("Dealer:");
            dealerName.setFill(Color.WHITE);
            dealerName.setTextAlignment(TextAlignment.RIGHT);

            Text playersName = new Text();
            playersName.setFont(Font.font("Ariel", FontWeight.BOLD,15));
            playersName.setX(20);
            playersName.setY(290);
            playersName.setText(player.getName());
            playersName.setFill(Color.WHITE);
            playersName.setTextAlignment(TextAlignment.RIGHT);
            for (int i = 0; i < CARDSPOSSIBLE.value; i++) {
                dealerCards[i] = new ImageView(new Image("Images/Cards/" + BACK.fileName + ".png", 140.0 , 180.0,false , true));
                dealerCards[i].setX(20 + (60 * i));
                dealerCards[i].setY(50);
            }
            CardsData c[] = CardsData.values();
            for (int i = 0; i < CARDSPOSSIBLE.value; i++) {

                playerCards[i] = new ImageView(new Image("Images/Cards/" + c[rand.nextInt(DECK.value) + 1].fileName + ".png", 140.0 , 180.0,false , true));
                playerCards[i].setX(20 + (60 * i));
                playerCards[i].setY(300);
            }

            startButton= new Button("START GAME!");
            new ButtonsStyling(startButton,25);
            startButton.setLayoutX(20);
            startButton.setLayoutY(500);
            startButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if(Working.isGameStarted())
                            {
                                startButton.setDisable(true);
                                currentGame.playerCalledHit();
                            }
                            else
                            {
                                startButton.setText("HIT");
                                startGame();
                            }
                        }
                    });
            standButton= new Button("STAND!");
            standButton.setLayoutX(100);
            standButton.setLayoutY(500);
            standButton.setVisible(false);
            new ButtonsStyling(standButton,25);

            backButton= new Button("GAME SELECT");
            new ButtonsStyling(backButton,25);
            backButton.setLayoutX(25);
            backButton.setLayoutY(600);
            backButton.setOnAction(e -> {
                new GameSelect();
            });

            standButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    standButton.setDisable(true);
                    currentGame.playerStands();
                }
            });
            root.getChildren().addAll(dealerCards);
            root.getChildren().addAll(playerCards);
            root.getChildren().add(dealerName);
            root.getChildren().add(playersName);
            root.getChildren().add(startButton);
            root.getChildren().add(standButton);
            root.getChildren().add(backButton);

            layout = new AnchorPane();
            BackgroundImage b = new BackgroundImage(new Image("Images/space.jpg", Screen.getWidth(), Screen.getFullScreenHeight(),true,false), BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);

            layout.setBackground(new Background(b));
            layout.getChildren().add(root);
            new StageDisplay(layout,"Blackjack");
        }
        catch (Exception  e)
        {
            e.printStackTrace();
        }
    }

    private void startGame()
    {
        currentGame = new Working(this,root);
        currentGame.startGame();
        standButton.setVisible(true);
        main_menu.getMain().getPlayer().addGame(this);
    }

    public void resetDealersCards()
    {
        for (int i = 0; i < CARDSPOSSIBLE.value; i++) {
           dealerCards[i].setVisible(false);
        }
    }

    public void resetPlayersCards()
    {
        for (int i = 0; i < CARDSPOSSIBLE.value; i++) {
            playerCards[i].setVisible(false);
        }
    }

    public void addDealersCard(CardsData c)
    {
        for (int i = 0; i < CARDSPOSSIBLE.value; i++) {
                if (!dealerCards[i].isVisible()) {
                    //dealerCards[i].setImage(new Image(new FileInputStream("PNG\\" + c.fileName + ".png"), 140.0, 180.0, false, true));
                    dealerCards[i].setImage(new Image("Images/Cards/" + BACK.fileName + ".png", 140.0, 180.0, false, true));
                    dealerCards[i].setVisible(true);
                    playRotateAnimation(dealerCards[i]);
                    break;
                }
        }
    }

    public void addPlayersCard(CardsData c){
        for (int i = 0; i < CARDSPOSSIBLE.value; i++) {

                if (!playerCards[i].isVisible()) {
                    playerCards[i].setImage(new Image("Images/Cards/" + c.fileName + ".png", 140.0, 180.0, false, true));
                    playerCards[i].setVisible(true);
                    playRotateAnimation(playerCards[i]);
                    break;
                }

        }
    }

    public void flipDealersCards(CardsData c[])
    {
        for (int i = 0; i < CARDSPOSSIBLE.value; i++) {

                if (dealerCards[i].isVisible()) {
                    dealerCards[i].setImage(new Image("Images/Cards/" + c[i].fileName + ".png", 140.0, 180.0, false, true));
                    playRotateAnimation(dealerCards[i]);
                }
        }
    }

    public void endGame()
    {
        startButton.setText("START GAME!");
        startButton.setDisable(false);
        standButton.setVisible(false);
        standButton.setDisable(false);
    }

    public void playersTurn()
    {
        main_menu.getMain().getPlayer().addTurn(this);
        startButton.setDisable(false);
    }

    private void playRotateAnimation(Node n)
    {
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        rotateTransition.setNode(n);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(1);
        rotateTransition.setAutoReverse(false);
        rotateTransition.play();
    }

}

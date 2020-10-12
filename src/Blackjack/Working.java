package Blackjack;

import Games.BlackJackGame;
import MISC.CustomDialog;
import Menu.main_menu;
import javafx.scene.Group;

import java.util.Random;

import static Blackjack.Cards.*;
import static Blackjack.GameStates.*;

public class Working implements BlackJackGame {

    private static boolean gameStarted;
    private boolean dealerStand;
    private boolean playerStand;

    private static CardsData cards[];
    private static boolean cardUsed[];
    private static CardsData dealersCard[];
    private static CardsData playersCard[];

    private BlackJack m;
    Group root;

    Random rand = new Random();

    {
        cards = CardsData.values();
        cardUsed = new boolean[DECK.value];
        dealersCard = new CardsData[CARDSPOSSIBLE.value];
        playersCard = new CardsData[CARDSPOSSIBLE.value];
    }

    public Working(BlackJack m,Group root) {
        this.m = m;
        this.root = root;
    }

    public void startGame()
    {
        m.resetDealersCards();
        m.resetPlayersCards();
        gameStarted=true;
        playerStand=dealerStand = false;
        for(int i=0; i<DECK.value; i++) cardUsed[i]=false;
        for(int i=0; i<2; i++)
        {
            dealersCard[i] = getRandomCard();
            m.addDealersCard(dealersCard[i]);
            playersCard[i] = getRandomCard();
            m.addPlayersCard(playersCard[i]);
        }
    }

    private CardsData getRandomCard()
    {
        CardsData c = cards[rand.nextInt(DECK.value) + 1];
        if(cardUsed[c.ordinal() - 1]) return getRandomCard();
        cardUsed[c.ordinal() - 1] = true;
        return c;
    }

    private int Count(CardsData c[])
    {
        int sum=0,ace=0;
        for(int i=0; i<CARDSPOSSIBLE.value; i++)
        {
            try{
                if(c[i].value == 1) ace++;
                if(c[i].value > 10) sum+=10;
                else sum+=c[i].value;
            }
            catch (NullPointerException e)
            {
                break;
            }
        }
        while(ace > 0)
        {
            if(sum+10 > BLACKJACK.value) break;
            sum+=10;
            ace--;
        }
        return sum;
    }

    private void CheckForBlackJack()
    {
        int dsum;
        dsum=Count(dealersCard);
        int psum=Count(playersCard);;
        if(dsum == BLACKJACK.value)
        {
            if(psum == BLACKJACK.value)
            {
                endGame(DRAW);
                return;
            }
            endGame(DEALER_BJ);
            return;
        }
        if(psum == BLACKJACK.value)
        {
            endGame(BJ);
            return;
        }
        if(dsum > BLACKJACK.value)
        {
            if(psum > BLACKJACK.value)
            {
                endGame(BJ);
                return;
            }
            endGame(WIN);
            return;
        }
        if(psum > BLACKJACK.value)
        {
            endGame(BUSTED);
            return;
        }
        if(playerStand == true && dealerStand == true)
        {
            if(psum > dsum)
            {
                endGame(WIN);
                return;
            }
            if(psum == dsum)
            {
                endGame(DRAW);
                return;
            }
            if(psum < dsum)
            {
                endGame(LOST);
                return;
            }
        }
    }

    public void endGame(GameStates condition)
    {
        switch (condition)
        {

            case DRAW:
            {
                new CustomDialog(main_menu.getMain().getStage(), "Game Over!","OOPS! The match ended with a draw\n\tYour Score: " + Count(playersCard) + "\n\tDealers Score: "+ Count(dealersCard),"CLOSE","Images/crash.png");
                break;
            }
            case WIN:
            {
                main_menu.getMain().getPlayer().addWin(this);
                new CustomDialog(main_menu.getMain().getStage(), "Game Over!","Congrats You have won the game!\n\tYour Score: " + Count(playersCard) + "\n\tDealers Score: "+ Count(dealersCard),"CLOSE","Images/ship.png");
                break;
            }
            case LOST:
            {
                main_menu.getMain().getPlayer().addLose(this);
                new CustomDialog(main_menu.getMain().getStage(), "Game Over!","OHO! Looks like you were not up to the challenge\n\tYour Score: " + Count(playersCard) + "\n\tDealers Score: "+ Count(dealersCard),"CLOSE","Images/explode.png");
                break;
            }
            case BJ:
            {
                main_menu.getMain().getPlayer().addBlackJack(this);
                new CustomDialog(main_menu.getMain().getStage(), "Game Over!","WOOHA! You are amazing!\n\tYour Score: BLACKJACK"  + "\n\tDealers Score: "+ Count(dealersCard),"CLOSE","Images/joker.png");
                break;
            }
            case BUSTED:
            {
                main_menu.getMain().getPlayer().addLose(this);
                new CustomDialog(main_menu.getMain().getStage(), "Game Over!","Eh? You can't cross BLACKJACK!\n\tYour Score: " + Count(playersCard) + "\n\tDealers Score: "+ Count(dealersCard),"CLOSE","Images/explode.png");
                break;
            }
            case DEALER_BJ:
            {
                new CustomDialog(main_menu.getMain().getStage(), "Game Over!","YOU JUST GOT BLACKJACK-ED!!\n\tYour Score: " + Count(playersCard) + "\n\tDealers Score: BLACKJACK!","CLOSE","Images/explode.png");
                break;
            }
        }
        main_menu.getMain().getPlayer().addPoints(this,Count(playersCard));
        m.flipDealersCards(dealersCard);
        m.endGame();
        gameStarted=false;
    }

    public void playerCalledHit()
    {
        int i=0;
        for(; i<CARDSPOSSIBLE.value; i++)
        {
            try{
                int v = playersCard[i].value;
            }
            catch (NullPointerException e)
            {
                playersCard[i] = getRandomCard();
                break;
            }
        }
        if(i == CARDSPOSSIBLE.value) playerStand = true;
        else m.addPlayersCard(playersCard[i]);
        dealersTurn();
        main_menu.getMain().getPlayer().addHits(this);
    }

    private void dealersTurn()
    {

        if(Count(dealersCard) < 13)
        {
            int i=0;
            for(; i<CARDSPOSSIBLE.value; i++)
            {
                try{
                    int v = dealersCard[i].value;
                }
                catch (NullPointerException e)
                {
                    dealersCard[i] = getRandomCard();
                    break;
                }
            }

            if(i == CARDSPOSSIBLE.value) dealerStand=true;
            else m.addDealersCard(dealersCard[i]);
            if(playerStand)
            {
                dealersTurn();
                return;
            }
        }
        else dealerStand=true;
        CheckForBlackJack();
        if(!playerStand && gameStarted) m.playersTurn();
    }

    public static boolean isGameStarted() {
        return gameStarted;
    }
    public static void setGameStarted(boolean b) {gameStarted=b;}

    public void playerStands()
    {
        playerStand=true;
        dealersTurn();
    }

}

package XinRow;

import Games.XinRowGame;
import MISC.CustomDialog;
import MISC.XinRowInputDialog;
import Menu.*;
import Player.*;
import javafx.scene.shape.Circle;

public class Working implements XinRowGame {
    private XinRow currentGameMain;
    private static  Player players[];
    private int pCurrentTurn = -1;
    private int totalPlayers;
    private int toCheckInRow;
    static boolean playersSelected;
    static
    {
        playersSelected=false;
    }

    public Working(XinRow currentGameMain, int totalPlayers, int toCheckInRow) {

        this.currentGameMain = currentGameMain;
        this.totalPlayers=totalPlayers;
        this.toCheckInRow = toCheckInRow;
        if(playersSelected)
            if(totalPlayers != players.length)
            {
                playersSelected = false;
                currentGameMain.resetPlayers();
            }


        if(!playersSelected)
        {
            players = new Player[totalPlayers];
            players[0]= main_menu.getMain().getPlayer();
            for(int i=1; i<totalPlayers; i++)
            {
                XinRowInputDialog dialog = new XinRowInputDialog(currentGameMain.getStage(),"Name","Enter player " + (i+1) + "'s name","NEXT");
                players[i] = new Player(dialog.getText());
            }
            playersSelected=true;
            for(Player p: players) currentGameMain.addPlayersData(p);
        }
        for(Player p: players) p.addGame(this);
        nextTurn();
    }

    public void nextTurn()
    {
        pCurrentTurn++;
        if(pCurrentTurn >= totalPlayers) pCurrentTurn=0;
        currentGameMain.currentPlayer=players[pCurrentTurn];
        for(Circle c: BackGroundGrid.clickableCricles) c.setFill(players[pCurrentTurn].getPlanet());
        players[pCurrentTurn].addTurn(this);
    }

    public boolean checkForWinner(int x, int y)
    {
        int counter=0;
        try{
            for(int i=0; i<toCheckInRow; i++)
            {
                if(BackGroundGrid.locationUsed[x + i][y] && BackGroundGrid.locationOwnerBy[x + i][y].equals(players[pCurrentTurn]))
                {
                    counter++;
                }
                else break;
                if(counter == toCheckInRow)
                {
                    endGame();
                    return true;
                }
            }
        }

        catch (ArrayIndexOutOfBoundsException e)
        {

        }
        try{
            for(int i=1; i<toCheckInRow; i++)
            {
                if(BackGroundGrid.locationUsed[x - i][y] && BackGroundGrid.locationOwnerBy[x - i][y].equals(players[pCurrentTurn]))
                {
                    counter++;
                }
                else break;
                if(counter == toCheckInRow)
                {
                    endGame();
                    return true;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {

        }
        counter=0;
        try{
            for(int i=0; i<toCheckInRow; i++)
            {
                if(BackGroundGrid.locationUsed[x][y + i] && BackGroundGrid.locationOwnerBy[x][y + i].equals(players[pCurrentTurn]))
                {
                    counter++;
                }
                else break;
                if(counter == toCheckInRow)
                {
                    endGame();
                    return true;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {

        }
        try{
            for(int i=1; i<toCheckInRow; i++)
            {
                if(BackGroundGrid.locationUsed[x][y - i] && BackGroundGrid.locationOwnerBy[x][y - i].equals(players[pCurrentTurn]))
                {
                    counter++;
                }
                else break;
                if(counter == toCheckInRow)
                {
                    endGame();
                    return true;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {

        }
        counter=0;
        try{
            for(int i=0; i<toCheckInRow; i++)
            {
                if(BackGroundGrid.locationUsed[x + i][y + i] && BackGroundGrid.locationOwnerBy[x + i][y + i].equals(players[pCurrentTurn]))
                {
                    counter++;
                }
                else break;
                if(counter == toCheckInRow)
                {
                    endGame();
                    return true;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {

        }
        try{
            for(int i=1; i<toCheckInRow; i++)
            {
                if(BackGroundGrid.locationUsed[x - i][y - i] && BackGroundGrid.locationOwnerBy[x - i][y - i].equals(players[pCurrentTurn]))
                {
                    counter++;
                }
                else break;
                if(counter == toCheckInRow)
                {
                    endGame();
                    return true;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {

        }
        counter=0;
        try{
            for(int i=0; i<toCheckInRow; i++)
            {
                if(BackGroundGrid.locationUsed[x - i][y + i] && BackGroundGrid.locationOwnerBy[x - i][y + i].equals(players[pCurrentTurn]))
                {
                    counter++;
                }
                else break;
                if(counter == toCheckInRow)
                {
                    endGame();
                    return true;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {

        }
        try{
            for(int i=1; i<toCheckInRow; i++)
            {
                if(BackGroundGrid.locationUsed[x + i][y - i] && BackGroundGrid.locationOwnerBy[x + i][y - i].equals(players[pCurrentTurn]))
                {
                    counter++;
                }
                else break;
                if(counter == toCheckInRow)
                {
                    endGame();
                    return true;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {

        }

        return false;
    }

    public void gameEndsDraw()
    {
        new CustomDialog(currentGameMain.getStage(), "Game Over!","DRAW!\n\tNo one has won the game.","CLOSE","Images/explode.png");
        currentGameMain.endGame();
    }

    private void endGame()
    {
        new CustomDialog(currentGameMain.getStage(), "Game Over!","Congrats "+ players[pCurrentTurn].getName() +   " for wining the game.","CLOSE",players[pCurrentTurn].getPlanet());
        currentGameMain.endGame();
        for(Player p: players)
        {
            if(p.equals(players[pCurrentTurn]))
            {
                p.addWin(this);
                continue;
            }
            p.addLose(this);
        }

    }
}

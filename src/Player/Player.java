package Player;

import Games.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.LinkedList;
import java.util.Random;

public class Player {

    private int ID;
    private String name;
    private ImagePattern planet;
    private int xInRowWins,xInRowPlayed,xInRowLost,xInRowTurns;
    private int tickCrossWins,tickCrossLost,tickCrossPlayed;
    private int blackJackPlayed,blackJackWins,blackJackLost,blackJackTurns,blackJackHits,blackJackPoints,blackJackBJ;
    private int brickBreakerWins,brickBreakerLost,brickBreakerPoints,brickBreakerPlayed;
    private int g2048Wins,g2048Lost,g2048Points,g2048HighestPoints,g2048Played;

    {
        xInRowWins=xInRowLost=xInRowPlayed=xInRowTurns=0;
        tickCrossWins=tickCrossLost=tickCrossPlayed=0;
        blackJackPlayed=blackJackWins=blackJackLost=blackJackTurns=blackJackHits=blackJackPoints=blackJackBJ=0;
        brickBreakerWins=brickBreakerLost=brickBreakerPoints=brickBreakerPlayed=0;
        g2048Wins=g2048Lost=g2048Points=g2048HighestPoints=g2048Played=0;
    }

    private static LinkedList<ImagePattern> planets = new LinkedList<>();

    static {
        for(int i=0; i<10; i++) planets.add(new ImagePattern(new Image("Images/p"+i+".png",50,50,true,false)));
    }

    public Player(String name) {
        this(-1,name);
    }

    public Player(int ID, String name) {
        this.ID = ID;
        this.name = name;

        Random r = new Random();
        int p = r.nextInt(planets.size());
        planet = planets.get(p);
        planets.remove(p);
        if(ID != -1) new StatsSaveLoad(this).loadStats();
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public ImagePattern getPlanet() {
        return planet;
    }

    public int getWins(Object game)
    {
        if(game instanceof TickCrossGame)
        {
            return tickCrossWins;
        }
        return 0;
    }

    public void addGame(Object game)
    {
        if(game instanceof XinRowGame)
        {
            xInRowPlayed++;
        }
        if(game instanceof TickCrossGame)
        {
            tickCrossPlayed++;
        }
        if(game instanceof BlackJackGame)
        {
            blackJackPlayed++;
        }
        if(game instanceof BrickBreakerGame)
        {
            brickBreakerPlayed++;
        }
        if(game instanceof G2048Game)
        {
            g2048Played++;
        }
    }

    public void addWin(Object game)
    {
        if(game instanceof XinRowGame)
        {
            xInRowWins++;
        }
        if(game instanceof TickCrossGame)
        {
            tickCrossWins++;
            System.out.println("Works");
        }
        if(game instanceof BlackJackGame)
        {
            blackJackWins++;
        }
        if(game instanceof BrickBreakerGame)
        {
            brickBreakerWins++;
        }
        if(game instanceof G2048Game)
        {
            g2048Wins++;
        }
    }

    public void addLose(Object game)
    {
        if(game instanceof XinRowGame)
        {
            xInRowLost++;
        }
        if(game instanceof TickCrossGame)
        {
            tickCrossLost++;
        }
        if(game instanceof BlackJackGame)
        {
            blackJackLost++;
        }
        if(game instanceof BrickBreakerGame)
        {
            brickBreakerLost++;
        }
        if(game instanceof G2048Game)
        {
            g2048Lost++;
        }
    }

    public void addTurn(Object game)
    {
        if(game instanceof XinRowGame)
        {
            xInRowTurns++;
        }
        if(game instanceof BlackJackGame)
        {
            blackJackTurns++;
        }
    }

    public void addHits(Object game)
    {
        if(game instanceof BlackJackGame)
        {
            blackJackHits++;
        }
    }

    public void addPoints(Object game,int points)
    {
        if(game instanceof BlackJackGame)
        {
            blackJackPoints+=points;
        }
        if(game instanceof BrickBreakerGame)
        {
            brickBreakerPoints+=points;
        }
        if(game instanceof G2048Game)
        {
            g2048Points+=points;
            if(g2048HighestPoints < points) g2048HighestPoints=points;
        }
    }

    public void addBlackJack(Object game)
    {
        if(game instanceof BlackJackGame)
        {
            blackJackBJ++;
        }
    }

    public int getxInRowWins() {
        return xInRowWins;
    }

    public void setxInRowWins(int xInRowWins) {
        this.xInRowWins = xInRowWins;
    }

    public int getxInRowPlayed() {
        return xInRowPlayed;
    }

    public void setxInRowPlayed(int xInRowPlayed) {
        this.xInRowPlayed = xInRowPlayed;
    }

    public int getxInRowLost() {
        return xInRowLost;
    }

    public void setxInRowLost(int xInRowLost) {
        this.xInRowLost = xInRowLost;
    }

    public int getxInRowTurns() {
        return xInRowTurns;
    }

    public void setxInRowTurns(int xInRowTurns) {
        this.xInRowTurns = xInRowTurns;
    }

    public int getTickCrossWins() {
        return tickCrossWins;
    }

    public void setTickCrossWins(int tickCrossWins) {
        this.tickCrossWins = tickCrossWins;
    }

    public int getTickCrossLost() {
        return tickCrossLost;
    }

    public void setTickCrossLost(int tickCrossLost) {
        this.tickCrossLost = tickCrossLost;
    }

    public int getTickCrossPlayed() {
        return tickCrossPlayed;
    }

    public void setTickCrossPlayed(int tickCrossPlayed) {
        this.tickCrossPlayed = tickCrossPlayed;
    }

    public int getBlackJackPlayed() {
        return blackJackPlayed;
    }

    public void setBlackJackPlayed(int blackJackPlayed) {
        this.blackJackPlayed = blackJackPlayed;
    }

    public int getBlackJackWins() {
        return blackJackWins;
    }

    public void setBlackJackWins(int blackJackWins) {
        this.blackJackWins = blackJackWins;
    }

    public int getBlackJackLost() {
        return blackJackLost;
    }

    public void setBlackJackLost(int blackJackLost) {
        this.blackJackLost = blackJackLost;
    }

    public int getBlackJackTurns() {
        return blackJackTurns;
    }

    public void setBlackJackTurns(int blackJackTurns) {
        this.blackJackTurns = blackJackTurns;
    }

    public int getBlackJackHits() {
        return blackJackHits;
    }

    public void setBlackJackHits(int blackJackHits) {
        this.blackJackHits = blackJackHits;
    }

    public int getBlackJackPoints() {
        return blackJackPoints;
    }

    public void setBlackJackPoints(int blackJackPoints) {
        this.blackJackPoints = blackJackPoints;
    }

    public int getBlackJackBJ() {
        return blackJackBJ;
    }

    public void setBlackJackBJ(int blackJackBJ) {
        this.blackJackBJ = blackJackBJ;
    }

    public int getBrickBreakerWins() {
        return brickBreakerWins;
    }

    public void setBrickBreakerWins(int brickBreakerWins) {
        this.brickBreakerWins = brickBreakerWins;
    }

    public int getBrickBreakerLost() {
        return brickBreakerLost;
    }

    public void setBrickBreakerLost(int brickBreakerLost) {
        this.brickBreakerLost = brickBreakerLost;
    }

    public int getBrickBreakerPoints() {
        return brickBreakerPoints;
    }

    public void setBrickBreakerPoints(int brickBreakerPoints) {
        this.brickBreakerPoints = brickBreakerPoints;
    }

    public int getBrickBreakerPlayed() {
        return brickBreakerPlayed;
    }

    public void setBrickBreakerPlayed(int brickBreakerPlayed) {
        this.brickBreakerPlayed = brickBreakerPlayed;
    }

    public int getG2048Wins() {
        return g2048Wins;
    }

    public void setG2048Wins(int g2048Wins) {
        this.g2048Wins = g2048Wins;
    }

    public int getG2048Lost() {
        return g2048Lost;
    }

    public void setG2048Lost(int g2048Lost) {
        this.g2048Lost = g2048Lost;
    }

    public int getG2048Points() {
        return g2048Points;
    }

    public void setG2048Points(int g2048Points) {
        this.g2048Points = g2048Points;
    }

    public int getG2048HighestPoints() {
        return g2048HighestPoints;
    }

    public void setG2048HighestPoints(int g2048HighestPoints) {
        this.g2048HighestPoints = g2048HighestPoints;
    }

    public int getG2048Played() {
        return g2048Played;
    }

    public void setG2048Played(int g2048Played) {
        this.g2048Played = g2048Played;
    }
}

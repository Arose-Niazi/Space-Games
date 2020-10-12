package XinRow;

import Games.XinRowGame;
import Menu.Screen;
import Player.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.LinkedList;


public class BackGroundGrid extends Rectangle implements XinRowGame {

    static ArrayList<Circle> spaces = new ArrayList<>();
    static LinkedList<Circle> clickableCricles = new LinkedList<>();
    static LinkedList<Circle> allPlacedCircles;
    static boolean locationUsed[][] = new boolean[10][10];
    static Player locationOwnerBy[][] = new Player[10][10];


    static int lastYLoc;

    static {
        resetGrid();
    }

    static void resetGrid()
    {
        locationUsed = new boolean[10][10];
        allPlacedCircles = new LinkedList<>();
    }
    public BackGroundGrid() {
        super(Screen.getWidth() - 750, Screen.getHeight() - 500, 500, 500);
        setFill(Color.TRANSPARENT);
        setStroke(Color.BLACK);
        setStrokeWidth(2.0);

        ImagePattern defualtDropBallsBack = new ImagePattern(new Image("Images/sun.jpeg",50,50,true,false));
        for(int i=0; i<10; i++)
        {
            Circle c = new Circle((Screen.getWidth() - 725) + i*50 , Screen.getHeight() - 525,20);
            c.setFill(defualtDropBallsBack);
            c.setOnMouseClicked(e -> new CircleMouseHandler(e));
            clickableCricles.add(c);
            c.setStroke(Color.BLACK);
        }
        ImagePattern spaceGridB = new ImagePattern(new Image("Images/space-s.jpg",50,50,true,false));
        for(int i=0; i<10; i++)
        {
            for(int k=0; k<10; k++)
            {
                Circle c = new Circle((Screen.getWidth() - 725) + i*50,(Screen.getHeight() - 475) + k*50,20);
                c.setFill(Color.TRANSPARENT);
                c.setStroke(Color.BLACK);
                c.setStrokeWidth(2.0);

                spaces.add(c);
                lastYLoc = 100 + k*50;

            }
        }
    }

    static void addAnotherSelectableBall(int ballLoc) {
        for(int i=ballLoc+1; i<10; i++)
        {
            new MoveCircle(BackGroundGrid.clickableCricles.get(i), -50, -1, Duration.millis(500));
        }
        allPlacedCircles.add(clickableCricles.get(ballLoc));
        clickableCricles.remove(ballLoc);
        Circle c = new Circle((Screen.getWidth() - 725) + 9*50 , Screen.getHeight() - 525,20);
        c.setFill(XinRow.currentPlayer.getPlanet());
        clickableCricles.add(c);
        XinRow.root.getChildren().removeAll(clickableCricles);
        XinRow.root.getChildren().addAll(clickableCricles);
        c.setOnMouseClicked(e -> new CircleMouseHandler(e));
        c.setStroke(Color.BLACK);
    }
}

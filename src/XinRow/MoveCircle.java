package XinRow;

import Games.XinRowGame;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class MoveCircle implements XinRowGame {
    public MoveCircle(Node n, int x, int y, Duration d){
        this(n,x,y,d,false);
    }

    public MoveCircle(Node n, int x, int y, Duration d,boolean wait){
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(d);
        translateTransition.setNode(n);
        if(x != -1)
            translateTransition.setByX(x);
        if(y != -1)
            translateTransition.setByY(y);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        if(wait == true)translateTransition.setOnFinished(e -> {
            XinRow.canSelectNext = true;
        });
        translateTransition.play();
    }
}

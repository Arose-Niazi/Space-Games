package BrickBreaker;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;


public class Ball extends Moveable {
    private int score=0;
    private double x=1;
    private double y=1;
    Image ball=new Image("Images/ball.jpg",15,15,false,true);


    public Ball(){
        super();
        super.setImage(ball);
    }
    public void move(Pad p,ArrayList<Brick> b){
        if(super.getPositionX()<=5 || super.getPositionX()>530){
            x=-x;
        }
        if(super.getPositionY()<=5 || super.getPositionY()>535){
            y=-1;
            y=-y;
        }
        if(super.intersects(p)){
            setX((this.getPositionX()-(p.getPositionX()+15))/50);
            y=-y;
        }
        try {

            for(Brick c:b){
                if(this.intersects(c)){
                    if(y>0){
                        y=1;
                    }
                    else {
                        y=-1;
                    }
                    score+=10;

                    if(y>0){
                        y=-y;
                    }
                    c.setPower(c.getPower()+y);
                    if(this.getBoundary().getMaxX()>=c.getBoundary().getMinX()+2 && this.getBoundary().getMinX()<=c.getBoundary().getMaxX()-2){
                        this.setY(-y);
                    }
                    else {
                        x=-x;
                    }
                    if(c.getPower()<1){
                    b.remove(c);}
                    else {
                        if(c.getPower()<2){
                            c.setImage(new Image("Images/finalBrokenBrick.jpg",50,20,false,true));
                        }
                   }
                }
            }
        }catch (ConcurrentModificationException e){
           // System.out.println("exception");
        }





        super.setVelocity(x,y);
        super.update(3);

    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

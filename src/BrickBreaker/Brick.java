package BrickBreaker;

import javafx.scene.image.Image;

public class Brick extends Moveable {
    double power=2.5;

    Image brick=new Image("Images/finalBrick.jpg",50,20,false,true);
    public Brick(int x,int y){
        this.setImage(brick);
        this.setPosition(x,y);

    }



    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

}

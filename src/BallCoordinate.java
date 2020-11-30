import java.awt.*;
public class BallCoordinate {
    int ballX;
    int ballY;
    int speedX;
    int speedY;
    Rectangle hitBox;

    //constructor
    public BallCoordinate(){
        ballX = (int)(Math.random()*1004)+1;
        int chance = (int)(Math.random()*2)+1;
        if (chance ==1) {
            ballY = 0;
        }else if (chance ==2){
            ballY = 670;
        }
        speedY =(int)(Math.random()*20)-10;
        speedX =(int)(Math.random()*20)+10;
        hitBox = new Rectangle(ballX,ballY,10,10);
    }
    public void setBallX(int ballX){
        this.ballX = ballX;
    }
    public void setBallY(int ballY){
        this.ballY = ballY;
    }
    public int getSpeedX(){
        return speedX;
    }
    public int getSpeedY(){
        return speedY;
    }
    public void setSpeedX(int direction){
        this.speedX = this.speedX*direction;
    }
    public void setSpeedY(int direction){
        this.speedY = this.speedY*direction;;
    }

    public int getBallX(){
        return ballX;
    }
    public int getBallY(){

        return ballY;
    }
    public Rectangle getHitbox (){
        return hitBox;
    }
}

import javax.swing.*;

public class QuadTreeCollision extends JFrame  {
   // private static ArrayList<BouncingBall> listOfBalls = new ArrayList<BouncingBall>();
   //private QuadTree ballStorage;
    private static BouncingBall ball;
    static int numBall=5;

    public static void main(String[] args) {
        ball = new BouncingBall(numBall);
    }
}
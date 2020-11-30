import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BouncingBall extends JFrame {
    private static Rectangle hitBox = new Rectangle();
    private static int numBalls;
    public static DrawGraphics canvas;
    private ArrayList <BallCoordinate> listOfBalls = new ArrayList<>();
    public static ThisKeyListener KeyListener = new ThisKeyListener();

    //constructor
    public BouncingBall(int numBall){
        numBalls = numBall;
        for (int i = 0; i<numBalls;i++) {
            this.listOfBalls.add(new BallCoordinate());
        }
        JFrame displayWindow = new JFrame("QuadTree Collision");
        displayWindow.setSize(1024, 726);
        displayWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas = new DrawGraphics();
        canvas.setNumBalls(numBall);
        canvas.setListOfBalls(listOfBalls);
        canvas.addKeyListener(KeyListener);
        displayWindow.add(canvas);
        displayWindow.setVisible(true);
        canvas.AnimationLoop();
        canvas.DrawBall();
    }//BouncingBalls

    public boolean isColliding() {
        if (listOfBalls.get(0).getHitbox().intersects(listOfBalls.get(1).getHitbox())){
            return true;
        }
        return false;
    }//isColliding

    public class DrawGraphics extends JPanel{
        private int numBalls;
        private ArrayList<BallCoordinate> listOfBalls = new ArrayList<>();
        Graphics gx;

        public DrawGraphics(){
            setFocusable(true);
            requestFocusInWindow();
            }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.gx = this.getGraphics();
            setBackground(Color.WHITE);
            if (isColliding()){
                g.setColor(Color.black);
            }else {
                g.setColor(Color.CYAN);
            }
                for (int i = 0; i<numBalls;i++) {
                    this.listOfBalls.add(new BallCoordinate());
                }
            for (int j = 0; j<numBalls;j++) {
                g.fillOval(this.listOfBalls.get(j).getBallX(), this.listOfBalls.get(j).getBallY(), 10, 10);
                hitBox.setLocation(this.listOfBalls.get(j).getBallX(),this.listOfBalls.get(j).getBallY());

            }
        }//paintComponents
        public void DrawBall(){
            this.gx.setColor(Color.CYAN);
            this.gx.fillOval(100,200,10,10);
        }

        public void setNumBalls(int ballAmount){
            this.numBalls = ballAmount;
        }
        public int getNumBalls(){
            return  this.numBalls;
        }
        public void setListOfBalls(ArrayList<BallCoordinate> listOfBalls){
            this.listOfBalls = listOfBalls;
        }

        public void AnimationLoop() {
            int x;
            int y;
            while (true) {
                for (int i = 0; i < numBalls; i++) {
                    if (this.listOfBalls.get(i).getBallX() > 1004 || this.listOfBalls.get(i).getBallX() < 0) {
                        listOfBalls.get(i).setSpeedX(-1);
                    }
                    if (this.listOfBalls.get(i).getBallY() > 680 || this.listOfBalls.get(i).getBallY() < 0) {
                        listOfBalls.get(i).setSpeedY(-1);
                    }
                    x = this.listOfBalls.get(i).getBallX() + listOfBalls.get(i).getSpeedX();
                    y = this.listOfBalls.get(i).getBallY()+ listOfBalls.get(i).getSpeedY();
                    this.listOfBalls.get(i).setBallY(y);
                    this.listOfBalls.get(i).setBallX(x);
                    try {
                        Thread.sleep(5);
                        canvas.repaint();
                        Toolkit.getDefaultToolkit().sync();
                    } catch (InterruptedException e) {}
                }// end of for loop
            }//end of while loop
        }//Animation Loop
    }//DrawGraphics

    static class ThisKeyListener implements KeyListener {
        public void keyPressed(KeyEvent e) {
            //increase number of balls on screen by right key
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                numBalls += 1;
                canvas.setNumBalls(numBalls);
                canvas.paintComponent(canvas.gx);
                //decrease number of balls on screen by left key
            }else if(e.getKeyCode() == KeyEvent.VK_LEFT && numBalls > 0){
                numBalls -=1;
                canvas.setNumBalls(numBalls);
                canvas.paintComponent(canvas.gx);
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

            }
        }//keyPressed
        public void keyTyped(KeyEvent e){
        }//keyTyped
        public void keyReleased(KeyEvent e) {
        }//keyReleased
    }//ThisKeyListener

}

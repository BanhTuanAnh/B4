import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
public class GameWindow extends JFrame {// de hien thi ra man hinh window
    private  GameCanvas gameCanvas;
    private long lastTime = 0;
    public GameWindow(){
        this.setSize(1024,600);// dai - rong set kich thuoc man hinh
        this.gameCanvas= new GameCanvas();
        this.add(this.gameCanvas); // dua game vanvas vao window
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode()==KeyEvent.VK_LEFT){
                    System.out.println("Space");
                }
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                Random random=new Random();
                if(keyEvent.getKeyCode()==KeyEvent.VK_LEFT){
                    gameCanvas.positionXPlayer-=10;
                    if(gameCanvas.positionXPlayer<0){
                        gameCanvas.positionYPlayer=random.nextInt(600);
                        gameCanvas.positionXPlayer= 1024;
                    }
                }
                if(keyEvent.getKeyCode()==KeyEvent.VK_RIGHT){
                    gameCanvas.positionXPlayer+=10;
                    if(gameCanvas.positionXPlayer>1020){
                        gameCanvas.positionYPlayer=random.nextInt(600);
                        gameCanvas.positionXPlayer= 0;
                    }
                }
                if(keyEvent.getKeyCode()==KeyEvent.VK_DOWN){
                    gameCanvas.positionYPlayer+=10;
                    if(gameCanvas.positionYPlayer>600){
                        gameCanvas.positionYPlayer= 0;
                        gameCanvas.positionXPlayer= random.nextInt(1024);
                    }
                }
                if(keyEvent.getKeyCode()==KeyEvent.VK_UP){
                    gameCanvas.positionYPlayer-=10;
                    if(gameCanvas.positionYPlayer<0){
                        gameCanvas.positionXPlayer=random.nextInt(1024);
                        gameCanvas.positionYPlayer= 600;
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
        this.setVisible(true);// cho phep cua so window hien len. dat o cuoi
    }
    public void gameLoop(){
        while(true){
            long currentTime = System.nanoTime();
            if(currentTime-this.lastTime>=17000000){
                this.gameCanvas.enemyDiChuyen();
                this.gameCanvas.starDiChuyen();
                this.gameCanvas.renderAll();
                this.lastTime=currentTime;
            }
        }
    }

}

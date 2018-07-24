import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameCanvas extends JPanel {// van la class tach roi chua dc dua vafo window
    //cach dat ten bien
    //snake case user_name
    //camel case: userName
    private Graphics graphics;
    private BufferedImage backBuffered;
    private BufferedImage starImage;
    private BufferedImage playerImage;
    private BufferedImage[] enemyImage=new BufferedImage[10];
    public int positionXStar=300;
    public int positionYStar=10;// de toa do trong man hinh
    public int starTime=2000;
    public int stepXStar=10;
    public int[] positionXEnemy={0,100,200,300,400,500,600,800,900,1000};
    public int[] positionYEnemy={0,60,120,180,240,300,360,420,480,540,10};
    public int[] stepXEnemy={2,-8,13,-2,4,-10,-9,-5,-10,-13};
    public int[] stepYEnemy={-2,8,13,-2,-4,10,9,-5,-10,-13};;
    public int positionXPlayer=0;
    public int positionYPlayer=0;
    public GameCanvas(){
        this.setSize(1024,600);
        this.backBuffered=new BufferedImage(1024,600,BufferedImage.TYPE_INT_ARGB);
        this.graphics=this.backBuffered.getGraphics();// co ve sex ve len backbuffer
        // load anh
        try {
            this.starImage= ImageIO.read(new File("resources-rocket-master/resources/images/star.png"));
            // chuot phai vao pic roi chon copy relative path de lay link anh
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            this.playerImage=ImageIO.read(new File("resources-rocket-master/resources/images/star.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            try{
                this.enemyImage[i]=ImageIO.read(new File("resources-rocket-master/resources/images/circle.png"));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //draw
        this.setVisible(true);
    }
    @Override
    protected void paintComponent(Graphics g) {// noi ve tat ca moi thu
               g.drawImage(this.backBuffered,0,0,null);
        //        // ve
    }
    public void enemyDiChuyen(){
        for (int i = 0; i < 10; i++) {
            this.positionXEnemy[i]+=this.stepXEnemy[i];
            this.positionYEnemy[i]+=this.stepYEnemy[i];
            if(this.positionYEnemy[i]<0||this.positionYEnemy[i]>590){
                this.stepYEnemy[i]=-this.stepYEnemy[i];
            }
            if(this.positionXEnemy[i]<0||this.positionXEnemy[i]>1001){
                this.stepXEnemy[i]=-this.stepXEnemy[i];
            }
        }
    }
    public void starDiChuyen(){
        this.starTime-=10;
        positionXStar+=stepXStar;
        positionXStar=(positionXStar+1000)%1000;
        if(starTime==0){
            starTime=2000;
            stepXStar=-stepXStar;

        }
    }
    public void renderAll(){
        //1 ve nen
        this.graphics.setColor(Color.BLACK); // set mau
        graphics.fillRect(0,0,1024,600);  // set hinh dang x, y , width,height
        graphics.drawImage(this.starImage,this.positionXStar,this.positionYStar,100,100,null);
        graphics.drawImage(this.playerImage,this.positionXPlayer,this.positionYPlayer,50,50,null);
        for (int i = 0; i < 10; i++) {
            graphics.drawImage(this.enemyImage[i],this.positionXEnemy[i],this.positionYEnemy[i],(i)*10,(i)*10,null);
        }
        this.repaint();// goi luon ham ve laij man hinh trong renderAll
    }

}

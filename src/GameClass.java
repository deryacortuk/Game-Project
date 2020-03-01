
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;



class Bullet{
    private int x;
    private int y;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
            
}

public class GameClass  extends JPanel implements KeyListener, ActionListener{
     Timer timer = new Timer(5,this);
     private int elapsed = 0;
     private int used_bulled =0;
     private BufferedImage image;
     private ArrayList<Bullet> bullet = new ArrayList<Bullet>();
     
     private int bulletDirY = 1;
     private int topX = 0;
     private int topDirX = 2;
     private int spacecraftX = 0;
     private int dirSpaceX = 20;
     
     public boolean control(){
         for(Bullet bullets : bullet){
             if(new Rectangle(bullets.getX(),bullets.getY(),10,20).intersects(new Rectangle(topX,0,20,20))){
                 return true;
             }
         }
         return false;
     }

    public GameClass() {
        
         try {
             image =ImageIO.read(new FileImageInputStream(new File("spacecraft.png")));
         } catch (IOException ex) {
             Logger.getLogger(GameClass.class.getName()).log(Level.SEVERE, null, ex);
         }
         setBackground(Color.DARK_GRAY);
         timer.start();
         
         
        
    }

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        elapsed +=5;
        
        g.setColor(Color.red);
        g.fillOval(topX, 0, 17, 19);
        g.drawImage(image,spacecraftX,490,image.getWidth()/10, image.getHeight()/10,this);
        
        for(Bullet bullets : bullet){
            if(bullets.getY()<0){
                bullet.remove(bullets);
            }
        }
        g.setColor(Color.BLUE);
        for(Bullet bullets: bullet){
            g.fillRect(bullets.getX(),bullets.getY(),10, 20);
        }
        if(control()){
            timer.stop();
            String message =" You won\n" +
                    "used bullets:" + used_bulled + "\nelapsed time:" + elapsed/1000.0 ;
            
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
        
    }
     
     
     
     
             
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        
        
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        
        if(c == KeyEvent.VK_LEFT){
            
            if(spacecraftX <=0){
                
                spacecraftX = 0;
                
            }
            else {
                spacecraftX -= dirSpaceX;
            }
                    
        }
        
        else if (c==KeyEvent.VK_RIGHT){
            
            if(spacecraftX >=750){
                spacecraftX = 750;
            }else {
                spacecraftX += dirSpaceX;
            }
            
        
    }
        else if(c ==KeyEvent.VK_CONTROL){
            bullet.add(new Bullet(spacecraftX+15,475));
            elapsed++;
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(Bullet bullets : bullet){
            bullets.setY(bullets.getY() -bulletDirY);
            
        }
        
        topX +=topDirX;
        if(topX>=750){
            topDirX = -topDirX;
            
        }
        if(topX<=0){
            topDirX = -topDirX;
        }
        repaint();
    }
    
}

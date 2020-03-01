
import java.awt.HeadlessException;
import javax.swing.JFrame;


public class Space  extends JFrame{

    public Space(String title) throws HeadlessException {
        super(title);
    }
    
    public static void main(String[] args) {
        Space space = new Space("Space Game");
        space.setResizable(false);
        space.setFocusable(false);
        space.setSize(800, 600);
        space.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GameClass gameClass = new GameClass();
        gameClass.requestFocus();
        gameClass.addKeyListener(gameClass);
        gameClass.setFocusable(true);
        gameClass.setFocusTraversalKeysEnabled(false);
        
        space.add(gameClass);
        space.setVisible(true);
        
        
    }
}

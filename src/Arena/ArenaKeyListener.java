package Arena;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ArenaKeyListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        int keyCode = e.getKeyCode();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_G){
            Arena.change = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
    }
}

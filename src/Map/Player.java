package Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
public class Player extends Entity {
    public static PlayerKeyListener playerKeyListener;
    public static PlayerMouseListener playerMouseListener;

    public Player(int speed, int x, int y) {
        super(speed, x, y);
        playerKeyListener = new PlayerKeyListener();
        playerKeyListener.setPlayer(this);
        playerMouseListener = new PlayerMouseListener();
        playerMouseListener.setPlayer(this);

        try {
            sprites.add(ImageIO.read(getClass().getResource("/Player/0.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {
        super.update();
        if (playerMouseListener.used) {
            playerMouseListener.used = false;
            powers.get(selected).use();
        }
        double tmp = speed;
        if (down && (left || right)) {
            y += tmp * 0.707;
        } else if (down) {
            y += tmp;
        }

        if (up && (left || right)) {
            y -= tmp * 0.707;
        } else if (up) {
            y -= tmp;
        }

        if (left && (up || down)) {
            x -= tmp * 0.707;
        } else if (left) {
            x -= tmp;
        }

        if (right && (up || down)) {

            x += tmp * 0.707;
        } else if (right) {
            x += tmp;
        }
    }

    @Override
    public void draw(Graphics2D g) {

        BufferedImage tmp = sprites.get(selectedImg);
        switch (getDirection(this)) {
            case RIGHT_DOWN, LEFT_UP -> tmp = Visual.rotateImageByDegrees(tmp, 45);
            case DOWN -> tmp = Visual.rotateImageByDegrees(tmp, 90);
            case RIGHT, LEFT -> tmp = sprites.get(selectedImg);
            case LEFT_DOWN, RIGHT_UP -> tmp = Visual.rotateImageByDegrees(tmp, 315);
            case UP -> tmp = Visual.rotateImageByDegrees(tmp, 270);
        }
        g.drawImage(tmp, (Map.WIDTH_SCREEN*Map.SIZE-1)/2 - Map.SIZE/2, (Map.HEIGHT_SCREEN*Map.SIZE-1)/2 - Map.SIZE/2, Map.SIZE, Map.SIZE, null);
    }
    static class PlayerKeyListener implements KeyListener {
        public Player player;

        public void setPlayer(Player player) {
            this.player = player;
        }

        public void keyTyped(KeyEvent e){

        }

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_W:
                    player.up = true;
                    break;
                case KeyEvent.VK_S:
                    player.down = true;
                    break;
                case KeyEvent.VK_A:
                    player.left = true;
                    break;
                case KeyEvent.VK_D:
                    player.right = true;
                    break;
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_W:
                    player.up = false;
                    break;
                case KeyEvent.VK_S:
                    player.down = false;
                    break;
                case KeyEvent.VK_A:
                    player.left = false;
                    break;
                case KeyEvent.VK_D:
                    player.right = false;
                    break;
            }
        }
    }
    static class PlayerMouseListener extends MouseAdapter {
        boolean used;
        Player player;

        public void setPlayer(Player player) {
            this.player = player;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON3) {
                used = true;
            }
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            player.selected += e.getWheelRotation();
            if(player.selected<0)player.selected*=-1;
            player.selected = player.selected%player.powers.size();
        }
    }
}
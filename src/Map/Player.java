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
    public int screenX;
    public int screenY;
    public int selected;

    public Player(int speed, int x, int y) {
        super(speed, x, y);
        selected = 0;
        playerKeyListener = new PlayerKeyListener();
        playerKeyListener.setPlayer(this);
        playerMouseListener = new PlayerMouseListener();
        playerMouseListener.setPlayer(this);
        screenX = (Map.WIDTH_SCREEN*Map.SIZE-1)/2 - Map.SIZE/2;
        screenY = (Map.HEIGHT_SCREEN*Map.SIZE-1)/2 - Map.SIZE/2;
        try {
            sprites.add(ImageIO.read(getClass().getResource("/Player/(1).png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        powers.add(new Dash(180, this));
        powers.add(new Dash(180, this));
    }

    @Override
    public void update() {
        super.update();
        if (playerMouseListener.used) {
            playerMouseListener.used = false;
            powers.get(selected).use();
        }
        if (down && (left || right)) {
            y += speedy * 0.707;
        } else if (down) {
            y += speedy;
        }

        if (up && (left || right)) {
            y -= speedy * 0.707;
        } else if (up) {
            y -= speedy;
        }

        if (left && (up || down)) {
            x -= speedx * 0.707;
        } else if (left) {
            x -= speedx;
        }

        if (right && (up || down)) {

            x += speedx * 0.707;
        } else if (right) {
            x += speedx;
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
        g.drawImage(tmp, screenX, screenY, Map.SIZE, Map.SIZE, null);
    }
    static class PlayerKeyListener implements KeyListener {
        public Player player;

        public void setPlayer(Player player) {
            this.player = player;
        }

        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_W) {
                player.up = true;
            }
            if (keyCode == KeyEvent.VK_S) {
                player.down = true;
            }
            if (keyCode == KeyEvent.VK_A) {
                player.left = true;
            }
            if (keyCode == KeyEvent.VK_D) {
                player.right = true;
            }
            if (keyCode == KeyEvent.VK_UP) {
                Map.zoomInOut(1);
            }
            if (keyCode == KeyEvent.VK_DOWN) {
                Map.zoomInOut(-1);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_W) {
                player.up = false;
            }
            if (keyCode == KeyEvent.VK_S) {
                player.down = false;
            }
            if (keyCode == KeyEvent.VK_A) {
                player.left = false;
            }
            if (keyCode == KeyEvent.VK_D) {
                player.right = false;
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
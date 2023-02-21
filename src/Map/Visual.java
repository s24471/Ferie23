package Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Visual {


    double x, y;
    int loop, time, curr, selected;
    boolean alive;
    Directions direction;
    public ArrayList<BufferedImage> sprites;
    int height;
    int width;
    public Visual(double x, double y, String path, Directions direction, int amount, int time, int loop){
        this(x, y, path, direction, amount, time, loop, Map.SIZE, Map.SIZE);
    }
    public Visual(double x, double y, String path, Directions direction, int amount, int time, int loop, int height, int width){
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.time = time/amount;
        this.loop = loop;
        this.curr = 0;
        this.height = height;
        this.width = width;
        selected = 0;
        sprites = new ArrayList<>();
        alive = true;
        for (int i = 0; i < amount; i++) {
            try {
                System.out.println(path + i + ".png");
                BufferedImage tmp = ImageIO.read(getClass().getResource(path+"(" + i + ").png"));

                sprites.add(rotate(tmp, direction));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void draw(Graphics2D g){
        curr--;
        if(curr<0){
            curr = time;
            selected++;
        }
        if(selected>=sprites.size()){
            if(loop>0) {
                loop--;
                selected = 0;
            }
            else {
                alive = false;
                return;
            }
        }
        g.drawImage(sprites.get(selected),(int)(x-Map.player.x+Map.player.screenX), (int)(y-Map.player.y+Map.player.screenY), Map.SIZE, Map.SIZE, null);
    }

    public static BufferedImage rotate(BufferedImage tmp, Directions d) {
        switch (d){
            case RIGHT_UP -> tmp = rotateImageByDegrees(tmp,315 );
            case RIGHT_DOWN -> tmp = rotateImageByDegrees(tmp, 45);
            case LEFT -> tmp = rotateImageByDegrees(tmp, 180);
            case LEFT_UP -> tmp = rotateImageByDegrees(tmp, 225);
            case LEFT_DOWN -> tmp = rotateImageByDegrees(tmp, 135);
            case UP -> tmp = rotateImageByDegrees(tmp, 270);
            case DOWN -> tmp = rotateImageByDegrees(tmp, 90);
        }
        return tmp;
    }
    public static BufferedImage rotateImageByDegrees(BufferedImage bimg, double angle) {

        int w = bimg.getWidth();
        int h = bimg.getHeight();

        BufferedImage rotated = new BufferedImage(w, h, bimg.getType());
        Graphics2D graphic = rotated.createGraphics();
        graphic.rotate(Math.toRadians(angle), w/2, h/2);
        graphic.drawImage(bimg, null, 0, 0);
        graphic.dispose();
        return rotated;
    }
}

package Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Tile {

    static public ArrayList<BufferedImage>[] sprites = new ArrayList[3];
    int y;
    int x;
    int amount=0;
    int curr;
    int selected;
    int time;
    int type;



    public Tile(int y, int x) {
        this.y = y;
        this.x = x;
        selected = 0;
        curr=0;
        type =0;
        time = (int)(Math.random()*120)+120;

    }

    public static Tile NEW(int y, int x, int type){
        switch (type)
        {
            case '0': return new GrassTile(y, x);
            case '1': return new WaterTile(y, x);
            case '2': return new Tree(y, x);
            default: {
                System.out.println("AAAAAA");
                return null;
            }
        }
    }

    public void loadSprites(String path){
        if(sprites[type] == null) {
            sprites[type] = new ArrayList<>();
            for (int i = 1; i <= amount; i++) {
                try {
                    //System.out.println(path + "(" + i + ").png");
                    BufferedImage tmp = ImageIO.read(getClass().getResource(path + "(" + i + ").png"));
                    sprites[type].add(tmp);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void draw(Graphics2D g){
        curr--;
        if(curr<0){
            curr = time;
            selected++;
        }
        if(selected>=sprites[type].size()){
            selected = 0;
        }
        if(
            x*Map.SIZE +Map.SIZE> Map.player.x - Map.player.screenX &&
            x*Map.SIZE -Map.SIZE< Map.player.x + Map.player.screenX &&
            y*Map.SIZE +2*Map.SIZE> Map.player.y - Map.player.screenY &&
            y*Map.SIZE -2*Map.SIZE< Map.player.y + Map.player.screenY
        )
        g.drawImage(sprites[type].get(selected), (int)(x*Map.SIZE-Map.player.x+Map.player.screenX), (int)(y*Map.SIZE-Map.player.y+Map.player.screenY), Map.SIZE, Map.SIZE, null);
    }

}

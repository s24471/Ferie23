package Arena;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Tile extends JLabel {
    static public ArrayList<BufferedImage>[] sprites = new ArrayList[1];
    int y;
    int x;
    int amount=0;
    int curr;
    int selected;
    int time;
    int type;
    public Tile(int y, int x){
        this.y = y;
        this.x = x;
        selected = 0;
        curr=0;
        type =0;
        time = (int)(Math.random()*1000)+360;
    }

    public static Tile NEW(int y, int x, int type){
        switch (type)
        {
            case '0': return new GrassTile(y, x);
            default: {
                System.out.println(type);
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

    public BufferedImage draw(){
        curr--;
        if(curr<0){
            curr = time;
            selected++;
        }
        if(selected>=sprites[type].size()){
            selected = 0;
        }
        return sprites[type].get(selected);
    }
}

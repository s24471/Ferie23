package Map;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Entity {
    public double speed;
    public int x;
    public int y;
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    public boolean alive;
    public int selected;

    public ArrayList<Power> powers;
    ArrayList<BufferedImage> sprites;
    int selectedImg;
    public Entity(double speed, int x, int y) {
        alive = true;
        this.speed = speed;
        this.x = x;
        this.y = y;

        sprites = new ArrayList<>();
        powers = new ArrayList<>();
        selected = 0;
        selectedImg = 0;
    }

    public void update(){
        for(Power power: powers){
            power.frame();
        }
    }

    public void draw(Graphics2D g){}

    public static Directions getDirection(Entity entity){
        Directions direction;
        if(entity.down){
            if(entity.right)
                direction = Directions.RIGHT_DOWN;
            else if(entity.left)
                direction = Directions.LEFT_DOWN;
            else
                direction = Directions.DOWN;
        }else if(entity.up){
            if(entity.left)
                direction = Directions.LEFT_UP;
            else if(entity.right)
                direction = Directions.RIGHT_UP;
            else
                direction = Directions.UP;
        }else if(entity.left){
            direction = Directions.LEFT;
        }else{
            direction = Directions.RIGHT;
        }
        return direction;
    }
    public void kill(){
        alive=false;
    }

}

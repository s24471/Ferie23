package Map;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Entity {
    public double defspeedx;
    public double defspeedy;
    public double speedx;
    public double speedy;
    public double x;
    public double y;
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    public boolean alive;

    public ArrayList<Power> powers;
    ArrayList<BufferedImage> sprites;
    int selectedImg;
    public Entity(double speed, double x, double y) {
        alive = true;
        this.defspeedx = Map.WIDTH_WORLD/speed;
        this.defspeedy = Map.HEIGHT_WORLD/speed;
        this.speedx = Map.WIDTH_WORLD/defspeedx;
        this.speedy = Map.HEIGHT_WORLD/defspeedy;

        this.x = x;
        this.y = y;

        sprites = new ArrayList<>();
        powers = new ArrayList<>();
        selectedImg = 0;
    }

    public void update(){
        for(Power power: powers){
            power.frame();
        }
    }

    public void draw(Graphics2D g){

        //x*Map.SIZE-Map.player.x+(Map.WIDTH_SCREEN*Map.SIZE-1)/2 - Map.SIZE/2 , y*Map.SIZE-Map.player.y+(Map.HEIGHT_SCREEN*Map.SIZE-1)/2 - Map.SIZE/2, Map.SIZE, Map.SIZE, null);
    }

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

package Map;

import java.awt.image.BufferedImage;

public class Power {
    public boolean ready;
    public int cooldown;
    public int curr;
    public BufferedImage iconActive;
    public BufferedImage iconInactive;
    public Entity entity;
    public Power(int cooldown, Entity entity) {
        this.cooldown = cooldown;
        this.entity = entity;

        ready = true;
        curr = 0;
    }

    public void frame(){
        if(curr>0)curr--;
        else ready = true;
    }
    public void use() {
        curr = cooldown;
        ready = false;
    }

    public BufferedImage getIcon(){
        if(ready) return iconActive;
        return iconInactive;
    }

    public float getProgress(){
        if(ready)return 1;
        return 1-(float)(curr)/(float)(cooldown);
    }
}

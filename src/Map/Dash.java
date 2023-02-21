package Map;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Dash extends Power{
    public Dash(int cooldown, Entity entity) {
        super(cooldown, entity);
        try {
            iconActive = (ImageIO.read(getClass().getResource("/Dash/0.png")));
            iconInactive = (ImageIO.read(getClass().getResource("/Dash/1.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void use() {
        if (ready) {
            super.use();
            Directions direction = Entity.getDirection(entity);
            Brain.visuals.add(new Visual(entity.x, entity.y, "/DashWave/", direction, 6, 25, 0));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int tmp = 6;
                    for (int i = 0; i < entity.speedx * 8; i++) {
                        if (entity.down && (entity.left || entity.right)) {
                            entity.y += tmp * 0.707;
                        } else if (entity.down) {
                            entity.y += tmp;
                        }

                        if (entity.up && (entity.left || entity.right)) {
                            entity.y -= tmp * 0.707;
                        } else if (entity.up) {
                            entity.y -= tmp;
                        }

                        if (entity.left && (entity.up || entity.down)) {
                            entity.x -= tmp * 0.707;
                        } else if (entity.left) {
                            entity.x -= tmp;
                        }

                        if (entity.right && (entity.up || entity.down)) {
                            entity.x += tmp * 0.707;
                        } else if (entity.right) {
                            entity.x += tmp;
                        }
                        try {
                            TimeUnit.NANOSECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            }).start();
        }
    }
}

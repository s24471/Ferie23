package Map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class Brain implements Runnable{
    final int FPS = 60;
    Thread fpsCounter;
    int fpsCount;
    boolean alive;
    ArrayList<Entity> entities;
    ArrayList<Entity> qentities;
    ArrayList<Visual> visuals;
    ArrayList<Visual> qvisuals;
    public Brain(){
        fpsCounter = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (fpsCount < 58) System.out.println(fpsCount);
                fpsCount = 0;
            }
        });
        alive = true;
        fpsCount = 0;
        entities = new ArrayList<>();
        qentities = new ArrayList<>();
        visuals = new ArrayList<>();
        qvisuals = new ArrayList<>();

    }



    @Override
    public void run() {
        double interval = (double) 1000000000 / FPS;
        double nextFPS = System.nanoTime() + interval;
        fpsCounter.start();

        while (alive) {
            update();
            fpsCount++;
            paint();
            double left = nextFPS - System.nanoTime();
            if (left > 0) {
                try {
                    TimeUnit.NANOSECONDS.sleep((long) (left));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            nextFPS += interval;
        }
    }

    public void update(){
        Iterator<Entity> ie = entities.iterator();
        while (ie.hasNext()) {
            Entity e = ie.next();
            e.update();
            if(!e.alive) ie.remove();
        }
        entities.addAll(qentities);
        qentities = new ArrayList<>();
        visuals.addAll(qvisuals);
        qvisuals = new ArrayList<>();
    }

    public void paint(){

    }

}

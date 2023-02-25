package Map;
import Arena.Arena;

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
    static ArrayList<Visual> visuals;
    ArrayList<Visual> qvisuals;
    public Map map;
    public Window window;
    static public boolean change;
    Player player;
    public Brain(){
        change = false;
        fpsCounter = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (fpsCount < 58)
                    System.out.println(fpsCount);
                fpsCount = 0;
            }
        });
        alive = true;
        fpsCount = 0;
        entities = new ArrayList<>();
        qentities = new ArrayList<>();
        visuals = new ArrayList<>();
        qvisuals = new ArrayList<>();
        player = new Player(8, Map.SIZE*Map.WIDTH_MAP/2, Map.SIZE*Map.HEIGHT_MAP/2);
        entities.add(player);
        map = new Map(this, player);
        window = new Window(this);
        new Thread(this).start();
    }



    @Override
    public void run() {
        double interval = (double) 1000000000 / FPS;
        double nextFPS = System.nanoTime() + interval;
        fpsCounter.start();

        while (alive) {
            if(change){
                change();
            }
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
    public void change(){
        change = false;
        window.change();
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
        map.repaint();
    }

}

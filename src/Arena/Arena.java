package Arena;

import Map.Brain;

import java.util.concurrent.TimeUnit;

public class Arena implements Runnable{
    public static boolean change;
    ArenaFrame arenaFrame;
    Brain brain;
    public Arena(ArenaFrame arenaFrame, Brain brain){
        this.arenaFrame = arenaFrame;
        this.brain = brain;
        change = false;
    }

    public void run() {
        double interval = (double) 1000000000 / Brain.FPS;
        double nextFPS = System.nanoTime() + interval;
        while (!change) {
            update();
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

        change = false;
        brain.window.change();
    }
    public void update() {

    }
    public void paint() {
        arenaFrame.repaint();
    }


}

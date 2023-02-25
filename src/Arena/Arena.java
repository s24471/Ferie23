package Arena;

import Map.Brain;

import java.util.concurrent.TimeUnit;

public class Arena{
    public static boolean change;
    ArenaFrame arenaFrame;
    Brain brain;
    public Arena(ArenaFrame arenaFrame, Brain brain){
        this.arenaFrame = arenaFrame;
        this.brain = brain;
        change = false;
    }

    public void run(){
        while(!change){
            //twoja gierka
            try {
                TimeUnit.MILLISECONDS.sleep(10);
                //System.out.println("B");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        change=false;
        brain.window.change();
    }

}

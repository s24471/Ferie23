import Arena.*;
import Map.Brain;

public class Main {
    public static void main(String[] args) {
//        new Brain();
        new Arena(new ArenaFrame(), new Brain());
        System.out.println("Started");

    }
}
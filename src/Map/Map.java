package Map;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Map extends JPanel {
    public static final int SIZE = 64;
    public static final int WIDTH_SCREEN = 30;
    public static final int HEIGHT_SCREEN = 16;
    public static final int WIDTH_MAP = 50;
    public static final int HEIGHT_MAP = 30;
    public static final int GAP = 8;
    public static Tile[][] MAP;
    Brain brain;
    Player player;

    public Map(Brain brain, Player player){
        this.brain = brain;
        this.player = player;
        this.setPreferredSize(new Dimension(WIDTH_SCREEN * SIZE, HEIGHT_SCREEN * SIZE));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        this.addKeyListener(Player.playerKeyListener);
        this.addMouseListener(Player.playerMouseListener);
        MAP = new Tile[HEIGHT_MAP][WIDTH_MAP];
        try {
            Scanner scanner = new Scanner(new FileReader("src/Map/mapa.txt"));
            for (int i = 0; i < HEIGHT_MAP; i++) {
                String tmp = scanner.nextLine();
                for (int j = 0; j < WIDTH_MAP; j++) {
                    MAP[i][j] = Tile.NEW(i, j, tmp.charAt(j));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D)g).setStroke(new BasicStroke(5));
        g.setColor(Color.RED);
        //for(Entity e: brain.entities)g.fillRect(e.x, e.y, SIZE, SIZE);

        player.draw((Graphics2D) g);
        for(Entity entity: brain.entities){
            entity.draw((Graphics2D) g);
        }
        for(Visual visual: brain.visuals){
            visual.draw((Graphics2D) g);
        }

        for(int i=0; i<brain.player.powers.size(); i++){
            g.drawImage(brain.player.powers.get(i).getIcon(), i*(SIZE+SIZE/3+GAP), HEIGHT_SCREEN *(SIZE-1), SIZE, SIZE, null);
            g.setColor(Color.white);
            float progress = brain.player.powers.get(i).getProgress();
            g.fillRect(i*(SIZE+SIZE/3+GAP)+SIZE, HEIGHT_SCREEN *(SIZE-1)+SIZE - (int)(SIZE*progress) , SIZE/3, (int)(SIZE*progress));
            if(brain.player.selected == i){
                g.setColor(Color.BLUE);
                g.drawRect(i*(SIZE+SIZE/3+GAP), HEIGHT_SCREEN *(SIZE-1), SIZE, SIZE);
            }
        }
    }

}

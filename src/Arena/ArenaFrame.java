package Arena;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static Map.Map.*;

public class ArenaFrame extends JPanel {
    private final int width = 8;
    private final int height = 8;
    Tile[][] tiles;

    ArenaKeyListener arenaKeyListener;
    public ArenaFrame(){
        tiles = new Tile[height][width];
        this.setPreferredSize(new Dimension(WIDTH_SCREEN * SIZE, HEIGHT_SCREEN * SIZE));
        this.setBackground(Color.BLUE);
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        this.arenaKeyListener = new ArenaKeyListener();
        this.addKeyListener(arenaKeyListener);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = Tile.NEW(i, j, '0');
                tile.setOpaque(true);
                //TODO: 01.03.2023 podmienic na sprites
                tiles[i][j]=tile;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.println("===");
                System.out.println(WIDTH_SCREEN/width*i);
                System.out.println(HEIGHT_SCREEN/height*j);
                System.out.println(WIDTH_SCREEN/width);
                System.out.println(HEIGHT_SCREEN/height);
                g.drawImage(tiles[i][j].draw(),DEF_WIDTH_SCREEN*DEF_SIZE/width*i,DEF_HEIGHT_SCREEN*DEF_SIZE/height*j,WIDTH_SCREEN*DEF_SIZE/width,HEIGHT_SCREEN*DEF_SIZE/height,null);
            }
        }
    }


}

package Arena;

import Map.Tile;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static Map.Map.*;
import static Map.Window.*;

public class ArenaFrame extends JPanel {
    private final int width = 8;
    private final int height = 8;

    ArenaKeyListener arenaKeyListener;
    public ArenaFrame(){
        this.setPreferredSize(new Dimension(WIDTH_SCREEN * SIZE, HEIGHT_SCREEN * SIZE));
        this.setBackground(Color.BLUE);
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        this.arenaKeyListener = new ArenaKeyListener();
        this.addKeyListener(arenaKeyListener);
        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(height,width));
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(i, j);
                tile.setOpaque(true);
                //TODO: 01.03.2023 podmienic na sprites
                if ((i + j) % 2 == 0) {

//                    tile.setColor(Color.YELLOW);
                } else {
//                    tile.setColor(Color.ORANGE);
                }
                panel.add(tile);
            }
        }
        this.add(panel, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawRect(0, 0, 100, 100);
    }


}

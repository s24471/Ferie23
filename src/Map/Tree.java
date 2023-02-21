package Map;

import java.awt.*;

public class Tree extends Tile{
    Tile tile;
    public Tree(int y, int x) {
        super(y, x);
        amount = 60;
        type = 2;
        tile = new GrassTile(y, x);
        loadSprites("/Tile/Tree/");
    }

    @Override
    public void draw(Graphics2D g) {
        tile.draw(g);
        super.draw(g);
    }
}

package Map;

import java.awt.*;

public class GrassTile extends Tile {

    public GrassTile(int y, int x) {
        super(y, x);
        amount = 1;
        type = 0;
        loadSprites("/Tile/Grass/");
    }
}

package Map;

public class WaterTile extends Tile {
    public WaterTile(int y, int x) {
        super(y, x);
        amount = 4;
        type=1;
        loadSprites("/Tile/Water/");
    }
}

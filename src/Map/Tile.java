package Map;

public class Tile {

    int y;
    int x;

    public Tile(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public static Tile NEW(int y, int x, int type){
        switch (type)
        {
            case 0: return new GrassTile(y, x);
            case 1: return new WaterTile(y, x);
            default: return null;
        }
    }
}

package Arena;

public class GrassTile extends Tile{

    public GrassTile(int y, int x) {
        super(y, x);
        amount = 2;
        type = 0;
        loadSprites("/Tile/Grass/");
    }
}

package Arena;

import javax.swing.*;
import java.awt.*;

public class Tile extends JLabel {
    private int row, col;
    private Color color;

    public Tile(int row, int col) {
        this.col = col;
        this.row = row;
    }

    public void resetBackground() {
        this.setBackground(color);
    }


    public void setColor(Color color) {
        this.color = color;
        this.setBackground(color);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Color getColor() {
        return color;
    }
}

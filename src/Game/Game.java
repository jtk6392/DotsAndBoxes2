package Game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;

/**
 * Primary Game loop for Dots and Boxes
 */
public class Game implements Paint {

    private Board dots;

    /**
     * Loads in all data necessary in the game
     */
    public void Intialize()
    {
        dots = new Board();
        dots = new Board(10);
    }
    public static void main(String[] args) {
        System.out.println("Hello, world");
    }

    @Override
    public PaintContext createContext(ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds, AffineTransform xform, RenderingHints hints) {
        return null;
    }

    @Override
    public int getTransparency() {
        return 0;
    }
}

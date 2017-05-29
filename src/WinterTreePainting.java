import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

/**
 * WinterTreePainting is a subclass of TreePainting
 * and as such, inherits most of the functionality of
 * the parent class. However, it overrides some
 * method to create a depiction of the winter season
 * with snow on the ground and snowy colored leaves.
 *
 * @author: Sabirah Shuaybi
 */

public class WinterTreePainting extends TreePainting {

    /**
     * Background FLOOR color for winter
     **/
    private static final Color LIGHT_GRAY = new Color(170, 170, 170);

    /**
     * Method for painting background and floor.
     * Overrides the paint method specified in TreePainting (parent).
     * Overrides with white and gray backdrop and floor colors
     * and differently sized panel (Collage vs. Single panel)
     *
     * @param g: The graphics object to draw on
     */
    protected void paintBackground(Graphics g) {
        g.setColor(LIGHT_GRAY);
        g.fillRect(0, 0, TreeApplication.COLLAGE_FRAME_WIDTH / 5, TreeApplication.FRAME_HEIGHT);

        g.setColor(Color.WHITE);
        g.fillRect(0, TreePainting.Y_COORD_FLOOR,
                TreeApplication.COLLAGE_FRAME_WIDTH / 5, TreePainting.FLOOR_HEIGHT);
    }

    /**
     * Overrides getLeafColor() method in parent class
     *
     * @return a white color for leaf
     * to correspond with the winter theme
     **/
    protected Color getLeafColor() {
        return Color.WHITE;
    }
}
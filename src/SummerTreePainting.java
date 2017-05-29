import java.awt.Color;
import java.awt.Graphics;

/**
 * SummerTreePainting is a subclass of TreePainting
 * and as such, inherits most of the functionality of
 * the parent class. However, it overrides some
 * method to create a depiction of the summer season
 * with a bright blue summer backdrop.
 *
 * @author: Sabirah Shuaybi
 */

public class SummerTreePainting extends TreePainting {


    /**
     * Background color for summer
     **/
    private static final Color SKY_BLUE = new Color(135, 206, 250);

    /**
     * Background FLOOR color for summer
     **/
    private static final Color GREEN = new Color(53, 167, 41);

    /**
     * Method for painting background and floor.
     * Overrides the paint method specified in TreePainting (parent).
     * Overrides with sky blue backdrop and green floor
     * and differently sized panel (Collage vs. Single panel)
     *
     * @param g: The graphics object to draw on
     */
    protected void paintBackground(Graphics g) {
        g.setColor(SKY_BLUE);
        g.fillRect(0, 0, TreeApplication.COLLAGE_FRAME_WIDTH / 5, TreeApplication.FRAME_HEIGHT);

        g.setColor(GREEN);
        g.fillRect(0, TreePainting.Y_COORD_FLOOR,
                TreeApplication.COLLAGE_FRAME_WIDTH / 5, TreePainting.FLOOR_HEIGHT);
    }

}
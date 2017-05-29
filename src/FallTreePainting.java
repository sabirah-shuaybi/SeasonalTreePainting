import java.awt.Color;
import java.util.Random;
import java.awt.Graphics;
import java.awt.geom.Point2D;

/**
 * FallTreePainting is a subclass of TreePainting
 * and as such, inherits most of the functionality of
 * the parent class. However, it overrides some
 * method to create a depiction of the fall season
 * with randomly generated fall colors (hues of
 * reds, oranges, yellows and browns).
 *
 * @author: Sabirah Shuaybi
 */

public class FallTreePainting extends TreePainting {

    /**
     * Background color for fall
     **/
    private static final Color ORANGE = new Color(255, 136, 97);

    /**
     * Background FLOOR color for fall
     **/
    private static final Color FALL_BROWN = new Color(96, 37, 20);

    /**
     * Blue component of RGB for a fall color leaf
     **/
    private static final int BLUE_COMPONENT = 36;

    /**
     * Red component of RGB for a fall color leaf
     **/
    private static final int RED_COMPONENT = 220;

    /**
     * Method for painting background and floor.
     * Overrides the paint method specified in TreePainting (parent).
     * Overrides with orange and brown background colors
     * and differently sized panel (Collage vs. Single panel)
     *
     * @param g: The graphics object to draw on
     */
    protected void paintBackground(Graphics g) {
        g.setColor(ORANGE);
        g.fillRect(0, 0, TreeApplication.COLLAGE_FRAME_WIDTH / 5, TreeApplication.FRAME_HEIGHT);

        g.setColor(FALL_BROWN);
        g.fillRect(0, TreePainting.Y_COORD_FLOOR,
                TreeApplication.COLLAGE_FRAME_WIDTH / 5, TreePainting.FLOOR_HEIGHT);
    }

    /**
     * Overrides getLeafColor() method in parent class
     *
     * @return a random fall color for leaf
     * to correspond with the fall theme
     **/
    protected Color getLeafColor() {
        return randomFallColor();
    }

    /**
     * Method for randomly generating fall colors
     * for the leaves. Each repaint will result in
     * a new array of random fall leaf colors.
     *
     * @return a color, random but within a specified scope
     * to ensure a fall color only
     */
    private Color randomFallColor() {
        Random r = new Random();

        //Randomize green component within a certain range (to generate fall-like colors)
        int min = 200;
        int max = 250;
        int greenComponent = r.nextInt(((max - min) + 1) + min);

        //Always keep the red and blue components constant
        //To create naturally looking fall-like colors
        return new Color(RED_COMPONENT, greenComponent, BLUE_COMPONENT);
    }

}
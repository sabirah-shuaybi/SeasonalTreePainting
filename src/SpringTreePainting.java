import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * SpringTreePainting is a subclass of TreePainting
 * and as such, inherits most of the functionality of
 * the parent class. However, it overrides some
 * method to create a depiction of the spring season
 * with pink blossoms and bright green leaves.
 *
 * @author: Sabirah Shuaybi
 */

public class SpringTreePainting extends TreePainting {

    /**
     * Background color for spring
     **/
    private static final Color SPRING_GREEN = new Color(154, 205, 50);

    /**
     * Background FLOOR color for spring
     **/
    private static final Color DARK_GREEN = new Color(25, 96, 17);

    /**
     * Method for painting background and floor.
     * Overrides the paint method specified in TreePainting (parent).
     * Overrides with spring green and dark green colors
     * to create a spring-like atmosphere.
     *
     * @param g: The graphics object to draw on
     */
    protected void paintBackground(Graphics g) {
        g.setColor(SPRING_GREEN);
        g.fillRect(0, 0, TreeApplication.COLLAGE_FRAME_WIDTH / 5, TreeApplication.FRAME_HEIGHT);

        g.setColor(DARK_GREEN);
        g.fillRect(0, TreePainting.Y_COORD_FLOOR,
                TreeApplication.COLLAGE_FRAME_WIDTH / 5, TreePainting.FLOOR_HEIGHT);
    }

    /**
     * Method for randomly generating either a spring
     * green leaf or a fresh pink flower.
     *
     * @return a color, either pink or green
     */
    private Color randomSpringColor() {
        Random r = new Random();
        int n = r.nextInt(2);

        if (n == 0) {
            //Pink color for blossoms
            return new Color(173, 47, 113);
        }
        //Green color for leaves
        else return new Color(40, 165, 48);
    }

    /**
     * Overrides getLeafColor() method in parent class
     *
     * @return a random spring blossom color
     * to correspond with the spring theme
     **/
    protected Color getLeafColor() {
        return randomSpringColor();
    }

}
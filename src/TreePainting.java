import java.awt.Color;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.JComponent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * TreePainting is a GUI interface that allows the user to paint a tree
 * via the mouse events of pressing and releasing, which designate the length
 * of the tree branch. This program then paints a fixed number of branches at
 * specified angles, coming out of the truck. Finally a single leaf is painted
 * at the end of each branch. The tree painting is dynamic such that the user
 * has the ability to determine the size of the tree (via length of mouse drag)
 * and the program will render the branches and leaf sizes proportionately
 * to the trunk.
 *
 * @author: Sabirah Shuaybi
 */

public class TreePainting extends JComponent implements MouseListener {

    protected static final int NUM_BRANCHES = 20;

    /**
     * The y coordinate of the background floor
     **/
    protected static final int Y_COORD_FLOOR = 370;

    protected static final int FLOOR_HEIGHT = 50;

    /**
     * Color of trunk and branches
     **/
    private static final Color LIGHT_BROWN = new Color(160, 82, 45);

    /**
     * The amount by which to divide by to calculate leaf diameter
     * (relative to trunk length)
     **/
    protected static final int LEAF_DIAM_DIVIDER = 4;

    /**
     * Width branch is about half of the width of trunk
     **/
    protected static final int TRUNK_WIDTH = 15;
    protected static final int BRANCH_WIDTH = 7;

    /**
     * Golden ratio makes the trunk length:branch length ratio
     * aesthetically appealing
     **/
    protected static final double GOLDEN_RATIO = 1.618;

    /**
     * Points to designate the start and end of trunk
     **/
    private Point2D.Double trunkStart;
    private Point2D.Double trunkEnd;

    private boolean mouseReleased;

    public TreePainting() {

        addMouseListener(this);
    }

    /**
     * Method for drawing on this component.
     * Overrides the paint method specified in JComponent (parent).
     * Calls Paints background and if mouse released, paints tree.
     *
     * @param g: The graphics object to draw on
     */
    public void paintComponent(Graphics g) {
        paintBackground(g);

        //Only paint tree if user has released mouse
        if (mouseReleased) {
            paintTree(g);
        }
    }

    /**
     * Method for drawing the background of the tree painting.
     * Draws the floor as part of the backdrop.
     *
     * @param g: The graphics object to draw on
     */
    protected void paintBackground(Graphics g) {
        //Black backdrop fills the entire panel
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, TreeApplication.SINGLE_FRAME_WIDTH, TreeApplication.FRAME_HEIGHT);

        //Gray floor on the bottom of the panel
        g.setColor(new Color(76, 70, 72));
        g.fillRect(0, Y_COORD_FLOOR, TreeApplication.COLLAGE_FRAME_WIDTH / 5, FLOOR_HEIGHT);
    }

    /**
     * Method responsible for painting trunk and branches.
     * Only paints branches at the specified range of angles
     * coming out of the trunk (to avoid the tree
     * resembling a dandelion and to create a more realistic
     * looking tree with a funnel effect on branches)
     *
     * @param g: The graphics object to draw on
     */
    private void paintTree(Graphics g) {
        //Type cast g into a 2D Graphics object
        Graphics2D g2d = (Graphics2D) g;

        //To enable a thicker trunk
        g2d.setStroke(new BasicStroke(TRUNK_WIDTH));

        g2d.setColor(LIGHT_BROWN);

        //Draw trunk from user press point to user release point
        g2d.drawLine((int) trunkStart.getX(), (int) trunkStart.getY(), (int) trunkEnd.getX(), (int) trunkEnd.getY());

        //Consolidate the start/end points of trunk into a line object
        Line2D.Double trunkLine = new Line2D.Double(trunkStart, trunkEnd);

        //Aesthetically configure branch length relative to length of trunk
        double trunkLength = trunkStart.distance(trunkEnd);
        double branchLength = trunkLength / GOLDEN_RATIO;

        int numBranches = 0;

        //Draw branches at the end of the trunk
        while (numBranches < NUM_BRANCHES) {

            //Get the end point of branch (1. To see if branch will be angled within the
            //specified range and 2. To know where to draw the leaf)
            Point2D.Double branchEndPoint = computeEndpoint(trunkEnd, branchLength, getRandomAngle());

            //Create a line for a branch from end of trunk point to end of branch point
            Line2D.Double branchLine = new Line2D.Double(trunkEnd, branchEndPoint);

            double angle = getAbsoluteAngleBetween(trunkLine, branchLine);

            //Draw branch only if the angle is within the desired range (about 90 degrees)
            if (angle < Math.PI / 2) {
                g2d.setStroke(new BasicStroke(BRANCH_WIDTH));
                //Reset branch color to light brown
                g2d.setColor(LIGHT_BROWN);

                g2d.draw(branchLine);

                //Paint on the leaves/blossoms
                paintLeaf(g2d, branchEndPoint, branchLength);

                numBranches++;
            }
        }
    }

    /**
     * Method for painting leaf centered on top of branch.
     *
     * @param g:            The graphics object to draw on
     * @param p:            Point at which to draw leaf (AKA branch end point)
     * @param branchLength: Length of the branch (relative to trunk length)
     */
    protected void paintLeaf(Graphics g, Point2D.Double p, double branchLength) {
        g.setColor(getLeafColor());

        //Calculate leaf size relative to branch length
        //The shorter the trunk, the smaller the leaves
        //The longer the trunk, the larger the leaves
        double leafSize = branchLength / LEAF_DIAM_DIVIDER;

        //To center leaf on top of the branch branch
        double leafCenterX = p.getX() - leafSize / 2;
        double leafCenterY = p.getY() - leafSize / 2;

        //Draw the leaf
        g.fillOval((int) leafCenterX, (int) leafCenterY, (int) leafSize, (int) leafSize);
    }

    /**
     * Method that renders a random angle.
     *
     * @return double: a random angle in radians
     */
    private double getRandomAngle() {
        Random r = new Random();
        //In radians
        return r.nextDouble() * 2 * Math.PI;
    }

    /**
     * Compute the point that is length away from point p at the specified angle.
     * Uses cosine to get the new x coordinate, sine to get the new y coordinate.
     *
     * @param p:      a (starting) point
     * @param length: the distance away from point p
     * @param angle:  specified angle
     * @return point that is length away from p at given angle
     */
    private Point2D.Double computeEndpoint(Point2D p, double length, double angle) {
        return new Point2D.Double(p.getX() + length * Math.cos(angle), // x is cos
                p.getY() + length * Math.sin(angle)); // y is sin
    }

    /**
     * Positions/Shifts line to the origin (of a typical x,y coordinate graph),
     * thus rendering it a vector.
     *
     * @param line: the line to be converted to a vector
     * @return vector point
     */
    private Point2D.Double getVector(Line2D.Double line) {
        //Translating line to the origin (shifting while maintaining the slope of line)
        return new Point2D.Double(line.getX2() - line.getX1(), line.getY2() - line.getY1());
    }

    /**
     * Computes the absolute angle (no negatives)between two lines
     * via vector formula.
     *
     * @param line1: the first line
     * @param line2: the second line
     * @return the absolute value of the angle between line1 and line2
     */
    private double getAbsoluteAngleBetween(Line2D.Double line1, Line2D.Double line2) {
        //Converting two lines into two vectors
        //These vectors have been positioned at the origin
        //(to calculate the absolute angle between them)
        Point2D.Double v1 = getVector(line1);
        Point2D.Double v2 = getVector(line2);
        double y1 = v1.getY();
        double x1 = v1.getX();
        double x2 = v2.getX();
        double y2 = v2.getY();

        //Formula for calculating the angle between two vectors
        double numer = (x1 * x2 + y1 * y2);
        double denom = (Math.sqrt(x1 * x1 + y1 * y1) * Math.sqrt(x2 * x2 + y2 * y2));
        return Math.acos(numer / denom);

    }

    /**
     * Getter method that returns leaf color.
     *
     * @return leaf color (which, in this case, is green)
     */
    protected Color getLeafColor() {
        return Color.GREEN;
    }

    /**
     * Methods below are required by mouseListener interface
     *
     * @param e: the user mouse event
     */

    public void mousePressed(MouseEvent e) {
        //Capture the point at which mouse was pressed
        trunkStart = new Point2D.Double(e.getX(), e.getY());
    }

    public void mouseReleased(MouseEvent e) {
        mouseReleased = true;

        //Capture the point at which mouse was released
        trunkEnd = new Point2D.Double(e.getX(), e.getY());

        //Now that user has completed the mouse event,
        //invoke the paintComponent method via repaint()
        //This will re-paint the screen with a new tree
        //each time mouse is released
        repaint();
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {

    }
}

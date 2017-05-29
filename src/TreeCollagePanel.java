import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

/**
 * TreeCollagePanel is a JPanel. It will hold all the
 * seasonal TreePaintings we want to display as well as the
 * standard, non seasonal painting. It uses
 * a GridLayout to specify how they are arranged in the GUI.
 * <p>
 * Added feature: JLabels are created and formatted to indicate
 * the season for each TreePainting (for unambiguity to user
 * as well as for presentational, aesthetic purposes)
 *
 * @author: Sabirah Shuaybi
 */
public class TreeCollagePanel extends JPanel {
    private JLabel instructions;
    private JPanel mainCollagePanel;

    /**
     * The labels for each collage panel
     **/
    private JLabel noSeasonLabel = new JLabel("No Season");
    private JLabel winterLabel = new JLabel("Winter");
    private JLabel springLabel = new JLabel("Spring");
    private JLabel summerLabel = new JLabel("Summer");
    private JLabel fallLabel = new JLabel("Fall");

    private static final Color LABEL_BORDER_COLOR = new Color(59, 72, 175);

    //Create a rounded, line border with specified color and width
    private Border labelBorder = BorderFactory.createLineBorder(LABEL_BORDER_COLOR, 5);

    private Font font = noSeasonLabel.getFont();
    //Define a bolded version of the same font
    private Font boldFont = new Font(font.getFontName(), Font.BOLD, 16);

    /**
     * Calls super (the superclass constructor) and sets a BorderLayout.
     * <p>
     * In the NORTH region, adds 'instructions', a JLabel, that will
     * display the instructions.
     * <p>
     * In the CENTER panel, adds 'mainCollagePanel', a JPanel, that will
     * be organized with a GridLayout to hold a 5 seasonal compartments.
     * Thus, the mainCollagePanel contains 5 inner JPanels, each of which
     * contains a TreePainting object (1 basic TreePainting object, and
     * 4 seasonal TreePainting subclass objects) as well as a descriptive label.
     * The labels are designated to the SOUTH of the innermost panels.
     *
     * @param instructionsText; String of instructions to display
     */
    public TreeCollagePanel(String instructionsText) {
        super(new BorderLayout());

        //Create instructions and place at the top
        instructions = new JLabel(instructionsText);
        instructions.setHorizontalAlignment(JLabel.CENTER);

        //Place instructions on top of the frame
        add(instructions, BorderLayout.NORTH);

        //Bold the instructions and set a border around them
        instructions.setBorder(labelBorder);
        instructions.setFont(boldFont);

        mainCollagePanel = new JPanel(new GridLayout(1, 4));
        add(mainCollagePanel, BorderLayout.CENTER);

        //Total of 5 separate mini panels within the mainCollage panel
        JPanel standardPanel = new JPanel();
        JPanel winterPanel = new JPanel();
        JPanel springPanel = new JPanel();
        JPanel summerPanel = new JPanel();
        JPanel fallPanel = new JPanel();

        //Set up, fill and format all the panels (standard panel + 4 seasonal panels)
        setUpASeasonPanel(standardPanel, noSeasonLabel, new TreePainting());
        setUpASeasonPanel(winterPanel, winterLabel, new WinterTreePainting());
        setUpASeasonPanel(springPanel, springLabel, new SpringTreePainting());
        setUpASeasonPanel(summerPanel, summerLabel, new SummerTreePainting());
        setUpASeasonPanel(fallPanel, fallLabel, new FallTreePainting());
    }

    /**
     * A generic method that sets up an entire collage panel
     * by setting layout (BorderLayout), filling in the panel
     * with a label and a TreePainting object and finally,
     * positioning and formatting the label for nice presentation
     *
     * @param panel:
     * @param label;
     * @param painting;
     **/
    private void setUpASeasonPanel(JPanel panel, JLabel label, TreePainting painting) {
        panel.setLayout(new BorderLayout());
        mainCollagePanel.add(panel);
        panel.add(painting, BorderLayout.CENTER);
        panel.add(label, BorderLayout.SOUTH);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(labelBorder);
        label.setFont(boldFont);
    }

}
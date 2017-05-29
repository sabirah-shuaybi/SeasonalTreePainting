import javax.swing.JFrame;

/**
 * Main application for starting up the Tree Painting GUI.
 *
 * Command line usage:
 *
 * To create a single tree painting:
 *     java -cp bin TreeApplication
 *
 * To create a collage of five seasons of tree paintings:
 *     java -cp bin TreeApplication seasons
 *
 * @author ponbarry
 **/
public class TreeApplication
{
	public static final String INSTRUCTIONS_TEXT = "Click, drag, and release to paint a tree!";
	public static final int COLLAGE_FRAME_WIDTH = 1200;
	public static final int SINGLE_FRAME_WIDTH = 400;
	public static final int FRAME_HEIGHT = 500;

	/**
	 * Create a JFrame that holds the TreePaintings.
	 **/
	public static void main( String[] args )
	{
		JFrame guiFrame;

		// if argument "seasons" is passed in
		if (args.length > 0 && args[0].equals("seasons")) {

			// create a new JFrame to hold a TreeCollage (a collage of five paintings)
			guiFrame = new JFrame( "Tree Painting Collage");

			// set size
			guiFrame.setSize( COLLAGE_FRAME_WIDTH, FRAME_HEIGHT );

			// create a TreeCollage and add it
			guiFrame.add( new TreeCollagePanel(INSTRUCTIONS_TEXT) );

		} else {

			// create a new JFrame to hold a single TreePainting
			guiFrame = new JFrame( "A Single Tree Painting");

			// set size
			guiFrame.setSize( SINGLE_FRAME_WIDTH, FRAME_HEIGHT );

			// create a TreePanel and add it
			guiFrame.add( new SingleTreePanel(INSTRUCTIONS_TEXT) );
		}

		// exit normally on closing the window
		guiFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// show frame
		guiFrame.setVisible( true );
	}
}

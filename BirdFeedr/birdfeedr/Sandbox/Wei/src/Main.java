import bluej.extensions.BlueJ;
import bluej.extensions.Extension;

/**
 * The base point of this BlueJ extension.
 * 
 * @author Miguel Roman-Roman
 * @author Wei Huang
 * @author Di Tran
 * @author Josh Gillham
 * @author Nathan Witt
 * @author Thomas Macari
 */
public class Main extends Extension {

	/**
         * Used to get the name of this extension
         * 
         * @return the name of this extension
         */
        @Override
	public String getName() {
		return "Coding Practice Extension";
	}

	/**
         * Used to get the current version of this extension
         * 
         * @return the current version of this extension
         */
        @Override
	public String getVersion() {
		return "Feb2015";
	}

        /**
         * Used to check if this extension is compatible with the BlueJ
         * version used
         * 
         * @return true if this extension is compatible, false otherwise
         */
        @Override
	public boolean isCompatible() {
		return true;
	}

	/**
         * Used at start up of the extension
         * 
         * @param blueJ The BlueJ instance
         */
        @Override
	public void startup(BlueJ blueJ) {
		blueJ.setMenuGenerator(new MenuBuilder());
		StateManager.parseExercises();
	}

}

import bluej.extensions.BlueJ;
import bluej.extensions.PreferenceGenerator;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * This class implements the preference panel behaviour for a BlueJ extension
 */
public class Preferences implements PreferenceGenerator {
    private JPanel myPanel;
    private JTextField color;
    private BlueJ bluej;
    public static final String PROFILE_LABEL="Favorite-Colour";

    // Construct the panel, and initialise it from any stored values
    public Preferences(BlueJ bluej) {
        this.bluej = bluej;
        myPanel = new JPanel();
        myPanel.add (new JLabel ("Name"));
        color = new JTextField (40);
        myPanel.add (color);
        myPanel.add (new JLabel ("Email"));
        color = new JTextField (40);
        myPanel.add (color);
        // Load the default value
        loadValues();
    }

    @Override
    public JPanel getPanel ()  { return myPanel; }

    @Override
    public void saveValues () {
        // Save the preference value in the BlueJ properties file
        bluej.setExtensionPropertyString(PROFILE_LABEL, color.getText());
    }

    @Override
    public void loadValues () {
        // Load the property value from the BlueJ properties file, default to an empty string
        color.setText(bluej.getExtensionPropertyString(PROFILE_LABEL,""));
    }
}
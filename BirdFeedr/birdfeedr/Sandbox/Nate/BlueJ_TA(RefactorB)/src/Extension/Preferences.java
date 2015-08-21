package Extension;

import bluej.extensions.*;
import bluej.extensions.event.*;
import bluej.extensions.editor.*;

import java.net.URL;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.GroupLayout.*;
import java.awt.FlowLayout;
import java.io.File;

import Extension.BackEnd.*;

/**
 * Write a description of class Preferences here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Preferences implements PreferenceGenerator
{
    private JPanel myPanel, localPanel, uriPanel;
    private JLabel localDir, uriDir;
    private JTextField uriTextfield, localTextfield;
    private JButton localButton, uriButton;
    private BlueJ bluej;
    public static final String PROFILE_LABEL="Exercise-Location";

    public Preferences(BlueJ bluej)
    {
        this.bluej = bluej;
        myPanel = new JPanel();
        localPanel = new JPanel();
        uriPanel = new JPanel();
        
        localDir = new JLabel("Local Exercise Directory");
        uriDir = new JLabel("URI Exercise Location");
        uriTextfield = new JTextField (40);
        localTextfield =new JTextField (40);

        localButton = new JButton("Load");
        localButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String location = openFileChooser();
                localTextfield.setText(location);
                
                StateManager.setExercisesLocation(location);
                StateManager.parseExercises();
            }
        });
        
        uriButton = new JButton("Connect");
        uriButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               //to be implemented
           }
        });
        
        BoxLayout layout = new BoxLayout(myPanel, BoxLayout.Y_AXIS);
        myPanel.setLayout(layout);
        
        localPanel.add (new JLabel ("Local Exercise Directory"));
        localPanel.add(localTextfield);
        localPanel.add(localButton);
        
        myPanel.add(localPanel);
        
        uriPanel.add (new JLabel ("URI Exercise Location"));
        uriPanel.add (uriTextfield);
        uriPanel.add(uriButton);
        
        myPanel.add(uriPanel);
       
        
        uriTextfield.setText("Currently no implemented");
        uriTextfield.setEditable(false);
       
        loadValues();
    }
    
    public String openFileChooser() {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("."));
        fc.setDialogTitle("Choose Exercise Location");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setAcceptAllFileFilterUsed(false);
         
        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
            return fc.getSelectedFile().getAbsolutePath();
        } else {
            return "";
        }
    }

    public JPanel getPanel () { return myPanel; }

    public void saveValues ()
    {
        // Save the preference value in the BlueJ properties file
        bluej.setExtensionPropertyString(PROFILE_LABEL, localTextfield.getText());
    }

    public void loadValues ()
    {
        // Load the property value from the BlueJ proerties file, 
        // default to an empty string
        String exLocation = bluej.getExtensionPropertyString(PROFILE_LABEL, "");
        localTextfield.setText(exLocation);
        if(exLocation != null && !exLocation.isEmpty()) {
            StateManager.setExercisesLocation(exLocation);
            StateManager.parseExercises();
        }
    }
}

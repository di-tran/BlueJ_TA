import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Window;

/**
 * Write a description of class FXMLDescriptionDocumentController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FXMLDescriptionDocumentController implements Initializable
{
    
    //The ui elements to access and manipulate from the javafx display
    @FXML
    private Stage stage;
    @FXML
    private ListView<Exercise> list;
    @FXML
    private Label titleLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label examplesLabel;
    //@FXML
    //private TextArea textArea;
    @FXML
    private Button okButton;
    private Exercise exercise;
    
    
     /**
     * Called whenever a button or menu item is pressed, and calls a function
     * depending on the button/item pressed.
     *
     * @param event the calling event
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource().equals(okButton)){
        	StateManager.disposeDescriptionGUI();
        }
    }
    
    public void initialize(URL url, ResourceBundle rb) {
    	if(StateManager.currentExercise != null){
    		exercise = StateManager.currentExercise;
    		titleLabel.setText(exercise.title);
            descriptionLabel.setText(exercise.description);
            examplesLabel.setText(exercise.examples);
    	}
    }
    
    
}

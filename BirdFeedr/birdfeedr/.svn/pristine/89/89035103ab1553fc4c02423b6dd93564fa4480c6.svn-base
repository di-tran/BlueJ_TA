import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import org.junit.runner.Result;
import javax.swing.JOptionPane;

/**
 * Write a description of class FXMLTestDocumentController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FXMLTestResultsDocumentController implements Initializable
{
    @FXML
    private Button runButton;
    @FXML
    private TextArea textArea;
    @FXML
    private Label titleLabel;
    
    private Exercise exercise;
    
    
    /**
     * Called whenever a button or menu item is pressed, and calls a function
     * depending on the button/item pressed.
     *
     * @param event the calling event
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource().equals(runButton)){
            //JOptionPane.showMessageDialog(null, "Compiles!");
            textArea.clear();
            //check if exercise is null
            if(StateManager.currentExercise != null){
                runTest();
            }
        }
    }
    
    /**
     * 
     */
    public void runTest() {
        Result results = StateManager.runExerciseTests();
        exercise = StateManager.currentExercise;
        textArea.setText(results.getFailures().toString());
    }
    
    /**
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textArea.clear();
        textArea.setEditable(false);
        if(StateManager.currentExercise != null){
            titleLabel.setText(StateManager.currentExercise.title);
            runTest();
            //textArea.setEditable(false);
        }
    }
}

import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;
/**
 * Write a description of class ExecuteAction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExecuteTestsAction extends CodingActions
{
    /**
     * 
     */
    public ExecuteTestsAction(String menuName) {
        super(menuName);
    }
    
    /**
     * 
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if(StateManager.currentExercise == null) {
            StateManager.showErrorMessage("No Exercise Selected" +
                                            "\nGo to Tools->CodingBat to pick exercise");
        }
        else {
            StateManager.createTestResultsGUI();
        }
    }
}

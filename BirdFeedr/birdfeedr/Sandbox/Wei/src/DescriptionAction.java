import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;

/**
 * Write a description of class DescriptionAction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DescriptionAction extends CodingActions
{
    /**
     * 
     */
    public DescriptionAction(String menuName) {
        super(menuName);
    }
    
    /**
     * 
     */
    @Override
    public void actionPerformed(ActionEvent event) {
            StateManager.createDescriptionGUI();
    }
}

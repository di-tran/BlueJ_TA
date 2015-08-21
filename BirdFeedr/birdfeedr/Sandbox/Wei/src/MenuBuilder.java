import javax.swing.JMenu;
import javax.swing.JMenuItem;
import bluej.extensions.BPackage;
import bluej.extensions.BClass;
import bluej.extensions.MenuGenerator;

/**
 * Creates Menu items for this BlueJ extension
 * 
 * @author Miguel Roman-Roman
 * @author Wei Huang
 * @author Di Tran
 * @author Josh Gillham
 * @author Nathan Witt
 * @author Thomas Macari
 */
public class MenuBuilder extends MenuGenerator {

    /**
     * Adds extension menu items to the BlueJ tools menu
     * 
     * @param bPackage  BlueJ package
     * @return     the menu item to be added to BlueJ tools menu
     */
    @Override
    public JMenuItem getToolsMenuItem(BPackage bPackage){
        JMenu jM = new JMenu("Coding Practice");
        
        try{
            jM.add(new JMenuItem(new CodingExercisesAction("Coding Practice")));
        } catch(Exception e){
            e.printStackTrace();
        }
        return jM;
    }
    
    /**
     * 
     */
    @Override
    public JMenuItem getClassMenuItem(BClass bClass) {
        if(!bClass.getName().equals("Exercise")){
            return null;
        }
        
        JMenu jm = new JMenu("Coding Practice");
        
        try {
            jm.add(new JMenuItem(new DescriptionAction("Exercise Description")));
            jm.add(new JMenuItem(new ExecuteTestsAction("Execute")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return jm;
    }
}

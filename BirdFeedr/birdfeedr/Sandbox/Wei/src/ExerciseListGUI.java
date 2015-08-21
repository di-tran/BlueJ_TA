import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * The Graphical User Interface for the BlueJ Bat extension
 * 
 * @author Miguel Roman-Roman
 * @author Wei Huang
 * @author Di Tran
 * @author Josh Gillham
 * @author Nathan Witt
 * @author Thomas Macari
 */
public class ExerciseListGUI extends JFrame{
    
    private JFXPanel panel;
    
    /**
     * Creates the GUI for the extension
     */
    public ExerciseListGUI() {
        this.setTitle("GUI");
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setSize(605, 530);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLocation(100, 100);
        this.createGUI();
    }
    
    /**
     * Used to create a JavaFX panel
     */
    private void createGUI() {
        panel = new JFXPanel();
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                panel.setScene(createScene());
            }
        });
    }
    
    /**
     * Creates a JavaFX scene
     * 
     * @return the JavaFX scene
     */
    private Scene createScene() {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            FXMLExerciseDocumentController controller = new FXMLExerciseDocumentController();
            loader.setController(controller);
            loader.setClassLoader(getClass().getClassLoader());
            root =  (Parent)loader.load();
            System.out.println(loader.getController().toString());
            System.out.println(loader.getLocation());
            return new Scene(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Scene(root);
    }
    
    /**
     * Packs the GUI and sets it visible
     */
    public void packGUI() {
        this.getContentPane().add(panel);
        this.add(panel);
        //this.pack();
        this.setVisible(true);
    }
    
    public static void main(String[]args){
    	SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ExerciseListGUI gui = new ExerciseListGUI();
                gui.packGUI();
            }
        });
    }
}

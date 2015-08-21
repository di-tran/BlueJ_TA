import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;

/**
 *
 * @author tmacari
 */
public class XMLReader {
    private JAXBContext context;
    private Unmarshaller um;
    private Exercise ex;
    
    public XMLReader(){
        
    }
    
    public Exercise readExercise(InputStream is){
        ex = null;
        try {
            context=JAXBContext.newInstance(Exercise.class);
            um=context.createUnmarshaller();
            ex=(Exercise) um.unmarshal(new InputStreamReader( is ) );
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        return ex;
    }
    
    public Exercise readExercise(String filePath)  {
       try {
           return readExercise( new FileInputStream( filePath ) );
       }
       catch ( FileNotFoundException  e )
       {
           e.printStackTrace();
           return null;
       }
    }
    
    
    public static void main(String[] args) {
        XMLReader reader=new XMLReader();
        Exercise e=reader.readExercise("/Users/tmacari/NetBeansProjects/XMLReader/src/xmlreader/Exercise.xml");
        e.printExercise();
    }
    
}

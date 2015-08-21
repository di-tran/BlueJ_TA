/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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
    
    public Exercise readExercise(String filePath){
        ex=null;
        
        try {
            File file=new File(filePath);
            context=JAXBContext.newInstance(Exercise.class);
            um=context.createUnmarshaller();
            ex=(Exercise) um.unmarshal(file);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        
        
        return ex;
    }
    
    
    public static void main(String[] args) {
        XMLReader reader=new XMLReader();
        Exercise e=reader.readExercise("/Users/tmacari/NetBeansProjects/XMLReader/src/xmlreader/Exercise.xml");
        e.printExercise();
    }
    
}

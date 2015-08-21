import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * 
 */
public class XMLScanner {
	private ArrayList<String> strList;
	private String fileContents;
	private Path p;
	private Exercise ex;
	
	/**
	 * 
	 */
	public XMLScanner(){
		
	}
	
	/**
	 * 
	 */
	public Exercise getContents(Path p){
		this.p=p;
		ex=new Exercise();
		fileContents="";
		strList=new ArrayList<String>();
		readFile();	
		
		return ex;
	}
	
	/**
	 * 
	 */
	private void readFile(){	
		try{		
			for(String line:Files.readAllLines(p, StandardCharsets.UTF_8)){
				strList.add(line);
			}
			
			for(int x=0; x<strList.size(); x++){
				fileContents=fileContents.concat(strList.get(x).trim());
			}
			
			getContents();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	private void getContents(){	
		String[] temp=new String[7];
		
		temp[0]=fileContents.substring(fileContents.indexOf("<title>")+7, fileContents.indexOf("</title>"));
		temp[1]=fileContents.substring(fileContents.indexOf("<description>")+13, fileContents.indexOf("</description>"));
		temp[2]=fileContents.substring(fileContents.indexOf("<example1>")+10, fileContents.indexOf("</example1>"));
		temp[3]=fileContents.substring(fileContents.indexOf("<example2>")+10, fileContents.indexOf("</example2>"));
		temp[4]=fileContents.substring(fileContents.indexOf("<example3>")+10, fileContents.indexOf("</example3>"));
		temp[5]=fileContents.substring(fileContents.indexOf("<hint>")+6, fileContents.indexOf("</hint>"));
		temp[6]=fileContents.substring(fileContents.indexOf("<sampleAnswer>")+14, fileContents.indexOf("</sampleAnswer>"));
		
		String[] examples=new String[]{temp[2], temp[3], temp[4]};
		
		//ex.setValues(temp[0], temp[1], temp[5], temp[6], examples);
	}
}
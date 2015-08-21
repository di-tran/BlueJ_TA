import java.io.File;
import java.net.InetAddress;
import java.net.ServerSocket;
import javax.swing.*;
import java.awt.Frame;

class ExecuteThread extends Thread {
   
    private static String javaPath = System.getProperty("java.home") + "/bin/java";
    private static String junitPath;
    private static String extPath;
    private static String testPath;
    private static String testName;

    private final String userClassName;//ignored
    private final File execDir;

    private SocketThread socketThread;
    
    ExecuteThread(File eDir, String userName){
        execDir = eDir;
        userClassName = userName; 
    }
    
    public static void setJunitPath(String path){
        junitPath = path;
    }
    
    public static void setExtPath(String path){
        extPath = path;
    }
    
    public static void setTestPath(File testFile){
        try{
            String[] temp = testFile.getName().split("\\.");
            if(temp[1].equals("class")){
                testPath = testFile.getParent();
                testName = temp[0];
            }
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
        System.out.println(testPath + " : " + testName);
    }
    
    public static boolean hasTestLoaded(){
        return testPath != null;
    }
    
    @Override
    public void run(){
        try{
            String results = runTest();
            JOptionPane.showMessageDialog(null, results);
        }catch(Exception e){
            System.err.println("Runner Err: " + e);
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public Object[] runTest() throws Exception {
        if(socketThread != null && socketThread.isAlive()){
            return "Still Running Test";
        }else if(testPath == null){
            return "Test class unspecified";
        }
        //
        ServerSocket serverSocket = new ServerSocket(0, 0, InetAddress.getByName(null));
        
        String fullCp = "\"" + testPath + ";.;" + junitPath + ";"+ extPath +"\"";
        ProcessBuilder bldr = new ProcessBuilder(
                javaPath, "-cp", fullCp,
                "TestRunner",  serverSocket.getLocalPort() + "", testName, userClassName);  
        //File errs = new File("errs.txt");
        //errs.createNewFile();
        //bldr.redirectError(errs);
        bldr.directory(execDir);
        
        Process proc = bldr.start();
       
        socketThread = new SocketThread(serverSocket.accept()); 
        serverSocket.close();
        socketThread.start();
        
        proc.waitFor();
        
        Object rawData = decrypt(socketThread.readInAll());
        return rawData;
    }
    
    //
    public Object[] decrypt(Sring rawData){
        return null;
    }
    
    public void stopTest(){
        socketThread.close();
    }
}

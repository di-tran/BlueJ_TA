import java.io.File;
import java.net.InetAddress;
import java.net.ServerSocket;
import javax.swing.*;
import java.awt.Frame;

public class JUnitRunner {
   
    private final String javaPath = System.getProperty("java.home") + "/bin/java";//java.exe loc
    private final String junitPath;//junit.jar
    private final String extPath;//our extension install dir
    
    private RunnerThread runThread;
    
    public JUnitRunner(String junitPath, String extPath){
        this.junitPath = junitPath;
        this.extPath = extPath;
    }
    
    public boolean isRunning(){
        return runThread != null && runThread.isAlive();
    }
    
     public void stopTest(){
         if(isRunning()){
             runThread.stopTest();
         }
    }
    
    public ResultHolder runTest(File userProjDir, File junitClassPath) throws Exception {
        ServerSocket serverSocket = new ServerSocket(0, 0, InetAddress.getByName(null));
        String testName = junitClassPath.getName().split("\\.")[0];
        String fullCp = "\"" + junitClassPath.getParent() + ";.;" + junitPath + ";"+ extPath +"\"";
        ProcessBuilder bldr = new ProcessBuilder(
                javaPath, "-cp", fullCp,
                "TestRunner",  serverSocket.getLocalPort() + "", testName);
        bldr.directory(userProjDir);
       
        ResultHolder holder = new ResultHolder();
        
        runThread = new RunnerThread(holder, serverSocket, bldr); 
        runThread.start();
        
        return holder;
    }
}

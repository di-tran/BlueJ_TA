package Extension.BackEnd.Runner;

import bluej.extensions.BlueJ;
import java.io.File;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * Executes junit class files with user files.
 *
 * @author Nathan
 */
public class JUnitRunner {

    /**
     * the location of the java jdk
     */
    private final static String JAVAPATH = System.getProperty("java.home") + "/bin/java";

    /**
     * The name and location within the extension jar of the TestRunner.class
     */
    private final static String RUNNERNAME = "Extension.BackEnd.Runner.TestRunner";
    
    /**
     * the location of the junit.jar lib
     */
    private final String junitPath;

    /**
     * the location of the installed extension
     */
    private final String extPath;

    /**
     * The underlying thread to manage the execution of test.
     */
    private RunnerThread runThread;

    /**
     * Creates a new JUnitrunner
     *
     * @param bluej the parent bluej that this runner is associated with.
     */
    public JUnitRunner(BlueJ bluej) {
        this(bluej.getSystemLibDir().getAbsolutePath() + "/*", ExtPathGen.genExtPaths(bluej, "*"));
    }

    /**
     * Creates a new JUnitrunner
     *
     * @param junitPath the absolute path to the junit.jar file
     * @param extPath the absolute path(s) to the extension.jar file
     */
    public JUnitRunner(String junitPath, String extPath) {
        this.junitPath = junitPath;
        this.extPath = extPath;
    }

    /**
     * Checks if there is a junit test currently being run by this runner.
     *
     * @return true if a test is being executed.
     */
    public boolean isRunning() {
        return runThread != null && runThread.isAlive();
    }

    /**
     * Stops the currently running junit test. Any result holders waiting for
     * results will be populated with cancel messages.
     */
    public void stopTest() {
        if (isRunning()) {
            runThread.stopTest();
        }
    }

    /**
     * Executes the junit test within the file specified by junitClassPath with
     * the files located in the userProjDir.
     *
     * @param userProjDir the files to execute the junit test with
     * @param junitClassPath the junit class to execute
     * @return a holder class that will be populated with the test results on
     * completion of the test.
     * @throws Exception if the test could not be run.
     */
    public ResultHolder runTest(File userProjDir, File junitClassPath) throws Exception {
        ServerSocket serverSocket = new ServerSocket(0, 0, InetAddress.getByName(null));
        String testName = junitClassPath.getName().split("\\.")[0];
        char cpchar = File.pathSeparatorChar;
        String fullCp = "\""
                //+ junitClassPath.getParent() + cpchar
                + "." + cpchar
                + junitPath + cpchar
                + extPath
                + "\"";
        ProcessBuilder bldr = new ProcessBuilder(
                JAVAPATH, "-cp", fullCp, RUNNERNAME, 
                serverSocket.getLocalPort() + "",
                junitClassPath.getParent(), 
                testName);
        bldr.directory(userProjDir);

        ResultHolder holder = new ResultHolder();

        runThread = new RunnerThread(holder, serverSocket, bldr);
        runThread.start();

        return holder;
    }
}

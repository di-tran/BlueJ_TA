import Helpers.GitHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.eclipse.jgit.api.InitCommand;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class for the Git Extension application
 *
 *@author Miguel Roman-Roman
 *@author Josh Gillham
 *@author Thomas Macari
 */
public class TestGitHelper_LocalTwoRepos
{
    /**
     * Default constructor for test class test
     */
    public TestGitHelper_LocalTwoRepos()
    {
    }
    
    // Test repository folders.
    File firstProjectFolder = null;
    File secondProjectFolder = null;
    
    // Constants.
    static final String firstTestJavaFile = "test.java"; // Arbitrary.
    static final String username = ""; // Intentionally blank.
    static final String password = ""; // Intentionally blank.
            
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     * 
     * Creates two repositories. The 2nd is the clone of the first.
     */
    @Before
    public void setUp()
    {
        // Create temp directory.
        try {
            firstProjectFolder = File.createTempFile( "testGitInit", ".dir" );
            secondProjectFolder = File.createTempFile( "testGitInit", ".dir" );
        }
        catch( IOException e )
        {
            e.printStackTrace();
            fail(e.getMessage());
        }
        
        firstProjectFolder.delete();
        firstProjectFolder.mkdirs();
        System.out.println("tempProjectFolder: "
                + firstProjectFolder.getAbsolutePath() );
        firstProjectFolder.deleteOnExit();
        
        secondProjectFolder.delete();
            
        // Folder should not exist.
        assertFalse(secondProjectFolder.exists() );
        
        // Initialize repository.
        try {
            new InitCommand().setDirectory(firstProjectFolder )
                    .setBare(false).call();
            new File( firstProjectFolder, firstTestJavaFile).createNewFile();
            GitHelper.runCommitCommand(firstProjectFolder,
                    "Commit firstTestJavaFile." );
        }
        catch ( Exception e ) 
        {
            AssertionError er =
                    new AssertionError( "Exception thrown: " + e.getMessage() );
            er.setStackTrace(e.getStackTrace());
            throw er;
        }
        assertTrue(firstProjectFolder.exists() );
        assertTrue(new File( firstProjectFolder, firstTestJavaFile).exists() );
        try {
            
            // Clone first repo into 2nd folder.
            GitHelper.runCloneCommand(secondProjectFolder, 
                                        firstProjectFolder.getAbsolutePath(),  
                                         username, password);
        }
        catch ( Exception e ) 
        {
            AssertionError er =
                    new AssertionError( "Exception thrown: " + e.getMessage() );
            er.setStackTrace(e.getStackTrace());
            throw er;
        }
        
        assertTrue(secondProjectFolder.exists() );
        assertTrue(new File( secondProjectFolder, firstTestJavaFile).exists() );
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        // XXX Needs to be replaced with a delete recursive function.
        firstProjectFolder.delete();
    }
    
    // XXX create clone test for remote host.
    
    /**
     * Makes sure the runCloneCommand works locally.
     */
    @Test
    public void testGitCloneLocally()
    {
        // Left blank intentionally.
        // This tests that the setup works.
    }
    
    // XXX create a git push test for a remote host.
    
    /**
     * Makes sure the runPushCommand works locally.
     */
    @Test
    public void testGitPushLocally()
    {
        final String testJavaFile2 = "testPush.java"; // Arbitrary.
        try
        {
            // Create and commit a second Java file.
            new File( firstProjectFolder, testJavaFile2 ).createNewFile();
            GitHelper.runCommitCommand(firstProjectFolder,
                    "Commiting testJavaFile2." );
            
            // The 2nd file should not exist.
            assertFalse(new File( secondProjectFolder, testJavaFile2 ).exists() );
            
            // Try pushing to the 2nd repository.
            GitHelper.runPushCommand(firstProjectFolder, 
                               secondProjectFolder.getAbsolutePath(),
                               username,
                               password);
            
            // After pushing the 2nd file should exist.
            assertTrue(new File( secondProjectFolder, testJavaFile2 ).exists() );
        } 
        catch ( Exception e ) 
        {
            AssertionError er =
                    new AssertionError( "Exception thrown: " + e.getMessage() );
            er.setStackTrace(e.getStackTrace());
            throw er;
        }
    }
    
    // XXX create a git pull test for a remote host.
    
    /**
     * Makes sure the runPullCommand works locally.
     */
    @Test
    public void testGitPullLocally()
    {
        final String testJavaFile2 = "testPull2.java"; // Arbitrary.
        try
        {
            // Create and commit a second Java file.
            new File( firstProjectFolder, testJavaFile2 ).createNewFile();
            GitHelper.runCommitCommand(firstProjectFolder,
                    "Commiting testJavaFile2." );
            
            // The 2nd file should not exist.
            assertFalse(new File( secondProjectFolder, testJavaFile2 ).exists() );
            
            // Try pulling from the 1st repository.
            GitHelper.runPullCommand(secondProjectFolder, 
                               "origin" );
            
            // After pushing the 2nd file should exist.
            assertTrue(new File( secondProjectFolder, testJavaFile2 ).exists() );
        } 
        catch ( Exception e ) 
        {
            AssertionError er =
                    new AssertionError( "Exception thrown: " + e.getMessage() );
            er.setStackTrace(e.getStackTrace());
            throw er;
        }
    }
    
    /**
     * Makes sure the runPullCommand does not loose code when a merge occurs.
     */
    @Test
    public void testGitPullLocally_Merge()
    {
        final String testJavaFile2 = "testPull2.java"; // Arbitrary.
        try
        {
            // Create and commit a second Java file.
            final File origFile = new File( firstProjectFolder, testJavaFile2 );
            origFile.createNewFile();
            GitHelper.runCommitCommand(firstProjectFolder,
                    "Commiting testJavaFile2." );
            
            // The 2nd file should not exist.
            assertFalse(new File( secondProjectFolder, testJavaFile2 ).exists() );
            
            // Try pulling from the 1st repository.
            GitHelper.runPullCommand(secondProjectFolder, 
                               "origin" );
            
            // After pushing the 2nd file should exist.
            final File dest = new File( secondProjectFolder, testJavaFile2 );
            assertTrue(dest.exists() );
            
            // Add a line to the source repository and commit.
            new FileWriter( origFile ).append("Source line"
                    + System.lineSeparator() ).close();
            GitHelper.runCommitCommand(firstProjectFolder,
                    "Added the source line." );
            
            // Add a line to the destination repository and commit.
            new FileWriter( dest ).append("Dest line"
                    + System.lineSeparator() ).close();
            GitHelper.runCommitCommand(secondProjectFolder,
                    "Added the dest line." );
            
            // Run the pull command again.
            GitHelper.runPullCommand(secondProjectFolder, 
                               "origin" );
            
            // Make sure both lines are there and the pull did not create a
            //  merge conflict.
            Scanner sc =
                    new Scanner( new FileInputStream( dest ) );
            int linesFound = 0;
            boolean mergeConflictFound = false;
            while (sc.hasNextLine() )
            {
                String match = sc.findInLine("Dest line|Source line");
                if ( match != null && !match.isEmpty() )
                {
                    ++linesFound;
                }
                match = sc.findInLine("===");
                if ( match != null && !match.isEmpty() )
                {
                    mergeConflictFound = true;
                }
                sc.nextLine();
            }
            assertEquals( 2, linesFound );
            assertFalse( mergeConflictFound );
            
        } 
        catch ( Exception e ) 
        {
            AssertionError er =
                    new AssertionError( "Exception thrown: " + e.getMessage() );
            er.setStackTrace(e.getStackTrace());
            throw er;
        }
    }
}

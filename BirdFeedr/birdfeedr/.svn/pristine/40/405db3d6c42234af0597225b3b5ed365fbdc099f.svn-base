import Helpers.GitHelper;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
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
public class TestGitHelper_LocalBasics
{
    /**
     * Default constructor for test class test
     */
    public TestGitHelper_LocalBasics()
    {
    }
    static File tempProjectFolder = null;
            
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        try {
            tempProjectFolder = File.createTempFile( "testGitInit", ".dir" );
        }
        catch( IOException e )
        {
            e.printStackTrace();
            fail(e.getMessage());
        }
        tempProjectFolder.delete();
        tempProjectFolder.mkdirs();
        System.out.println( "tempProjectFolder: "
                + tempProjectFolder.getAbsolutePath() );
        tempProjectFolder.deleteOnExit();
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
        tempProjectFolder.delete();
    }
    
    /**
     * Makes sure a .git dir was created.
     * Makes sure that runInitCommand works
     */
    @Test
    public void testGitInit() {
        final File gitDir = new File( tempProjectFolder, ".git" );
        assertFalse( gitDir.exists() );
        try {
            GitHelper.runInitCommand( tempProjectFolder );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            fail(e.getMessage());
        }
        assertTrue( gitDir.exists() );
    }
    static public Set<String> getUncommittedJavaFiels( Git repo )
            throws GitAPIException
    {
        Status st = repo.status().call();
        Set<String> allUncommitted = st.getUncommittedChanges();
        allUncommitted.addAll( st.getUntracked() );
        Set<String> allUncommittedOnlyJava = new HashSet<String>();
        for ( String item : allUncommitted )
        {
            if ( item.matches("(?i).*\\.java"))
            {
                allUncommittedOnlyJava.add(item);
                System.out.println("allUncommittedOnlyJava item:" + item);
            }
        }
        return allUncommittedOnlyJava;
    }
    
    /**
     * Makes sure all the java files are added.
     * Makes sure runCommitCommand works
     */
    @Test
    public void testGitCommit()
    {
        Git repo;
        try {
            repo = new InitCommand().setDirectory( tempProjectFolder )
                    .setBare(false).call();
            final File temp = new File( tempProjectFolder, "test.java" );
            temp.createNewFile();
            Set<String> allUncommittedOnlyJava = getUncommittedJavaFiels(repo);
            assertFalse( allUncommittedOnlyJava.isEmpty() );
            GitHelper.runCommitCommand( tempProjectFolder, "Bark!" );
            allUncommittedOnlyJava = getUncommittedJavaFiels(repo);
            assertTrue( allUncommittedOnlyJava.isEmpty() );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}

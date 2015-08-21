
import org.eclipse.jgit.api.*;
import java.io.File;

public class GitDemo
{
    public static void main( String[] args )
    {
        System.out.println( "Welcome to GitDemo" );
        final String BASE = "./results";
        final String TEST_GIT_DIR = BASE + "/git-test-dir";
        final String TEST_GIT_MANAGEMENT_DIR = TEST_GIT_DIR + "/.git";
        try
        {
            System.out.printf( "Creating %s folder.\n", TEST_GIT_DIR );
            // Makes the results folder.
            new File( BASE ).mkdirs();
            System.out.println( "Trying to initialize .git repository." );
            // Initializes the repository.
            new InitCommand().setDirectory( new File( TEST_GIT_DIR ) ).call();
            System.out.printf( "The folder \"%s\" exists: %s\n",
                TEST_GIT_MANAGEMENT_DIR
                ,
                new File( TEST_GIT_MANAGEMENT_DIR ).exists()
            );
            if ( new File( TEST_GIT_MANAGEMENT_DIR ).exists() )
                System.out.println( "Success!" );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
}

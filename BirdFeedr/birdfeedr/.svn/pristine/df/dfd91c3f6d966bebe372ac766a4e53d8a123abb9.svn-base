package Helpers;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

/**
 * GitHelper is used to operate JGit commands and allow the use of Git
 * with Java.
 * Holds various static methods to ease Git commands.
 * 
 *@author Miguel Roman-Roman
 *@author Josh Gillham
 *@author Thomas Macari
 */
public class GitHelper {
    
    /**
     * Used to add files to a Git repository
     * 
     * @param repo  the repository to add files to
     * @param strings   the directory list of files to add
     * 
     * @throws GitAPIException Thrown when a call to JGit fails.
     */
    public static void addFilesToGit( Git repo, Iterable<String> strings )
                throws GitAPIException
    {
        AddCommand ac = new AddCommand( repo.getRepository() );
        for ( String item : strings )
        {
            ac.addFilepattern(item).call();
        }
    }
     
    /**
     * Used to get a list of all files not currently being tracked by
     * a Git repository
     * 
     * @param repo  the Git repository
     * @return  a Set of string directories of all files not being tracked by
     *  the Git repository. It will be empty if all files are tracked.
     * 
     * @throws GitAPIException Thrown when a call to JGit fails.
     */
    public static Set<String> getUntrackedJavaFiles( Git repo )
            throws GitAPIException
    {
        Set<String> untrackedJavaFiles = new HashSet<String>();
        try {
            Status st = repo.status().call();
            for ( String item : st.getUntracked() )
            {
                if ( item.matches("(?i).*\\.java"))
                {
                    untrackedJavaFiles.add(item);
                    System.out.println("untrackedJavaFiles item:" + item);
                }
            }
        }
        // This happens when the repository has not been initialized.
        // In that case, we assume there are no untracked files.
        catch ( org.eclipse.jgit.errors.NoWorkTreeException e ){}
        return untrackedJavaFiles;
    }
     
    /**
     * Used to run the Git Init command.
     * Initializes a Git repository in the given location
     * 
     * @param projectFolder     the location to initialize the Git repository
     * 
     * @throws GitAPIException Thrown when a call to JGit fails.
     */
    public static void runInitCommand(File projectFolder) throws GitAPIException {
        new InitCommand().setDirectory(projectFolder).setBare(false).call();
    }

    /**
     * Used to run the Git Push command 
     * Pushes committed files to a remote Git repository
     *
     * @param projectFolder the location of the Git repository
     * @param remote    the URL of the remote location to push to
     * @param username  the username credentials for this user
     * @param password  the password credential for this user
     * 
     * @throws GitAPIException Thrown when a call to JGit fails.
     * @throws IOException Thrown when the projectFolder cannot be accessed.
     */
    public static void runPushCommand(File projectFolder, 
                                        String remote, 
                                        String username, 
                                        String password) 
            throws GitAPIException, IOException {
        Git repo = new Git(new FileRepository(new File(projectFolder, ".git")));
        RefSpec spec = new RefSpec("refs/heads/master:refs/heads/x");
        repo.push().setCredentialsProvider(
                new UsernamePasswordCredentialsProvider(username, password))
                .setRemote(remote).setRefSpecs(spec).call();
    }

    /**
     * Used to run the Git Commit command
     * Commits all files tracked files and adds untracked files to the Git
     * repository
     * 
     * @param projectFolder the location of the git repository
     * @param msg           the message of the commit
     * 
     * @throws GitAPIException Thrown when a call to JGit fails.
     * @throws IOException Thrown when the projectFolder cannot be accessed.
     */
    public static void runCommitCommand(File projectFolder, 
                                        String msg) 
                    throws GitAPIException, IOException
    {
        Git repo = new Git(new FileRepository(new File(projectFolder, ".git")));
        addFilesToGit(repo, getUntrackedJavaFiles(repo));
        repo.commit().setAll(true).setMessage(msg).call();
    }

    /**
     * Used to run the Git Clone command
     * Clones a remote repository into a given location.
     * 
     * @param projectFolder the location to clone the Git repository
     * @param remote        the URL of the remote Git repository
     * @param username      the username credential (might not be needed)
     * @param password      the password credential (might not be needed)
     * 
     * @throws GitAPIException Thrown when a call to JGit fails.
     */
    public static void runCloneCommand(File projectFolder, 
                                            String remote, 
                                            String username,
                                            String password) 
                    throws GitAPIException 
    {
        Git.cloneRepository().setBare(false).setCloneAllBranches(true)
                .setDirectory(projectFolder).setURI(remote)
                .setCredentialsProvider(
                        new UsernamePasswordCredentialsProvider(username,
                                                                password))
                .call();
    }
    
    public static void runPullCommand(File projectFolder, 
                                            String remote) 
                    throws GitAPIException, IOException 
    {
        Git repo = new Git(new FileRepository(new File(projectFolder, ".git")));
        repo.pull().setRebase(true).setRemote(remote).call();
    }
}

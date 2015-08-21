package Exceptions;

 public class NoCurrentProjectException
        extends GitExtensionException {
    public NoCurrentProjectException( Throwable cause )
    {
        super( "", cause );
    }
 }

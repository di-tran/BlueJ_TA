import java.io.File;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.JavaCompiler.CompilationTask;
import java.io.PrintWriter;

public abstract class Compiler {
    
    public static File compile(String[] classPaths, String javaPath) throws Exception {
        File javaFile = new File(javaPath);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        ByteArrayOutputStream errOut = new ByteArrayOutputStream();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        String cps = javaFile.getParent();
        for(String cp : classPaths){
            cps += ";" + cp;
        }
        ArrayList<String> optionList = 
            new ArrayList<String>(Arrays.asList("-cp", cps));
        Iterable<? extends JavaFileObject> classes =
            fileManager.getJavaFileObjectsFromFiles(Arrays.asList(javaFile)); 
        CompilationTask task = compiler.getTask(new PrintWriter(errOut), null, null, optionList, null, classes);
        if (task.call()) {
            return new File(javaPath.split("\\.")[0] + ".class"); 
        }
        throw new Exception(new String(errOut.toByteArray(), Charset.defaultCharset()));
    }
}

package TempStuff;

/**
 * Place holder class to be removed
 */
public abstract class RawExerciseData {
    
    public static final String projName = "TestExercise";
    
    public static final String userName = "ExA";
    public static final  String userTemplateCode
            = "public class ExA {\n"
            + "\n"
            + "  public int add(int a, int b){\n"
            + "      return a + b;\n"
            + "  }\n"
            + "}\n";
            
    //could be used for method checking (out of scope)
    //or force ExA to extend a template class or not allow to execute a test
    private static final String[] methods = {"public int add(int, int)"};
            
    public static final String testName = "TestExA";
    public static final String testCode 
            = "import org.junit.Test;\n"
            + "import static org.junit.Assert.assertEquals;\n"
            + "\n"
            + "public class TestExA {\n"
            + "  static ExA temp = new ExA();\n"
            + "\n"
            + "  @Test\n"
            + "  public void testA(){\n"
            + "     assertEquals(\"add(1, 2) = 3?\", temp.add(1,2), 3);\n"
            + "  }\n"
            + "}\n";
}

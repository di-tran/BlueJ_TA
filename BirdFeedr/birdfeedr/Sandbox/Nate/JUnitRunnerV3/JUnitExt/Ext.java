import bluej.extensions.BlueJ;
import bluej.extensions.Extension;
import java.net.URL;

public class Ext extends Extension {
    
    @Override
    public void startup(BlueJ bluej) {       
        bluej.setMenuGenerator(new MenuBldr(bluej));
    }
    
    @Override
    public boolean isCompatible() {
        return true;
    }
    
    @Override
    public String getVersion() {
        return "";
    }
    
    @Override
    public String getName() {
        return "";
    }
    
    @Override
    public void terminate() {
        System.out.println(getName() + " terminates");
    }
    
    @Override
    public String getDescription() {
        return getName();
    }
    
    @Override
    public URL getURL() {
        return null;
    }
}

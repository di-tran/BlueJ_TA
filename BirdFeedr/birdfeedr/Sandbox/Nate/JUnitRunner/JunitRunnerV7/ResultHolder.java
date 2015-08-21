
public class ResultHolder {
   
   boolean hasResults = false;
   private String results = "";
   
   public synchronized void setData(String data){
       results = data;
       hasResults = true;
       notifyAll();
   }
   
   public synchronized String getData(){
       return results;
   }
   
   public synchronized void waitForResults(){
       if(hasResults){
           return;
       }
       try{
           wait();
       }catch(Exception e){
       }
   }
}

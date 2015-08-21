
public class Populator {
    
    Object[] rawData;
    Holder holder;
    
    public Holder giveHolder(){
        holder = new Holder();
        rawData = new ExecuteThread().runTest();
        populate(rawData);
        return holder;
    }
    
    private void populate(Object[] rawData){
        //populates holder with data
        
    }
    
    
}

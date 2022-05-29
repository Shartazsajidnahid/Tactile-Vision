import java.util.HashMap;

public class FunctionsForMid {
    private HashMap<String, String> tableMap;
    public FunctionsForMid(){
        tableMap= new HashMap<>();
        tableMap.put("std","student");
        tableMap.put("adm","admin");
        tableMap.put("tea","teacher");
        tableMap.put("stf","staff");
    }
    public String parseID(String unparsedID){
        return new String(unparsedID.substring(4,unparsedID.length()));
    }
    public String parseTable (String unparsedID){
         return tableMap.get(new String(unparsedID.substring(0,3)));
    }
}

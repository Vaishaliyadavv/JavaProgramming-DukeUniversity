
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringA, String stringB){
        int firstOcc = stringB.indexOf(stringA);
        if(firstOcc == -1){
            return false;
        }
        int secondOcc = stringB.indexOf(stringA , firstOcc + stringA.length());
        return secondOcc != -1;
    }
    
    public void testing(){
        System.out.println("Testing twoOccurences method");
            
        String[][] testPairs = {
            {"by", "A story by Abby Long"},
            {"a", "banana"},
            {"atg", "ctgtatgta"},
            {"zoo", "zoology"}
        };
        
        for(String[] pair : testPairs){
            boolean result = twoOccurrences(pair[0], pair[1]);
            System.out.println("Does \"" + pair[0] 
            + "\" appear at least twice in \"" + pair[1] + "\"? " + result);
        }
        
        System.out.println("\nTesting lastPart method:");
        String[][] testPairs2 = {
            {"an", "banana"},
            {"zoo", "forest"},
            {"by", "A story by Abby Long"},
            {"te", "template"},
            {"a", "hello"}
        };
        for(String[] pair : testPairs2){
            String result = lastPart(pair[0], pair[1]);
            System.out.println("The part of the string after \"" + 
            pair[0] + "\" in \"" + pair[1] + "\" is \"" + result + "\"");
            
        }
    }
    
    public String lastPart(String stringA , String stringB){
        int firstOcc = stringB.indexOf(stringA);
        
        if(firstOcc == -1){
            return stringB;
        }
        return stringB.substring(firstOcc + stringA.length());
    }
    
    public static void main(String[] args){
        Part3 part3 = new Part3();
        part3.testing();
    }
}

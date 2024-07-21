
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.ArrayList;
public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> nameCounts;
    
    public CharactersInPlay(){
        names = new ArrayList<String>();
        nameCounts = new ArrayList<Integer>();
    }
    
    public void update(String person){
        int index = names.indexOf(person);
        if(index == -1){
            names.add(person);
            nameCounts.add(1);
        } 
        else{
            int count  = nameCounts.get(index);
            nameCounts.set(index, count + 1);
        }
    }
    
    public void findAllCharacters(){
        names.clear();
        nameCounts.clear();
        FileResource resource = new FileResource();
        for(String line : resource.lines()){
            if(line.contains(".")){
                int index = line.indexOf(".");
                String person = line.substring(0,index).trim();
                update(person);
            }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        for (int i = 0; i < names.size(); i++) {
            int count = nameCounts.get(i);
            if (count >= num1 && count <= num2) {
                System.out.println(names.get(i));
            }
        }
    }
    
    public int findIndexOfMax(){
        int maxIndex = 0;
        for(int i=0;i<nameCounts.size();i++){
            if(nameCounts.get(i) > nameCounts.get(maxIndex)){
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void tester(){
        System.out.println("--------------------------------------");
        findAllCharacters();
        for(int i=0;i<names.size();i++){
            int count = nameCounts.get(i);
            if(count > 1){
                System.out.println(names.get(i) + "\t" + count);
            }
        }
        //int maxIndex = findIndexOfMax();
        //System.out.println("\nCharacters appearing max times : "  + names.get(maxIndex) + " " + nameCounts.get(maxIndex));
        
        System.out.println("between 10 - 15 speaking lines characters : ");
        charactersWithNumParts(10,15);
    }
}

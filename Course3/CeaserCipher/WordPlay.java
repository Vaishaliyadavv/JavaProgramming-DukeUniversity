
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordPlay {
    public boolean isVowel(char ch){
        ch = Character.toLowerCase(ch);
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
    
    public String replaceVowels(String phrase, char ch){
        StringBuilder result = new StringBuilder(phrase);
        for(int i=0 ;i<phrase.length();i++){
            char currentChar = result.charAt(i);
            if(isVowel(currentChar)){
                result.setCharAt(i,ch);
            }
        }
        return result.toString();
    }
    
    public void testReplceVowels(){
        System.out.println(replaceVowels("Hello WOrld" , '*'));
        System.out.println(replaceVowels("JavA ProgrammIng" , '+'));
    }
    
    public String emphasize(String phrase, char ch){
        StringBuilder result = new StringBuilder(phrase);
        for(int i=0 ;i<phrase.length();i++){
            char currentChar = result.charAt(i);
            if(Character.toLowerCase(currentChar) == Character.toLowerCase(ch)){
                if(i%2 == 0){
                    result.setCharAt(i,'*');
                }
                else{
                    result.setCharAt(i,'+');
                }
            }
        }
        return result.toString();
    }
    
    public void testEmphasize(){
        System.out.println(emphasize("dna ctgaaaAgA",'a'));
    }
}

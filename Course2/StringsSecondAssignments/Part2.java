
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringA , String stringB){
        int count = 0;
        int startIndex = 0;
        while(true){
            int index = stringB.indexOf(stringA, startIndex);
            if(index == -1){
                break;
            }
            count++;
            startIndex = index + stringA.length();
        }
        return count;
    }
    
    public void testHowMany(){
        String stringa1 = "GAA";
        String stringb1 = "ATGAACGAATTGAATC";
        System.out.println("howMany(\"GAA\", \"ATGAACGAATTGAATC\") = " 
                            + howMany(stringa1, stringb1)); // Expected: 3

        String stringa2 = "AA";
        String stringb2 = "ATAAAA";
        System.out.println("howMany(\"AA\", \"ATAAAA\") = " 
                            + howMany(stringa2, stringb2)); // Expected: 2

        String stringa3 = "ATG";
        String stringb3 = "ATGATGATG";
        System.out.println("howMany(\"ATG\", \"ATGATGATG\") = " 
                            + howMany(stringa3, stringb3)); // Expected: 3

        String stringa4 = "A";
        String stringb4 = "AAAAA";
        System.out.println("howMany(\"A\", \"AAAAA\") = " 
                            + howMany(stringa4, stringb4)); // Expected: 5

        String stringa5 = "AAA";
        String stringb5 = "AAAAAA";
        System.out.println("howMany(\"AAA\", \"AAAAAA\") = "   
                            + howMany(stringa5, stringb5)); // Expected: 2
    }
    
    public static void main(String[] args){
        Part2 part2 = new Part2();
        part2.testHowMany();
    }
}

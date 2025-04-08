import java.util.*;


public class uniqueCharacters {

    private static Map<String, Integer> cache = new HashMap<>();

    public static int countUniqueChar(String str){
       if(cache.containsKey(str)){
        System.out.println(str);
        return cache.get(str);
       }

       Set<Character> uniqueChar = new HashSet<>();
       char[] input = str.toCharArray();
       for(char c: input){
        uniqueChar.add(c);
       }

       int count = uniqueChar.size();
       cache.put(str, count);

       return count;
    }
    public static void main(String[] args) {
        
        System.out.println(countUniqueChar("Hello"));
        System.out.println(countUniqueChar("WORLD"));
        System.out.println(countUniqueChar("WORLD"));
        
    }
}

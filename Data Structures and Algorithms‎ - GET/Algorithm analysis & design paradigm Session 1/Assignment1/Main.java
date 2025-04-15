import java.util.*;

public class Main {
    public static void main(String[] args) {
        String json = "[{\"key\": \"apple\", \"value\": 10}, {\"key\": \"banana\", \"value\": 5}, {\"key\": \"cherry\", \"value\": 20}]";

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>();

        // Basic manual parsing (not recommended for big/complex JSON)
        json = json.replaceAll("[\\[\\]{}\"]", ""); // Remove brackets and quotes
        String[] objects = json.split("},\\s*\\{"); // Split each JSON object

        for (String obj : objects) {
            String[] parts = obj.split(",");
            String key = null;
            Integer value = null;

            for (String part : parts) {
                String[] kv = part.trim().split(":");
                if (kv[0].trim().equals("key")) {
                    key = kv[1].trim();
                } else if (kv[0].trim().equals("value")) {
                    value = Integer.parseInt(kv[1].trim());
                }
            }

            if (key != null && value != null) {
                entryList.add(new AbstractMap.SimpleEntry<>(key, value));
            }
        }

        // Now use your BSTDictionary
        BSTDictionary<String, Integer> dict = new BSTDictionary<>(entryList);

        dict.add("date", 15);
        dict.delete("banana");

        System.out.println("All sorted: " + dict.getSortedList());
        System.out.println("Range [apple, cherry]: " + dict.getRange("apple", "date"));
    }
}

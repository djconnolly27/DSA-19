import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FrequencyPrint {

    static String frequencyPrint(String s) {
        String[] splitArray = s.split("\\s+");
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < splitArray.length; i++) {
            map.put(splitArray[i], map.getOrDefault(splitArray[i], 0)+1);
        }
        HashMap<Integer, ArrayList<String>> map2 = new HashMap<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            ArrayList<String> new_entry =  map2.getOrDefault(entry.getValue(), new ArrayList<String>());
            new_entry.add(entry.getKey());
            map2.put(entry.getValue(), new_entry);
        }
        StringBuilder str = new StringBuilder();
        int numAdded = 0;
        for (int i = 1; numAdded < splitArray.length; i++) {
            if (map2.get(i) != null) {
                for (int k = 0; k < map2.get(i).size(); k++) {
                    for (int j = 0; j < i; j++) {
                        if (numAdded == 0) {
                            str.append(map2.get(i).get(k));
                            numAdded++;
                        } else {
                            str.append(" " + map2.get(i).get(k));
                            numAdded++;
                        }
                    }
                }

            }
        }
        return str.toString();
    }

}

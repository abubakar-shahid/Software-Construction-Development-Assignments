import java.util.*;

public class Q2_FirstNonRepeatingStream {

    private Map<Character, Integer> inputStream;
    private List<Character> inputs;

    Q2_FirstNonRepeatingStream() {
        this.inputStream = new LinkedHashMap<Character, Integer>();
        this.inputs = new ArrayList<>();
    }

    public void addChar(char ch) {
        this.inputs.add(ch);
        if (this.inputStream.containsKey(ch)) {
            this.inputStream.remove(ch);
        } else {
            int x = ch;
            this.inputStream.put(ch, x);
        }
    }

    public char getFirstNonRepeating() {
        System.out.println("All input Characters: " + this.inputs);
        if (this.inputStream.isEmpty()) {
            return '-';
        }
        System.out.println("The Set of Non-Repeating Elements is : " + this.inputStream.keySet());
        Iterator<Map.Entry<Character, Integer>> iterator = this.inputStream.entrySet().iterator();
        return (iterator.next().getKey());
    }

}

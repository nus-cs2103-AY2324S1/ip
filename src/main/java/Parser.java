import java.util.Dictionary;
import java.util.Hashtable;

public class Parser {
    String defaultInput;
    Dictionary<String, String> taggedInputs;
    Parser(String input) {
        taggedInputs = new Hashtable<>();
        String[] phrases = input.split("/");
        this.defaultInput = phrases[0];
        for (int i = 1; i < phrases.length; i++) {
            String[] words = phrases[i].split(" ", 2);
            taggedInputs.put(words[0], words[1]);
        }
    }
    public String getDefaultString() {
        return defaultInput;
    }
    public String getTaggedInput(String tag) {
        return taggedInputs.get(tag);
    }
}

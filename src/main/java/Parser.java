import java.util.ArrayList;

public class Parser {

    private final String type;

    public Parser(String type) {
        this.type = type;
    }

    public ArrayList<String> convert(String input) {
        ArrayList<String> texts = new ArrayList<>();
        switch (type) {
        case "E": {
            String desc = input.substring(6, input.indexOf("/from") - 1);
            texts.add(desc);
            String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
            texts.add((from));
            String to = input.substring(input.indexOf("/to") + 4);
            texts.add(to);
            break;
        }
        case "D": {
            String desc = input.substring(9, input.indexOf("/by") - 1);
            texts.add(desc);
            String by = input.substring(input.indexOf("/by") + 4);
            texts.add(by);
            break;
        }
        case "T": {
            String desc = input.substring(input.indexOf("todo") + 5);
            texts.add(desc);
            break;
        }
        }


        return texts;
    }
}

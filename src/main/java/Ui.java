import java.util.List;

/**
 * Represents the Ui of the chatbot.
 */
public class Ui {
    public static String line = "\t____________________________________________________________\n";
    /**
     * Pretty prints a single string.
     * @param s String to be printed.
     */
    public static void print(String s){
        System.out.println(line + String.format("\t%s\n", s) + line);
    }

    /**
     * Pretty prints a list of strings.
     * @param lst List of strings to be printed.
     */
    public static void print(List<String> lst){
        String text = line;
        for(String s: lst) {
            text += String.format("\t%s\n", s);
        }
        text += line;
        System.out.println(text);
    }
}

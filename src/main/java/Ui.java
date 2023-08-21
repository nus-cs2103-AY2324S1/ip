import java.util.Arrays;
import java.util.List;

/**
 * Represents the Ui of the chatbot.
 */
public class Ui {
    private static final String line = "\t____________________________________________________________\n";
    /**
     * Pretty prints a single string.
     * @param s String to be printed.
     */
    public void print(String s){
        System.out.println(line + String.format("\t%s\n", s) + line);
    }

    /**
     * Pretty prints a list of strings.
     * @param lst List of strings to be printed.
     */
    public void print(List<String> lst){
        String text = line;
        for(String s: lst) {
            text += String.format("\t%s\n", s);
        }
        text += line;
        System.out.println(text);
    }
    /**
     * Function to say hi to the user.
     */
    public void sayHi(){
        this.print(Arrays.asList("Hello! I'm Minion!", "What can I do for you?"));
    }

    /**
     * Function to say bye to the user.
     */
    public void sayBye(){
        this.print("Bye. Hope to see you again soon!");
    }
}

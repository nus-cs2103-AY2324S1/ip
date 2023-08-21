import java.util.Arrays;
import java.util.List;

/**
 * Represents the Ui of the chatbot.
 */
public class Ui {
    private static final String line = "\t____________________________________________________________\n";
    /**
     * Pretty prints string(s).
     * @param args string(s) to be printed.
     */
    public void print(String... args){
        String text = line;
        for(int i = 0; i < args.length; i++) {
            text += String.format("\t%s\n", args[i]);
        }
        text += line;
        System.out.println(text);
    }
    /**
     * Function to say hi to the user.
     */
    public void sayHi(){
        this.print("Hello! I'm Minion!", "What can I do for you?");
    }

    /**
     * Function to say bye to the user.
     */
    public void sayBye(){
        this.print("Bye. Hope to see you again soon!");
    }
}

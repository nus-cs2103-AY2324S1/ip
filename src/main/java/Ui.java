import java.util.Scanner;

/**
 * Represents the Ui of the chatbot.
 */
public class Ui {
    private static final String DIVIDER = "\t____________________________________________________________\n";
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Pretty prints string(s).
     * @param args string(s) to be printed.
     */
    public void print(String... args){
        String text = DIVIDER;
        for(int i = 0; i < args.length; i++) {
            text += String.format("\t%s\n", args[i]);
        }
        text += DIVIDER;
        System.out.println(text);
    }
    /**
     * Function to say hi to the user.
     */
    public void showWelcome(){
        this.print(Messages.MESSAGE_WELCOME);
    }

    /**
     * Function to say bye to the user.
     */
    public void showGoodbye(){
        this.print(Messages.MESSAGE_GOODBYE);
    }

    public void showLoadingError() {
        this.print(Messages.MESSAGE_FILE_NOT_FOUND);
    }

    public void tearDown() {
        this.showGoodbye();
        sc.close();
    }
}

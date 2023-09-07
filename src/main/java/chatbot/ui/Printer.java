package chatbot.ui;

import java.util.Scanner;

/**
 * Class representing a UI which reads inputs from users
 * and prints outputs depending on the command or error.
 *
 * @author Owen Yeo
 */
public class Printer {

    //Scanner used to see user input
    private static final Scanner sc = new Scanner(System.in);

    //String representing a border.
    private static final String BORDER = "____________________________________________________________\n";

    /**
     * Prints the inputs out for the user.
     *
     * @param inputs
     */
    public String print(String[] inputs) {
        String output = "";
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i] != null) {
                output += inputs[i] +"\n";
            }
        }
        return output;
    }
    

    /**
     * Prints an introduction.
     */
    public String intro() {
        return "Hello! I am Bobby Wasabi\nWhat can I do for you today?";
    }

    /**
     * Prints a goodbye message.
     */
    public String bye() {
        return "Bye. Have a bad day you doofus.";
    }

    /**
     * Reads the input from the users
     * 
     * @return String representing input
     */
    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Prints error messages.
     * 
     * @param e Exception instance.
     */
    public String showError(Exception e) {
        return "Error! " + e.getMessage();
    }

}

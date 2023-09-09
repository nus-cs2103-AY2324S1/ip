package chatbot.ui;

/**
 * Class representing a Printer that prints outputs 
 * depending on the command or error.
 *
 * @author Owen Yeo
 */
public class Printer {

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
     * Prints error messages.
     * 
     * @param e Exception instance.
     */
    public String showError(Exception e) {
        return "Error! " + e.getMessage();
    }

}

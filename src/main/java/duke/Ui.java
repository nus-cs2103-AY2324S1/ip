package duke;

import java.util.Scanner;

/**
 * The class in charge of input and output to the user
 */
public class Ui {
    protected String line = "----------------------------------------------------\n";
    public Ui() {}

    /**
     * The Scanner object to accept user input
     */
    protected Scanner myObj = new Scanner(System.in);

    public void greet() {
        System.out.println(line);
        System.out.println(format_response(
                "Hello I'm Project54\n" +
                        "What can I do for you?"
        ));
    }

    public void bye() {
        System.out.println(format_response(
                "Bye. Hope to see you again soon"
        ));
    }

    public String readCommand() {
        return myObj.nextLine();
    }

    /**
     * Adds the line at the end of each sentence for each reply
     * @param response the generated response by the chatbot
     * @return the new string that will be output to the user
     */
    public String format_response(String response) {
        return response + "\n\n" + line;
    }
}

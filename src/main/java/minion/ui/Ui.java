package minion.ui;

import minion.common.Messages;

import java.util.Scanner;

/**
 * Represents the minion.ui.Ui of the chatbot.
 */
public class Ui {
    private static final String DIVIDER = "\t____________________________________________________________\n";
    private final Scanner sc;

    /**
     * Constructs the Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the command from the scanner.
     * @return the command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Pretty prints string(s).
     * @param args string(s) to be printed.
     */
    public void print(String... args){
        StringBuilder sb = new StringBuilder(DIVIDER);
        for(String arg: args) {
            sb.append("\t");
            sb.append(arg);
            sb.append("\n");
        }
        sb.append(DIVIDER);
        System.out.println(sb);
    }

    /**
     * Prints welcome to the user.
     */
    public void showWelcome(){
        this.print(Messages.MESSAGE_WELCOME);
    }

    /**
     * Prints goodbye to the user.
     */
    public void showGoodbye(){
        this.print(Messages.MESSAGE_GOODBYE);
    }

    /**
     * Displays loading error to the user.
     */
    public void showLoadingError() {
        this.print(Messages.MESSAGE_FILE_NOT_FOUND);
    }

    /**
     * Cleanup function for the Ui.
     */
    public void tearDown() {
        this.showGoodbye();
        sc.close();
    }
}

package duke.ui;

import java.util.Scanner;

/**
 * A Ui that reads inputs from the user and prints to the user.
 */
public class VerboseUi extends Ui {

    private static final String HORIZONTAL_LINE = "_".repeat(60);

    private String name;

    /**
     * Constructor for VerboseUi.
     * 
     * @param name Name used when greeting users.
     */
    public VerboseUi(String name) {
        super(new Scanner(System.in));
        this.name = name;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void greet() {
        print(String.format("Hello! I'm %s\nWhat can I do for you?", name));
    }

    /**
     * @inheritdoc
     */
    @Override
    public void print(String msg) {
        printIndent(HORIZONTAL_LINE);
        for (String line : msg.split("\n")) {
            printIndent(" ".repeat(2) + line);
        }
        printIndent(HORIZONTAL_LINE);
        System.out.println();
    }

    private void printIndent(String msg) {
        for (String line : msg.split("\n")) {
            System.out.println(" ".repeat(2) + line);
        }
    }

}

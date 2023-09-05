package duke.ui;

import java.util.Scanner;

/**
 * A Ui that reads inputs from the user and prints to the user.
 */
public class VerboseUi extends Ui {

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
        this.msg = msg;
    }

}

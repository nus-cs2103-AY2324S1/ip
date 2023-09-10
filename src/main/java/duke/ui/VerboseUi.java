package duke.ui;

import java.util.Scanner;

/**
 * A Ui that reads inputs from the user and prints to the user.
 */
public class VerboseUi extends Ui {

    /**
     * Constructs VerboseUi.
     *
     * @param name Name used when greeting users.
     */
    public VerboseUi(String name) {
        super(new Scanner(System.in));
    }

    /**
     * @inheritdoc
     */
    @Override
    public void print(String msg) {
        this.msg = msg;
    }

}

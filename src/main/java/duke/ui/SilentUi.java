package duke.ui;

import java.util.Scanner;

/**
 * Ui used internally that does not print any output.
 */
public class SilentUi extends Ui {

    /**
     * Constructs SilentUi.
     *
     * @param sc The scanner from which input is read.
     */
    public SilentUi(Scanner sc) {
        super(sc);
    }

    /**
     * @inheritdoc
     */
    @Override
    public void print(String msg) {}

}

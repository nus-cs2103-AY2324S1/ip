package duke.ui;

import java.util.Scanner;

public class SilentUi extends Ui {

    public SilentUi(Scanner sc) {
        super(sc);
    }

    @Override
    public void print(String msg) {}

    @Override
    public void greet() {}

}

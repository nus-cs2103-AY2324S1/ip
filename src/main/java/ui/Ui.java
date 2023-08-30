package ui;

public class Ui {

    private static final String NAME = "Kevin";
    private static final int SPLITTER_LENGTH = 50;

    public Ui() {
    }

    public void lineSplitter() {
        for (int i = 0; i < Ui.SPLITTER_LENGTH; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public void greet() {
        lineSplitter();
        System.out.println("Hello! I'm " + Ui.NAME + "\n" + "What can I do for you?\n");
    }

    public void bye() {
        lineSplitter();
        System.out.println("Bye. Hope to see you again soon!\n");
        lineSplitter();
    }
}

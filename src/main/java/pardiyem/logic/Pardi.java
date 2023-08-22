package pardiyem.logic;

import java.util.ArrayList;
import java.util.Objects;

public class Pardi {
    private final String GREETING = "\nHello, I'm Pardi\nWhat can I do for you?\n";
    private final String BYE = "\nCiao! See you again!\n";
    private ArrayList<String> tasklist;
    public Pardi() {
        this.tasklist = new ArrayList<String>();
    }
    public void greeting() {
        System.out.println(GREETING);
    }

    public void exit() {
        System.out.println(BYE);
    }

    public boolean run(String in) {
        if (Objects.equals(in, "bye")) {
            return false;
        } else {
            if (Objects.equals(in, "list")) {
                for (int i = 1; i <= this.tasklist.size(); i++) {
                    System.out.printf("\n%d. %s", i, this.tasklist.get(i-1));
                }
            } else {
                this.tasklist.add(in);
                System.out.println("\nadded: " + in + "\n");
            }
            return true;
        }
    }
}

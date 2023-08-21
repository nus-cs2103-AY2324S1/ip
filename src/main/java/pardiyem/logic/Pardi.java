package pardiyem.logic;

import java.util.Objects;

public class Pardi {
    private final String GREETING = "\nHello, I'm Pardi\nWhat can I do for you?\n";
    private final String BYE = "\nCiao! See you again!\n";
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
            System.out.println("\n" + in + "\n");
            return true;
        }
    }
}

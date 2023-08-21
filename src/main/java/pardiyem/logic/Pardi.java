package pardiyem.logic;

public class Pardi {
    private final String GREETING = "\nHello, I'm Pardi\nWhat can I do for you?";
    private final String BYE = "\nCiao! See you again!";
    public void greeting() {
        System.out.println(GREETING);
    }

    public void exit() {
        System.out.println(BYE);
    }
}

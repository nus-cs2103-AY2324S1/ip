/**
 * This class encapsulates my version of Duke, called Rat.
 * @author Keagan
 */
public class Rat {
    static String welcomeMsg = "                  Hello! I'm Rat!\n"
            + "              What can I do for you?";
    static String exitMsg = "           Bye. Hope to see you again soon!";

    static void printWithLines(String s) {
        System.out.println("=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=");
        System.out.println(s);
        System.out.println("=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=");
    }
    public static void main(String[] args) {
        printWithLines(welcomeMsg);
        printWithLines(exitMsg);
    }
}



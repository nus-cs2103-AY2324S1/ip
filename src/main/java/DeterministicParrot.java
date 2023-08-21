public class DeterministicParrot {

    /**
     * Prints a line
     */
    public static void printDash() {
        System.out.println("____________________________________________________________");
    }

    /**
     *  Hello! I'm [YOUR CHATBOT NAME]
     *  What can I do for you?
     */
    public static void greet() {
        printDash();
        System.out.println("Hello! I'm DeterministicParrot");
        System.out.println("What can I do for you?");
        printDash();
    }
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        printDash();
    }
    public static void main(String[] args) {
        greet();
        bye();
    }
}

public class Duke {

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.printLine();
        duke.printGreeting();
        duke.printLine();
        duke.printBye();
        duke.printLine();
    }

    public void printGreeting() {
        System.out.println("Hello! I'm Max\n" + "What can I do for you?");
    }

    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }
}

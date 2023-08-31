package duke;

public class Messages {

    public Messages() {
        Messages.Greet();
        Ui ui = new Ui();
    }

    public static final void Greet() {
        System.out.println("Hello! I'm Chatty!");
        System.out.println("What can I do for you today? ");
    }

    public static final void Exit() {
        System.out.println("Bye bye! Hope to see you again soon");
    }
}

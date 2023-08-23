import java.util.Scanner;

public class UserInterface {
    private final Scanner sc;
    private final Storage store;
    private final CommandParser parser;

    public UserInterface(Scanner sc, Storage store, CommandParser parser) {
        this.sc = sc;
        this.store = store;
        this.parser = parser;
    }

    public void start() {
        System.out.println("Hello! I'm Ducky\nWhat can I do for you?");
        while (true) {
            String input = sc.nextLine();

                Command c = this.parser.parse(input);
                switch (c.getType()) {
                    case LIST:
                        System.out.println("Here are the tasks in your list:");
                        System.out.println(this.store.list());
                        break;
                    case MARK:
                        int mark_index = Integer.parseInt(c.getArgs()[0]) - 1;
                        this.store.mark(mark_index);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(this.store.getTaskDescription(mark_index));
                        break;
                    case UNMARK:
                        int unmark_index = Integer.parseInt(c.getArgs()[0]) - 1;
                        this.store.unmark(unmark_index);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(this.store.getTaskDescription(unmark_index));
                        break;
                    case BYE:
                        System.out.println("Bye. Hope to see you again soon!");
                        System.exit(0);
                    case TASK:
                        this.store.add(new Task(input));
                        System.out.println("added: " + input);
                }

        }
    }
}

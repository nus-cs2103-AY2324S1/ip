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
                        this.store.mark(Integer.parseInt(c.getArgs()[0]));
                        break;
                    case UNMARK:
                        this.store.unmark(Integer.parseInt(c.getArgs()[0]));
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

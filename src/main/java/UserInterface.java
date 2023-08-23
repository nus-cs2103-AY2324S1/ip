import java.util.Scanner;

public class UserInterface {
    private final Scanner sc;
    private final Storage store;

    public UserInterface(Scanner sc, Storage store) {
        this.sc = sc;
        this.store = store;
    }

    public void start() {
        System.out.println("Hello! I'm Ducky\nWhat can I do for you?");
        while (true) {
            String input = sc.nextLine();
            switch (input) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    System.exit(0);
                case "list":
                    System.out.println(this.store.list());
                    break;
                default:
                    this.store.add(input);
                    System.out.println("added: " + input);
            }
        }
    }
}

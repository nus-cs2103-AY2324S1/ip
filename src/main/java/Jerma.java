import java.util.Scanner;

enum Command {
    LIST, BYE, ADD;

    public static Command parse(String input) {
        for (Command command : Command.values()) {
            if (command.toString().toLowerCase().equals(input))
                return command;
        }
        return Command.ADD;
    }
}

public class Jerma {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Jerma.");

        Scanner scanner = new Scanner(System.in);
        listen: while (true) {
            String input = scanner.nextLine();
            Command command = Command.parse(input);

            switch (command) {
            case LIST:
                break;
            case BYE:
                break listen;
            case ADD:
                System.out.println(input);
                break;
            }
        }
        scanner.close();

        System.out.println("See ya soon!");
    }
}

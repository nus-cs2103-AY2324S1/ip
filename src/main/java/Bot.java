import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Bot {
    Storage storage = new Storage();

    public static enum Command {
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        BYE
    }

    public void printWelcomeMessage() {
        String name = "LINUS";
        MessagePrinter.print(
                "Hello! I'm " + name + "\n"
                        + "What can I do for you?");
    }

    public void printExitMessage() {
        MessagePrinter.print("Bye. Hope to see you again soon!");
    }

    public void chat() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {
                String[] items = null;
                String description = "";
                int index = 0;
                String input = sc.nextLine();
                String[] inputSplit = input.split(" ", 2);
                String command = inputSplit[0];
                String data = inputSplit.length == 2 ? inputSplit[1] : "";

                switch (Command.valueOf(command.toUpperCase())) {
                case BYE:
                    break;
                case LIST:
                    storage.list();
                    continue;
                case MARK:
                    index = Integer.parseInt(data);
                    storage.mark(index);
                    continue;
                case UNMARK:
                    index = Integer.parseInt(data);
                    storage.unmark(index);
                    continue;
                case DELETE:
                    index = Integer.parseInt(data);
                    storage.delete(index);
                    continue;
                case TODO:
                    if (data == "") {
                        throw new LinusException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    description = data;
                    storage.add(new ToDo(description));
                    continue;
                case DEADLINE:
                    if (data == "") {
                        throw new LinusException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }

                    items = data.split(" /by ");
                    if (items.length != 2) {
                        throw new LinusException("☹ OOPS!!! Please specify the deadline in the correct format: deadline <description> /by <date>");
                    }

                    description = items[0];
                    String by = items[1];

                    storage.add(new Deadline(description, by));
                    continue;
                case EVENT:
                    if (data == "") {
                        throw new LinusException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }

                    items = data.split(" /from | /to ");
                    if (items.length != 3) {
                        throw new LinusException("☹ OOPS!!! Please specify the event in the correct format: event <description> /from <date> /to <date>");
                    }
                    description = items[0];
                    String from = items[1];
                    String to = items[2];

                    storage.add(new Event(description, from, to));
                    continue;
                }
                break;
            } catch (IllegalArgumentException e) {
                MessagePrinter.print("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "Please start your inputs with a valid command.");
            } catch (LinusException e) {
                MessagePrinter.print(e.getMessage());
            }
        }
    }

    public void start() {
        this.printWelcomeMessage();
        this.chat();
        this.printExitMessage();
    }
}
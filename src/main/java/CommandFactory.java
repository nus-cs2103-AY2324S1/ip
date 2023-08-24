import java.util.ArrayList;
import java.util.Scanner;
public class CommandFactory {
    enum Command {
        TODO,
        DEADLINE,
        EVENT,
        BYE,
        MARK,
        UNMARK,
        LIST
    }

    public static void CommandActions() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> inputs = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ");
            String[] details = input.split("/");
            Command command = null;

            try {
                command = Command.valueOf(parts[0].toUpperCase());
                //System.out.println((command.name()));
            } catch (IllegalArgumentException e) {
                // Handle invalid command
            }

            if (command != null) {
                Task newTask = null;
                Task oldtask = null;
                int number = 0;
                try {
                    number = Integer.parseInt(parts[1]);
                    oldtask = inputs.get(number-1);
                } catch (Exception e) {
                    // Handle invalid command
                }
                switch (command) {
                    case BYE:
                        System.out.println("Bye. Hope to see you again soon!");
                        break;

                    case LIST:
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 1; i < inputs.size()+1; i++) {
                            Task taskToPrint = inputs.get(i - 1);
                            System.out.println(i + ". " + taskToPrint.getStatusAndDescription());
                        }
                        break;

                    case UNMARK:
                        oldtask.markAsNotDone();
                        break;

                    case MARK:
                        oldtask.markAsDone();
                        break;

                    case TODO:
                        newTask = new ToDo(details[0].substring(5));
                        inputs.add(newTask);
                        break;

                    case DEADLINE:
                        newTask = new Deadline(details[0].split("/")[0].substring(9),
                                details[1].split("by ")[1].trim());
                        inputs.add(newTask);
                        break;

                    case EVENT:
                        newTask = new Event(details[0].split("/")[0].substring(6),
                                details[1].split("from ")[1].trim(),
                                details[2].split("to ")[1].trim());
                        inputs.add(newTask);
                        break;

                    default:
                        break;
                }
            }

            if (command == Command.BYE) {
                break;
            }
        }

        scanner.close();
    }
}

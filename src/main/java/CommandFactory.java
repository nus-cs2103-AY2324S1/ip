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

            try{
                try {
                    command = Command.valueOf(parts[0].toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new InvalidInputException();
                }

                if (command != null) {
                    Task newTask = null;
                    Task oldtask = null;
                    int number = 0;
                    if (command == Command.MARK || command == Command.UNMARK){
                        try {
                            number = Integer.parseInt(parts[1]);
                            oldtask = inputs.get(number-1);
                        } catch (Exception e) {
                            throw new InvalidListNumberException();
                        }
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
                            if (details[0].trim().length() == 4) {
                                throw new InvalidToDoException();
                            } else {
                                newTask = new ToDo(details[0].substring(5));
                                inputs.add(newTask);
                            }
                            break;

                        case DEADLINE:
                            if (details[0].trim().length() == 8) {
                                throw new InvalidDeadlineException();
                            } else {
                                newTask = new Deadline(details[0].split("/")[0].substring(9),
                                        details[1].split("by ")[1].trim());
                                inputs.add(newTask);
                            }
                            break;

                        case EVENT:
                            if (details[0].trim().length() == 5) {
                                throw new InvalidEventException();
                            } else {
                                newTask = new Event(details[0].split("/")[0].substring(6),
                                        details[1].split("from ")[1].trim(),
                                        details[2].split("to ")[1].trim());
                                inputs.add(newTask);
                            }
                            break;

                        default:
                            break;
                    }
                }
                if (command == Command.BYE) {
                    break;
                }
            } catch (DukeException e){
                System.out.println(e);
            }
        }

        scanner.close();
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static class StoreList {
        ArrayList<Task> list = new ArrayList<>();
        StoreList() {
        }

        String add(Commands type, String item) {
            try {

                Task task = Task.create(type, item);
                list.add(task);
                return String.format(
                        "added: %s\nYou have %d tasks.",
                        task,
                        list.size()
                );
            } catch (DukeException e) {
                return e.toString();
            }
        }

        String markDone(String position) {
            try {
                int index = Integer.parseInt(position) - 1;
                Task task = list.get(index);
                task.markAsDone();
                return String.format("Nice! You have completed the task:\n    %s", task);
            } catch (NumberFormatException e) {
                return "Err: Index provided is not an integer";
            } catch (IndexOutOfBoundsException e) {
                return "Err: Index provided is out of position of the list";
            }
        }

        String markUndone(String position) {
            try {
                int index = Integer.parseInt(position) - 1;
                Task task = list.remove(index);
                return String.format("Ok! Task marked undone:\n    %s", task);
            } catch (NumberFormatException e) {
                return "Err: Index provided is not an integer";
            } catch (IndexOutOfBoundsException e) {
                return "Err: Index provided is out of position of the list";
            }
        }

        String delete(String position) {
            try {
                int index = Integer.parseInt(position) - 1;
                Task task = list.remove(index);
                return String.format(
                        "removed: %s\nYou have %d tasks.",
                        task,
                        list.size()
                );
            } catch (NumberFormatException e) {
                return "Err: Index provided is not an integer";
            } catch (IndexOutOfBoundsException e) {
                return "Err: Index provided is out of position of the list";
            }
        }
        @Override
        public String toString() {
            if (list.size() == 0) {
                return "You have no tasks :).";
            }
            String result = "";
            for (int i = 0; i < list.size(); i++) {
                result += String.format("    %d. %s\n", i + 1, list.get(i));
            }
            return result;
        }
    }

    private static StoreList list = new StoreList();
    public static void main(String[] args) {
        String logo = "Nino!";
        System.out.println("Hello, my name is " + logo);
        System.out.println(wrapper("What can I do for you?"));
        reader();
    }

    private static void reader() {
        Scanner sc = new Scanner(System.in);
        Reading: while (true) {
            String line = sc.nextLine();
            if (line.length() == 0) {
                printer("Err: No command input");
                continue Reading;
            }
            String[] instruction = line.split(" ", 2);
            Commands cmd = Commands.get(instruction[0]);
            boolean hasSecondPart = instruction.length == 2;
            String response;
            switch (cmd) {
                case bye:
                    response = "Goodbye.";
                    sc.close();
                    printer(response);
                    break Reading;
                case list:
                    response = list.toString();
                    break;
                case deadline:
                case todo:
                case event:
                    if (!hasSecondPart) {
                        response = missingDetails(cmd);
                        break;
                    }
                    response = list.add(cmd, instruction[1]);
                    break;
                case mark:
                    if (!hasSecondPart) {
                        response = missingDetails(cmd);
                        break;
                    }
                    response = list.markDone(instruction[1]);
                    break;
                case unmark:
                    if (!hasSecondPart) {
                        response = missingDetails(cmd);
                        break;
                    }
                    response = list.markUndone(instruction[1]);
                    break;
                case delete:
                    if (!hasSecondPart) {
                        response = missingDetails(cmd);
                        break;
                    }
                    response = list.delete(instruction[1]);
                    break;
                default:
                    response = "Err: Unknown command - " + instruction[0];
                    break;
            }
            printer(response);
        }
    }

    private static String wrapper(String line) {
        String frame = "=====================";
        return String.format("%s\n%s", line, frame);
    }

    private static void printer(String item) {
        System.out.println(wrapper(item));
    }

    private static String missingDetails(Commands cmd) {
        return "Err: no details given after command - " + cmd.toString();
    }
}

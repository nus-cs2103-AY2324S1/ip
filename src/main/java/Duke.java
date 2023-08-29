import java.time.DateTimeException;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        FunnyList items = new FunnyList();
        printLine();
        System.out.println("\tHello! I'm FUNNY.\n\tWhat can I do for you?");
        printLine();
        String input = scanner.nextLine().trim();

        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {

                    items.displayList();
                } else {
                    String command = input.split(" ")[0];
                    if (command.equals("mark")) {
                        String[] data = input.split(" ", 2);
                        if (data.length < 2) {
                            throw new DukeException("There must be a selected task");
                        }
                        items.completeTask(Integer.valueOf(data[1]) - 1);
                    } else if (command.equals("unmark")) {
                        String[] data = input.split(" ", 2);
                        if (data.length < 2) {
                            throw new DukeException("There must be a selected task");
                        }
                        items.undoTask(Integer.valueOf(data[1]) - 1);
                    } else if (command.equals("delete")) {
                        String[] data = input.split(" ", 2);
                        if (data.length < 2) {
                            throw new DukeException("There must be a selected task");
                        }
                        items.deleteTask(Integer.valueOf(data[1]) - 1);
                    } else if (command.equals("todo")) {
                        String[] data = input.split(" ", 2);
                        if (data.length < 2) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        items.add(new ToDo(input.split(" ", 2)[1]));
                    } else if (command.equals("deadline")) {
                        String[] data = input.split(" /by ", 2);
                        if (data.length < 2) {
                            throw new DukeException("A task with deadline requires a /by (timedate) descriptor");
                        }
                        if (data[0].split(" ", 2).length < 2) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        }

                        items.add(new Deadline(data[0].split(" ", 2)[1], data[1]));
                    } else if (command.equals("event")) {
                        String[] data = input.split(" /from ", 2);
                        if (data.length < 2) {
                            throw new DukeException("An event requires a /from (timedate) descriptor");
                        }

                        String[] period = data[1].split( "/to ");
                        if (period.length < 2) {
                            throw new DukeException("An event requires a /to (timedate) descriptor");
                        }

                        if (data[0].split(" ", 2).length < 2) {
                            throw new DukeException("The description of an event cannot be empty.");
                        }

                        items.add(new Event(data[0].split(" ", 2)[1], period));
                    } else {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException e) {
                printLine();
                System.out.println(e);
                printLine();
            } catch (NumberFormatException e) {
                printLine();
                System.out.println(new DukeException("Please specify the index of the task (Numbers only)"));
                printLine();
            } catch (DateTimeException e) {
                printLine();
                System.out.println(new DukeException("Please represent time in a proper time format of yyyy-mm-dd"));
            } finally {
                input = scanner.nextLine().trim(); // Get next input
            }
        }
        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }


    public static void printLine() {
        System.out.print("\t");
        for (int i = 0; i < 80; i++) {
            System.out.print("─");
        }
        System.out.println();
    }
}

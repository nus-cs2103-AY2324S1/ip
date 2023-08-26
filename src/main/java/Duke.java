import java.util.Scanner;

public class Duke {

    public static String horizontalLine = "_".repeat(60) + "\n";
    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);
        String task = "";
        System.out.println("Hello! I'm Bot\n" + "What can I do for you?\n" + horizontalLine);
        while (true) {
            try {
                task = sc.nextLine();
                if (task.equals("bye")) {
                    break;
                }
                if (task.equals("list")) {
                    Task taskInstance = new Task("");
                    taskInstance.printList();
                    continue;
                }
                String[] elements = task.split((" "));
                if (elements.length >= 2) {
                    String instruction = elements[0];
                    if (instruction.equals("mark") || instruction.equals("unmark")) {
                        try {
                            int no = Integer.parseInt(elements[1]);
                            Task taskInstance = new Task("");
                            if (instruction.equals("mark")) {
                                taskInstance.mark(no);
                            } else {
                                taskInstance.unmark(no);
                            }
                            continue;
                        } catch (NumberFormatException e) {
                            System.err.println(Duke.horizontalLine + "You did not enter a valid integer :(\n" + Duke.horizontalLine);
                            continue;
                        }
                    }
                    if (instruction.equals("delete")) {
                        try {
                            int no = Integer.parseInt(elements[1]);
                            Task taskInstance = new Task("");
                            taskInstance.delete(no);
                            continue;
                        } catch (NumberFormatException e) {
                            System.err.println(Duke.horizontalLine + "You did not enter a valid integer :(\n" + Duke.horizontalLine);
                            continue;
                        }
                    }
                    int firstSpaceIndex = task.indexOf(' ');
                    String actualTask = task.substring(firstSpaceIndex + 1);
                    if (instruction.equals("todo")) {
                        Todo todo = new Todo(actualTask);
                        todo.print();
                    }
                    else if (instruction.equals("deadline")) {
                        String[] taskAndDeadline = actualTask.split(("/by"));
                        if (taskAndDeadline.length == 1 || taskAndDeadline.length == 0) {
                            throw new DukeException(Duke.horizontalLine + "☹ OOPS!!! Invalid format for deadline :-(\n" + Duke.horizontalLine);
                        }
                        String onlyTask = taskAndDeadline[0];
                        String by = taskAndDeadline[1];
                        Deadline deadline = new Deadline(onlyTask, by);
                        deadline.print();
                    }
                    else if (instruction.equals("event")) {
                        String[] taskAndToFrom = actualTask.split(("/from"));
                        if (taskAndToFrom.length == 1 || taskAndToFrom.length == 0) {
                            throw new DukeException(Duke.horizontalLine + "☹ OOPS!!! Invalid format for event :-(\n" + Duke.horizontalLine);
                        }
                        String onlyTask = taskAndToFrom[0];
                        if (onlyTask.trim().isEmpty()) {
                            throw new DukeException(Duke.horizontalLine + "☹ OOPS!!! Invalid format for event :-(\n" + Duke.horizontalLine);
                        }
                        String[] ToFrom = taskAndToFrom[1].split("/to");
                        if (ToFrom.length == 1 || ToFrom.length == 0) {
                            throw new DukeException(Duke.horizontalLine + "☹ OOPS!!! Invalid format for event :-(\n" + Duke.horizontalLine);
                        }
                        String from = ToFrom[0];
                        String to = ToFrom[1];
                        Event event = new Event(onlyTask, from, to);
                        event.print();
                    }
                    else {
                        throw new DukeException(Duke.horizontalLine + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + Duke.horizontalLine);
                    }
                } else if (elements[0].equals("todo") || elements[0].equals("deadline") || elements[0].equals("event")) {
                    throw new DukeException(Duke.horizontalLine + "☹ OOPS!!! The description of a " + elements[0] + " cannot be empty.\n" + Duke.horizontalLine);
                }
                else {
                    throw new DukeException(Duke.horizontalLine + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + Duke.horizontalLine);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println(horizontalLine+ "Bye. Hope to see you again soon!\n" + horizontalLine);
    }
}
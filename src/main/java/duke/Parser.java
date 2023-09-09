package duke;

import java.util.Scanner;

public class Parser {

    public static int run;
    public static void parse(Scanner sc) {
        while (true) {
            try {
                String task = Ui.readCommand(sc);
                if (task.equals("bye")) {
                    run = 0;
                    break;
                }
                if (task.equals("list")) {
                    Task taskInstance = new Task();
                    taskInstance.printList();
                    continue;
                }
                String[] elements = task.split((" "));
                if (elements.length >= 2) {
                    String instruction = elements[0];
                    if (instruction.equals("mark") || instruction.equals("unmark")) {
                        try {
                            int no = Integer.parseInt(elements[1]);
                            Task taskInstance = new Task();
                            if (instruction.equals("mark")) {
                                taskInstance.mark(no);
                            } else {
                                taskInstance.unmark(no);
                            }
                            continue;
                        } catch (NumberFormatException e) {
                            Ui.showError(Ui.horizontalLine
                                    + "You did not enter a valid integer :(\n"
                                    + Ui.horizontalLine);
                            continue;
                        }
                    }
                    if (instruction.equals("delete")) {
                        try {
                            int no = Integer.parseInt(elements[1]);
                            Task taskInstance = new Task();
                            taskInstance.delete(no);
                            continue;
                        } catch (NumberFormatException e) {
                            Ui.showError(Ui.horizontalLine
                                    + "You did not enter a valid integer :(\n"
                                    + Ui.horizontalLine);
                            continue;
                        }
                    }
                    int firstSpaceIndex = task.indexOf(' ');
                    String actualTask = task.substring(firstSpaceIndex + 1);
                    if (instruction.equals("todo")) {
                        Todo todo = new Todo(actualTask, true);
                        todo.print();
                    }
                    else if (instruction.equals("deadline")) {
                        String[] taskAndDeadline = actualTask.split("/by");
                        if (taskAndDeadline.length == 1 || taskAndDeadline.length == 0) {
                            throw new DukeException(Ui.horizontalLine
                                    + "OOPS!!! Invalid format for deadline :-(\n"
                                    + Ui.horizontalLine);
                        }
                        String onlyTask = taskAndDeadline[0].trim(); // Trim to remove extra spaces
                        String by = taskAndDeadline[1].trim();// Trim to remove extra spaces
                        if (!by.contains(" ")) {
                            by += " 2359";
                        }
                        Deadline deadline = new Deadline(onlyTask, true, by);
                        deadline.print();
                    }
                    else if (instruction.equals("event")) {
                        String[] taskAndToFrom = actualTask.split("/from");
                        if (taskAndToFrom.length == 1 || taskAndToFrom.length == 0) {
                            throw new DukeException(Ui.horizontalLine
                                    + "OOPS!!! Invalid format for event :-(\n"
                                    + Ui.horizontalLine);
                        }
                        String onlyTask = taskAndToFrom[0].trim(); // Trim to remove extra spaces
                        if (onlyTask.isEmpty()) {
                            throw new DukeException(Ui.horizontalLine
                                    + "OOPS!!! Invalid format for event :-(\n"
                                    + Ui.horizontalLine);
                        }
                        String[] ToFrom = taskAndToFrom[1].split("/to");
                        if (ToFrom.length == 1 || ToFrom.length == 0) {
                            throw new DukeException(Ui.horizontalLine
                                    + "OOPS!!! Invalid format for event :-(\n"
                                    + Ui.horizontalLine);
                        }
                        String from = ToFrom[0].trim(); // Trim to remove extra spaces
                        String to = ToFrom[1].trim(); // Trim to remove extra spaces
                        if (!from.contains(" ")) {
                            from += " 2359";
                        }
                        if (!to.contains(" ")) {
                            to += " 2359";
                        }
                        Event event = new Event(onlyTask, true, from, to);
                        event.print();
                    }
                    else {
                        throw new DukeException(Ui.horizontalLine
                                + "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                                + Ui.horizontalLine);
                    }
                } else if (elements[0].equals("todo") || elements[0].equals("deadline")
                        || elements[0].equals("event")) {
                    throw new DukeException(Ui.horizontalLine
                            + "OOPS!!! The description of a "
                            + elements[0] + " cannot be empty.\n" + Ui.horizontalLine);
                }
                else {
                    throw new DukeException(Ui.horizontalLine
                            + "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                            + Ui.horizontalLine);
                }
            } catch (Exception e) {
                Ui.showError(e.getMessage());
            }
        }
    }
}

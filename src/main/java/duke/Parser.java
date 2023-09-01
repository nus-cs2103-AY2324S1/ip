package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Parser {
    final static String line = "____________________________________________________________";

    public Parser() {

    }
    public void parse(String command, TaskList tasks) {
        try {
            String[] commandArr = command.split(" ",2);
            switch (commandArr[0]) {
                case "list":
                    tasks.print();
                    System.out.print(line + "\n");
                    break;
                case "mark":
                    int mIndex = command.charAt(5) - 49;
                    Task t = tasks.getTask(mIndex);
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t.toString() + "\n" + line);
                    break;
                case "unmark":
                    int umIndex = command.charAt(7) - 49;
                    Task umTask = tasks.getTask(umIndex);
                    umTask.markAsUndone();
                    System.out.println("Nice! I've marked this task as not done yet:");
                    System.out.println(umTask.toString() + "\n" + line);
                    break;
                case "deadline":
                    if (commandArr[1].isEmpty()) {
                        throw new DukeException(String.format(DukeException.NON_EMPTY, "DEADLINE"));
                    }
                    String[] deadlineArr = commandArr[1].split("/by ", 2);
                    Task deadline = new Deadline(deadlineArr[0], deadlineArr[1]);
                    tasks.addTask(deadline);
                    System.out.println("added " + deadline.toString() + "\n");
                    System.out.println("Now you have " + tasks.getSize() + " tasks in the list. \n" + line);
                    break;
                case "event":
                    if (commandArr[1].isEmpty()) {
                        throw new DukeException(String.format(DukeException.NON_EMPTY, "EVENT"));
                    }
                    String[] eventArr1 = commandArr[1].split(" /from ", 2);
                    String[] eventArr2 = eventArr1[1].split(" /to ", 2);
                    Task event = new Event(eventArr1[0], eventArr2[0], eventArr2[1]);
                    tasks.addTask(event);
                    System.out.println("added " + event.toString() + "\n");
                    System.out.println("Now you have " + tasks.getSize() + " tasks in the list. \n" + line);
                    break;
                case "todo":
                    if (commandArr[1].isEmpty()) {
                        throw new DukeException(String.format(DukeException.NON_EMPTY, "TODO"));
                    }
                    Task todo = new Todo(commandArr[1]);
                    tasks.addTask(todo);
                    System.out.println("added " + todo.toString() + "\n");
                    System.out.println("Now you have " + tasks.getSize() + " tasks in the list. \n" + line);
                    break;
                case "delete":
                    int deleteIndex = command.charAt(7) - 49;
                    Task deleted = tasks.deleteTask(deleteIndex);
                    System.out.println("Noted. I've removed the task: \n");
                    System.out.println(deleted.toString() + "\n");
                    System.out.println("Now you have " + tasks.getSize() + " tasks in the list. \n" + line);
                    break;
                case "bye":
                    Ui.bye();
                    break;
                default:
                    throw new DukeException(DukeException.UNKNOWN);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage() + "\n" + line);
        }
    }
}

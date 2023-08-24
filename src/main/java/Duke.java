import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static final String horizontalLine = "    ____________________________________________________________\n";
    public static ArrayList<Task> taskArray = new ArrayList<>();
    public static int numTask = 0;
    public static void greet() {
        System.out.println(horizontalLine
                + "     Hello! I'm POPOOH\n"
                + "     What can I do for you?\n"
                + horizontalLine);
    }
    public static void exit() {
        System.out.println(horizontalLine
                           + "     Bye. Hope to see you again soon!\n"
                           + horizontalLine);
    }
    public static void printCommand(String command){
        if (Objects.equals(command, "bye")) {
            exit();
        } else if (command.contains("unmark")) {

            System.out.println(horizontalLine);
            int taskNum = Integer.parseInt(command.substring(7)) - 1;
            // check
            taskArray.get(taskNum).markAsUndone();
            System.out.println(horizontalLine);

        } else if (command.contains("mark")) {

            System.out.println(horizontalLine);
            int taskNum = Integer.parseInt(command.substring(5)) - 1;
            // check
            taskArray.get(taskNum).markAsDone();
            System.out.println(horizontalLine);

        } else if (command.contains("delete")) {
            int deleteTask = Integer.parseInt(command.substring(7)) - 1;
            Task removed = taskArray.get(deleteTask);
            taskArray.remove(deleteTask);
            numTask--;
            System.out.println(horizontalLine+ "     Noted. I've removed this task:\n"
                               + "     " + removed.printDesc()
                               + "     Now you have " + numTask +" tasks in the list.\n"
                               + horizontalLine);
        }
        else {
            try {
                addTask(command);
            } catch (DukeException message) {
                System.out.println(horizontalLine + message.getMessage() + "\n" + horizontalLine);
            }
        }
    }
    public static void addTask(String task) throws DukeException {
        if (Objects.equals(task, "list")) {

            System.out.println(horizontalLine + "     Here are the tasks in your list:\n");
            for (int i = 0; i < numTask; i++) {
                System.out.println("     " + (i + 1) + ". " + taskArray.get(i).printDesc());
            }
            System.out.println(horizontalLine);

        } else {
            String[] taskDetails = task.split(" ", 2);
            if (Objects.equals(taskDetails[0], "todo")) {
                try {
                    taskArray.add(new Todo(taskDetails[1]));
                    taskArray.get(numTask).printMessage(numTask);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(horizontalLine + "     OOPS!!! The description of todo cannot be empty :(.\n"
                            + horizontalLine);
                    numTask--;
                }
                numTask++;
            } else if (Objects.equals(taskDetails[0], "deadline")) {
                try {
                    String[] deadlineDetails = taskDetails[1].split("/by", 2);
                    taskArray.add(new Deadline(deadlineDetails[0], deadlineDetails[1]));
                    taskArray.get(numTask).printMessage(numTask);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(horizontalLine
                                       + "     OOPS!!! The description of deadline is incomplete.\n"
                                       + horizontalLine);
                    numTask--;
                }
                numTask++;
            } else if (Objects.equals(taskDetails[0], "event")) {
                try {
                    String[] eventDetails = taskDetails[1].split("/", 3);
                    taskArray.add(new Event(eventDetails[0], eventDetails[1].substring(5),
                            eventDetails[2].substring(3)));
                    taskArray.get(numTask).printMessage(numTask);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(horizontalLine
                                       + "     OOPS!!! The description of event is incomplete :(.\n"
                                       + horizontalLine);
                    numTask--;
                }
                numTask++;
            } else throw new DukeException("     OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    public static void main(String[] args) {
        greet();
        Scanner input = new Scanner(System.in);

        while (input.hasNext()) {
            String command = input.nextLine();
            printCommand(command);
        }
    }
}

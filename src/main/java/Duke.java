import java.util.*;
public class Duke {
    protected static String H_LINE = "____________________________________________________________\n";
    protected static boolean botInUse = true;
    protected static TaskManager taskManager = new TaskManager();

    
    public static void listen(String input) throws InvalidUserInputException {
        if (input.equals("bye")) {
            botInUse=false;
            System.out.println(H_LINE
                    +  "Bye. Hope to see you again soon!\n"
                    + H_LINE);
        } else if (input.equals("list")) {
            String outputList = taskManager.outputNumberedList();
            System.out.println(H_LINE
                    + "Here are the tasks in your list:\n"
                    + outputList
                    + H_LINE);
        } else if (input.contains("unmark")) {
            int a = Integer.parseInt(input.substring(7));
            taskManager.getTask(a-1).markAsUndone();
            System.out.println(H_LINE
                    + "OK, I've marked this task as not done yet:\n"
                    + taskManager.getTask(a-1).toString() + "\n"
                    + H_LINE);
        } else if (input.contains("mark")) {
            int a = Integer.parseInt(input.substring(5));
            taskManager.getTask(a-1).markAsDone();
            System.out.println(H_LINE
                    + "Nice! I've marked this task as done:\n"
                    + taskManager.getTask(a-1).toString() + "\n"
                    + H_LINE);
        } else if (input.contains("todo")) {
            try {
                String toDoDescription = input.split("todo ")[1];
                ToDo toDoTask = new ToDo(toDoDescription);
                taskManager.addTask(toDoTask);
                System.out.println(H_LINE
                        + "Got it. I've added this task:\n"
                        + toDoTask + "\n"
                        + "Now you have " + taskManager.getSize() + ((taskManager.getSize() > 1) ? " tasks " : " task ") + "in the list.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(H_LINE
                        + "☹ OOPS!!! The description of a todo cannot be empty.\n"
                        + H_LINE);
            }
        } else if (input.contains("deadline")) {
            try {
                String by = input.split("/by ")[1];
                String deadlineDescription = input.split("deadline ")[1].split(" /by")[0];
                Deadline deadlineTask = new Deadline(deadlineDescription, by);
               taskManager.addTask(deadlineTask);
                System.out.println(H_LINE
                        + "Got it. I've added this task:\n"
                        + deadlineTask + "\n"
                        + "Now you have " + taskManager.getSize() + ((taskManager.getSize() > 1) ? " tasks " : " task ") + "in the list.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(H_LINE
                        + "☹ OOPS!!! The deadline needs to have a task description and /by .\n"
                        + H_LINE);
            }
            } else if (input.contains("event")) {
            try {
                String from = input.split("/from ")[1].split(" /to")[0];
                String to = input.split("/to ")[1];
                String eventDescription = input.split("event ")[1].split(" /from")[0];
                Event eventTask = new Event(eventDescription, from, to);
                taskManager.addTask(eventTask);
                System.out.println(H_LINE
                        + "Got it. I've added this task:\n"
                        + eventTask + "\n"
                        + "Now you have " + taskManager.getSize() + ((taskManager.getSize() > 1) ? " tasks " : " task ") + "in the list.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(H_LINE
                        + "☹ OOPS!!! The event needs to have a task description, /from and /to.\n"
                        + H_LINE);
            }
        } else {
            throw new InvalidUserInputException();
        }
    }
    public static void main(String[] args) {
        
        System.out.println(H_LINE
                            + "Hello! I'm ChadBob.\n"
                            + "What can I do for you?\n"
                            + H_LINE) ;
        Scanner sc = new Scanner(System.in);
        while(botInUse) {
            String input = sc.nextLine();
            try {
                listen(input);
            } catch (InvalidUserInputException e) {
                System.out.println(H_LINE
                                    + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                                    + H_LINE);
            }
        }

    }


}

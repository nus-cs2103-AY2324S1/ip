import java.util.*;
public class Duke {
    protected static String H_LINE = "____________________________________________________________\n";
    protected static boolean botInUse = true;
    protected static LinkedList<Task> storage = new LinkedList<>();

    /**
     * Converts a LinkedList of Task to a string of Numbered List with the Tasks' description and status.
     * @param linkedList LinkedList with Task object(s).
     * @return String that represents Numbered list of Tasks with their description and status.
     */
    public static String stringifyList(LinkedList<Task> linkedList) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < linkedList.size(); i++) {
            Task currTask = linkedList.get(i);
            str.append(i + 1).append(".")
                    .append(currTask.toString())
                    .append("\n");
        }
        return str.toString();
    }
    
    public static void listen(String input) throws InvalidUserInputException {
        if (input.equals("bye")) {
            botInUse=false;
            System.out.println(H_LINE
                    +  "Bye. Hope to see you again soon!\n"
                    + H_LINE);
        } else if (input.equals("list")) {
            String outputList = Duke.stringifyList(storage);
            System.out.println(H_LINE
                    + "Here are the tasks in your list:\n"
                    + outputList
                    + H_LINE);
        } else if (input.contains("unmark")) {
            int a = Integer.parseInt(input.substring(7));
            storage.get(a - 1).markAsUndone();
            System.out.println(H_LINE
                    + "OK, I've marked this task as not done yet:\n"
                    + storage.get(a - 1).toString() + "\n"
                    + H_LINE);
        } else if (input.contains("mark")) {
            int a = Integer.parseInt(input.substring(5));
            storage.get(a-1).markAsDone();
            System.out.println(H_LINE
                    + "Nice! I've marked this task as done:\n"
                    + storage.get(a - 1).toString() + "\n"
                    + H_LINE);
        } else if (input.contains("todo")) {
            try {
                String toDoDescription = input.split("todo ")[1];
                ToDo toDoTask = new ToDo(toDoDescription);
                storage.add(toDoTask);
                System.out.println(H_LINE
                        + "Got it. I've added this task:\n"
                        + toDoTask + "\n"
                        + "Now you have " + storage.size() + ((storage.size() > 1) ? " tasks " : " task ") + "in the list.");
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
                storage.add(deadlineTask);
                System.out.println(H_LINE
                        + "Got it. I've added this task:\n"
                        + deadlineTask + "\n"
                        + "Now you have " + storage.size() + ((storage.size() > 1) ? " tasks " : " task ") + "in the list.");
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
                storage.add(eventTask);
                System.out.println(H_LINE
                        + "Got it. I've added this task:\n"
                        + eventTask + "\n"
                        + "Now you have " + storage.size() + ((storage.size() > 1) ? " tasks " : " task ") + "in the list.");
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

        
        while(botInUse) {
            Scanner sc = new Scanner(System.in);
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

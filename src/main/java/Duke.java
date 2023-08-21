import java.util.*;
public class Duke {
    /**
     * Converts a LinkedList of Task to a string of Numbered List with the Tasks' description and status.
     * @param linkedList LinkedList with Task object(s).
     * @return String that represents Numbered list of Tasks with their description and status.
     */
    public static String stringifyList(LinkedList<Task> linkedList) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < linkedList.size(); i++) {
            Task currTask = linkedList.get(i);
            str.append(String.valueOf(i + 1)).append(".")
                    .append(currTask.toString())
                    .append("\n");
        }
        return str.toString();
    }
    public static void main(String[] args) {
        String horizontal_line = "____________________________________________________________\n";
        System.out.println(horizontal_line
                            + "Hello! I'm ChadBob.\n"
                            + "What can I do for you?\n"
                            + horizontal_line) ;
        boolean botInUse = true;
        LinkedList<Task> storage = new LinkedList<>();
        while(botInUse) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (Objects.equals(input, "bye")) {
                botInUse=false;
                System.out.println(horizontal_line
                                    +  "Bye. Hope to see you again soon!\n"
                                    + horizontal_line);
            } else if (Objects.equals(input, "list")) {
                String outputList = Duke.stringifyList(storage);
                System.out.println(horizontal_line
                        + "Here are the tasks in your list:\n"
                        + outputList
                        + horizontal_line);
            } else if (input.contains("unmark")) {
                int a = Integer.parseInt(input.substring(7));
                storage.get(a - 1).markAsUndone();
                System.out.println(horizontal_line
                        + "OK, I've marked this task as not done yet:\n"
                        + storage.get(a - 1).toString() + "\n"
                        + horizontal_line);
            } else if (input.contains("mark")) {
                int a = Integer.parseInt(input.substring(5));
                storage.get(a-1).markAsDone();
                System.out.println(horizontal_line
                                    + "Nice! I've marked this task as done:\n"
                                    + storage.get(a - 1).toString() + "\n"
                                    + horizontal_line);
            } else if (input.contains("todo")) {
                String toDoDescription = input.substring(5);
                ToDo toDoTask = new ToDo(toDoDescription);
                storage.add(toDoTask);
                System.out.println(horizontal_line
                                    + "Got it. I've added this task:\n"
                                    + toDoTask.toString() + "\n"
                                    + "Now you have " + storage.size() + ((storage.size() > 1) ? " tasks " : " task ") + "in the list.");
            } else if (input.contains("deadline")) {
                String by = input.split("/by ")[1];
                String deadlineDescription = input.split("deadline ")[1].split(" /by")[0];
                Deadline deadlineTask = new Deadline(deadlineDescription, by);
                storage.add(deadlineTask);
                System.out.println(horizontal_line
                        + "Got it. I've added this task:\n"
                        + deadlineTask.toString() + "\n"
                        + "Now you have " + storage.size() + ((storage.size() > 1) ? " tasks " : " task ") + "in the list.");
            } else if (input.contains("event")) {
                String from = input.split("/from ")[1].split(" /to")[0];
                String to = input.split("/to ")[1];
                String eventDescription = input.split("event ")[1].split(" /from")[0];
                Event eventTask = new Event(eventDescription, from, to);
                storage.add(eventTask);
                System.out.println(horizontal_line
                        + "Got it. I've added this task:\n"
                        + eventTask.toString() + "\n"
                        + "Now you have " + storage.size() + ((storage.size() > 1) ? " tasks " : " task ") + "in the list.");
            }
            else {
                storage.add(new Task(input));
                System.out.println(horizontal_line + "added: " + input +"\n" + horizontal_line);
            }
        }

    }


}

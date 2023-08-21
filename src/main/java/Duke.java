import java.util.*;
public class Duke {
    public static String stringifyList(LinkedList<Task> linkedList) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < linkedList.size(); i++) {
            Task currTask = linkedList.get(i);
            str.append(String.valueOf(i + 1)).append(".")
                    .append(currTask.getTaskInfo())
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
                System.out.println(horizontal_line + outputList + horizontal_line);
            } else if (input.contains("unmark")) {
                int a = Integer.parseInt(input.substring(7));
                storage.get(a - 1).markAsUndone();
                System.out.println(horizontal_line
                        + "OK, I've marked this task as not done yet:\n"
                        + storage.get(a - 1).getTaskInfo() + "\n"
                        + horizontal_line);
            } else if (input.contains("mark")) {
                int a = Integer.parseInt(input.substring(5));
                storage.get(a-1).markAsDone();
                System.out.println(horizontal_line
                                    + "Nice! I've marked this task as done:\n"
                                    + storage.get(a - 1).getTaskInfo() + "\n"
                                    + horizontal_line);
            }
            else {
                storage.add(new Task(input));
                System.out.println(horizontal_line + "added: " + input +"\n" + horizontal_line);
            }
        }

    }


}

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasksList = new ArrayList<>();

    public static void main(String[] args) {
        String divider = "   _______________________________________ \n";
        String greeting = "   Hello! I'm Sana \n   What can I do for you? \n";
        String bye = "   Bye. Hope to see you again soon! \n";

        Scanner myObj = new Scanner(System.in);
        System.out.println(divider + greeting + divider);

        while(true) {
            String command = myObj.next();
            String userInput = myObj.nextLine();

            if (command.equals("bye")) {
                System.out.println(divider + bye + divider);
                break;
            }

            switch (command) {
            case "list":
                StringBuilder task = new StringBuilder();
                for (int i = 0; i < tasksList.size(); i++) {
                    int id = i + 1;
                    task.append("   " + id + "." + tasksList.get(i).toString() + "\n");
                }
                System.out.println(divider + "   Here are the tasks in your list: \n" + task
                        + divider);
                break;
            case "mark":
                int taskId = userInput.charAt(1) - '0';
                tasksList.get(taskId - 1).markAsDone();
                System.out.println(divider + "   Nice! I've marked this task as done: \n     "
                        + tasksList.get(taskId - 1).toString() + "\n" + divider);
                break;
            case "unmark":
                taskId = userInput.charAt(1) - '0';
                tasksList.get(taskId - 1).markAsNotDone();
                System.out.println(divider + "   OK, I've marked this task as not done yet: \n     "
                        + tasksList.get(taskId - 1).toString() + "\n" + divider);
                break;
            case "todo":
                Task newTodo = new Todo(userInput);
                tasksList.add(newTodo);
                System.out.println(divider + "   Got it. I've added this task: \n" + "    " + newTodo.toString() + "\n"
                        + "   Now you have " + tasksList.size() + (tasksList.size() <= 1 ? " task" : " tasks")
                        + " in the list \n" + divider);
                break;
            case "deadline":
                int lastDescId = userInput.indexOf('/');
                String desc = userInput.substring(0, lastDescId - 1);
                String by = userInput.substring(lastDescId + 4);

                Task newDeadline = new Deadline(desc, by);
                tasksList.add(newDeadline);
                System.out.println(divider + "   Got it. I've added this task: \n" + "    " + newDeadline.toString() + "\n"
                        + "   Now you have " + tasksList.size() + (tasksList.size() <= 1 ? " task" : " tasks")
                        + " in the list \n" + divider);
                break;
            case "event":
                lastDescId = userInput.indexOf('/');
                desc = userInput.substring(0, lastDescId - 1);
                int lastFromId = userInput.indexOf('/', lastDescId + 1);
                String from = userInput.substring(lastDescId + 6, lastFromId - 1);
                String to = userInput.substring(lastFromId + 4);

                Task newEvent = new Event(desc, from, to);
                tasksList.add(newEvent);
                System.out.println(divider + "   Got it. I've added this task: \n" + "    " + newEvent.toString() + "\n"
                        + "   Now you have " + tasksList.size() + (tasksList.size() <= 1 ? " task" : " tasks")
                        + " in the list \n" + divider);
                break;
            }
        }
    }
}

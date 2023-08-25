import java.awt.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> taskList;
    private Duke() {this.taskList = new ArrayList<Task>(); }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String chatbotName = "Duke";
        String header = "____________________________________________________________\n" +
                "Hello! I'm " + chatbotName +
                " \nWhat can I do for you?";

        System.out.println(header);
        Duke duke = new Duke();
        duke.executeDuke();

    }

    private void executeDuke() {
        while (true) {
            System.out.println("____________________________________________________________\n");
            String userInput = sc.nextLine();
            System.out.println("____________________________________________________________\n");
            if (userInput.equals("bye")){
                System.out.println("\tBye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("Here are your entries:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println("\t" + (i + 1) + ". " + taskList.get(i).toString());
                }
            } else if (userInput.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                if (taskIndex >= 0 && taskIndex < taskList.size()) {
                    taskList.get(taskIndex).markDone();
                    System.out.println("Nice! I've marked this task as done:\n\t" +
                            taskList.get(taskIndex).toString());
                } else {
                    System.out.println("Invalid task index.");
                }
            } else if (userInput.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                if (taskIndex >= 0 && taskIndex < taskList.size()) {
                    taskList.get(taskIndex).markNotDone();
                    System.out.println("OK, I've marked this task as not done yet:\n\t" +
                            taskList.get(taskIndex).toString());
                } else {
                    System.out.println("Invalid task index.");
                }
            } else if (userInput.startsWith("todo ")) {
                String todoDescription = userInput.replace("todo ", "");
                Todo newTodo = new Todo(todoDescription);
                taskList.add(newTodo);
                displayCompletionMessage(newTodo);
            }
            else if (userInput.startsWith("deadline ")) {
                String[] info = userInput.replace("deadline ", "").split(" /by ");
                Deadline deadline = new Deadline(info[0], info[1]);
                taskList.add(deadline);
                displayCompletionMessage(deadline);
            } else if (userInput.startsWith("event ")) {
                String[] info = userInput.replace("event ", "").split(" /from ");
                String description = info[0];
                String[] timeInfo = info[1].split(" /to ");
                String startTime = timeInfo[0];
                String endTime = timeInfo[1];

                Event event = new Event(description, startTime, endTime);
                taskList.add(event);
                displayCompletionMessage(event);

            }

        }
    }

    private void displayCompletionMessage(Task task) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + taskList.size() + " tasks in the list.");
    }


}

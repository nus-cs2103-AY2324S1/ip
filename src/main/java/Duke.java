import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Chatty\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        while (true) {
            String userInput = scanner.nextLine();
            String[] words = userInput.split(" ");

            if (words.length == 2) {
                if (words[0].equals("mark")) {
                    int taskIndex = Integer.parseInt(words[1]) - 1;
                    if (taskIndex >= 0 && taskIndex< taskList.size()) {
                        Task task = taskList.get(taskIndex);
                        task.markAsDone();
                        System.out.println("Nice! I've marked this task as done:\n " + task);
                    } else {
                        System.out.println("Invalid task index.");
                    }
                    continue;
                }

                if (words[0].equals("unmark")) {
                    int taskIndex = Integer.parseInt(words[1]) - 1;
                    if (taskIndex >= 0 && taskIndex< taskList.size()) {
                        Task task = taskList.get(taskIndex);
                        task.markAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:\n " + task);
                    } else {
                        System.out.println("Invalid task index.");
                    }
                    continue;
                }
            }

            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < taskList.size(); j++) {
                    int index = j + 1;
                    System.out.println(index + ". " + taskList.get(j).toString());
                }
            } else if (userInput.equals("bye")) {
                break;
            } else {
                Task newTask = createNewTask(userInput);

                taskList.add(newTask);
                System.out.println("Got it. I've added this task:\n " + newTask
                    + "\nNow you have " + taskList.size() +" tasks in the list.");
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    private static Task createNewTask(String userInput) {
        Task newTask;
        //Remove first word
        String[] arr = userInput.split(" ", 2);
        if (arr[0].equals("todo")) {
            newTask = new ToDo(arr[1]);
        } else if (arr[0].equals("deadline")) {
            String[] a = arr[1].split(" /by ");
            newTask = new Deadline(a[0], a[1]);
        } else {
            String[] a = arr[1].split(" /from ");
            String[] fromto = a[1].split("/to ");
            newTask = new Event(a[0], fromto[0], fromto[1]);
        }
        return newTask;
    }
}

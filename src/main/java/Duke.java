import java.util.Scanner;
import java.util.Arrays;
public class Duke {

    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int taskCount = 0;
        System.out.println(
                "____________________________________________________________\n" +
                        "Hello! I'm Ding!\n" +
                        "What can I do for you?\n" +
                        "____________________________________________________________\n");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            String command = str.split(" ")[0];
            switch (command) {
                case "list":
                    System.out.println("These are your tasks... If I remember correctly:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(String.format("%d. %s", i + 1, tasks[i]));
                    }
                    break;
                case "mark":
                    if (str.split(" ").length == 2) {
                        int taskIndex = Integer.parseInt(str.split(" ")[1]) - 1;
                        if (taskIndex >= taskCount) {
                            System.out.println("I can't find that task :( I may have lost it...");
                            break;
                        }
                        tasks[taskIndex].markAsDone();
                        System.out.println(String.format("Ding: Okay, I marked this task as done, but I have no idea what that means:\n %s", tasks[taskIndex]));
                    } else {
                        System.out.println("Ding: Which task do you want to mark as done?");
                    }
                    break;
                case "unmark":
                    if (str.split(" ").length == 2) {
                        int taskIndex = Integer.parseInt(str.split(" ")[1]) - 1;
                        if (taskIndex >= taskCount) {
                            System.out.println("I can't find that task :( I may have lost it...");
                            break;
                        }
                        tasks[taskIndex].markAsUndone();
                        System.out.println(String.format("Ding: Okay, I marked this task as undone, but I have no idea what that means:\n %s", tasks[taskIndex]));
                    } else {
                        System.out.println("Ding: Which task do you want to mark as undone?");
                    }
                    break;
                case "todo":
                    if (str.split(" ").length > 1) {
                        ToDo todo = new ToDo(str.split(" ")[1]);
                        tasks[taskCount] = todo;
                        if (taskCount < 100) {
                            taskCount++;
                        }
                        System.out.println(String.format("Ding: What does '%s' mean? I'll just add it anyway.\n You have like %d tasks now", str, taskCount));
                    } else {
                        System.out.println("I seriously have no idea what I need to do here");
                    }
                    break;
                case "deadline":
                    if (str.split(" ").length > 3) {
                        String fullTaskDescription = str.split(" ", 2)[1];
                        String description = fullTaskDescription.split(" /by ")[0];
                        String by = fullTaskDescription.split(" /by ")[1];

                        Deadline deadline = new Deadline(description, by);
                        tasks[taskCount] = deadline;
                        if (taskCount < 100) {
                            taskCount++;
                        }
                        System.out.println(String.format("Ding: What does '%s' mean? I'll just add it anyway.\n You have like %d tasks now", str, taskCount));
                    } else {
                        System.out.println("I seriously have no idea what I need to do here");
                    }
                    break;
                case "event":
                    if (str.split(" ").length > 4) {
                        String fullTaskDescription = str.split(" ", 2)[1];
                        String description = fullTaskDescription.split(" /from ")[0];
                        String from = String.join("", fullTaskDescription.split(" /from ")[1]).split(" /to ")[0];
                        String to = fullTaskDescription.split(" /to ")[1];

                        Event event = new Event(description, from, to);
                        tasks[taskCount] = event;
                        if (taskCount < 100) {
                            taskCount++;
                        }
                        System.out.println(String.format("Ding: What does '%s' mean? I'll just add it anyway.\n You have like %d tasks now", str, taskCount));
                    } else {
                        System.out.println("I seriously have no idea what I need to do here");
                    }
                    break;
                default:
                    System.out.println("I seriously have no idea what I need to do here");

            }
            System.out.println("\n____________________________________________________________\n");
            str = sc.nextLine();
        }
        System.out.println(
                "____________________________________________________________\n" +
                "Ding: Bye. Hopefully I get to see you again soon!\n" +
                "____________________________________________________________");

    }

}

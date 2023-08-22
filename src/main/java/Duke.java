import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static final String chatbot = "War Room";
    private static Task[] userData = new Task[100];
    private static int index = 0;

    public static void main(String[] args) {
        System.out.println("Hello! I'm " + chatbot);
        System.out.println("What can I do for you?");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String user_input = scanner.nextLine();
            String[] words = user_input.split(" ");
            /* This is the check for deadline */
            if (words.length > 0 && Objects.equals(words[0], "deadline")) {
                String taskDescription = "";
                int z = words.length;
                for (int j = 1; j < words.length; j++) {
                    if (Objects.equals(words[j], "/by")) {
                        z = j;
                        break;
                    } else {
                        taskDescription += " " + words[j];
                    }
                }
                String deadline = "";
                for (int i = z + 1; i < words.length; i++) {
                    deadline += " " + words[i];
                }
                Task newTask = new Deadline(taskDescription, deadline);
                userData[index] = newTask;
                index++;
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask.toString());
                System.out.println("Now you have " + index + " tasks in the list.");
            }
            /* This is the check for todo */
            else if (words.length > 0 && Objects.equals(words[0], "todo")) {
                String taskDescription = "";
                for (int j = 1; j < words.length; j++) {
                    taskDescription += " " + words[j];
                }
                Task newTask = new Todo(taskDescription);
                userData[index] = newTask;
                index++;
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask.toString());
                System.out.println("Now you have " + index + " tasks in the list.");
            }
            /* This is the check for event */
            else if (words.length > 0 && Objects.equals(words[0], "event")) {
                String taskDescription = "";
                int from = 0;
                int to = 0;
                for (int j = 1; j < words.length; j++) {
                    if (Objects.equals(words[j], "/from")) {
                        from = j;
                        break;
                    } else {
                        taskDescription += " " + words[j];
                    }
                }
                String fromChar = "";
                for (int i = from + 1; i < words.length; i++) {
                    if (Objects.equals(words[i], "/to")) {
                        to = i;
                        break;
                    } else {
                        fromChar += " " + words[i];
                    }
                }

                String toChar = "";
                for (int i = to + 1; i < words.length; i++) {
                    toChar += " " + words[i];
                }

                Task newTask = new Event(taskDescription, fromChar, toChar);
                userData[index] = newTask;
                index++;
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask.toString());
                System.out.println("Now you have " + index + " tasks in the list.");
            }
            else if (words.length > 0 && Objects.equals(words[0], "mark")) {
                int referenceIndex = Integer.parseInt(words[1]);
                Task currentTask = userData[referenceIndex - 1];
                currentTask.isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(currentTask.toString());
            } else if (words.length > 0 && Objects.equals(words[0], "unmark")) {
                int referenceIndex = Integer.parseInt(words[1]);
                Task currentTask = userData[referenceIndex - 1];
                currentTask.isDone = false;
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(currentTask.toString());
            } else if (Objects.equals(user_input, "list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    Task currentTask = userData[i];
                    System.out.println((i+1) + "." + currentTask.toString());
                }
            }
            else if (Objects.equals(user_input, "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scanner.close();
                break;
            }
        }
    }
}

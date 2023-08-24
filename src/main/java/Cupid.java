import java.util.Scanner;
import java.util.ArrayList;

public class Cupid {

    public enum ChatFunction {
        LIST,
        MARK,
        UNMARK
    }

    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println("____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, I'm Cupid.");
        System.out.println("What can I do for you?");

        while (true) {
            String input = scanner.nextLine();
            String[] inputArray = input.split(" ");

            try {
                ChatFunction function = ChatFunction.valueOf(inputArray[0].toUpperCase());
                switch (function) {
                    case LIST:
                        for (int i=0; i<taskList.size(); i++) {
                            String message = String.format("%d. [%s] %s", i+1, taskList.get(i).getStatusIcon(), taskList.get(i).getDescription());
                            System.out.println(message);
                        };
                        continue;

                    case MARK:
                        if (inputArray.length < 2) {
                            System.out.println("Invalid mark task provided.");
                            break;
                        }

                        try {
                            int targetTaskIdx = Integer.parseInt(inputArray[1]) -1;
                            Task markTask = taskList.get(targetTaskIdx);
                            markTask.markAsDone();
                            System.out.println("Nice! I've marked this task as done:");
                            String message = String.format("[%s] %s", markTask.getStatusIcon(), markTask.getDescription());
                            System.out.println(message);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number provided.");
                            break;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Invalid task number provided.");
                            break;
                        }

                    case UNMARK:
                        if (inputArray.length < 2) {
                            System.out.println("Invalid unmark task provided.");
                            break;
                        }

                        try {
                            int targetTaskIdx = Integer.parseInt(inputArray[1]) -1;
                            Task task = taskList.get(targetTaskIdx);
                            task.markAsUndone();
                            System.out.println("Ok! I've marked this task as not done yet:");
                            String message = String.format("[%s] %s", task.getStatusIcon(), task.getDescription());
                            System.out.println(message);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number provided.");
                            break;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Invalid task number provided.");
                            break;
                        }

                    default:
                        break;
                }
            } catch (IllegalArgumentException e) {
                // If task inserted not an ENUM
                Task newTask = new Task(input);
                taskList.add(newTask);
                System.out.println("Added: " + input);
            }


            if (input.toLowerCase().equals("bye")) {
                break;
            }
        }



        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}

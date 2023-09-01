import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    public void showWelcomeMessage() {
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Hello! I am Nila");
        System.out.println("What can I do for you?");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    //Method to display command line
    public void showCommandLine(){
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
    }
    // Method to display a message
    public void showMessage(String message) {
        System.out.println(message);
    }

    // Method to display an error message
    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    // Method to display the list of tasks
    public void showTaskList(ArrayList<Task> taskList) {
        System.out.println("Here are your tasks:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }

    // Method to display a task added message
    public void showTaskAddedMessage() {
        System.out.println("Got it! I've added the task to your list.");
    }

    public void showCurrentStatus(ArrayList<Task> taskList){
        System.out.println(taskList.get(taskList.size() - 1).getStatusIcon());
        System.out.println(" Now you have " + taskList.size() +" tasks in the list.");
    }

    // Method to display a task deleted message
    public void showTaskDeletedMessage() {
        System.out.println("Noted! I've removed the task from your list.");
    }

    public void showTaskMarkedMessage() {
        System.out.println("Nice! I've marked this task as done.");
    }

    public void showTaskUnmarkedMessage() {
        System.out.println(" OK, I've marked this task as not done yet.");
    }

    // Method to display an unknown command message
    public void showUnknownCommandMessage() {
        System.out.println("Sorry, I don't understand that command.");
    }

    // Method to show loading error message
    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting with an empty list.");
    }

    // Method to display a goodbye message
    public void showGoodbyeMessage() {
        System.out.println("\nBye. Hope to see you again soon!");
    }

}
//    public void start() {
//
//        Scanner sc = new Scanner(System.in);
//        String str = sc.nextLine();
//
//        while (!str.equals("bye")) {
//            String[] input = str.split(" ", 2);
//
//            try {
//                if (str.equals("todo") || str.equals("deadline") || str.equals("event")) {
//                    throw new DukeException("OOPS!!! The description of a " + str + " cannot be empty.");
//                } else if (str.equals("mark") || str.equals("unmark") || str.equals("delete")) {
//                    throw new DukeException("OOPS!!! Please specify which task you want to mark, unmark or delete .");
//                } else if (input[0].equals("list")) {
//                    taskmanager.displayList();
//                    str = sc.nextLine();
//                } else if (input[0].equals("mark")) {
//                    int index = Integer.parseInt(input[1]) - 1;
//                    taskmanager.mark(index);
//                    str = sc.nextLine();
//                } else if (input[0].equals("unmark")) {
//                    int index = Integer.parseInt(input[1]) - 1;
//                    taskmanager.unmark(index);
//                    str = sc.nextLine();
//                } else if (input[0].equals("todo")) {
//                    String description = input[1];
//                    taskmanager.todo(description);
//                    str = sc.nextLine();
//                } else if (input[0].equals("deadline")) {
//                    String[] full_desc = input[1].split(" /by ");
//                    String description = full_desc[0];
//                    LocalDateTime by = LocalDateTime.parse(full_desc[1]);
//                    taskmanager.deadline(description, by);
//                    str = sc.nextLine();
//                } else if (input[0].equals("event")) {
//                    String[] full_desc = input[1].split(" /from | /to ");
//                    String description = full_desc[0];
//                    LocalDateTime from = LocalDateTime.parse(full_desc[1]);
//                    LocalTime to = LocalTime.parse(full_desc[2]);
//                    taskmanager.event(description, from, to);
//                    str = sc.nextLine();
//                } else if (input[0].equals("delete")) {
//                    int index = Integer.parseInt(input[1]) - 1;
//                    taskmanager.delete(index);
//                    str = sc.nextLine();
//                } else {
//                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
//                }
//            } catch (DukeException e) {
//                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
//                System.out.println(e.getMessage());
//                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
//                str = sc.nextLine();
//            }
//        }
//
//        sc.close();
//    }
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //Initiate Scanner to handle user input
        Scanner scanner = new Scanner(System.in);

        // Introduction Message
        System.out.println(logo);
        System.out.println("Hello! I'm Chad");
        System.out.println("What can I do for you?");
        System.out.println();

        // Task List Storage
        ArrayList<Task> taskList = new ArrayList<>();

        // Saving User input into a list
        while (true) {
            String userInput = scanner.nextLine();
            Task userTask = new Task(userInput);
            // If user types bye, goodbye message is printed.
            if (userTask.getTaskName().equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again");
                break;
            }
            // If user types list, the list is printed.
            else if (userTask.getTaskName().equalsIgnoreCase("list")){
                System.out.println("Here are the tasks for your list:");
                for (int i = 0; i < taskList.size(); i++){
                    // If task is uncompleted, it will not contain a "X"
                    if (!taskList.get(i).isTaskCompleted()){
                        System.out.println(Integer.toString(i+1) + ".[ ] " + taskList.get(i).getTaskName());
                    }
                    // If task is completed, it will contain a "X"
                    else {
                        System.out.println(Integer.toString(i+1) + ".[X]" + taskList.get(i).getTaskName());
                    }
                }
            } else if (userTask.getTaskName().contains("mark") && !userTask.getTaskName().contains("unmark")){
                // Obtain the task number by excluding the first 5 characters of the task
                int taskNumber = Integer.parseInt(userTask.getTaskName().substring(5)) - 1;
                Task taskToBeMarked = taskList.get(taskNumber);
                // If the task has already been marked, prompt the user
                if (taskToBeMarked.isTaskCompleted()){
                    System.out.println("Task has already been marked as completed.");
                }
                // Mark task as completed
                else {
                    Task markedTask = taskToBeMarked.markTaskCompleted();
                    taskList.set(taskNumber, markedTask);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] "+taskToBeMarked.getTaskName());
                }

            } else if (userTask.getTaskName().contains("unmark")) {
                // Obtain the task number by excluding the first 6 characters of the task
                int taskNumber2 = Integer.parseInt(userTask.getTaskName().substring(7)) - 1;
                Task taskToBeUnmarked = taskList.get(taskNumber2);
                // If the task has already been marked, prompt the user
                if (!taskToBeUnmarked.isTaskCompleted()) {
                    System.out.println("Task has already been marked as uncompleted.");
                }
                // If the task has already been marked, prompt the user
                else {
                    Task unmarkedTask = taskToBeUnmarked.markTaskUncompleted();
                    taskList.set(taskNumber2, unmarkedTask);
                    System.out.println("OK, I've marked this task as not done yet");
                    System.out.println("[ ] " + taskToBeUnmarked.getTaskName());
                }
            }
            // If user types neither list nor bye, the task is added to the list and displayed.
            else {
                taskList.add(userTask);
                System.out.println("added: " + userTask.getTaskName());
            }
        }
        scanner.close();
    }
}

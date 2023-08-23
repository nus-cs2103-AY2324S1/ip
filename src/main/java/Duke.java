import java.sql.SQLOutput;
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
            // If user types bye, goodbye message is printed.
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            // If user types list, the list is printed.
            else if (userInput.equalsIgnoreCase("list")){
                System.out.println("Here are the tasks for your list:");
                for (int i = 0; i < taskList.size(); i++){
                    System.out.println(Integer.toString(i+1) + "." + taskList.get(i).toString());
                }
            }
            // If user types mark command, we mark the event
            else if (userInput.contains("mark") && !userInput.contains("unmark")){
                // Obtain the task number by excluding the first 5 characters of the task
                int taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
                Task taskToBeMarked = taskList.get(taskNumber);
                // If the task has already been marked, prompt the user
                if (taskToBeMarked.isTaskCompleted()){
                    System.out.println("Task has already been marked as completed.");
                }
                else {
                    // Mark task as completed
                    taskToBeMarked.markTaskCompleted();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskToBeMarked.toString());
                }
            // If user types unmark command, we unmark the event
            } else if (userInput.contains("unmark")) {
                // Obtain the task number by excluding the first 6 characters of the task
                int taskNumber2 = Integer.parseInt(userInput.substring(7)) - 1;
                Task taskToBeUnmarked = taskList.get(taskNumber2);
                // If the task has already been unmarked, prompt the user
                if (!taskToBeUnmarked.isTaskCompleted()) {
                    System.out.println("Task has already been marked as uncompleted.");
                }
                else {
                    // Mark task as uncompleted
                    taskToBeUnmarked.markTaskUncompleted();
                    System.out.println("OK, I've marked this task as not done yet");
                    System.out.println(taskToBeUnmarked.toString());
                }
            }
            // Identify type of task and add it to the list
            else {
                if (userInput.contains("todo")){
                    Todo toDoTask = new Todo(userInput.substring(5));
                    taskList.add(toDoTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(toDoTask.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                }
                else if (userInput.contains("deadline")){
                    String[] deadlineString = userInput.substring(9).split("/");
                    Deadline deadlineTask = new Deadline(deadlineString[0].trim(), deadlineString[1].substring(3));
                    taskList.add(deadlineTask);
                    System.out.println("Got it, I've added this task:");
                    System.out.println(deadlineTask.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                }
                else if (userInput.contains("event")){
                    String[] eventString = userInput.substring(6).split("/");
                    Event eventTask = new Event(eventString[0].trim(), eventString[1].substring(5).trim(), eventString[2].substring(3));
                    taskList.add(eventTask);
                    System.out.println("Got it, I've added this task:");
                    System.out.println(eventTask.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                }
                else {
                    Task task = new Task(userInput);
                    taskList.add(task);
                    System.out.println("Got it, I've added this task:");
                    System.out.println(task.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                }
            }
        }
        scanner.close();
    }
}

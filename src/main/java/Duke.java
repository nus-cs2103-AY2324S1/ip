import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
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
            String userInput = scanner.nextLine().trim();
            // If user types bye, goodbye message is printed.
            try {
                // Program terminates if user types "bye" and only "bye"
                if (userInput.contains("bye")){
                    // Check if only "bye" is typed and not "bye bye" (Example)
                    if (userInput.length() == 3){
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    } else {
                        throw new DukeException("I'm sorry, I don't understand this command. Did you mean to type 'bye' ?");
                    }
                }
                // If user types list, the list is printed. Otherwise, an exception is thrown.
                else if (userInput.contains("list")){
                    // Check if only list is typed and not "list my items" (Example)
                    if (userInput.length() == 4){
                        System.out.println("Here are the tasks for your list:");
                        for (int i = 0; i < taskList.size(); i++){
                            System.out.println(Integer.toString(i+1) + "." + taskList.get(i).toString());
                        }
                    } else {
                        throw new DukeException("I'm sorry, I don't understand this command. Did you mean to type 'list' ?");
                    }
                }
                // If user types mark command, we mark the event
                else if (userInput.contains("mark") && !userInput.contains("unmark")){
                    if(userInput.length() == 4){
                        // Check if task number is present
                        throw new DukeException("I'm sorry, you seem to have forgotten to enter the task number.");
                    } else {
                        try {
                            int taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
                            Task taskToBeMarked = taskList.get(taskNumber);
                            if (taskToBeMarked.isTaskCompleted()) {
                                // If task is already completed, prompt user.
                                throw new DukeException("Task has already been marked as completed.");
                            } else {
                                // Mark task as completed.
                                taskToBeMarked.markTaskCompleted();
                                System.out.println("Nice! I've marked this task as done:");
                                System.out.println(taskToBeMarked.toString());
                            }
                        } catch (IndexOutOfBoundsException e){
                            // Check if task number provided is within size of task list.
                            throw new DukeException("I'm sorry, the task number you have entered a number that exceeds the size of your task list.");
                        }
                    }
                    // If user types unmark command, we unmark the event
                } else if (userInput.contains("unmark")) {
                    if (userInput.length() == 6){
                        throw new DukeException("I'm sorry, you seem to have forgotten to enter the task number.");
                    } else {
                        try {
                            int taskNumber2 = Integer.parseInt(userInput.substring(7)) - 1;
                            Task taskToBeUnmarked = taskList.get(taskNumber2);
                            // If the task has already been unmarked, prompt the user
                            if (!taskToBeUnmarked.isTaskCompleted()) {
                                throw new DukeException("Task has already been marked as uncompleted.");
                            }
                            else {
                                // Mark task as uncompleted
                                taskToBeUnmarked.markTaskUncompleted();
                                System.out.println("OK, I've marked this task as not done yet");
                                System.out.println(taskToBeUnmarked.toString());
                            }
                        } catch (IndexOutOfBoundsException e){
                            throw new DukeException("I'm sorry, the task number you have entered a number that exceeds the size of your task list.");
                        }
                    }
                }
                // Identify type of task and add it to the list
                else {
                    if (userInput.contains("todo")){
                        // Check if event description is present.
                        if (userInput.length() == 4){
                            throw new DukeException("I'm sorry, the description of a todo cannot be empty.");
                        } else{
                            Todo toDoTask = new Todo(userInput.substring(5));
                            taskList.add(toDoTask);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(toDoTask.toString());
                            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        }
                    }
                    else if (userInput.contains("deadline")){
                        if (userInput.length() == 8){
                            throw new DukeException("I'm sorry, the description of a deadline cannot be empty.");
                        } else {
                            String[] deadlineString = userInput.substring(9).split("/");
                            if (deadlineString.length == 1){
                                // Check if deadline is present.
                                throw new DukeException("Deadline is not provided. Please indicate your deadline by placing a '/by' before the deadline.");
                            } else {
                                Deadline deadlineTask = new Deadline(deadlineString[0].trim(), deadlineString[1].substring(3));
                                taskList.add(deadlineTask);
                                System.out.println("Got it, I've added this task:");
                                System.out.println(deadlineTask.toString());
                                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                            }
                        }
                    }
                    else if (userInput.contains("event")){
                        if (userInput.length() == 5){
                            throw new DukeException("I'm sorry, the description of an event cannot be empty.");
                        } else {
                            String[] eventString = userInput.substring(6).split("/");
                            // Check if a start and end time has been provided.
                            if (eventString.length < 3){
                                throw new DukeException("I'm sorry, you seem to have forgotten to enter a start/end time. " +
                                        "Please indicate the start time starting with /from and the end time starting with /to.");
                            } else if (eventString.length > 3){
                                throw new DukeException("I'm sorry, you seem to have entered too many timings.");
                            } else {
                                Event eventTask = new Event(eventString[0].trim(), eventString[1].substring(5).trim(), eventString[2].substring(3));
                                taskList.add(eventTask);
                                System.out.println("Got it, I've added this task:");
                                System.out.println(eventTask.toString());
                                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                            }
                        }
                    }
                    else {
                        throw new DukeException("I'm sorry, I don't understand this task.");
                    }
                }
            } catch (DukeException exception) {
                System.out.println(exception.getMessage() + "\n");
            }
        }
            scanner.close();
    }
}

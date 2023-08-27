import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        horizontalLine();
        System.out.println("Hello! I'm Snow!");
        System.out.println("What can I do for you?");
        horizontalLine();

        Scanner userInput = new Scanner(System.in);  // Create a Scanner object
        String userOutput = userInput.nextLine();  // Read user input
        ArrayList<Task> inputList = new ArrayList<>();
        while (!userOutput.equals("bye")) {
            try {
                String[] splitOutput = userOutput.split(" ");
                if (userOutput.equals("list")) {
                    if (inputList.isEmpty()) {
                        System.out.println("There is no task in your list.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < inputList.size(); i++) {
                            System.out.println(i + 1 + ". " + inputList.get(i).toString());
                        }
                    }
                } else if (splitOutput[0].equals("deadline")) {
                    Deadline.setDeadline(userOutput, inputList);
                } else if (splitOutput[0].equals("todo")) {
                    Todo.setTodo(userOutput, inputList);
                } else if (splitOutput[0].equals("event")) {
                    Event.setEvent(userOutput, inputList);
                } else if (splitOutput[0].equals("delete")) {
                    try {
                        Integer deleteIndex = Integer.valueOf(splitOutput[1]);
                        Task deletedTask = inputList.get(deleteIndex - 1);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(deletedTask);
                        inputList.remove(deleteIndex - 1);
                        System.out.println("Now you have " + inputList.size() + " tasks in the list.");
                    } catch (Exception e) {
                        throw new IndexOutOfBoundsException();
                    }
                } else if (splitOutput[0].equals("mark")) {
                    System.out.println("Nice! I've marked this task as done:");
                    Task selectedTask = inputList.get(Integer.parseInt(splitOutput[1]) - 1);
                    selectedTask.mark();
                    System.out.println(selectedTask.toString());
                } else if (splitOutput[0].equals("unmark")) {
                    System.out.println(" OK, I've marked this task as not done yet:");
                    Task selectedTask = inputList.get(Integer.parseInt(splitOutput[1]) - 1);
                    selectedTask.unmark();
                    System.out.println(selectedTask.toString());
                } else if (userOutput.equals("bye")) {
                    break;
                } else {
                    throw new InvalidInputException();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            createFile(inputList);
            userOutput = userInput.nextLine();  // Read user input
        }
        printBye();
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void horizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void createFile(ArrayList<Task> tasks) {
        try {
            File myObj = new File("./data/duke.txt");
            myObj.createNewFile();
            FileWriter myWriter = new FileWriter("./data/duke.txt");
            Integer taskSize = tasks.size();
            for (int i = 0; i < taskSize; i++) {
                myWriter.write(String.valueOf(tasks.get(i)) + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

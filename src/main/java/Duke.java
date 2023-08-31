import java.util.Scanner;
import java.lang.Exception;
import java.util.ArrayList;

//My chatbot function
public class Duke {
    public static void main(String[] args) {
        final String SPACE = "------------------------------------"; // for spacing purposes
        String name = "Adam's Bot"; // name of bot
        ArrayList<Task> toDoList = new ArrayList<Task>(); // ArrayList to store the items
        int counter = 0; // Counter to keep track of pointer

        System.out.println(SPACE);
        System.out.println("Hello! I'm "+ name);
        System.out.println("What can I do for you?");
        System.out.println(SPACE);

        // Allow user input
        Scanner scanner = new Scanner(System.in);
        String userInput;
        userInput = scanner.nextLine().trim();// remove trailing spaces and get use input

        // prompt for user input if input is not "bye"
        while(!userInput.equalsIgnoreCase("bye")) {

            // if user input is "list", list out all the tasks
            if (userInput.equalsIgnoreCase("list")) {

                System.out.println(SPACE);

                // iterate through ArrayList to print tasks
                for (int i = 0; i < toDoList.size(); i++) {
                    int currentNumber = i + 1;
                    System.out.println(currentNumber + ". " + toDoList.get(i).toString());
                }
                System.out.println(SPACE);

            // if user input starts with mark, mark the task on the list as done with a cross and print out the task
            } else if (userInput.toLowerCase().startsWith("mark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) -1;
                Task task = toDoList.get(index);
                System.out.println(SPACE);
                task.markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + task.toString());
                System.out.println(SPACE);

            // if user input starts with unmark, unmark the task on the list and remove the cross and print out the task
            } else if (userInput.toLowerCase().startsWith("unmark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) -1;
                Task task = toDoList.get(index);
                System.out.println(SPACE);
                task.unmarkDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + task.toString());
                System.out.println(SPACE);

            // if user input starts with todo, insert a todo task into the list and print out the task
            } else if (userInput.toLowerCase().startsWith("todo")){
                try {
                    String taskName = userInput.split(" ", 2)[1];
                    //add item into list
                    ToDo task = new ToDo(taskName);
                    toDoList.add(counter, task);
                    counter++;
                    System.out.println(SPACE);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task.toString());
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    System.out.println(SPACE);
                } catch(ArrayIndexOutOfBoundsException e) {
                    System.out.println(SPACE);
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(SPACE);
                }

            // if user input starts with deadline, insert a deadline task into the list and print out the task
            } else if (userInput.toLowerCase().startsWith("deadline")){
                try {

                    String taskName = userInput.split(" /by ", 2)[0].split(" ", 2)[1];
                    String dueDate = userInput.split(" /by ", 2)[1];
                    //add item into list
                    Deadline task = new Deadline(taskName, dueDate);
                    toDoList.add(counter, task);
                    counter++;
                    System.out.println(SPACE);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task.toString());
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    System.out.println(SPACE);
                } catch ( ArrayIndexOutOfBoundsException e) {
                    if (userInput.split(" ").length == 1) {
                        System.out.println(SPACE);
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty");
                        System.out.println(SPACE);
                    } else {
                        System.out.println(SPACE);
                        System.out.println("☹ OOPS!!! The description of a deadline does not have \"/by\" specified");
                        System.out.println(SPACE);
                    }
                }

            // if user input starts with event, insert an event task into the list and print out the task
            } else if (userInput.toLowerCase().startsWith("event")){
                try {

                    String taskName = userInput.split(" /from | /to ", 3)[0].split(" ", 2)[1];
                    String startDate = userInput.split(" /from | /to", 3)[1];
                    String dueDate = userInput.split(" /from | /to ", 3)[2];
                    //add item into list
                    Event task = new Event(taskName, startDate, dueDate);
                    toDoList.add(counter, task);
                    counter++;
                    System.out.println(SPACE);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task.toString());
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    System.out.println(SPACE);
                } catch (ArrayIndexOutOfBoundsException e) {
                    if (userInput.split(" ").length == 1) {
                        System.out.println(SPACE);
                        System.out.println("☹ OOPS!!! The description of a event cannot be empty");
                        System.out.println(SPACE);
                    } else {
                        System.out.println(SPACE);
                        System.out.println("☹ OOPS!!! The description of a deadline does not have either \"/from\" or \"/to\" specified");
                        System.out.println(SPACE);
                    }
                }

            // if user input starts with delete, check the number that the user input and delete the task of that number from the list
            } else if (userInput.toLowerCase().startsWith("delete")) {
                try {
                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    Task task = toDoList.get(index);
                    toDoList.remove(index);
                    counter--;
                    System.out.println(SPACE);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + task.toString());
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    System.out.println(SPACE);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(SPACE);
                    System.out.println("☹ OOPS!!! The task number you entered is not in the list.");
                    System.out.println(SPACE);
                } catch (NumberFormatException e) {
                    System.out.println(SPACE);
                    System.out.println("☹ OOPS!!! The task number you entered is invalid. Please enter a number.");
                    System.out.println(SPACE);
                }

            // if user input and invalid input, ask user to input again
            } else {
                System.out.println(SPACE);
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(SPACE);
            }
            userInput = scanner.nextLine().trim();
        }

        System.out.println(SPACE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SPACE);
    }
}

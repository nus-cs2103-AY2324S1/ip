import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Corubi {
    public static void main(String[] args) {
        // Setting of final parameters
        final String name = "Corubi";
        final String divider = "---------------------------------------------------";
        ArrayList<Task> enteredText = new ArrayList();

        // Initiate the bot greeting
        Scanner sc = new Scanner(System.in);
        System.out.println(divider);
        System.out.println("Hello! I am " + name + ". \nWhat can I do for you?");
        System.out.println(divider);

        // Allow user input
        String input = sc.nextLine();

        // List of accpeted commands
        ArrayList<String> commands = new ArrayList<>();
        String[] commandList = {"todo", "deadline", "event", "mark", "unmark", "bye"};
        Collections.addAll(commands, commandList);

        // Check if input command is in the list of accepted commands
        try {
            if (!commands.contains(input.split(" ")[0])) {
                throw new WrongCommandException(input);
            }
        } catch (WrongCommandException e) {
            System.out.println(e.getMessage());
            input = sc.nextLine();
        }

        // Exit the chatbot if the user says "bye"
        while (!input.equals("bye") && !input.equals("Bye")) {

            // If input is "list" command, show the list.
            if (input.equals("list") || input.equals("List")) {
                for (int i = 0; i < enteredText.size(); i++) {
                    System.out.printf("%d. %s \n", i + 1, enteredText.get(i).toString());
                }
                System.out.println(divider);
                input = sc.nextLine();
            } else if (input.contains("unmark") || input.contains("Unmark")) {
                // If command is unmark, then unmark the item

                // Split the input by spaces
                String[] splitInput = input.split(" ");

                // Iterate through the parts to find the number
                for (String num : splitInput) {
                    try {
                        int number = Integer.parseInt(num);

                        // Handle the exception if number provided is beyond the size of list
                        try {
                            enteredText.get(number - 1).unmarkDone();
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(number + " is too high! List size is only " + enteredText.size());
                        } finally {
                            System.out.println(divider);
                            input = sc.nextLine();
                            break; // Stop searching after first number is found
                        }
                    } catch (NumberFormatException e) {
                        // Not a number, continue searching
                    }
                }
            } else if (input.contains("mark") || input.contains("Mark")) {
                // If the input contains the word mark, mark the item number as done

                // Split the input by spaces
                String[] splitInput = input.split(" ");

                // Iterate through the parts to find the number
                for (String num : splitInput) {
                    try {
                        int number = Integer.parseInt(num);

                        // Handle the exception if number provided is beyond the size of list
                        try {
                            enteredText.get(number - 1).markDone();
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(number + " is too high! List size is only " + enteredText.size());
                        } finally {
                            System.out.println(divider);
                            input = sc.nextLine();
                            break; // Stop searching after first number is found
                        }
                    } catch (NumberFormatException e) {
                        // Not a number, continue searching
                    }
                }
            } else {
                // Add the input to the list
                if (input.contains("todo ")) {
                    input = input.split("todo ")[1];
                    Task newTask = new ToDos(input);
                    enteredText.add(newTask);
                    System.out.println("Okay! I have added the following task\n" + newTask.toString());
                } else if (input.contains("deadline ")) {
                    input = input.split("deadline ")[1];
                    String[] splitString = input.split("/by");
                    Task newTask = new Deadlines(splitString[0], splitString[1]);
                    enteredText.add(newTask);
                    System.out.println("Okay! I have added the following task\n" + newTask.toString());
                } else if (input.contains("event ")) {
                    input = input.split("event ")[1];
                    String taskName = input.split("/from")[0];
                    String to = input.split("/to")[1];
                    String from = input.split("to ")[0].split("/from ")[1];
                    Task newTask = new Events(taskName, from, to);
                    enteredText.add(newTask);
                    System.out.println("Okay! I have added the following task\n" + newTask.toString());
                }
                System.out.println(divider);
                input = sc.nextLine();
            }
        }
        System.out.println(input + " " + input + "...please come back soon :(");
    }
}

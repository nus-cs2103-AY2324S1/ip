package Ui;
import Tasks.*;
import Parser.Parser;
import java.io.IOException;
import CustomExceptions.WrongCommandException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import Storage.Storage;

public class Ui {
    // Setting of final parameters
    final String name = "Corubi";
    final String divider = "---------------------------------------------------";

    // Array of Tasks that user has entered
    String input;

    /**
     * Displays the bot's greeting message.
     */
    public void start() {
        // Initiate the bot greeting
        System.out.println(divider);
        System.out.println("Hello! I am " + name + ". \nWhat can I do for you?");
        System.out.println(divider);
    }

    /**
     * Handles user input and performs corresponding actions.
     *
     * @param store  The Storage instance for managing data persistence.
     * @param tasks  The TaskList instance for managing tasks.
     * @param parser The Parser instance for parsing user input.
     * @throws IOException If an I/O operation is interrupted.
     */
    public void takeCommands(Storage store, TaskList tasks, Parser parser) throws IOException {
        Scanner sc = new Scanner(System.in);
        store.load(parser);
        input = sc.nextLine();

        // List of accepted commands
        ArrayList<String> commands = new ArrayList<>();
        String[] commandList = {"todo", "deadline", "event", "mark", "unmark", "bye"};
        Collections.addAll(commands, commandList);

        // Exit the chatbot if the user says "bye"
        while (!input.equals("bye") && !input.equals("Bye")) {

            // If input is "list" command, show the list.
            if (input.equals("list") || input.equals("List")) {
                System.out.println(divider);
                tasks.printList();
                input = sc.nextLine();
            } else if (input.contains("unmark") || input.contains("Unmark")) {
                // If command is unmark, then unmark the item

                int number = parser.findNum(input);
                // Handle the exception if number provided is beyond the size of list
                try {
                    tasks.retrieve(number - 1).unmarkDone();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(number + " is too high! List size is only " + tasks.size());
                } finally {
                    System.out.println(divider);
                    store.overwrite();
                    input = sc.nextLine();
                }
            } else if (input.contains("mark") || input.contains("Mark")) {
                // If the input contains the word mark, mark the item number as done

                int number = parser.findNum(input);

                // Handle the exception if number provided is beyond the size of list
                try {
                    tasks.retrieve(number - 1).markDone();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(number + " is too high! List size is only " + tasks.size());
                } finally {
                    System.out.println(divider);
                    store.overwrite();
                    input = sc.nextLine();
                }
            } else if (input.contains("delete")) {
                // The delete command

                int number = parser.findNum(input);

                // Handle the exception if number provided is beyond the size of list
                try {
                    Task index = tasks.retrieve(number - 1);
                    tasks.remove(index);
                    System.out.printf("I have deleted the following task:\n" +
                            "%s\n" +
                            "Your list has %d items left\n\n", index.toString(), tasks.size());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(number + " is too high! List size is only " + tasks.size());
                }
                System.out.println(divider);
                store.overwrite();
                input = sc.nextLine();
            } else {
                // Add the input to the list
                if (input.contains("todo ")) {
                    Task newTask = new ToDos(parser.taskName(input), false);
                    tasks.add(newTask);
                    store.write(newTask);
                    System.out.println("Okay! I have added the following task\n" + newTask);
                } else if (input.contains("deadline ")) {
                    Task newTask = new Deadlines(parser.taskName(input), parser.taskBy(input), false);
                    tasks.add(newTask);
                    System.out.println("Okay! I have added the following task\n" + newTask);
                    store.write(newTask);
                } else if (input.contains("event ")) {
                    Task newTask = new Events(parser.taskName(input), parser.taskFrom(input), parser.taskTo(input), false);
                    tasks.add(newTask);
                    System.out.println("Okay! I have added the following task\n" + newTask.toString());
                    store.write(newTask);
                } else {
                    // Check if input command is in the list of accepted commands
                    try {
                        if (!commands.contains(input.split(" ")[0])) {
                            throw new WrongCommandException(input);
                        }
                    } catch (WrongCommandException e) {
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println(divider);
                input = sc.nextLine();
            }
        }
        System.out.println(input + " " + input + "...please come back soon :(");
    }
}

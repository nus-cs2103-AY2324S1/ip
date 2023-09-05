package ui;

import customexceptions.WrongCommandException;
import parser.Parser;
import storage.Storage;
import tasks.Deadlines;
import tasks.Events;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


/**
 * The `Ui` class handles user interactions and provides a text-based interface for the Corubi chatbot.
 */
public class Ui {
    private final String NAME = "Corubi";
    private final String DIVIDER = "---------------------------------------------------";

    private String input;

    /**
     * Displays the bot's greeting message.
     */
    public void start() {
        System.out.println(DIVIDER);
        System.out.println("Hello! I am " + NAME + ". \nWhat can I do for you?");
        System.out.println(DIVIDER);
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

        ArrayList<String> commands = new ArrayList<>();
        String[] commandList = {"todo", "deadline", "event", "mark", "unmark", "bye"};
        Collections.addAll(commands, commandList);

        while (!input.equals("bye") && !input.equals("Bye")) {
            if (input.equals("list") || input.equals("List")) {
                System.out.println(DIVIDER);
                tasks.printList();
                input = sc.nextLine();
            } else if (input.contains("unmark") || input.contains("Unmark")) {
                int number = parser.findNum(input);
                try {
                    tasks.retrieve(number - 1).unmarkDone();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(number + " is too high! List size is only " + tasks.size());
                } finally {
                    System.out.println(DIVIDER);
                    store.overwrite();
                    input = sc.nextLine();
                }
            } else if (input.contains("mark") || input.contains("Mark")) {
                int number = parser.findNum(input);
                try {
                    tasks.retrieve(number - 1).markDone();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(number + " is too high! List size is only " + tasks.size());
                } finally {
                    System.out.println(DIVIDER);
                    store.overwrite();
                    input = sc.nextLine();
                }
            } else if (input.contains("delete")) {
                int number = parser.findNum(input);
                try {
                    Task index = tasks.retrieve(number - 1);
                    tasks.remove(index);
                    System.out.printf("I have deleted the following task:\n%s\nYour list has %d items left\n\n",
                            index.toString(), tasks.size());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(number + " is too high! List size is only " + tasks.size());
                }
                System.out.println(DIVIDER);
                store.overwrite();
                input = sc.nextLine();
            } else if (input.contains("find ")) {
                System.out.println(DIVIDER);
                System.out.println("Here are the matching items in your list:\n");
                tasks.find(parser.find(input));
                input = sc.nextLine();
            } else {
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
                    Task newTask = new Events(parser.taskName(input), parser.taskFrom(input), parser.taskTo(input),
                            false);
                    tasks.add(newTask);
                    System.out.println("Okay! I have added the following task\n" + newTask.toString());
                    store.write(newTask);
                } else {
                    try {
                        if (!commands.contains(input.split(" ")[0])) {
                            throw new WrongCommandException(input);
                        }
                    } catch (WrongCommandException e) {
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println(DIVIDER);
                input = sc.nextLine();
            }
        }
        System.out.println(input + " " + input + "...please come back soon :(");
    }
}

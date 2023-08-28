package main.java;
/**
 * The Duke class is responsible for responding to user's input.
 * It provides functionalities to add, mark, unmark, and delete tasks.
 */

import main.java.command.Command;
import main.java.exception.EmptyTodoException;
import main.java.exception.UnknownCommandException;
import main.java.exception.EmptyChoiceException;
import main.java.parser.Parser;
import main.java.tasklist.TaskList;
import main.java.ui.Ui;

import java.util.Scanner;

public class Botty {

    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Botty() {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.parser = new Parser();
    }
    public static void main(String[] args) {
        new Botty().run();
    }

    public void run() {
        String name = "Botty";
        String tmp = "";
        Scanner scanner = new Scanner(System.in);
        this.ui.greet(name);
        while (true) {
            tmp = scanner.nextLine();
            if (tmp.equals("bye")) {
                break;
            } else {
                try {
                    Command command = this.parser.parseInstruction(tmp);
                    command.execute(this.taskList, this.ui);
                } catch (UnknownCommandException e) {
                    System.out.println(e.getMessage());
                } catch (EmptyTodoException e) {
                    System.out.println(e.getMessage());
                } catch (EmptyChoiceException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        this.ui.bye();
    }
    /**
     * Parses the user's input and performs the requested action on the task list.
     *
     * @param input The user's input.
     * @throws UnknownCommandException If the input botty.botty.command is not recognized.
     * @throws EmptyTodoException      If the description of a todo is empty.
     * @throws EmptyChoiceException    If the task number to be marked or unmarked is not provided.
     */
//    public static void parseInput(String input) throws UnknownCommandException, EmptyTodoException, EmptyChoiceException {
//        String[] stringArray = input.split(" ", 2);
//        switch (stringArray[0]) {
//        case "list":
//            return new ListCommand();
//            System.out.println("Here are the tasks in your list:");
//            for (int i = 0; i < taskList.size(); i++) {
//                Task currTask = taskList.get(i);
//                String listCounter = (i + 1) + ".";
//                System.out.println(listCounter + currTask.toString());
//            }
//            System.out.println("");
//            break;
//        case "unmark":
//            if (stringArray.length == 1) {
//                throw new EmptyChoiceException("☹ OOPS!!! Select the task number to be unmarked.\n");
//            }
//            System.out.println("OK, I've marked this task as not done yet:");
//            Task unmarkTask = taskList.get(Integer.parseInt(stringArray[1]) - 1);
//            unmarkTask.markUndone();
//            System.out.println(unmarkTask + "\n");
//            break;
//        case "mark":
//            if (stringArray.length == 1) {
//                throw new EmptyChoiceException("☹ OOPS!!! Select the task number to be marked.\n");
//            }
//            System.out.println("Nice! I've marked this task as done:");
//            Task markTask = taskList.get(Integer.parseInt(stringArray[1]) - 1);
//            markTask.markDone();
//            System.out.println(markTask + "\n");
//            break;
//        case "todo":
//            if (stringArray.length == 1) {
//                throw new EmptyTodoException("☹ OOPS!!! The description of a todo cannot be empty.\n");
//            }
//            Todo toAddTodo = new Todo(stringArray[1]);
//            taskList.add(toAddTodo);
//            System.out.println("Got it. I've added this task:");
//            System.out.println("  " + toAddTodo);
//            System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
//            break;
//        case "deadline":
//            String[] splitDeadline = stringArray[1].split(" /by ", 2);
//            Deadline toAddDeadline = new Deadline(splitDeadline[0], splitDeadline[1]);
//            taskList.add(toAddDeadline);
//            System.out.println("Got it. I've added this task:");
//            System.out.println("  " + toAddDeadline);
//            System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
//            break;
//        case "event":
//            String[] splitFrom = stringArray[1].split(" /from ", 2);
//            String description = splitFrom[0];
//            String[] splitTo = splitFrom[1].split(" /to ", 2);
//            String from = splitTo[0];
//            String to = splitTo[1];
//            Event toAddEvent = new Event(description, from, to);
//            taskList.add(toAddEvent);
//            System.out.println("Got it. I've added this task:");
//            System.out.println("  " + toAddEvent);
//            System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
//            break;
//        case "delete":
//            System.out.println("Noted. I've removed this task:");
//            Task deletedTask = taskList.get(Integer.parseInt(stringArray[1]) - 1);
//            System.out.println("  " + deletedTask);
//            taskList.remove(deletedTask);
//            System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
//            break;
//        default:
//            throw new UnknownCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
//        }
//    }
}

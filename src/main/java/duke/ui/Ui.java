package duke.ui;

import duke.Duke;
import duke.task.*;
import duke.parser.*;

import java.util.Scanner;

public class Ui {
    String name;

    /**
     * The Ui class handles interactions with the user interface, displaying messages and receiving inputs.
     */
    public Ui() {

    }
    /**
     * Runs the task processing loop, taking user inputs and performing corresponding actions.
     *
     * @param oriTaskArray The original TaskArray containing tasks.
     * @return The updated TaskArray after processing user inputs.
     */
    public TaskArray runTask(TaskArray oriTaskArray) {
        TaskArray taskArray = oriTaskArray;
        //Requesting Input and Redirect to Parser
        Scanner scanner = new Scanner(System.in);
        Parser input = new Parser(acceptInput(scanner));
        //Repeating Asking User if Not Equals Bye
        String command = input.getCommand();
        while (!command.equals("bye")) {
            switch (command) {
                case "help":
                    helpFunction();
                    break;
                case "list":
                    taskArray.printTaskArrayList();
                    break;
                case "find":
                    taskArray.printFind(input.getExtracted());
                    break;
                case "delete":
                    int deleteIndex = input.processRemoveIndex();
                    if (deleteIndex >= 0) {
                        taskArray.removeTask(deleteIndex - 1);
                    } else {
                        printInvalidArgIndex();
                        continue;
                    }
                    break;

                case "mark":
                    int markIndex = input.processMarkIndex();
                    if (markIndex >= 0) {
                        taskArray.get(markIndex - 1).mark();
                    } else {
                        printInvalidArgIndex();
                        continue;
                    }
                    break;

                case "unmark":
                    int unmarkIndex = input.processUnmarkIndex();
                    if (unmarkIndex >= 0) {
                        taskArray.get(unmarkIndex - 1).unmark();
                    } else {
                        printInvalidArgIndex();
                        continue;
                    }
                    break;

                case "todo":
                    Task toDo = input.processToDo();
                    if (toDo == null) {
                        printInsufficientArgToDo();
                    } else {
                        taskArray.add(toDo);
                    }
                    break;

                case "deadline":
                    Task deadlineTask = input.processDeadline();
                    if (deadlineTask == null) {
                        printInsufficientArgDeadline();
                    } else {
                        taskArray.add(deadlineTask);
                    }
                    break;

                case "event":
                    Task eventTask = input.processEvent();
                    if (eventTask == null) {
                        printInsufficientArgEvent();
                    } else {
                        taskArray.add(eventTask);
                    }
                    break;


                default:
                    printInvalidArg();

            }
            input = new Parser(acceptInput(scanner));
            command = input.getCommand();
        }
        scanner.close();
        byeFunction();
        return taskArray;

    }


    public String runGUITask(TaskArray oriTaskArray, String inputString) {
        //Requesting Input and Redirect to Parser
        Parser input = new Parser(inputString);
        //Repeating Asking User if Not Equals Bye
        String command = input.getCommand();
        switch (command) {
            case "help":
                return helpFunction();
            case "save":
                return Duke.saveData();
            case "clear":
                return Duke.clearData();
            case "list":
                return oriTaskArray.printTaskArrayList();
            case "find":
                return oriTaskArray.printFind(input.getExtracted());
            case "remove":
                int removeIndex = input.processRemoveIndex();

                if (removeIndex >= 0) {
                    return oriTaskArray.removeTask(removeIndex - 1);
                } else {
                    return printInvalidArgIndex();
                }
            case "addTag":
                int addingTaskIndex = input.processTagGetTaskIndex();
                Tag tagForAddTag = input.processGetTag();
                if (addingTaskIndex == -1 || tagForAddTag == null) {
                    return printInvalidAddTagArg();
                }

                return oriTaskArray.get(addingTaskIndex).setTag(tagForAddTag);
            case "removeTag":
                int removingTaskIndex = input.processTagGetTaskIndex();
                if (removingTaskIndex == -1) {
                    return printInvalidRemoveTagIndex();
                }

                return oriTaskArray.get(removingTaskIndex).removeTag();

            case "listTag":
                Tag tagForListTag = input.processListTag();
                if (tagForListTag == null) {
                    return printInvalidListTaskArg();
                }

                return oriTaskArray.printByTag(tagForListTag);

            case "mark":
                int markIndex = input.processMarkIndex();
                if (markIndex >= 0) {
                    return oriTaskArray.get(markIndex - 1).mark();
                } else {
                    return printInvalidArgIndex();
                }

            case "unmark":
                int unmarkIndex = input.processUnmarkIndex();
                if (unmarkIndex >= 0) {
                    return oriTaskArray.get(unmarkIndex - 1).unmark();
                } else {
                    return printInvalidArgIndex();
                }

            case "todo":
                Task toDo = input.processToDo();
                if (toDo == null) {
                    return printInsufficientArgToDo();
                } else {
                    return oriTaskArray.add(toDo);
                }

            case "deadline":
                Task deadlineTask = input.processDeadline();
                if (deadlineTask == null) {
                    return printInsufficientArgDeadline();
                } else {
                    return oriTaskArray.add(deadlineTask);
                }

            case "event":
                Task eventTask = input.processEvent();
                if (eventTask == null) {
                    return printInsufficientArgEvent();
                } else {
                    return oriTaskArray.add(eventTask);
                }
            case "bye":
                return byeFunction();

            default:
                return printInvalidArg();
        }
    }


    /**
     * Greets the user with a welcome message.
     *
     * @param name The name of the bot.
     */
    public static String greetFunction(String name) {

        String greetings = "Hello! I'm " + name + "\n"
                + "What can I do for you?";
        return greetings;
    }

    /**
     * Displays the list of available commands to the user.
     */
    public String helpFunction() {
        String output = ("You can visit our product website :\nhttps://github.com/kimshitong/ip\n");
        output += ("");
        return output;
    }

    /**
     * Accepts user input from the scanner.
     *
     * @param scanner The Scanner object used for input.
     * @return The user's input as a String.
     */

    public String acceptInput(Scanner scanner) {
        String input = scanner.nextLine();

        return input;
    }


    /**
     * Displays a goodbye message to the user.
     */
    public static String byeFunction() {
        return "Bye. Hope to see you again soon";
    }

    /**
     * Displays an error message for an invalid index.
     */
    public String printInvalidArgIndex() {
        return "OOPS!!! Invalid Index!";
    }

    /**
     * Displays an error message for insufficient arguments for an event task.
     */
    public String printInsufficientArgEvent() {
        return ("OOPS!!! The argument for the event is invalid! \n Please use this format instead : event [task] /from [dd/mm/yyyy HHmm] /to [dd/mm/yyyy HHmm]  ");
    }

    /**
     * Displays an error message for insufficient arguments for a deadline task.
     */
    public String printInsufficientArgDeadline() {
         return "OOPS!!! The argument for the deadline is invalid! \n Please use this format instead : deadline [task] /by [dd/mm/yyyy HHmm]";
    }

    /**
     * Displays an error message for an empty description in a to-do task.
     */
    public String printInsufficientArgToDo() {
        return "OOPS!!! The description of a ToDo cannot be empty.";
    }

    /**
     * Displays an error message for an invalid command.
     */
    public String printInvalidArg() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    public String printInvalidRemoveTagIndex() {
        return "OOPS!!! The argument for the removeTag is invalid! \n Please use this format instead : removeTag [taskIndex] [tagName]";
    }
    public String printInvalidAddTagArg() {
        return "OOPS!!! The argument for the addTag is invalid! \n Please use this format instead : addTag [taskIndex] [tagName]";
    }

    public static String printInvalidListTaskArg(){
        return "OOPS!!! The argument for the listTag is invalid or your tag is invalid! \n Please use this format instead : listTag [tagName]";

    }



}

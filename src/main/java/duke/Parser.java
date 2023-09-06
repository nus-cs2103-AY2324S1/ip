package duke;

import java.io.IOException;

import duke.exceptions.EmptyDetailsOfTaskError;
import duke.exceptions.WrongMarkException;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.exceptions.UnknownCommandException;

/**
 * Describes the actions taken by the bot when commands is given
 */
public class Parser {
    /**
     * Describes the action taken
     * 
     * @param command  User command
     * @param taskList Takes in a taskList to add/delete/change details
     *                 in the taskList from done to not done and vice
     *                 versa
     * @param helper   Takes in the bot interaction with the user
     * @param storage  Takes in the storage to store the taskList
     *                 of the particular user after the user wants to end
     *                 the program of the bot
     * @return a boolean that allow the main application to end the program
     *         when a particular command "bye" is given from the user
     * @throws WrongMarkException      if asked to mark a marked file or unmark a
     *                                 file
     *                                 that is not marked
     * @throws UnknownCommandException if asked commands that the bot do not
     *                                 understand
     */
    public static String isExitOrContinue(String command,
            TaskList tasks,
            UI helper,
            Storage storage)
            throws WrongMarkException,
            UnknownCommandException {
        String output = "";
        String[] splittedCommand = command.split(" ");
        String commandType = splittedCommand[0];
        switch (commandType) {
            case "bye":
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    output = "OOPS!!! There is no file to save.";
                    System.out.println("OOPS!!! There is no file to save.");
                }
                break;
            case "find":
                String keyword = splittedCommand[1];
                output = helper.findTask(keyword, tasks);
                break;
            case "list":
                helper.printLine();
                output = tasks.printList();
                break;
            case "mark":
                try {
                    int taskNumber = Integer.parseInt(splittedCommand[1]);
                    Task task = tasks.get(taskNumber - 1);
                    if (!task.isItDone()) {
                        task.setAsDone();
                        output = helper.markTask(task);
                    } else if (task.isItDone()) {
                        helper.printLine();
                        throw new WrongMarkException("This task is already done.");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    helper.printLine();
                    output = "OOPS!!! Must choose something to mark.";
                    System.out.println("OOPS!!! Must choose something to unmark.");
                } catch (NullPointerException e) {
                    helper.printLine();
                    output = "OOPS!!! You chose air.";
                    System.out.println("OOPS!!! You chose air.");
                }
                break;
            case "unmark":
                try {
                    int taskNumber = Integer.parseInt(splittedCommand[1]);
                    Task task = tasks.get(taskNumber - 1);
                    if (task.isItDone()) {
                        task.setAsUndone();
                        output = helper.unMarkTask(task);
                    } else {
                        helper.printLine();
                        throw new WrongMarkException("This task is not done yet.");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    helper.printLine();
                    output = "OOPS!!! Must choose something to unmark.";
                    System.out.println("OOPS!!! Must choose something to unmark.");
                } catch (NullPointerException e) {
                    helper.printLine();
                    output = "OOPS!!! You chose air.";
                    System.out.println("OOPS!!! You chose air.");
                }
                break;
            case "delete":
                String[] splittedInput = command.split(" ");
                int taskNumber = Integer.parseInt(splittedInput[1]);
                output = helper.deleteTask(tasks, taskNumber);
                break;
            default:
                try {
                    Task currentTask = Task.createTask(command);
                    tasks.add(currentTask);
                    output = helper.addTask(currentTask, tasks);
                } catch (EmptyDetailsOfTaskError e) {
                    helper.printLine();
                    output = e.getMessage();
                    System.out.println(e.getMessage());
                } catch (UnknownCommandException e) {
                    helper.printLine();
                    output = e.getMessage();
                    System.out.println(e.getMessage());
                }
        }
        return output;
    }
}

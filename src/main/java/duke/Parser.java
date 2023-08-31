package duke;

import java.io.IOException;

import duke.exceptions.EmptyDetailsOfTaskError;
import duke.exceptions.WrongMarkException;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.exceptions.UnknownCommandException;

public class Parser {
    public static boolean parse(String command, TaskList taskList, UI helper, Storage storage)
            throws WrongMarkException, UnknownCommandException {
        String[] splittedCommand = command.split(" ");
        String commandType = splittedCommand[0];
        switch (commandType) {
            case "bye":
                try {
                    storage.save(taskList);
                } catch (IOException e) {
                    System.out.println("OOPS!!! There is no file to save.");
                }
                break;
            case "find":
                helper.findTask();
                String keyword = splittedCommand[1];
                taskList.findTaskFromTaskList(keyword);
                break;
            case "list":
                helper.printLine();
                taskList.printList();
                break;
            case "mark":
                try {
                    int taskNumber = Integer.parseInt(splittedCommand[1]);
                    Task task = taskList.get(taskNumber - 1);
                    if (!task.isItDone()) {
                        task.markAsDone();
                        helper.markTask(task);
                    } else if (task.isItDone()) {
                        helper.printLine();
                        throw new WrongMarkException("This task is already done.");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    helper.printLine();
                    System.out.println("OOPS!!! Must choose something to unmark.");
                } catch (NullPointerException e) {
                    helper.printLine();
                    System.out.println("OOPS!!! You chose air.");
                }
                break;
            case "unmark":
                try {
                    int taskNumber = Integer.parseInt(splittedCommand[1]);
                    Task task = taskList.get(taskNumber - 1);
                    if (task.isItDone()) {
                        task.markAsUndone();
                        helper.unMarkTask(task);
                    } else {
                        helper.printLine();
                        throw new WrongMarkException("This task is not done yet.");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    helper.printLine();
                    System.out.println("OOPS!!! Must choose something to unmark.");
                } catch (NullPointerException e) {
                    helper.printLine();
                    System.out.println("OOPS!!! You chose air.");
                }
                break;
            case "delete":
                String[] splittedInput = command.split(" ");
                int taskNumber = Integer.parseInt(splittedInput[1]);
                helper.deleteTask(taskList, taskNumber);
                break;
            default:
                try {
                    Task currentTask = Task.createTask(command);
                    taskList.add(currentTask);
                    helper.addTask(currentTask, taskList);
                } catch (EmptyDetailsOfTaskError e) {
                    helper.printLine();
                    System.out.println(e.getMessage());
                } catch (UnknownCommandException e) {
                    helper.printLine();
                    System.out.println(e.getMessage());
                }
        }
        if (commandType.equals("bye")) {
            return true;
        } else {
            return false;
        }
    }
}

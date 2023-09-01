package parser;

import Exceptions.*;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import datafile.Storage;
import tasklist.TaskList;
import ui.Ui;

public class Parser {


    enum Commands {
        BYE,
        MARK,
        UNMARK,
        DELETE,
        LIST,
        TODO,
        DEADLINE,
        EVENT
    }

    public static void parseTasks(String input, TaskList tasks, Storage tasksData, Ui ui) throws LemonException {
        if (!input.equals("bye")) {
                String commandType = input.split(" ")[0].toUpperCase();
                Commands command = Commands.valueOf(commandType);
                switch (command) {
                    case LIST:
                        if (tasks.getTasksSize() < 1) {
                            throw new NoTasksException("");
                        }
                        ui.listAll(tasks);
                        break;
                    case MARK:
                        try {
                            int indexToMark = Integer.valueOf(input.split(" ")[1]);
                            String taskDescription = tasks.markTask(indexToMark - 1);
                            tasksData.saveTasks(tasks.getTaskList());
                            ui.markPrint(taskDescription, tasks);
                            break;
                        } catch (IndexOutOfBoundsException e) {
                            throw new InvalidTaskIndexException("");
                        }
                    case UNMARK:
                        try {
                            int indexToUnmark = Integer.valueOf(input.split(" ")[1]);
                            String unmarkTaskDescription = tasks.unmarkTask(indexToUnmark - 1);
                            tasksData.saveTasks(tasks.getTaskList());
                            ui.unmarkPrint(unmarkTaskDescription, tasks);
                            break;
                        } catch (IndexOutOfBoundsException e) {
                            throw new InvalidTaskIndexException("");
                        }
                    case TODO:
                        String[] taskSplit = input.split(" ", 2);
                        if (taskSplit.length < 2) {
                            throw new InvalidTodoException("");
                        }
                        String taskDescription = taskSplit[1];
                        String taskDescriptionTodo = tasks.addTasks(new Todo(taskDescription));
                        tasksData.saveTasks(tasks.getTaskList());
                        ui.addTasks(taskDescriptionTodo, tasks);
                        break;
                    case DEADLINE:
                        String task = input.split(" ", 2)[1];
                        String[] getDeadlineArray = task.split("/by ", 2);
                        if (getDeadlineArray.length < 2) {
                            throw new InvalidDeadlineException("");
                        }
                        String taskDesc = getDeadlineArray[0];
                        String by = getDeadlineArray[1];
                        Task newDeadlineTask = new Deadline(taskDesc, by);
                        String taskDescriptionDeadline = tasks.addTasks(newDeadlineTask);
                        tasksData.saveTasks(tasks.getTaskList());
                        ui.addTasks(taskDescriptionDeadline, tasks);
                        break;
                    case EVENT:
                        String inputTask = input.split(" ", 2)[1];
                        String[] getEventFromArray = inputTask.split("/from ", 2);
                        if (getEventFromArray.length < 2) {
                            throw new InvalidEventException("");
                        }
                        String taskDetails = getEventFromArray[0];
                        String[] getEventToArray = getEventFromArray[1].split(" /to ", 2);
                        if (getEventToArray.length < 2) {
                            throw new InvalidEventException("");
                        }
                        String from = getEventToArray[0];
                        String to = getEventToArray[1];
                        Task newEventTask = new Event(taskDetails, from, to);
                        String taskDescriptionEvent = tasks.addTasks(newEventTask);
                        tasksData.saveTasks(tasks.getTaskList());
                        ui.addTasks(taskDescriptionEvent, tasks);
                        break;
                    case DELETE:
                        int inputDelete = Integer.valueOf(input.split(" ", 2)[1]) - 1;
                        try {
                            Task taskToDelete = tasks.getTask(inputDelete);
                            String taskDescriptionDelete = tasks.deleteTask(taskToDelete);
                            tasksData.saveTasks(tasks.getTaskList());
                            ui.deletePrint(taskDescriptionDelete, tasks);
                        } catch (IndexOutOfBoundsException e) {
                            throw new InvalidTaskIndexException("");
                        }
                        break;
                    default:
                        throw new InvalidTaskException("");
            }
        }
    }
}

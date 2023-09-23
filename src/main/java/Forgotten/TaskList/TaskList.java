package Forgotten.TaskList;

import Forgotten.Priority;
import Forgotten.Storage.Storage;
import Forgotten.Task.Deadline;
import Forgotten.Task.Event;
import Forgotten.Task.Todo;
import Forgotten.Ui.Ui;
import Forgotten.Task.Task;
import Forgotten.Exception.TaskListEmptyException;
import Forgotten.Exception.DescriptionIncompleteException;
import Forgotten.Exception.IllegalCommandException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class contains the task list
 * e.g., it has operations to add/delete tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    public TaskList(ArrayList<Task> taskList, Storage storage) {
        this.tasks = taskList;
        this.storage = storage;
    }

    /**
     * Lists out all the tasks in the task list.
     *
     * @return The reply message for the bot
     */
    public String listAllTasks() {
        int count = 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");

        for (Task task: tasks) {
            String string = String.format("%d.%s\n", count, task);
            stringBuilder.append(string);
            count++;
        }

        return stringBuilder.toString();
    }

    /**
     * This method deletes a task specified by the parameter.
     *
     * @param taskNumber Index of the task to be deleted.
     * @throws TaskListEmptyException If there is no task in the task list.
     * @return The reply message for the bot.
     */
    public String deleteTask(String taskNumber) throws TaskListEmptyException {
        String string = "";

        if (tasks.size() < 1) {
            throw new TaskListEmptyException("OOPS!!! You cannot delete an empty list.");
        }

        int number = Integer.parseInt(taskNumber);
        string += "Noted. I've removed this task:\n"
                + tasks.get(number - 1)
                + "\n";

        tasks.remove(number - 1);
        // Update the file
        storage.rewriteFile(this.tasks);
        string += "Now you have " + tasks.size() + " tasks in the list.";

        return string;
    }


    /**
     * This method marks a specific task to be done.
     *
     * @param taskNumber Index of the task to be marked.
     * @return The reply message for the bot.
     */
    public String markTaskDone(String taskNumber) {
        String string = "";
        Task currentTask = tasks.get(Integer.parseInt(taskNumber) - 1);
        currentTask.setIsDone();
        storage.rewriteFile(this.tasks);
        string += "Nice! I've marked this task as done:\n" + currentTask;
        return string;
    }

    /**
     * This method marks a specific task to be not done.
     *
     * @param taskNumber Index o f the task to be marked.
     * @return The reply message for the bot.
     */
    public String unmarkTaskDone(String taskNumber) {
        String string = "";

        Task currentTask = tasks.get(Integer.parseInt(taskNumber) - 1);
        currentTask.setNotDone();

        storage.rewriteFile(this.tasks);
        string += "Nice! I've unmarked this task as done:\n" + currentTask;
        return string;
    }

    /**
     * This method adds a new task into the task list.
     *
     * @param userInput Task description.
     * @throws DescriptionIncompleteException If the description does not follow the command.
     * @throws IllegalCommandException If an undefined command is entered.
     * @return The reply message for the bot.
     */
    public String addNewTask(String userInput) throws DescriptionIncompleteException, IllegalCommandException {
        String string = "";
        String[] splitMessage = userInput.split(" ", 2);
        String instruction = splitMessage[0];
        Task task = null;

        if (!(instruction.equals("todo")||instruction.equals("deadline")||instruction.equals("event"))) {
            throw new IllegalCommandException("OOPS!!! I'm sorry, but I don't know what that means :-()");
        }

        if (splitMessage.length < 2) {
            throw new DescriptionIncompleteException("OOPS!!! The description of an instruction cannot be empty.");
        }

        switch (instruction) {
            case "todo":
                task = Todo.createNewTodoTask(splitMessage[1]);
                break;
            case "deadline":
                task = Deadline.createNewDeadlineTask(splitMessage[1]);
                break;
            case "event":
                task = Event.createNewEventTask(splitMessage[1]);
                break;
        }

        tasks.add(task);

        assert tasks.contains(task): "The new task is not added successfully";

        try {
            storage.writeTaskToFile(task.toFileString());
        } catch (IOException error) {
            string = error.getMessage();
            return string;
        }

        string += "Got it. I've added this task:\n"
                + task + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
        return string;
    }

    /**
     * This method finds all tasks with descriptions that match the keyword.
     *
     * @param keyword The task you want to find.
     * @return The reply message for the bot.
     */
    public String findTask(String keyword) {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 1;
        stringBuilder.append("Here are the matching tasks in your list:\n");

        for (Task task: tasks) {
            if (task.getDescription().contains(keyword)) {
                String string = count + "." + task + "\n";
                stringBuilder.append(string);
                count++;
            }
        }
        return stringBuilder.toString();
    }

    /**
     * This method prioritizes the task to the given priority.
     *
     * @param userInput This includes the task number and the priority.
     * @return The reply message for the bot.
     */
    public String prioritizeTask(String userInput) {
        String string = "";
        String[] splitMessage = userInput.split(" ");

        int taskNumber = Integer.parseInt(splitMessage[0]);
        Priority priority = Priority.valueOf(splitMessage[1]);

        Task currentTask = tasks.get(taskNumber - 1);
        currentTask.setPriority(priority);

        string += "Got it. I've prioritized the given task:\n"
                + currentTask;

        return string;
    }
}

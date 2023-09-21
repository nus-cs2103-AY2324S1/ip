package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private boolean hasDisplayedWelcome = false;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads a user command.
     *
     * @return The user's command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Loads tasks from the file path.
     *
     * @param filePath  The file path.
     * @return  A TaskList containing the loaded tasks.
     */
    public TaskList loadTasks(String filePath) {
        TaskList tasks = new TaskList();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Data file does not exist.");
            return tasks;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] taskData = line.split(" \\| ");
                if (taskData.length < 2) {
                    System.out.println("Skipping corrupted task data: " + line);
                    return tasks;
                }

                Storage.TaskType taskType = Storage.TaskType.valueOf(taskData[0]);
                String taskDescription = taskData[1];
                String taskTime1 = (taskData.length > 2) ? taskData[2] : "";
                String taskTime2 = (taskData.length > 3) ? taskData[3] : "";
                switch (taskType) {
                case TODO:
                    tasks.addTask(new Todo(taskDescription));
                    break;
                case DEADLINE:
                    tasks.addTask(new Deadline(taskDescription, taskTime1));
                    break;
                case EVENT:
                    tasks.addTask(new Event(taskDescription, taskTime1, taskTime2));
                    break;
                default:
                    System.out.println("Invalid task type: " + taskType);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks.");
        }
        return tasks;
    }

    /**
     * Handles user commands.
     *
     * @param userCommand  The user's command.
     * @param tasks        The list of tasks.
     * @param storage      The storage object to save tasks.
     * @return      True if the program should exit.
     */
    public String handleCommand(String userCommand, TaskList tasks, Storage storage) {
        String output = "";
        try {
            String[] parts = userCommand.split(" ", 2);
            String commandType = parts[0].toLowerCase();

            switch (commandType) {
            case "bye":
                output = handleByeCommand(tasks, storage);
                break;
            case "list":
                output = showTaskList(tasks);
                break;
            case "mark":
                output = handleMarkCommand(parts, tasks, storage);
                break;
            case "unmark":
                output = handleUnmarkCommand(parts, tasks, storage);
                break;
            case "todo":
                output = handleTodoCommand(userCommand, tasks, storage);
                break;
            case "deadline":
                output = handleDeadlineCommand(userCommand, tasks, storage);
                break;
            case "event":
                output = handleEventCommand(userCommand, tasks, storage);
                break;
            case "delete":
                output = handleDeleteCommand(parts, tasks, storage);
                break;
            case "find":
                output = handleFindCommand(parts, tasks);
                break;
            case "update":
                output = handleUpdateCommand(parts, tasks);
                break;
            case "woof":
                output = "meow";
                break;
            default:
                output = showError(userCommand);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return showError("I don't know this...");
        }
        return output;
    }

    /**
     * Handles the 'bye' command.
     *
     * @param tasks   The TaskList containing tasks.
     * @param storage The Storage object for saving tasks.
     */
    public String handleByeCommand(TaskList tasks, Storage storage) {
        storage.saveTasks(tasks);
        return showExit();

    }

    /**
     * Handles the 'mark' command.
     *
     * @param parts   An array containing command parts.
     * @param tasks   The TaskList containing tasks.
     * @param storage The Storage object for saving tasks.
     */
    public String handleMarkCommand(String[] parts, TaskList tasks, Storage storage) {
        if (parts.length == 1) {
            return "pls enter: mark <index>";
        }

        int doneTaskIndex = Integer.parseInt(parts[1]) - 1;
        tasks.markTaskAsDone(doneTaskIndex);
        storage.saveTasks(tasks);
        return showTaskMarkedAsDone(tasks.getTask(doneTaskIndex));
    }

    /**
     * Handles the 'unmark' command.
     *
     * @param parts   An array containing command parts.
     * @param tasks   The TaskList containing tasks.
     * @param storage The Storage object for saving tasks.
     */
    public String handleUnmarkCommand(String[] parts, TaskList tasks, Storage storage) {
        if (parts.length == 1) {
            return "pls enter: unmark <index>";
        }

        int notDoneTaskIndex = Integer.parseInt(parts[1]) - 1;
        tasks.markTaskAsNotDone(notDoneTaskIndex);
        storage.saveTasks(tasks);
        return showTaskMarkedAsNotDone(tasks.getTask(notDoneTaskIndex));
    }

    /**
     * Handles the 'todo' command.
     *
     * @param userCommand A string representing user command.
     * @param tasks   The TaskList containing tasks.
     * @param storage The Storage object for saving tasks.
     */
    public String handleTodoCommand(String userCommand, TaskList tasks, Storage storage) {
        Task newTask = Parser.parse(userCommand);
        if (newTask == null) {
            return "What you want to do?? \n(format: todo <task>)";
        }
        tasks.addTask(newTask);
        storage.saveTasks(tasks);
        return showTaskAdded(newTask, tasks.size());
    }

    /**
     * Handles the 'deadline' command.
     *
     * @param userCommand A string representing user command.
     * @param tasks   The TaskList containing tasks.
     * @param storage The Storage object for saving tasks.
     */
    public String handleDeadlineCommand(String userCommand, TaskList tasks, Storage storage) {
        Task newTask = Parser.parse(userCommand);
        if (newTask == null) {
            return "Please describe your deadline! \n" +
                    "(format: deadline <task> /by <time>) \n" +
                    "(indicate your time like: 2023-05-20)";
        }
        tasks.addTask(newTask);
        storage.saveTasks(tasks);
        return showTaskAdded(newTask, tasks.size());
    }

    /**
     * Handles the 'event' command.
     *
     * @param userCommand A string representing user command.
     * @param tasks   The TaskList containing tasks.
     * @param storage The Storage object for saving tasks.
     */
    public String handleEventCommand(String userCommand, TaskList tasks, Storage storage) {
        Task newTask = Parser.parse(userCommand);
        if (newTask == null) {
            return "Please describe your event! \n" +
                    "(format: event <task> /from <time> /to <time> \n" +
                    "(indicate both your time like: 2023-06-12 1400)";
        }
        tasks.addTask(newTask);
        storage.saveTasks(tasks);
        return showTaskAdded(newTask, tasks.size());
    }

    /**
     * Handles the 'delete' command.
     *
     * @param parts   An array containing command parts.
     * @param tasks   The TaskList containing tasks.
     * @param storage The Storage object for saving tasks.
     */
    public String handleDeleteCommand(String[] parts, TaskList tasks, Storage storage) {
        if (parts.length == 1) {
            return "pls enter: delete <index>";
        }

        int deletedTaskIndex = Integer.parseInt(parts[1]) - 1;
        tasks.deleteTask(deletedTaskIndex);
        storage.saveTasks(tasks);
        return showTaskDeleted(tasks.size());
    }

    /**
     * Handles the 'find' command.
     *
     * @param parts   An array containing command parts.
     * @param tasks   The TaskList containing tasks.
     */
    public String handleFindCommand(String[] parts, TaskList tasks) {
        if (parts.length == 1) {
            return "pls enter: find <keyword>";
        }
        return showFindCommand(parts[1], tasks);
    }

    /**
     * Handles the 'update' command.
     *
     * @param parts  An array containing command parts.
     * @param tasks  The TaskList containing tasks.
     */
    public String handleUpdateCommand(String[] parts, TaskList tasks) {
        if (parts.length == 1) {
            return "pls enter: update <index> </parts> </new_info>\n" +
                    "(eg: update 2 /descr /have dinner)\n" +
                    "\nRef. for </parts>:\n" +
                    "1./descr: for all types of task.\n" +
                    "2./time: for 'Deadline' only.\n" +
                    "3./start_time, /end_time: for 'Event' only.";
        }
        String[] updateParts = parts[1].split(" /");
        String updateType = updateParts[1].toLowerCase();
        String output = "";

        switch(updateType) {
        case "descr":
            output = updateDescr(updateParts, tasks);
            break;
        case "time":
            output = updateTime(updateParts, tasks);
            break;
        case "start_time":
            output = updateStartTime(updateParts, tasks);
            break;
        case "end_time":
            output = updateEndTime(updateParts, tasks);
            break;
        default:
            output = showError("sry... idk what u want to update.\n" +
                    "u can type 'update' for more info!");
        }
        return output;
    }

    /**
     * Updates the description of the task.
     *
     * @param updateParts  An array containing update command.
     * @param tasks     The TaskList containing tasks.
     */
    public String updateDescr(String[] updateParts, TaskList tasks) {
        if (updateParts.length < 3 ) {
            return "Make sure you enter both index and description!";
        }
        int index = Integer.parseInt(updateParts[0]) - 1;
        String description = updateParts[2];
        Task task = tasks.getTask(index);

        task.updateDescription(description);
        return "Great, I've updated task " + (index + 1) + " !!";
    }

    /**
     * Updates the time of a Deadline.
     *
     * @param updateParts  An array containing update command.
     * @param tasks    A TaskList containing tasks.
     */
    public String updateTime(String[] updateParts, TaskList tasks) {
        if (updateParts.length < 3 ) {
            return "Make sure you enter both index and time!";
        }
        int index = Integer.parseInt(updateParts[0]) - 1;
        String time = updateParts[2];
        Deadline task = (Deadline)tasks.getTask(index);

        task.updateTime(time);
        return "woohoo~~ the time of task " + (index + 1) + " has been updated!";
    }

    /**
     * Updates the start time of a Event.
     *
     * @param updateParts  An array containing the update command.
     * @param tasks   A TaskList containing tasks.
     */
    public String updateStartTime(String[] updateParts, TaskList tasks) {
        if (updateParts.length < 3) {
            return "Make sure you enter both index and time !!";
        }
        int index = Integer.parseInt(updateParts[0]) - 1;
        String startTime = updateParts[2];
        Event task = (Event)tasks.getTask(index);

        task.updateStartTime(startTime);
        return "nice! task " + (index + 1) + " has new start time now!";
    }

    /**
     * Updates the end time of a Event.
     *
     * @param updateParts  An array containing the update command.
     * @param tasks   A TaskList containing tasks.
     */
    public String updateEndTime(String[] updateParts, TaskList tasks) {
        if (updateParts.length < 3) {
            return "Make sure you enter both index and time !!";
        }
        int index = Integer.parseInt(updateParts[0]) - 1;
        String endTime = updateParts[2];
        Event task = (Event)tasks.getTask(index);

        task.updateEndTime(endTime);
        return "nice! task " + (index + 1) + " has new end time now!";
    }

    /**
     * Displays a welcome message.
     */
    public String showWelcome() {
        return "Hi, I'm BiuBiu.\nWhat can I do for you?";
    }

    /**
     * Displays an error message.
     *
     * @param errorCommand  The error message.
     */
    public String showError(String errorCommand) {
        return errorCommand;
    }

    /**
     * Displays a message confirming the addition of a task.
     *
     * @param task  The task that we added.
     * @param taskCount  The current number of tasks in the list.
     */
    public String showTaskAdded (Task task, int taskCount) {
        String output = "*ฅ^•ﻌ•^ฅ* ogei!! I've added this task:";
        output += "\n " + task;
        output += "\nNow you have " + taskCount + " tasks in the list! meow--";
        return output;
    }

    /**
     * Displays a message confirming the deletion of a task.
     *
     * @param taskCount  The current number of tasks in the list.
     */
    public String showTaskDeleted (int taskCount) {
        String output = "OK, I've removed this task.";
        output += "\nNow you have " + taskCount + " tasks in the list.";
        return output;
    }

    /**
     * Displays the list of tasks.
     *
     * @param taskList  The task list.
     */
    public String showTaskList (TaskList taskList) {
        String output = "Here are the tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            output += "\n" + (i + 1) + ". " + taskList.getTask(i);

        }
        output += "\nପ(๑•ᴗ•๑)ଓ ♡";
        return output;
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task  The task that was marked as done.
     */
    public String showTaskMarkedAsDone (Task task) {
        String output = "Nice! I've marked this task as done:";
        output += "\n  " + task;
        return output;
    }

    /**
     * Displays a message confirming that a task has been marked as not done.
     *
     * @param task  The task that was marked as not done.
     */
    public String showTaskMarkedAsNotDone (Task task) {
        String output =  "Noted, I've marked this task as not done yet:";
        output += "\n  " + task;
        return output;
    }

    /**
     * Displays an loading error message.
     */
    public String showLoadingError() {
        return "Error loading tasks from file.";
    }

    /**
     * Displays tasks tha match the keyword.
     *
     * @param keyword  The keyword to search for the matching task.
     * @param tasks  The list of tasks to search within.
     */
    public String showFindCommand (String keyword, TaskList tasks) {
        TaskList matchingTasks = tasks.findTasksByKeyword(keyword);

        String output = "Here are the matching tasks: ";
        for (int i = 0; i < matchingTasks.size(); i++) {
            output += "\n" + (i + 1) + "." + matchingTasks.getTask(i);
        }
        return output;
    }

    /**
     * Displays an exit message.
     */
    public String showExit() {
        return "Bye. Have a great day!";
    }
}

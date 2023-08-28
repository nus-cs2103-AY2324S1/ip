package task;

import exception.IllegalTaskIndexException;
import exception.InvalidArgumentException;
import storage.Storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    public enum TaskType {

        TODO {
            @Override
            public Task createTask(String details) {
                return new ToDo(details);
            }
        },
        DEADLINE {
            @Override
            public Task createTask(String details) throws InvalidArgumentException {
                String[] split = details.split(" /by ");
                if (split.length != 2) {
                    throw new InvalidArgumentException("☹ OOPS!!! The deadline format is incorrect. " +
                            "It should be: deadline <name> /by <dateTime>");
                }
                String taskName = split[0], dateTime = split[1];
                return new Deadline(taskName, dateTime);
            }
        },
        EVENT {
            @Override
            public Task createTask(String details) throws InvalidArgumentException {
                String[] firstSplit = details.split(" /from ");
                String[] secondSplit = firstSplit[firstSplit.length - 1].split(" /to ");
                if (firstSplit.length != 2 || secondSplit.length != 2) {
                    throw new InvalidArgumentException("☹ OOPS!!! The event format is incorrect. " +
                            "It should be: event <name> /from <dateTime> /to <dateTime>");
                }
                String taskName = firstSplit[0], startDateTime = secondSplit[0], endDateTime = secondSplit[1];
                return new Event(taskName, startDateTime, endDateTime);
            }
        };

        /**
         * Creates a deadline task.
         * @param details The details of the deadline task.
         * @return The deadline task.
         * @throws InvalidArgumentException If the deadline task's format is invalid.
         */
        public abstract Task createTask(String details) throws InvalidArgumentException;
    }

    private List<Task> tasks;
    private final Storage storage;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
//        this.tasks = new ArrayList<>();
        this.storage = new Storage();
        try {
            this.tasks = storage.load();
        } catch (IOException e) {
            System.out.println("Error loading data from file: " + e);
            this.tasks = new ArrayList<>();
        }
    }

    /**
     * Adds a task to the list of tasks.
     * @param command The command that the user inputted.
     * @throws InvalidArgumentException If the task's format is invalid.
     */
    public void addTask(String command) throws InvalidArgumentException {
        String[] splitCommand = command.split("\\s", 2);

        if (splitCommand.length < 2) {
            throw new InvalidArgumentException("☹ OOPS!!! The description cannot be empty.");
        }
        String type = splitCommand[0];
        String taskDetails = splitCommand[1];
        TaskType taskType;
        try {
            taskType = TaskType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidArgumentException("Bro your task type is unknown: " + type);
        }
        tasks.add(taskType.createTask(taskDetails));
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e);
        }
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(this.tasks.get(this.tasks.size() - 1));
        String placeholder = tasks.size() == 1 ? "task" : "tasks";
        System.out.println("Now you have " + tasks.size() + " " + placeholder + " in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Lists all the tasks.
     */
    public void listTasks() {
        System.out.println("____________________________________________________________");
        if (this.tasks.isEmpty()) {
            System.out.println("There are no tasks in your list.");
        } else {
            for (int i = 0; i < this.tasks.size(); i++) {
                System.out.println((i + 1) + "." + this.tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Marks a task as done.
     * @param index The index of the task to be marked as done.
     * @throws IllegalTaskIndexException If the index is invalid.
     */
    public void markAsDone(int index) throws IllegalTaskIndexException {
        if (index > tasks.size() || index < 1) {
            throw new IllegalTaskIndexException();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        this.tasks.get(index - 1).markAsDone();
        System.out.println(this.tasks.get(index - 1));
        System.out.println("____________________________________________________________");
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e);
        }
    }

    /**
     * Marks a task as undone.
     * @param index The index of the task to be marked as undone.
     * @throws IllegalTaskIndexException If the index is invalid.
     */
    public void markAsUndone(int index) throws IllegalTaskIndexException {
        if (index > tasks.size() || index < 1) {
            throw new IllegalTaskIndexException();
        }
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        this.tasks.get(index - 1).markAsUndone();
        System.out.println(this.tasks.get(index-1));;
        System.out.println("____________________________________________________________");
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e);
        }
    }

    /**
     * Delete a task from the list of tasks.
     * @param index The index of the task to be deleted.
     * @throws IllegalTaskIndexException If the index is invalid.
     */
    public void deleteTask(int index) throws IllegalTaskIndexException {
        if (index > tasks.size() || index < 1) {
            throw new IllegalTaskIndexException();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index - 1));
        tasks.remove(index - 1);
        String placeholder = tasks.size() == 1 ? "task" : "tasks";
        System.out.println("Now you have " + tasks.size() + " " + placeholder + " in the list.");
        System.out.println("____________________________________________________________");
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e);
        }
    }

}

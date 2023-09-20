package duke;

import exceptions.DukeException;
import task.Task;

import dukeutilities.Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;


/**
 * The TaskList class represents a list of tasks and provides methods to manage tasks.
 * It can add, delete, mark tasks as done, and provide information about the task list.
 */
public class TaskList {
    private List<Task> todoList;

    /**
     * Constructs a TaskList object, initializing an empty list of tasks.
     */
    public TaskList() {
        this.todoList = new ArrayList<Task>();
    }

    public String getListInString() {
        StringBuilder list = new StringBuilder();
        for (Task task : this.todoList) {
            list.append(task.toString()).append("\n");
        }
        return list.toString();
    }

    /**
     * Returns the list object in the TaskList
     *
     * @return The list
     */
    public List<Task> getList() {
        return this.todoList;
    }

    public Task getTask(int index) {
        return this.todoList.get(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks.
     */
    public int getLength() {
        return this.todoList.size();
    }

    /**
     * Retrieves a string representation of a task at the specified index.
     *
     * @param index The index of the task.
     * @return The formatted string representing the task.
     */
    public String getTaskInString(int index) {
        return this.todoList.get(index).toString();
    }


    /**
     * Adds a new task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.todoList.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskToDeleteIndex The index of the task to be deleted.
     */
    public void deleteTask(int taskToDeleteIndex) {
        this.todoList.remove(taskToDeleteIndex);
    }

    /**
     * Marks a task as done in the TaskList.
     *
     * @param taskToMarkIndex The index of the task to be marked as done.
     */
    public void markTask(int taskToMarkIndex) {
        this.todoList.get(taskToMarkIndex).markDone();
    }

    /**
     * Marks a task as undone in the TaskList.
     *
     * @param taskToUnmarkIndex The index of the task to be marked as undone.
     */
    public void unmarkTask(int taskToUnmarkIndex) {
        this.todoList.get(taskToUnmarkIndex).markUndone();
    }

    /**
     * Marks a task as undone in the TaskList.
     *
     * @param query The string to be located
     * @return A TaskList object of the matching tasks
     */
    public TaskList findTask(String query) {
        TaskList queryList = new TaskList();
        for (Task t : this.todoList) {
            if (t.compareTitle(query)) {
                queryList.addTask(t);
            }
        }
        return queryList;
    }


    /**
     * Displays the total number of tasks in the TaskList.
     */
    public void printTaskListInString() {
        System.out.println(String.format("You have %d task(s) currently in this big list",
                todoList.size()));
    }

    /**
     * Returns the total number of tasks in the TaskList in String form.
     */
    public String NumberOfTaskListInString() {
        String s = String.format("Oh my, you have %d task(s) currently in this huge list",
                todoList.size());
        return s;
    }

    /**
     * Reads from an existing file of Tasks.
     *
     * @param filePath The file to be read from.
     * @return A TaskList object of the tasks in the file.
     */
    public static TaskList readFromFile(String filePath) {
        TaskList list = new TaskList();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Task parsedTask = Parser.parseLine(line);
                list.addTask(parsedTask);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


}

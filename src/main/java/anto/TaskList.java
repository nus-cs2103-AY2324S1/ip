package anto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TaskList class represents list of all current tasks and handles all actions
 * related to tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Creates a new TaskList class.
     *
     * @param taskList Initial ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Add task given to arraylist.
     *
     * @param newTask Task to be added.
     * @throws AntoException Throws anto exception if there is an IO Exception
     */
    public void addToList(Task newTask) throws AntoException {
        this.taskList.add(newTask);

        String relativePath = "data/anto.txt";
        Path absolutePath = Paths.get(relativePath).toAbsolutePath();
        File antoFile = absolutePath.toFile();

        try {
            FileWriter writer = new FileWriter(antoFile, true);
            if (newTask instanceof Todo) {
                writer.write(String.format("\nT | %s | %s", newTask.isDone ? "1" : "0", newTask.description));
            } else if (newTask instanceof Deadline) {
                writer.write(String.format("\nD | %s | %s | %s", newTask.isDone ? "1" : "0", newTask.description, (
                        (Deadline) newTask).by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));
            } else if (newTask instanceof Event) {
                writer.write(String.format("\nE | %s | %s | %s | %s", newTask.isDone ? "1" : "0", newTask.description, (
                        (Event) newTask).from.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")), (
                        (Event) newTask).to.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));
            }
            writer.close();
        } catch (IOException e) {
            throw new AntoException("OOPS!!! IOException");
        }
    }

    /**
     * Returns current array list of tasks.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getStorage() {
        return this.taskList;
    }

    /**
     * Returns current number of tasks.
     *
     * @return Current number of tasks.
     */
    public int getLength() {
        return this.taskList.size();
    }

    /**
     * Marks task at index as done.
     *
     * @param index Index of task to mark as done.
     * @throws AntoException Throws AntoException if there is an IO Exception.
     */
    public void markTaskAsDone(int index) throws AntoException {
        String relativePath = "data/anto.txt";
        Path absolutePath = Paths.get(relativePath).toAbsolutePath();
        File antoFile = absolutePath.toFile();

        String tempRelativePath = "data/tempFile.txt";
        Path tempAbsolutePath = Paths.get(tempRelativePath).toAbsolutePath();
        File tempFile = tempAbsolutePath.toFile();

        try {
            Scanner sc = new Scanner(antoFile);
            FileWriter writer = new FileWriter(tempFile);

            int line = 0;

            while (sc.hasNextLine()) {
                String currLine = sc.nextLine();
                if (line == index) {
                    writer.write(currLine.replace("| 0 |", "| 1 |")
                            + System.getProperty("line.separator"));
                } else {
                    writer.write(currLine + System.getProperty("line.separator"));
                }
                line++;
            }

            writer.close();
            sc.close();
            tempFile.renameTo(antoFile);
        } catch (IOException e) {
            throw new AntoException("OOPS!!! IOException");
        }

        this.taskList.get(index).markAsDone();
    }

    /**
     * Unmark task at index.
     *
     * @param index Index of task to unmark.
     * @throws AntoException Throws AntoException if there is an IO Exception.
     */
    public void unmarkTask(int index) throws AntoException {
        String relativePath = "data/anto.txt";
        Path absolutePath = Paths.get(relativePath).toAbsolutePath();
        File antoFile = absolutePath.toFile();

        String tempRelativePath = "data/tempFile.txt";
        Path tempAbsolutePath = Paths.get(tempRelativePath).toAbsolutePath();
        File tempFile = tempAbsolutePath.toFile();

        try {
            Scanner sc = new Scanner(antoFile);
            FileWriter writer = new FileWriter(tempFile);

            int line = 0;

            while (sc.hasNextLine()) {
                String currLine = sc.nextLine();
                if (line == index) {
                    writer.write(currLine.replace("| 1 |", "| 0 |")
                            + System.getProperty("line.separator"));
                } else {
                    writer.write(currLine + System.getProperty("line.separator"));
                }
                line++;
            }

            writer.close();
            sc.close();
            tempFile.renameTo(antoFile);
        } catch (IOException e) {
            throw new AntoException("OOPS!!! IOException");
        }

        this.taskList.get(index).unmark();
    }

    /**
     * Deletes task at given index.
     *
     * @param index Index of task to delete.
     * @return Deleted task.
     * @throws AntoException Throws AntoException if there is an IO Exception.
     */
    public Task deleteTask(int index) throws AntoException {
        String relativePath = "data/anto.txt";
        Path absolutePath = Paths.get(relativePath).toAbsolutePath();
        File antoFile = absolutePath.toFile();

        String tempRelativePath = "data/tempFile.txt";
        Path tempAbsolutePath = Paths.get(tempRelativePath).toAbsolutePath();
        File tempFile = tempAbsolutePath.toFile();

        try {
            Scanner sc = new Scanner(antoFile);
            FileWriter writer = new FileWriter(tempFile);

            int line = 0;

            while (sc.hasNextLine()) {
                String currLine = sc.nextLine();
                if (line != index) {
                    writer.write(currLine + System.getProperty("line.separator"));
                }
                line++;
            }

            writer.close();
            sc.close();
            tempFile.renameTo(antoFile);
        } catch (IOException e) {
            throw new AntoException("OOPS!!! IOException");
        }
        return this.taskList.remove(index);
    }

    /**
     * Find tasks with matching keyword.
     *
     * @param keyword Keyword to search tasks.
     * @return Return Array List of tasks with specified keyword.
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (Task curr : this.taskList) {
            if (curr.description.contains(keyword)) {
                foundTasks.add(curr);
            }
        }

        return foundTasks;
    }

}

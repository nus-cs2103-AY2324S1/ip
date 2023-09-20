package duke.data.storage;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import duke.data.task.Task;
import duke.data.task.builder.TaskBuilder;
import duke.data.task.tasklist.Tasklist;
import duke.exception.DukeException;



/**
 * Responsible for reading and writing to duke.txt in the data folder which stores the task list contents.
 */
public class Store {
    private static final Store store = new Store();
    private final Tasklist tasks = new Tasklist();
    private final String fileName = "./src/main/java/duke/data/duke.txt";
    private final TaskBuilder taskBuilder = new TaskBuilder();

    private Store() {
        // detect whether duke.txt exists, and create it if it doesn't
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }

        assert file.exists() : "file should exist";

        FileReader fr = null;
        try {
            fr = new FileReader(fileName);
            int i;
            String input = "";
            while ((i = fr.read()) != -1) {
                input += (char) i;
            }
            String[] inputStrs = input.split("\n");
            for (String inputStr : inputStrs) {
                if (inputStr.equals("")) {
                    continue;
                }
                String[] taskTypeAndIsMarked = inputStr.split("/isMarked ");
                assert taskTypeAndIsMarked.length == 2 : "taskTypeAndIsMarked should have length 2";
                Task task = taskBuilder.buildFromString(taskTypeAndIsMarked[0]);
                int prevTaskCount = tasks.getTaskCount();
                tasks.addTask(task);
                assert tasks.getTaskCount() == prevTaskCount + 1 : "task count should increase by 1";
                System.out.println(taskTypeAndIsMarked[1]);
                String[] isMarkedAndTagsStr = taskTypeAndIsMarked[1].split("/tags ");
                if (isMarkedAndTagsStr[0].contains("true")) {
                    tasks.mark(tasks.getTaskCount());
                }
                String[] tags = isMarkedAndTagsStr[1].split(",");
                for (String tag : tags) {
                    System.out.println(tag);
                    tasks.addTagToTaskAtIndex(tasks.getTaskCount(), tag);
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (DukeException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }
    /**
     * Returns the single instance of Store.
     * @return The instance of Store.
     */
    public static Store getInstance() {
        return store;
    }
    private void write() {
        try {
            FileWriter fw = new FileWriter(fileName);
            fw.write(tasks.getTaskRepresentations());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong storing the change: " + e.getMessage());
        }
    }

    /**
     * Adds a task to the task list.
     * @param task The task to be added.
     * @throws DukeException If task list is full.
     */
    public void addTask(Task task) throws DukeException {
        tasks.addTask(task);
        write();
    }
    /**
     * Returns the tasks in the task list.
     * @return The tasks in the task list.
     */
    public Task[] getTasks() {
        return tasks.getTasks();
    }
    /**
     * Returns the task at the specified index.
     * @param index The index of the task to be returned.
     * @return The task at the specified index.
     * @throws DukeException If index is invalid.
     */
    public Task getTask(int index) throws DukeException {
        return tasks.getTask(index);
    }
    /**
     * Deletes a task from the task list.
     * @param index The index of the task to be deleted.
     * @throws DukeException If index is invalid.
     */
    public void deleteTask(int index) throws DukeException {
        tasks.deleteTask(index);
        write();
    }
    /**
     * Marks a task as done.
     * @param index The index of the task to be marked as done.
     * @throws DukeException If index is invalid.
     */
    public void mark(int index) throws DukeException {
        tasks.mark(index);
        write();
    }
    /**
     * Marks a task as not done.
     * @param index The index of the task to be marked as not done.
     * @throws DukeException If index is invalid.
     */
    public void unmark(int index) throws DukeException {
        tasks.unmark(index);
        write();
    }
    public void setDescriptionAtIndex(int index, String description) throws DukeException {
        tasks.setDescriptionAtIndex(index, description);
        write();
    }
    public int getTaskCount() {
        return tasks.getTaskCount();
    }
    public boolean hasTaskAtIndex(int index) {
        return tasks.hasTaskAtIndex(index);
    }
    @Override
    public String toString() {
        return tasks.toString();
    }
    public String find(String keyword) throws DukeException {
        return tasks.findTasksWithKeyword(keyword).toString();
    }

    /**
     * Adds a tag to a task at the specified index.
     * @param index The index of the task to add the tag to.
     * @param tag The tag to be added.
     * @throws DukeException If index or tag is invalid.
     */
    public void addTagToTaskAtIndex(int index, String tag) throws DukeException {
        tasks.addTagToTaskAtIndex(index, tag);
        write();
    }

    /**
     * Removes a tag from a task at the specified index.
     * @param index The index of the task to remove the tag from.
     * @param tag The tag to be removed.
     * @throws DukeException If index or tag is invalid.
     */
    public void removeTagFromTaskAtIndex(int index, String tag) throws DukeException {
        tasks.removeTagFromTaskAtIndex(index, tag);
        write();
    }
    public String findTasksWithTag(String tag) throws DukeException {
        return tasks.findTasksWithTag(tag).toString();
    }
}


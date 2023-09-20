package duke.data.storage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.data.task.Task;
import duke.data.task.builder.TaskBuilder;
import duke.data.task.tasklist.Tasklist;
import duke.exception.DukeException;
import duke.exception.InternalException;


/**
 * Responsible for reading and writing to duke.txt in the data folder which stores the task list contents.
 */
public class Store {
    private static final Store store = new Store();
    private final Tasklist tasks = new Tasklist();
    private String filePath;
    private Path folder;
    private File storageFile;
    private final TaskBuilder taskBuilder = new TaskBuilder();

    private Store() {
        initPathToDataFile();
        try {
            loadTasklist();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assert false : e.getMessage();
        }
    }

    private void initPathToDataFile() {
        String rootPath = Paths.get("").toAbsolutePath().toString();
        this.filePath = Paths.get(rootPath, "data/tasks.txt").toString();
        Path path = Paths.get(filePath);
        int len = path.getNameCount();
        this.folder = Paths.get(rootPath, path.subpath(0, len - 1).toString());
        this.storageFile = new File(this.filePath);
    }
    private void loadTasklist() throws DukeException {
        if (!storageFile.exists()) {
            return;
        }
        try {
            Scanner sc = new Scanner(storageFile);
            while (sc.hasNext()) {
                String inputStr = sc.nextLine();
                if (inputStr.equals("")) {
                    continue;
                }
                // add task
                String[] taskTypeAndIsMarked = inputStr.split("/isMarked ");
                assert taskTypeAndIsMarked.length == 2 : "taskTypeAndIsMarked should have length 2";
                Task task = taskBuilder.buildFromString(taskTypeAndIsMarked[0]);
                tasks.addTask(task);
                //add tags
                String[] isMarkedAndTagsStr = taskTypeAndIsMarked[1].split("/tags ");
                if (isMarkedAndTagsStr[0].contains("true")) {
                    tasks.mark(tasks.getTaskCount());
                }
                String[] tags = isMarkedAndTagsStr[1].split(",");
                for (String tag : tags) {
                    if (tag.equals("todo") || tag.equals("deadline") || tag.equals("event")) {
                        continue;
                    }
                    tasks.addTagToTaskAtIndex(tasks.getTaskCount(), tag);
                }
            }

        } catch (FileNotFoundException e) {
            throw new InternalException("File not found: " + e.getMessage());
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
        if (!this.folder.toFile().exists()) {
            this.folder.toFile().mkdirs();
        }
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(tasks.getTaskRepresentations());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong storing the change: " + e.getMessage());
            assert false : "Something went wrong storing the change: " + e.getMessage();
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

    /**
     * Finds tasks with the specified keyword.
     * @param keyword The keyword to be searched for.
     * @return The tasks with the specified keyword.
     * @throws DukeException If keyword is invalid.
     */
    public String find(String keyword) {
        Tasklist foundTasks = tasks.findTasksWithKeyword(keyword);
        if (foundTasks.getTaskCount() == 0) {
            return "";
        }
        return foundTasks.toString();
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

    /**
     * Finds tasks with the specified tag.
     * @param tag The tag to be searched for.
     * @return The tasks with the specified tag.
     */
    public String findTasksWithTag(String tag) {
        Tasklist foundTasks = tasks.findTasksWithTag(tag);
        if (foundTasks.getTaskCount() == 0) {
            return "";
        }
        return foundTasks.toString();
    }
}


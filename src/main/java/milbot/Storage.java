package milbot;
import extensions.Tag;
import extensions.TagList;
import taskclasses.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Storage class handles reading tasks from and writing tasks to a file.
 */
public class Storage {
    private File taskFile;
    private File tagsFile;
    private FileWriter tasksOutput;
    private String tasksFilePath;
    private String tagsFilePath;
    private TaskList taskList;
    private TagList tagList;

    /**
     * Constructs a Storage instance with a default file path.
     */
    public Storage() {
        tasksFilePath = "src\\main\\data\\mil.txt";
        taskFile = new File(tasksFilePath);
        taskList = new TaskList();

        tagsFilePath = "src\\main\\data\\mil-tags.txt";
        tagsFile = new File(tagsFilePath);
        tagList = new TagList();
    }

    /**
     * Loads tasks from the file and adds them to the task list.
     */
    public TaskList loadTasksFromFile() {
        try (BufferedReader inputFile = new BufferedReader(new FileReader(taskFile))) {
            String line;
            while ((line = inputFile.readLine()) != null) {
                Task task = Task.createTaskFromData(line);
                if (task != null) {
                    taskList.addTask(task);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }

        return taskList;
    }

    public TagList loadTagsFromFile() {
        try (BufferedReader inputFile = new BufferedReader(new FileReader(tagsFile))) {
            String line;
            while ((line = inputFile.readLine()) != null) {
                if (line.contains("#")) {
                    Tag tag = new Tag(line.split("#")[1].trim());
                    tagList.addTag(tag);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading tags: " + e.getMessage());
        }

        return tagList;
    }


    /**
     * Saves tasks from the provided TaskList to the file.
     *
     * @param tasks The TaskList containing tasks to be saved.
     */
    public void saveTasksToFile(TaskList tasks) {
        try {
            taskList = tasks;
            tasksOutput = new FileWriter(tasksFilePath);
            BufferedWriter outputFile = new BufferedWriter(tasksOutput);
            for (Task task : tasks.getTaskList()) {
                outputFile.write(task.formatToFile());
                outputFile.newLine();
            }
            outputFile.close();
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    public void saveTagsToFile(TagList tags) {
        try {
            tagList = tags;
            tasksOutput = new FileWriter(tagsFilePath);
            BufferedWriter outputFile = new BufferedWriter(tasksOutput);
            for (Tag tag : tags.getTagList()) {
                outputFile.write(tag.toString());
                outputFile.newLine();
            }
            outputFile.close();
        } catch (IOException e) {
            System.err.println("Error saving tags: " + e.getMessage());
        }
    }
}

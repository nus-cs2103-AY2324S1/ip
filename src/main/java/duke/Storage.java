package duke;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    /**
     * The relative path of the file that contains the information of tasks.
     */
    private Path relativePath;

    /**
     * Constructs a Storage object with the provided file path.
     *
     * @param relativePath The relative file path where task data is stored.
     */
    public Storage(Path relativePath) {
        this.relativePath = relativePath;
    }

    /**
     * Reads and processes data of task from the text file of the relative path.
     *
     * @return An ArrayList of `Task` objects representing the tasks read from the file.
     */
    public ArrayList<Task> processFile() {

        ArrayList<Task> result = new ArrayList<>();
        try {
            if (Files.exists(relativePath)) {
                List<String> content = Files.readAllLines(relativePath);
                if (content.size() > 0) {
                    for (String line : content) {
                        result.add(processLines(line));
                    }
                } // else no task yet
            } else {
                Files.createFile(relativePath);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) { // catch for wrong format or corrupted file
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * Writes data of the task from the provided task list into the text file of the relative path.
     *
     * @param tasks The task list to be written into the file.
     */
    public void writeInto(TaskList tasks) {

        ArrayList<String> content = new ArrayList<>();
        tasks.copyArrInto(content);
        try {
            Files.write(relativePath, content);
        } catch (Exception e) {
            System.out.println("\t" + e);
        }
    }

    /**
     * Processes a line of text from the text file and convert it into a Task object accordingly.
     *
     * @param line The line of text representing a task in the file.
     * @return A Task object converted from the line of text.
     * @throws Exception If the line of text is not in the correct format or is corrupted.
     */
    public Task processLines(String line) throws Exception {

        Task t;
        String[] taskArr = line.split("/");
        switch (taskArr[0]) {
            case "T":
                t = new Todo(taskArr[2], !taskArr[1].isBlank());
                break;
            case "D":
                t = new Deadline(taskArr[2], !taskArr[1].isBlank(), processDateAndLine(taskArr[3]));
                break;
            case "E":
                t = new Event(taskArr[2], !taskArr[1].isBlank(), processDateAndLine(taskArr[3]),
                        processDateAndLine(taskArr[4]));
                break;
            default:
                throw new Exception("Some of the content is not in the correct format or it is corrupted");
        }
        return t;
    }

    /**
     * Processes a date and time in string format and convert it into a `LocalDateTime` object.
     *
     * @param dateAndTime The date and time in string format to be parsed.
     * @return A LocalDateTime object converted from parsed date and time.
     */
    public LocalDateTime processDateAndLine(String dateAndTime) {

        dateAndTime = dateAndTime.replace("T", "-").replace(":", "");
        String[] split = dateAndTime.split("-");
        int hr = Integer.parseInt(split[split.length - 1].substring(0, 2));
        int min = Integer.parseInt(split[split.length - 1].substring(2));
        return LocalDateTime.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]),
                Integer.parseInt(split[2]), hr, min);
    }
}

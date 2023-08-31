package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Data structure that stores the bot's tasks,
 * along with its operations to manipulate the data structure.
 */
public class TaskList {
    /** ArrayList stores bot's tasks. **/
    private ArrayList<Task> taskList;

    /**
     * Constructor for Tasklist with initial tasks read from saved file.
     * @param inputList String array containing tasks from saved file
     */
    public TaskList(ArrayList<String[]> inputList) {
        taskList = new ArrayList<>();
        for (String[] line : inputList) {
            switch (line[0]) {
                case "T":
                    ToDo t = new ToDo(line[2]);
                    if (line[1].equals("1")) {
                        t.markAsDone();
                    }
                    taskList.add(t);
                    break;
                case "D":
                    Deadline d = new Deadline(line[2],
                            LocalDateTime.parse(line[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
                    if (line[1].equals("1")) {
                        d.markAsDone();
                    }
                    taskList.add(d);
                    break;
                case "E":
                    Event e = new Event(line[2],
                            LocalDateTime.parse(line[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                            LocalDateTime.parse(line[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
                    if (line[1].equals("1")) {
                        e.markAsDone();
                    }
                    taskList.add(e);
                    break;
            }
        }
    }

    /**
     * Constructor for empty tasklist.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Deletes specified task from memory.
     * @param index Task number to be deleted
     * @throws DukeException errors that occur due to invalid user input
     */
    public void delete(int index) throws DukeException {
        if (index < 0 || index > taskList.size()-1) {
            throw new DukeException("Wow, deleting a nonexistent task? Check your tasks again with 'list'.");
        }
        Task t = taskList.get(index);
        taskList.remove(t);
        String returnLine = "Looks like you have more time to sleep now. Deleted this for you:\n"
                + t.toString()
                + "\nYou now have " + taskList.size() + " things to do.\n";
        System.out.println(returnLine);
    }

    /**
     * Adds a new Deadline task.
     * @param task Deadline to be added
     */
    public void addDeadline(Deadline task) {
        taskList.add(task);
    }

    /**
     * Adds a new Event task.
     * @param task Event to be added
     */
    public void addEvent(Event task) {
        taskList.add(task);
    }

    /**
     * Adds a new ToDo task.
     * @param task ToDo to be added
     */
    public void addToDo(ToDo task) {
        taskList.add(task);
    }

    /**
     * Retrieves specified task from tasklist
     * @param index task number to be retrieved
     * @return task specified
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Returns number of tasks in tasklist
     * @return number of tasks in tasklist
     */
    public int getLength() {
        return taskList.size();
    }

    /**
     * Returns a string representation of the tasks in the tasklist.
     * @return Tasks to write into save file
     */
    public String[] toWriteString() {
        String[] writtenTasks = new String[taskList.size()];
        for (int i = 0; i < taskList.size(); i++) {
            writtenTasks[i] = taskList.get(i).toWriteString();
        }
        return writtenTasks;
    }

}

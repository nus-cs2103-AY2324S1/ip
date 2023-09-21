package duke.util;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.InvalidTaskNumberException;
import duke.exception.NoTaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Tasklist containing the tasks added by the user.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor for an instance of an empty tasklist.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for a tasklist with a string of tasks.
     * @param fileData ArrayList of strings with each string being a task to be added into the tasklist
     * @throws DukeException exceptions that will be encountered when trying to add tasks
     */
    public TaskList(ArrayList<String> fileData) throws DukeException {
        String[] fields;
        this.tasks = new ArrayList<>();
        for (String info : fileData) {
            fields = info.split(" \\| ");
            switch (fields[0].trim()) {
            case "T":
                assert !fields[2].trim().isEmpty() : "task stored has no description";
                parseToDo(fields);

                break;
            case "D":
                assert !fields[2].trim().isEmpty() : "task stored has no description";
                parseDeadline(fields);
                break;
            case "E":
                assert !fields[2].trim().isEmpty() : "task stored has no description";
                parseEvent(fields);
                break;
            default:
                throw new DukeException("Invalid task type found in file");
            }
        }
    }

    /**
     * Parses the todo string stored in the file.
     * @param fields fields from the info stored
     * @throws NoTaskException error if task found in file is empty
     */
    private void parseToDo(String[] fields) throws NoTaskException {
        Task tempT;
        if (fields[2].isEmpty() || fields[3].isEmpty()) {
            throw new NoTaskException();
        }
        tempT = new ToDo(fields[2].trim());
        if (fields[1].equals("1")) {
            tempT.markAsDone();
        }
        tempT.setPriority(getPriority(fields[3].trim()));
        tasks.add(tempT);
    }

    /**
     * Parses the deadline string stored in the file.
     * @param fields fields from the info stored
     * @throws NoTaskException error if task found in file is empty
     */
    private void parseDeadline(String[] fields) throws DukeException {
        Task tempT;
        if (fields[2].isEmpty() || fields[3].isEmpty() || fields[4].isEmpty()) {
            throw new NoTaskException();
        }
        tempT = new Deadline(fields[2].trim(), fields[3]);
        if (fields[1].equals("1")) {
            tempT.markAsDone();
        }
        tempT.setPriority(getPriority(fields[4].trim()));
        tasks.add(tempT);
    }

    /**
     * Parses the event string stored in the file.
     * @param fields fields from the info stored
     * @throws NoTaskException error if task found in file is empty
     */
    private void parseEvent(String[] fields) throws DukeException {
        String[] time;
        Task tempT;
        if (fields[2].isEmpty() || fields[3].isEmpty() || fields[4].isEmpty()) {
            throw new NoTaskException();
        }
        time = fields[3].split("->");
        tempT = new Event(fields[2].trim(), time[0], time[1]);
        if (fields[1].equals("1")) {
            tempT.markAsDone();
        }
        tempT.setPriority(getPriority(fields[4]));
        tasks.add(tempT);
    }

    /**
     * Marks a task within the tasklist as complete.
     * @param taskNumber task number to be marked
     * @param ui instance of user interface
     * @return string containing user response
     * @throws InvalidTaskNumberException Error when given task number exceeds the number of tasks in the list
     */
    public String markTask(int taskNumber, Ui ui) throws InvalidTaskNumberException {
        if (taskNumber > tasks.size()) {
            throw new InvalidTaskNumberException(tasks.size());
        }
        tasks.get(taskNumber - 1).markAsDone();
        return ui.showMarked(tasks.get(taskNumber - 1));
    }

    /**
     * Unmarks a task with the given task number in the tasklist.
     * @param taskNumber task number to be unmarked
     * @param ui instance of user interface
     * @return string containing user response
     * @throws InvalidTaskNumberException exception when the task number given is outside the count of tasklist
     */
    public String unmarkTask(int taskNumber, Ui ui) throws InvalidTaskNumberException {
        if (taskNumber > tasks.size()) {
            throw new InvalidTaskNumberException(tasks.size());
        } else {
            tasks.get(taskNumber - 1).unmarkAsDone();
            return ui.showUnmarked(tasks.get(taskNumber - 1));
        }
    }

    /**
     * Adds given task to the tasklist.
     * @param task Task to be added to the list
     * @param ui instance of user interface
     * @return string containing user response
     */
    public String addTask(Task task, Ui ui) {
        tasks.add(task);
        return ui.showTaskAdded(task, tasks.size());
    }

    /**
     * Deletes a task from the tasklist.
     * @param taskNumber Task number of task to be deleted
     * @param ui instance of user interface
     * @return string containing user response
     * @throws InvalidTaskNumberException Exception when given task number is outside range of tasks in the list
     */
    public String deleteTask(int taskNumber, Ui ui) throws InvalidTaskNumberException {
        if (taskNumber > tasks.size()) {
            throw new InvalidTaskNumberException(tasks.size());
        } else {
            Task temp = tasks.remove(taskNumber - 1);
            assert temp != null;
            return ui.showComplete("Noted. I've removed this task:"
                    + temp
                    + ". Now you have " + this.tasks.size() + " task(s) in the list");
        }
    }

    /**
     * Returns tasks in the tasklist in format to be written into a file.
     * @return ArrayList of formatted strings to be written
     */
    public ArrayList<String> toWriteFormat() {
        ArrayList<String> taskStrings = new ArrayList<>();
        for (Task task : tasks) {
            assert task != null;
            taskStrings.add(task.toSaveFormat());
        }
        return taskStrings;
    }

    /**
     * Returns string representations of tasks in an arraylist to be printed by the UI.
     * @return ArrayList of strings to be printed onto ui
     */
    public ArrayList<String> getTaskStrings() {
        ArrayList<String> listOfStrings = new ArrayList<>();
        for (Task task : tasks) {
            listOfStrings.add(task.toString());
        }

        if (listOfStrings.isEmpty()) {
            listOfStrings.add("OH NO! The list is empty!!");
        }
        return listOfStrings;
    }

    /**
     * Filters tasklist to search for a keyword.
     * @param keyword keyword to search if task description contains
     * @return Arraylist containing filtered tasks
     */
    public ArrayList<String> filterToList(String keyword) {
        ArrayList<String> filteredList = new ArrayList<>();
        for (Task task : tasks) {
            if (task.contains(keyword)) {
                filteredList.add(task.toString());
            }
        }

        if(filteredList.isEmpty()) {
            filteredList.add("No results match your filter");
        }

        return filteredList;
    }

    /**
     * Changes priority of a task in the tasklist.
     * @param taskNumber task number of task to be changed
     * @param priority priority level to change to
     * @param ui instance of user interface
     * @return String to be displayed on UI
     * @throws InvalidTaskNumberException Error thrown when task number given is outside bounds
     */
    public String setPriority(int taskNumber, Priority priority, Ui ui) throws InvalidTaskNumberException {
        if (taskNumber > tasks.size()) {
            throw new InvalidTaskNumberException(tasks.size());
        } else {
            Task temp = tasks.get(taskNumber - 1);
            assert temp != null;
            temp.setPriority(priority);
            return ui.showComplete("Noted. I've updated the priority of this task:"
                    + temp);
        }
    }

    /**
     * Gets priority enum from string saved in the file.
     * @param priorityString the string saved in the file
     * @return priority enum of corresponding string
     */
    private Priority getPriority(String priorityString) {
        assert priorityString != null;
        switch(priorityString) {
        case "LOW":
            return Priority.LOW;
        case "MEDIUM":
            return Priority.MEDIUM;
        case "HIGH":
            return Priority.HIGH;
        default:
            return null; //should not hit this case but sets default low
        }
    }
}

package duke;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * The collection that contains the list of Tasks
 */
public class TaskList implements Serializable {
    private ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the number of tasks in ArrayList(Task)
     * @return The number of tasks in the collection
     */
    public int getSize() {
        return tasks.size();
    }
    /**
     * Gets the duke.Task at the specific position of the ArrayList(Task)
     * @param i The position of the duke.Task
     * @return The duke.Task
     */
    public Task getTaskByIndex(int i) {
        return tasks.get(i);
    }
    /**
     * Gets the ArrayList(Task)
     * @return The collection
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
    /**
     * Displays the list of Tasks
     */
    public String displayList() {
        return Ui.displayList(tasks);
    }
    /**
     * Handles the 'find' command, displays the list of Tasks whose description matches the user input
     * @param userInput The String that the user inputs to find similar Tasks
     */
    public String findMatchingTasks(String userInput) {
        ArrayList<Task> matchingTaskList = new ArrayList<>();
        for (Task task : tasks) {
            String taskDescription = task.getDescription();
            if (taskDescription.contains(userInput)) {
                matchingTaskList.add(task);
            }
        }
        return Ui.displayMatchingList(matchingTaskList);
    }
    /**
     * Adds a Task to TaskList
     * @param letter The first letter of the Task
     * @param userInput The chunk of text after the word todo, deadline, or event
     */
    public String addTask(String letter, String userInput) throws DukeException {
        if (letter.equals("T")) {
            if (userInput.length() <= 5) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            assert userInput.length() > 5 : "todo description is too short";
            String userInputWithoutPrefix = userInput.substring(5);
            tasks.add(ToDo.newToDo(userInputWithoutPrefix));
        }
        if (letter.equals("D")) {
            if (userInput.length() <= 9) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            assert userInput.length() > 9 : "deadline description is too short";
            String userInputWithoutPrefix = userInput.substring(9);
            tasks.add(Deadline.newDeadline(getDescription(userInputWithoutPrefix),
                    getBy(userInputWithoutPrefix)));
        }
        if (letter.equals("E")) {
            if (userInput.length() <= 6) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            assert userInput.length() > 6 : "event description is too short";
            String userInputWithoutPrefix = userInput.substring(6);
            tasks.add(Event.newEvent(getDescription(userInputWithoutPrefix),
                    getFrom(userInputWithoutPrefix), getTo(userInputWithoutPrefix)));
        }
        int tasksSize = tasks.size();
        Task newlyAddedTask = tasks.get(tasksSize - 1);
        return Ui.handleAddTaskUi(tasksSize, newlyAddedTask);
    }
    /**
     * Marks a task as either completed or uncompleted
     * For example, the input 'mark 1' will mark the Task at position 0 at the TaskArray as 'marked'
     * @param userInput User input
     * @throws DukeException If input is invalid
     */
    public String markTask(String userInput) throws DukeException {
        String digits = userInput.replaceAll("\\D+", ""); //remove non-digits
        int pos = Integer.parseInt(digits) - 1;
        if (pos >= tasks.size()) {
            throw new DukeException("You are trying to access a Task that does not exist!");
        }
        assert pos < tasks.size() : "pos should be less than the length of TaskList";
        Task curr = tasks.get(pos);
        if (userInput.contains("unmark")) {
            curr.markAsUnDone();
        } else if (userInput.contains("mark")) {
            curr.markAsDone();
        }
        return Ui.handleMarkTaskUi(userInput, curr);
    }
    /**
     * For Deadline and Event Tasks, obtains the Task description (before the first slash)
     * For example, the input 'event project meeting /from Mon 2pm /to 4pm' will return 'project meeting'
     *
     * @param userInput User input
     * @return The Task description
     */
    public static String getDescription(String userInput) {
        int len = userInput.length();
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (userInput.charAt(i) == '/') {
                break;
            }
            count++;
        }
        return userInput.substring(0, count);
    }
    /**
     * Obtains the 'by' time component of a Deadline Task
     * For example, the input 'deadline return book /by Sunday' will return 'Sunday'
     *
     * @param userInput The Task description, including the time component
     * @return The time component
     * @throws DukeException If user input is invalid
     */
    public static String getBy(String userInput) throws DukeException {
        String slash = "/";
        int first = userInput.indexOf(slash);
        if (first == -1 || !userInput.substring(first + 1, first + 3).contains("by")) {
            throw new DukeException("You need to add a by timing!");
        }
        return userInput.substring(first + 4); // returns "Sunday"
    }

    /**
     * Obtains the 'from' time component of an Event Task
     * For example, the input 'event project meeting /from Mon 2pm /to 4pm' will return 'Mon 2pm'
     *
     * @param userInput The Task description, including the time component
     * @return The 'from' time component
     * @throws DukeException If user input is invalid
     */
    public static String getFrom(String userInput) throws DukeException {
        String slash = "/";
        int firstSlash = userInput.indexOf(slash);
        int secondSlash = userInput.indexOf(slash, firstSlash + 1);

        if (firstSlash == -1 || secondSlash == -1
                || !userInput.substring(firstSlash, firstSlash + 5).equals("/from")) {
            throw new DukeException("You need to add a /from and /to for events");
        }

        return userInput.substring(firstSlash + 6, secondSlash - 1);
    }
    /**
     * Obtains the 'to' part of an Event Task
     * For example, the input 'event project meeting /from Mon 2pm /to 4pm' will return '4pm'
     *
     * @param userInput The Task description, including the time component
     * @return The 'to' time component
     * @throws DukeException If user input is invalid
     */
    public static String getTo(String userInput) throws DukeException {
        String slash = "/";
        int firstSlash = userInput.indexOf(slash);
        int secondSlash = userInput.indexOf(slash, firstSlash + 1);

        if (!userInput.substring(secondSlash, secondSlash + 3).equals("/to")) {
            throw new DukeException("You need to add a /to for events");
        }
        return userInput.substring(secondSlash + 4);
    }
    /**
     * Deletes a Task from TaskList
     * @param userInput The user input
     */
    public String deleteTask(String userInput) throws DukeException {
        String digits = userInput.replaceAll("\\D+", ""); //remove non-digits
        int pos = Integer.parseInt(digits) - 1;
        if (pos >= tasks.size()) {
            throw new DukeException("You are trying to delete a Task that does not exist");
        } else {
            assert pos < tasks.size() : "pos should be less than the length of TaskList";
            Task deletedTask = tasks.get(pos);
            tasks.remove(pos);
            int tasksSize = tasks.size();
            return Ui.handleDeleteTaskUi(deletedTask, tasksSize);
        }
    }
    /**
     * Displays user guide
     * @return Output
     */
    public String displayUserGuide() {
        return Ui.showUserGuide();
    }
    /**
     * Undo the most recent user command
     * @throws IOException If undo.txt is an empty file
     */
    public void undo(Storage storage) throws IOException, ClassNotFoundException {
        try {
            Path absolutePath = Duke.OLD_TASKLIST_FILE_PATH.toAbsolutePath();
            ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(absolutePath));
            @SuppressWarnings("unchecked")
            ArrayList<Task> loadedTasks = (ArrayList<Task>) inputStream.readObject();
            tasks.clear();
            tasks.addAll(loadedTasks);
            storage.copyUndoToTemp();
        } catch (IOException e) {
            tasks.clear();
        }
    }
}

package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.DukeException;

/**
 * Represents a list of tasks with various operations.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs a TaskList.
     *
     * @param list The ArrayList containing tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.list = null;
    }

    /**
     * Adds a task to the list based on the given type and description.
     *
     * @param type The type of task ("todo", "deadline", or "event").
     * @param description The description of the task.
     * @return The added task.
     * @throws DukeException If the input type is invalid.
     */
    public Task addTask(String type, String description) throws DukeException {
        assert type != null;
        assert description != null;
        if (type.equals("todo")) {
            return addTodo(description, list);
        } else if (type.equals("deadline")) {
            return addDeadline(description, list);
        } else if (type.equals("event")) {
            return addEvent(description, list);
        } else {
            throw new DukeException("That's not a valid input!");
        }
    }
    /**
     * Adds a ToDo task to the list of tasks.
     *
     * @param description The description of the ToDo task.
     * @param list The ArrayList to which the task will be added.
     * @return The added ToDo task.
     * @throws DukeException If the description is empty.
     */
    public ToDo addTodo(String description, ArrayList<Task> list) throws DukeException {
        assert list != null;
        if (description.isBlank()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        ToDo newTask = new ToDo(description);
        list.add(newTask);
        return newTask;
    }

    /**
     * Adds a new Deadline task to the list based on the description provided.
     *
     * @param description The description of the task and its deadline.
     * @param list        The ArrayList of tasks to add the new task to.
     * @return The newly added Deadline task.
     * @throws DukeException If there is an issue adding the task.
     */
    public Deadline addDeadline(String description, ArrayList<Task> list) throws DukeException {
        String[] deadline = description.stripTrailing().split("/by ", 2);
        if (deadline[0].isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        if (deadline.length == 1) {
            throw new DukeException("Please provide a valid deadline");
        }

        Deadline newTask = null;
        try {
            LocalDate endDate = LocalDate.parse(deadline[1]);
            newTask = new Deadline(deadline[0], endDate);
            list.add(newTask);

        } catch (DateTimeParseException e) {
            throw new DukeException("Your date should be formatted as YYYY-MM-DD");
        }

        return newTask;
    }
    /**
     * Adds a new Event task to the list based on the description provided.
     *
     * @param description The description of the task and its start and end dates.
     * @param list        The ArrayList of tasks to add the new task to.
     * @return The newly added Event task.
     * @throws DukeException If there is an issue adding the task.
     */
    public static Event addEvent(String description, ArrayList<Task> list) throws DukeException {
        String[] event = description.stripTrailing().split("/from |/to ");
        if (event[0].isEmpty()) {
            throw new DukeException("The description of an Event cannot be empty.");
        }
        if (event.length < 3) {
            throw new DukeException("Please provide a valid start and end date");
        }

        Event newTask = null;
        try {
            LocalDate start = LocalDate.parse(event[1].stripTrailing());
            LocalDate end = LocalDate.parse(event[2].stripTrailing());
            if (start.isAfter(end)) {
                throw new DukeException("Your start date has to be before your end date!");
            }
            newTask = new Event(event[0], start, end);

            list.add(newTask);
        } catch (DateTimeParseException e) {
            throw new DukeException("Your date should be formatted as YYYY-MM-DD");
        }

        return newTask;
    }
    /**
     * Deletes a task at the specified position.
     *
     * @param pos The position of the task to be deleted.
     * @return The deleted task.
     * @throws DukeException If the position is invalid or not provided.
     */
    public Task deleteTask(String pos) throws DukeException {
        if (pos.isEmpty()) {
            throw new DukeException("Please indicate which item you wish to delete");
        }
        try {
            int amt = Integer.parseInt(pos.strip()) - 1;
            if (amt >= list.size() || amt < 0) {
                throw new DukeException("That's not a valid item!");
            }

            Task temp = list.get(amt);
            list.remove(amt);

            return temp;
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter the number of the item you wish to delete");
        }
    }
    /**
     * Marks or unmarks a task at the specified position.
     *
     * @param type The action ("mark" or "unmark").
     * @param pos The position of the task to be marked or unmarked.
     * @return The task after marking or unmarking.
     * @throws DukeException If the position is invalid or not provided.
     */
    public Task markTask(String type, String pos) throws DukeException {
        if (pos.isEmpty()) {
            throw new DukeException("Please indicate which item you wish to mark");
        }
        try {
            int amt = Integer.parseInt(pos.strip()) - 1;
            if (amt >= list.size() || amt < 0) {
                throw new DukeException("That's not a valid item!");
            }
            Task newTask = list.get(amt);

            if (type.equals("mark")) {
                newTask.mark();
            } else if (type.equals("unmark")) {
                newTask.unMark();
            }
            return newTask;
        } catch (NumberFormatException e) {
            throw new DukeException("Please indicate which item you wish to mark");
        }
    }
    /**
     * Reads task information from a file and adds it to the task list.
     *
     * @param arr The array of task information from the file.
     * @throws DukeException If there's a problem with the file or task data.
     */
    public void readListFromFile(String[] arr) throws DukeException {
        if (arr.length != 3) {
            throw new DukeException("There seems to be a problem with the save file!\n"
                    + "Some of the tasks may be gone! Sorry!!\n");
        }
        String type = arr[0].strip();
        String description = arr[2].strip();
        String isMarked = arr[1].strip();
        if (type.equals("T")) {
            ToDo newTask = new ToDo(description);
            newTask.markFromRead(isMarked);
            list.add(newTask);
        } else if (type.equals("D")) {
            String[] deadline = description.stripTrailing().split("/by ", 2);
            Deadline newTask = new Deadline(deadline[0], LocalDate.parse(deadline[1]));
            newTask.markFromRead(isMarked);
            list.add(newTask);
        } else if (type.equals("E")) {
            String[] event = description.stripTrailing().split("/from |/to ");
            try {
                Event newTask = new Event(event[0], LocalDate.parse(event[1]), LocalDate.parse(event[2]));
                list.add(newTask);
                newTask.markFromRead(isMarked);
            } catch (DateTimeParseException e) {
                throw new DukeException("There seems to be a problem with the save file!\n");
            }
        }
    }
    /**
     * Finds tasks that match a specified keyword within the task list.
     *
     * This method searches through the task list for tasks whose descriptions contain the specified keyword,
     * and returns a list of tasks that match the search criteria.
     *
     * @param keyword The keyword to search for within task descriptions.
     * @return An ArrayList containing tasks that match the search criteria.
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> resultList = new ArrayList<>();
        Task[] temp = list.toArray(new Task[0]);

        for (Task task : temp) {
            String description = task.getDescription().stripTrailing().toLowerCase();
            if (description.contains(keyword.stripTrailing().toLowerCase())) {
                resultList.add(task);
            }
        }

        return resultList;
    }
    /**
     * Gets a task at the specified position.
     *
     * @param pos The position of the task to retrieve.
     * @return The task at the specified position.
     */
    public Task getTask(int pos) {
        return list.get(pos);
    }

    /**
     * Gets the entire task list.
     *
     * @return The ArrayList containing tasks.
     */
    public ArrayList<Task> getList() {
        assert list != null;
        return this.list;
    }
}

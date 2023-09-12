package duke.task;

import duke.Duke;
import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * Provides methods to add, delete, and manipulate tasks within the list.
 */
public class DukeList {

    /** The list of tasks stored in this DukeList. */
    ArrayList<Task> arr;

    /**
     * Constructs a DukeList object with the given initial list of tasks.
     *
     * @param dukelist The initial list of tasks.
     */
    public DukeList(ArrayList<Task> dukelist) {
        this.arr = dukelist;
    }

    /**
     * Constructs a DukeList object with an empty initial list of tasks.
     */
    public DukeList() {
        this.arr = new ArrayList<>(100);
    }

    /**
     * Retrieves the list of tasks stored in the DukeList.
     *
     * @return The list of tasks stored in the DukeList.
     */
    public ArrayList<Task> getList() {
        return this.arr;
    }


    /**
     * Deletes a task from the list by its index.
     *
     * @param description The index of the task to be deleted.
     */
    public Task deleteTask(String description) throws DukeException {
        if (!description.equals(null)) {
            int num = Integer.parseInt(description);
            Task deletedTask = arr.get(num);
            arr.remove(num);
            return deletedTask;
        } else {
            throw new DukeException("Please indicate the index of task to be deleted");
        }

    }
    public Todo createTodo(String description) throws DukeException{
        if (description == null || description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new Todo(description);
    }

    public Deadline createDeadline(String description) throws DukeException {
        String[] splited = description.split(" ", 2);
        if (splited.length > 1) {
            String[] splitted = description.split(" ", 2);
            String[] deadline = splitted[1].split("/by", 2);
            LocalDateTime by = formatData(deadline[1]);
            return new Deadline(deadline[0], by);
        } else {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
    }

    public Event createEvent(String description) throws DukeException {
        String[] splited = description.split(" ", 2);
        if (splited.length > 1) {
            String[] splitted = description.split(" ", 2);
            String[] from = splitted[1].split("/from", 2);
            String[] to = from[1].split("/to", 2);
            LocalDateTime start = formatData(to[0]);
            LocalDateTime end = formatData(to[1]);
            return new Event(from[0], start, end);
        } else {
            throw new DukeException("The description of an event cannot be empty.");
        }
    }


    public Task addTask(String taskType, String description) throws DukeException {
        Task newTask = null;
        try {
            if (taskType.equals("Todo")) {
                newTask = createTodo(description);
            }
            if (taskType.equals("Deadline")) {
                newTask = createDeadline(description);
            }
            if (taskType.equals("Event")) {
                newTask = createEvent(description);
            }
            this.arr.add(newTask);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
        return newTask;
    }



    /**
<<<<<<< HEAD
     * Marks a task as done by its index.
     *
     * @param number The index of the task to be marked as done.
     */
    public void setDone(int number) {
        Task chosenTask = arr.get(number);
        chosenTask.markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + chosenTask.toString());
    }

    /**
<<<<<<< HEAD
     * Marks a task as not done by its index.
     *
     * @param number The index of the task to be marked as not done.
     */
    public void setUndone(int number) {
        Task chosenTask = arr.get(number);
        chosenTask.markUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + chosenTask.toString());
    }

    public Task setTaskStatus(String description, boolean toMark) throws DukeException {
        String[] splited = description.split(" ", 2);
        if (splited.length > 1){
            int num = Integer.parseInt(splited[1]);
            if (toMark) {
                setDone(num);
            } else {
                setUndone(num);
            }
            return arr.get(num);

        } else {
            throw new DukeException("Please indicate the index of task to be marked");
        }
    }

    public String findTasks(String description) throws DukeException {
        String[] splited = description.split(" ", 2);
        if (splited.length > 1) {
            String desc = splited[1];
            DukeList newList = new DukeList();
            String strToReturn = "Here are the matching tasks in the list:";
            for (int i = 0; i < this.arr.size(); i++) {
                int count = 1;
                if (arr.get(i).description.contains(desc)) {
                    strToReturn += (count + ". " + arr.get(i).toString() + "\n");
                    count++;
                }
            }
            return strToReturn;
        } else {
            throw new DukeException("The description of find cannot be empty.");
        }
    }

    public LocalDateTime formatData(String data) {
        String trimmed = data.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime localDate;
        return localDate = LocalDateTime.parse(trimmed, formatter);
    }
}

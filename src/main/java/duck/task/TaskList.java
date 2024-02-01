package duck.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import duck.exceptions.DuckException;
import duck.exceptions.IllegalDateFormatException;
import duck.exceptions.SemanticException;
import duck.exceptions.SyntaxException;

/**
 * Manages all the operations of tasks on the list.
 */
public class TaskList {
    private List<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    public TaskList(List<Task> t) {
        this.listOfTasks = t;
    }
    public List<Task> getListOfTasks() {
        return listOfTasks;
    }

    public void setListOfTasks(List<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public void addTask(Task t) {
        this.listOfTasks.add(t);
    }

    public Task removeIndex(int index) {
        return this.listOfTasks.remove(index);
    }

    /**
     * Marks a task at index
     * @param index index to mark
     * @throws SemanticException if index invalid
     */
    public void markTask(int index) throws SemanticException {
        if (index < 0) {
            throw new SemanticException("Index must be positive");
        } else if (index >= this.size()) {
            throw new SemanticException("Index must be less than or equal to the size of Task List");
        }
        this.listOfTasks.get(index).markAsDone();
    }

    /**
     * Unmarks a task at index
     * @param index index to mark
     * @throws SemanticException if index invalid
     */
    public void unmarkTask(int index) throws SemanticException {
        if (index < 0) {
            throw new SemanticException("Index must be positive");
        } else if (index >= this.size()) {
            throw new SemanticException("Index must be less than or equal to the size of Task List");
        }
        this.listOfTasks.get(index).markUndone();
    }

    public int size() {
        return this.listOfTasks.size();
    }

    public Task get(int index) {
        assert index >= 0 : "index should be positive or zero";
        assert index < this.listOfTasks.size() : "index should be within size of tasklist";
        return this.listOfTasks.get(index);
    }

    public Deadline setDeadline(String str) throws IllegalDateFormatException, SyntaxException {
        String[] arr = str.split("/by ");
        try {
            if (arr.length != 2) {
                throw new SyntaxException("Please check the command syntax");
            }
            return new Deadline(arr[0], parseDateTime(arr[1]));
        } catch (DateTimeParseException e) {
            throw new IllegalDateFormatException("Wrong Format for the date kindly put in \nyyyy-MM-dd HHmm.", str);
        }
    }

    public Events setEvent(String str) throws IllegalDateFormatException, SyntaxException {
        try {
            String[] event = str.split("/from | /to ");
            if (event.length != 3) {
                throw new SyntaxException("Please check the command syntax");
            }
            return getEvents(event);
        } catch (DateTimeParseException e) {
            throw new IllegalDateFormatException("Wrong Format for the date kindly put in \nyyyy-MM-dd HHmm.", str);
        }
    }

    private static Events getEvents(String[] event) {
        return new Events(LocalDateTime.parse(event[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
            LocalDateTime.parse(event[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")), event[0]);
    }

    private LocalDateTime parseDateTime(String dateTime) throws IllegalDateFormatException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new IllegalDateFormatException("Incorrect format", dateTime);
        }
    }

    /**
     * Deletes a particular task from the list.
     * @param str Contains the delete command.
     * @param taskList It is the tasklist consisting of all the tasks.
     * @return Task object.
     * @throws SyntaxException Throws invalid Syntax exception for wrong syntax.
     * @throws SemanticException Throws Exception when invalid values are given.
     */
    public Task deleteTask(String str, TaskList taskList) throws SyntaxException, SemanticException {
        try {
            String[] string = str.split(" ");
            if (string.length != 2) {
                throw new SyntaxException("Need only index number after delete");
            }
            int index = Integer.parseInt(string[1]) - 1;
            return taskList.removeIndex(index);
        } catch (NumberFormatException e) {
            throw new SyntaxException("Need only index number after delete");
        } catch (IndexOutOfBoundsException e) {
            throw new SemanticException("Index is out of bounds, please write correct index number");
        }
    }

    private int getIndexOfMark(String str) {
        assert str.length() >= 4 : "Index should be there";
        int index = Integer.parseInt(str.substring(4).strip()) - 1;
        return index;
    }

    public Task setDone(String str, TaskList taskList) throws SyntaxException, SemanticException {
        try {
            int index = getIndexOfMark(str.strip());
            taskList.markTask(index);
            return taskList.get(index);
        } catch (NumberFormatException e) {
            throw new SyntaxException("Need only index number to mark as done");
        } catch (IndexOutOfBoundsException e) {
            throw new SemanticException("Index is out of bounds, please write correct index number");
        }
    }

    public Task setUndone(String str, TaskList taskList) throws SyntaxException, SemanticException {
        try {
            int index = getIndexOfUnmark(str.strip());
            taskList.unmarkTask(index);
            return taskList.get(index);
        } catch (NumberFormatException e) {
            throw new SyntaxException("Need only index number to mark as undone");
        } catch (IndexOutOfBoundsException e) {
            throw new SemanticException("Index is out of bounds, please write correct index number");
        }

    }

    /**
     * Parses a string to extract an integer index to unmark the task at that index number.
     * @param str The input string from which to extract the integer index.
     * @return The integer index parsed from the input string.
     * @throws NumberFormatException If the substring after first 7 characters cannot be parsed as an integer.
     */

    private int getIndexOfUnmark(String str) {
        return Integer.parseInt(str.substring(6).strip()) - 1;
    }

    public ToDo setToDo(String str) throws DuckException {
        String todo = str.strip();
        if (!todo.isBlank()) {
            return new ToDo(todo);
        } else {
            throw new DuckException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Searches for tasks in the list that contains a specific keyword in their description.
     * This method iterates through the list of tasks and checks if each task's description contains
     * that particular keyword or phrase.
     * @param command The keyword or phrase to search for within the task descriptions.
     * @return An ArrayList containing the tasks that match the search criteria.
     */
    public ArrayList<Task> find(String command) throws SyntaxException {
        if (command.isBlank()) {
            throw new SyntaxException("find keyword can not be blank");
        }
        String str = command.substring(1);
        ArrayList<Task> searchedTasks = new ArrayList<>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            if (listOfTasks.get(i).getDescription().contains(str)) {
                searchedTasks.add(listOfTasks.get(i));
            }
        }
        return searchedTasks;
    }

    /**
     * Searches for the tasks scheduled on a specific date based on a given date command.
     * This method parses the provided date command in the "yyyy-MM-DD" to search for tasks scheduled on that date.
     * Iterates through the list to find tasks that matches the specified date.
     * @param cmd The date command in the "yyyy-MM-dd" format to search for tasks scheduled on that date.
     * @return  An ArrayList containing tasks scheduled for the specified date.
     * @throws IllegalDateFormatException If the provided date command is in an invalid format.
     */
    public ArrayList<Task> checkSchedule(String cmd) throws SyntaxException {
        if (cmd.isBlank()) {
            throw new SyntaxException("Date must be provided");
        }
        ArrayList<Task> searchTaskOnSpecificDate = new ArrayList<>();
        try {
            LocalDate target = LocalDate.parse(cmd.strip(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            for (int i = 0; i < listOfTasks.size(); i++) {
                Task task = listOfTasks.get(i);
                if (task instanceof Events) {
                    LocalDate date = ((Events) task).getRawDate();
                    if (target.equals(date)) {
                        searchTaskOnSpecificDate.add(task);
                    }
                } else if (task instanceof Deadline) {
                    LocalDate date = ((Deadline) task).getRawDate();
                    if (target.equals(date)) {
                        searchTaskOnSpecificDate.add(task);
                    }
                }
            }
        } catch (DateTimeParseException e) {
            throw new IllegalDateFormatException("Invalid Date Format", cmd);
        }
        return searchTaskOnSpecificDate;
    }

}

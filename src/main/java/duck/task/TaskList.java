package duck.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import duck.exceptions.DuckException;
import duck.exceptions.IllegalDateFormatException;
import duck.exceptions.SemanticException;
import duck.exceptions.SyntaxException;

public class TaskList {
    private List<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    public TaskList(List<Task> t) {
        this.listOfTasks = t;
    }

    public void addTask(Task t) {
        this.listOfTasks.add(t);
    }

    public Task removeIndex(int index) {
        return this.listOfTasks.remove(index);
    }

    public void markTask(int index) {
        this.listOfTasks.get(index).markAsDone();
    }

    public void unmarkTask(int index) {
        this.listOfTasks.get(index).markUndone();
    }

    public int size() {
        return this.listOfTasks.size();
    }

    public Task get(int index) {
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
            return new Events(LocalDateTime.parse(event[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                LocalDateTime.parse(event[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")), event[0]);
        } catch (DateTimeParseException e) {
            throw new IllegalDateFormatException("Wrong Format for the date kindly put in \nyyyy-MM-dd HHmm.", str);
        }
    }

    private LocalDateTime parseDateTime(String dateTime) throws IllegalDateFormatException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new IllegalDateFormatException("Incorrect format", dateTime);
        }
    }

    public Task deleteTask(String str, TaskList taskList) throws SyntaxException, SemanticException {
        try {

            String[] string = str.split(" ");
            if (string.length != 2) {
                throw new SyntaxException("Need only index number after delete");
            }
            int index = Integer.parseInt(string[1]);
            return taskList.removeIndex(index);
        } catch (NumberFormatException e) {
            throw new SyntaxException("Need only index number after delete");
        } catch (IndexOutOfBoundsException e) {
            throw new SemanticException("Index is out of bounds, please write correct index number");
        }
    }

    private int getIndexOfMark(String str) {
        return Integer.parseInt(str.substring(5));
    }

    public Task setDone(String str, TaskList taskList) throws SyntaxException, SemanticException {
        try {
            int index = getIndexOfMark(str);
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
            int index = getIndexOfUnmark(str);
            taskList.unmarkTask(index);
            return taskList.get(index);
        } catch (NumberFormatException e) {
            throw new SyntaxException("Need only index number to mark as undone");
        } catch (IndexOutOfBoundsException e) {
            throw new SemanticException("Index is out of bounds, please write correct index number");
        }

    }

    private int getIndexOfUnmark(String str) {
        return Integer.parseInt(str.substring(7));
    }

    public ToDo setToDo(String str) throws DuckException {
        String[] todo = str.strip().split("todo ?+");
        if (todo.length > 0 && !todo[0].isBlank()) {
            return new ToDo(todo[0]);
        } else {
            throw new DuckException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

}

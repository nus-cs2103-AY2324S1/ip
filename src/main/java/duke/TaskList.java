package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> storedTasks;

    public TaskList() {
        this.storedTasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> storedTasks) {
        this.storedTasks = storedTasks;
    }

    public Task getTask(int i) {
        return this.storedTasks.get(i);
    }

    public int getLength() {
        return this.storedTasks.size();
    }

    public void addTodo(String s) throws DukeException {
        if (s.length() < 1) {
            throw new DukeInvalidTaskException("Description", "todo");
        }

        Todo todo = new Todo(s);
        System.out.println("New task added: " + todo);
        addTaskSuccess(todo);
    }

    public void addDeadline(String s) throws IndexOutOfBoundsException {
        String[] strArr = s.split(" /by ");

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            String dlName = strArr[0];
            LocalDateTime dlTime = LocalDateTime.parse(strArr[1], dateTimeFormatter);

            Deadline dl = new Deadline(dlName, dlTime);
            System.out.println("New task with deadline added: " + dl);
            addTaskSuccess(dl);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Enter a description or due date with /by");
        } catch (DateTimeParseException e) {
            System.out.println("Error: Enter valid date and time in the format DD/MM/YYYY HH:MM");
        }
    }

    public void addEvent(String s) throws IndexOutOfBoundsException, DateTimeParseException {
        String[] strArr = s.split(" /from ");

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            String eName = strArr[0];

            String[] dueDateArr = strArr[1].split(" /to ");
            LocalDateTime eFrom = LocalDateTime.parse(dueDateArr[0], dateTimeFormatter);
            LocalDateTime eTo = LocalDateTime.parse(dueDateArr[1], dateTimeFormatter);

            Event e = new Event(eName, eFrom, eTo);
            System.out.println("New task added: " + e);
            addTaskSuccess(e);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Enter description, start date with /from or end date with /to");
        } catch (DateTimeParseException e) {
            System.out.println("Error: Enter valid date and time in the format DD/MM/YYYY HH:MM");
        }
    }

    public void addTaskSuccess(Task t) {
        storedTasks.add(t);

        int len = storedTasks.size();
        System.out.println("You now have " + len + " task(s) in your list.");
    }

    public void handleDelete(String info) throws DukeException {
        int index;

        try {
            index = Integer.parseInt(info);
            Task t = this.getTask(index - 1);
            storedTasks.remove(index - 1);
            new Ui().printDelete(t);
            new Ui().printNumberOfTasks(this);
        } catch (NumberFormatException nfe) {
            throw new DukeInvalidTaskNumberException(info);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidTaskNumberException(info);
        }
    }
}

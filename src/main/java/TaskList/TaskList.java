package TaskList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public ToDo addToDo(Boolean isDone, String name) {
        ToDo newToDo = new ToDo(isDone, name);
        this.taskList.add(newToDo);
        return newToDo;
    }

    public Event addEvent(Boolean isDone, String name, String startTime, String endTime) {
        Event newEvent = new Event(isDone, name, startTime, endTime);
        this.taskList.add(newEvent);
        return newEvent;
    }

    public Deadline addDeadline(Boolean isDone, String name, LocalDateTime deadline) {
        Deadline newDeadline = new Deadline(isDone, name, deadline);
        this.taskList.add(newDeadline);
        return newDeadline;
    }

    public String list() {
        StringBuilder listOutput = new StringBuilder();
        listOutput.append("Here are the tasks in your list: ");

        for (int i = 0; i < this.taskList.size(); i++) {
            listOutput.append("\n\t").
                    append((i + 1)).
                    append(".").
                    append(this.taskList.get(i));
        }

        return listOutput.toString();
    }

    public Task mark(int toDoIndex) {
        this.taskList.get(toDoIndex - 1).mark();
        return this.taskList.get(toDoIndex - 1);
    }

    public Task unmark(int toDoIndex) {
        this.taskList.get(toDoIndex - 1).unmark();
        return this.taskList.get(toDoIndex - 1);
    }

    public Task delete(int deleteIndex) {
        Task deletedTask = this.taskList.get(deleteIndex - 1);
        this.taskList.remove(deleteIndex - 1);
        return deletedTask;
    }

    public int size() {
        return this.taskList.size();
    }

    @Override
    public String toString() {
        System.out.println(this.taskList);
        return this.taskList.toString();
    }
}

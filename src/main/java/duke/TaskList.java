package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<String[]> inputList) {
        tasks = new ArrayList<>();
        for (String[] line : inputList) {
            switch (line[0]) {
            case "T":
                ToDo t = new ToDo(line[2]);
                if (line[1].equals("1")) {
                    t.markAsDone();
                }
                tasks.add(t);
                break;
            case "D":
                Deadline d = new Deadline(line[2],
                        LocalDateTime.parse(line[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
                if (line[1].equals("1")) {
                    d.markAsDone();
                }
                tasks.add(d);
                break;
            case "E":
                Event e = new Event(line[2],
                        LocalDateTime.parse(line[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                        LocalDateTime.parse(line[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
                if (line[1].equals("1")) {
                    e.markAsDone();
                }
                tasks.add(e);
                break;
            }
        }
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void delete(int index) throws DukeException {
        if (index < 0 || index > tasks.size()-1) {
            throw new DukeException("Wow, deleting a nonexistent task? Check your tasks again with 'list'.");
        }
        Task t = tasks.get(index);
        tasks.remove(t);
        String returnLine = "Looks like you have more time to sleep now. Deleted this for you:\n"
                + t.toString()
                + "\nYou now have " + tasks.size() + " things to do.\n";
        System.out.println(returnLine);
    }

    public void addDeadline(Deadline task) {
        tasks.add(task);
    }

    public void addEvent(Event task) {
        tasks.add(task);
    }

    public void addToDo(ToDo task) {
        tasks.add(task);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getLength() {
        return tasks.size();
    }

    public String[] toWriteString() {
        String[] writtenTasks = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            writtenTasks[i] = tasks.get(i).toWriteString();
        }
        return writtenTasks;
    }

}

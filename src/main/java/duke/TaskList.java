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
    private ArrayList<Task> taskList;
    public TaskList(ArrayList<String[]> inputList) {
        taskList = new ArrayList<>();
        for (String[] line : inputList) {
            switch (line[0]) {
                case "T":
                    taskList.add(new ToDo(line[2]));
                    break;
                case "D":
                    taskList.add(new Deadline(line[2],
                            LocalDateTime.parse(line[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
                    break;
                case "E":
                    taskList.add(new Event(line[2],
                            LocalDateTime.parse(line[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                            LocalDateTime.parse(line[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))));
                    break;
            }
        }
    }

    public TaskList() {
        taskList = new ArrayList<>();
    }

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

    public void addDeadline(Deadline task) {
        taskList.add(task);
    }

    public void addEvent(Event task) {
        taskList.add(task);
    }

    public void addToDo(ToDo task) {
        taskList.add(task);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int getLength() {
        return taskList.size();
    }

    public String[] toWriteString() {
        String[] writtenTasks = new String[taskList.size()];
        for (int i = 0; i < taskList.size(); i++) {
            writtenTasks[i] = taskList.get(i).toWriteString();
        }
        return writtenTasks;
    }

}

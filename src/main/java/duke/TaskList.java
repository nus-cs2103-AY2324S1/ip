package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<String> data) {
        this.tasks = new ArrayList<>();
        for (String line : data) {
            readLine(line);
        }
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    private Task readLine(String line) {
        String[] split = line.split(" \\| ");
        String type = split[0];
        boolean isDone = split[1].equals("1");
        String description = split[2];
        Task task = null;
        if (type.equals("T")) {
            task = this.addToDo(description);
        } else if (type.equals("E")) {
            task = this.addEvent(description, split[3], split[4]);
        } else if (type.equals("D")) {
            task = this.addDeadline(description, split[3]);
        }

        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }
        return task;
    }

    private void checkIndex(int index) throws DukeException {
        if (index < 1 || index - 1 >= tasks.size()) {
            throw new DukeException("duke.task.Task number is out of range.");
        }
    }

    public void addTask(Task task, Ui ui) {
        this.tasks.add(task);
        ui.addMessage(task, tasks.size());
    }

    public void deleteTask(int index, Ui ui) throws DukeException {
        checkIndex(index);
        Task task = this.tasks.remove(index - 1);
        ui.deleteMessage(task, tasks.size());
    }

    /**
     * Finds the corresponding tasks with descriptions matching the keyword.
     *
     * @param keyword The keyword that is being searched in description.
     * @param ui      The user interface to interact with the user or display messages.
     */
    public void findTask(String keyword, Ui ui) {
        ArrayList<String> str = new ArrayList<>();
        for (Task task : tasks) {
            if (task.hasKeyword(keyword)) {
                str.add(task.toString());
            }
        }
        if (str.size() > 0) {
            ui.findMessage();
            for (String task : str) {
                ui.showMessage(task);
            }
        } else {
            ui.findNoMessage();
        }
    }

    public void markTask(int index, Ui ui) throws DukeException {
        checkIndex(index);
        Task task = this.tasks.get(index - 1);
        task.markAsDone();
        ui.markMessage(task);
    }

    public void unmarkTask(int index, Ui ui) throws DukeException {
        checkIndex(index);
        Task task = this.tasks.get(index - 1);
        task.markAsUndone();
        ui.unmarkMessage(task);
    }

    public ToDo addToDo(String description) {
        ToDo toDo = new ToDo(description);
        this.tasks.add(toDo);
        return toDo;
    }

    public Event addEvent(String description, String from, String to) {
        Event event = new Event(description,
                                LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                                LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        this.tasks.add(event);
        return event;
    }

    public Deadline addDeadline(String description, String by) {
        Deadline deadline = new Deadline(description, LocalDateTime.parse(by,
                                                                          DateTimeFormatter.ofPattern(
                                                                                  "yyyy-MM-dd HH:mm")));
        this.tasks.add(deadline);
        return deadline;
    }

    public void save(BufferedWriter writer) throws IOException {
        for (Task task : tasks) {
            writer.write(task.toSaveLine());
            writer.newLine();
        }
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            str += ((i + 1) + "." + tasks.get(i) + "\n");
        }
        return str;
    }

}

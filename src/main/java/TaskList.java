import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


class TaskList {
    private String filePath = "./data/tasks.txt";
    private List<Task> list;

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public String add(Task task) {
        list.add(task);
        updateFile();
        return "I Gotchu. This task added successfully:\n" + task.toString() +
                "\nYeaboi only " + list.size() + " in the list.";

    }

    public String mark(int idx) throws DukeException {
        if (idx > this.list.size()) {
            throw new DukeException("Out of bounds..");
        }

        Task task = this.list.get(idx - 1);
        task.toggleIsDone(true);
        updateFile();

        return "Noice! I've marked this task as donezo:\n" + task.toString();
    }

    public String delete(int idx) throws DukeException {
        if (idx > this.list.size()) {
            throw new DukeException("Out of bounds..");
        }

        Task task = this.list.remove(idx - 1);
        updateFile();

        return "Task remove UwU:\n" + task.toString();
    }

    public String unmark(int idx) throws DukeException {
        if (idx > this.list.size()) {
            throw new DukeException("Out of bounds..");
        }

        Task task = this.list.get(idx - 1);
        task.toggleIsDone(false);
        updateFile();

        return "OK, I've marked this task as not done yet bruh:\n" + task.toString();
    }

    public String dueOn(LocalDate due) {
        List<Task> dueList = list.stream()
                .filter(task -> (task instanceof Deadline))
                .filter(deadline -> ((Deadline) deadline).by.equals(due))
                .collect(Collectors.toList());

        if (dueList.isEmpty()) {
            return "Nothing to see here...";
        } else {
            StringBuilder s = new StringBuilder("ALERT!! Due on " + due);
            for (Task t : dueList) {
                s.append(t.toString());
            }

            return s.toString();
        }
    }

    public String print() {
        StringBuilder s = new StringBuilder("");
        s.append("Here yo tasks in your list my g:");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            s.append("\n" + (i + 1) + "." + task.toString());
        }
        return s.toString();
    }

    public String toFileString() {
        StringBuilder res = new StringBuilder("");
        for (Task t : list) {
            res.append(t.toFileString());
        }
        return res.toString();
    }

    public void updateFile() {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            String newContent = toFileString();
            fileWriter.write(newContent);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("oops i done goofed");
        }
    }
}
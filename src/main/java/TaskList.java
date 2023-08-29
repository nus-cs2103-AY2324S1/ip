import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


class TaskList {
    private String filePath = "./data/tasks.txt";
    private List<Task> list;
    public String divider = "____________________________________________________________";

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public void add(Task task) {
        list.add(task);
        System.out.println(divider);
        System.out.println("I Gotchu. This task added successfully:");
        System.out.println(task.toString());
        System.out.println("Yeaboi only " + list.size() + " in the list.");
        System.out.println(divider);
        updateFile();
    }

    public void mark(int idx) throws DukeException {
        if (idx > this.list.size()) {
            throw new DukeException("Out of bounds..");
        }

        Task task = this.list.get(idx - 1);
        task.toggleIsDone(true);

        System.out.println(divider);
        System.out.println("Noice! I've marked this task as donezo:");
        System.out.println(task.toString());
        System.out.println(divider);
        updateFile();
    }

    public void delete(int idx) throws DukeException {
        if (idx > this.list.size()) {
            throw new DukeException("Out of bounds..");
        }
        Task task = this.list.remove(idx - 1);
        System.out.println(divider);
        System.out.println("Task remove UwU");
        System.out.println(task.toString());
        System.out.println(divider);
        updateFile();
    }

    public void unmark(int idx) throws DukeException {
        if (idx > this.list.size()) {
            throw new DukeException("Out of bounds..");
        }

        Task task = this.list.get(idx - 1);
        task.toggleIsDone(false);

        System.out.println(divider);
        System.out.println("OK, I've marked this task as not done yet bruh:");
        System.out.println(task.toString());
        System.out.println(divider);
        updateFile();
    }

    public void dueOn(LocalDate due) {
        List<Task> dueList = list.stream()
                .filter(task -> (task instanceof Deadline))
                .filter(deadline -> ((Deadline) deadline).by.equals(due))
                .collect(Collectors.toList());

        if (dueList.isEmpty()) {
            System.out.println("Nothing to see here...");
        } else {
            System.out.println("ALERT!! Due on " + due);
            for (Task t : dueList) {
                System.out.println(t.toString());
            }
        }
    }

    public void print() {
        System.out.println(divider);
        System.out.println("Here yo tasks in your list my g:");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println((i + 1) + "." + task.toString());
        }
        System.out.println(divider);
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
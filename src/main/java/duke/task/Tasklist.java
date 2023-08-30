package duke.task;

import duke.exceptions.DukeInvalidArgumentException;
import duke.exceptions.DukeMissingArgumentException;
import duke.Parser;
import duke.Ui;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Tasklist {
    private ArrayList<Task> todolist;

    public Tasklist() {
        this.todolist = new ArrayList<>();
    }
    public void mark(int i) {
        if (i > todolist.size() || i <= 0) {
            System.out.println("Please mark something in the list");
            return;
        }
        todolist.get(i - 1).mark();
        Task t = todolist.get(i - 1);
        Ui.mark(t);
    }

    public void unmark(int i) {
        if (i > todolist.size() || i <= 0) {
            System.out.println("Please unmark something in the list");
            return;
        }
        todolist.get(i - 1).unmark();
        Task t = todolist.get(i - 1);
        Ui.unmark(t);
    }
    public void addtolist(String s) throws DukeMissingArgumentException, DukeInvalidArgumentException {
        StringBuilder str = new StringBuilder(s);
        String cmd = Parser.parseCommand(s);
        Task task = null;
        if (cmd == null) {
            throw new DukeInvalidArgumentException();
        }
        if (cmd.equals("todo")) {
            if (s.length() <= 5) {
                throw new DukeMissingArgumentException();
            } else {
                Todo t = new Todo(str.substring(5, str.length()).toString());
                todolist.add(t);
                task = t;
            }
        } else if (cmd.equals("deadline")) {
            if (s.length() <= 9) {
                throw new DukeMissingArgumentException();
            } else {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
                    String t = str.substring(9, str.length()).toString();
                    String[] arr = t.split("/by ");
                    LocalDateTime deadline = LocalDateTime.parse(arr[1], formatter);
                    Deadline d = new Deadline(arr[0], deadline);
                    todolist.add(d);
                    task = d;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeMissingArgumentException();
                } catch (DateTimeParseException e) {
                    System.out.println("Please enter the start/end time in the format of <DD/MM/YY HH:MM>!");
                    return;
                }
            }
        } else if (cmd.equals("event")) {
            if (s.length() <= 6) {
                throw new DukeMissingArgumentException();
            } else {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
                    String t = str.substring(6, str.length()).toString();
                    String[] arr = t.split("/from ");
                    String[] times = arr[1].split(" /to ");
                    LocalDateTime startTime = LocalDateTime.parse(times[0], formatter);
                    LocalDateTime endTime = LocalDateTime.parse(times[1], formatter);
                    if (startTime.isAfter(endTime)) {
                        System.out.println("End time must be after the start time!");
                        return;
                    }
                    Event e = new Event(arr[0], startTime, endTime);
                    todolist.add(e);
                    task = e;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeMissingArgumentException();
                } catch (DateTimeParseException e) {
                    System.out.println("Please enter the start/end time in the format of <DD/MM/YY HH:MM>!");
                    return;
                }
            }
        } else {
            throw new DukeInvalidArgumentException();
        }
        Ui.add(task, todolist.size());
    }

    public void delete(int i) {
        if (i > todolist.size() || i <= 0) {
            System.out.println("Please delete something in the list");
            return;
        }
        Task t = todolist.get(i - 1);
        todolist.remove(i - 1);
        Ui.delete(t, todolist.size());
    }

    public void printlist() {
        for (int i = 1; i <= this.todolist.size(); ++i) {
            Task t =  this.todolist.get(i - 1);
            System.out.println(i + ". " + t.toString());
        }
    }

    public void savelist(BufferedWriter bw) throws IOException {
        for (Task task : this.todolist) {
            bw.write(task.stringifyTask());
            bw.newLine();
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int i = 1;
        for (Task task : this.todolist) {
            s.append(i++);
            s.append(". ");
            s.append(task.toString());
            s.append("\n");
        }
        return s.toString();
    }
}

import java.io.BufferedWriter;
import java.io.FileWriter;
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
        todolist.get(i - 1).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(todolist.get(i - 1).toString());
    }

    public void unmark(int i) {
        todolist.get(i - 1).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(todolist.get(i - 1).toString());
    }
    public void addtolist(String s) throws DukeMissingArgumentException, DukeInvalidArgumentException {
        StringBuilder str = new StringBuilder(s);
        String check1 = "";
        String check2 = "";
        String check3 = "";
        if (s.length() >= 4) {
            check1 = str.substring(0, 4);
        }
        if (s.length() >= 8) {
            check2 = str.substring(0, 8);
        }
        if (s.length() >= 5) {
            check3 = str.substring(0, 5);
        }
        if (check1.equals("todo")) {
            if (s.length() <= 5) {
                throw new DukeMissingArgumentException();
            } else {
                System.out.println("Got it. I've added this task:");
                Todo t = new Todo(str.substring(5, str.length()).toString());
                todolist.add(t);
                System.out.println(t.toString());
            }
        } else if (check2.equals("deadline")) {
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
                    System.out.println("Got it. I've added this task:");
                    System.out.println(d.toString());
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeMissingArgumentException();
                } catch (DateTimeParseException e) {
                    System.out.println("Please enter the start/end time in the format of <DD/MM/YY HH:MM>!");
                }
            }
        } else if (check3.equals("event")) {
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
                        System.out.println("\tEnd time must be after the start time!\n");
                        return;
                    }
                    Event e = new Event(arr[0], startTime, endTime);
                    todolist.add(e);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(e.toString());
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeMissingArgumentException();
                } catch (DateTimeParseException e) {
                    System.out.println("Please enter the start/end time in the format of <DD/MM/YY HH:MM>!");
                }
            }
        } else {
            throw new DukeInvalidArgumentException();
        }
        System.out.println("Now you have " + todolist.size() + " tasks in the list.");

    }

    public void delete(int i) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(todolist.get(i - 1).toString());
        todolist.remove(i - 1);
        System.out.println("Now you have " + todolist.size() + " tasks in the list.");
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
}

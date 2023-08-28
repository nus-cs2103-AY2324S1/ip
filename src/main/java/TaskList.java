import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public static List<Task> list;
    private final Storage storage;
    private final UI ui;

    TaskList(Storage storage, UI ui) {
        list = new ArrayList<>();
        this.storage = storage;
        this.ui = ui;
    }

    public void addToDo(String task) throws BrunoException {
        if (task.split(" ").length == 1) {
            System.out.print("\t");
            throw new BrunoEmptyException(task.split(" ")[0]);
        }
        list.add(new ToDo(task.substring(task.indexOf(" ") + 1)));
        String s = "\tWoof. I have added this task:\n\t\t" + list.get(list.size() - 1).getString();
        storage.writeToFile();
        ui.displayMessage(s);
    }

    public void addDeadline(String task) throws BrunoException {
        if (task.split(" ").length == 1 || task.indexOf('/') == 9) {
            System.out.print("\t");
            throw new BrunoEmptyException(task.split(" ")[0]);
        }
        if (!task.substring(task.indexOf("deadline") + 1).contains("/by")) {
            System.out.print("\t");
            throw new BrunoMissingDeadlineException();
        }
        list.add(new Deadline(task.substring(task.indexOf(' ') + 1, task.indexOf('/') - 1),
                task.substring(task.lastIndexOf('/') + 4)));
        storage.writeToFile();
        String s = "\tWoof. I have added this task:\n\t\t" + list.get(list.size() - 1).getString();
        ui.displayMessage(s);
    }

    public void addEvent(String task) throws BrunoException {
        if (task.split(" ").length == 1 || task.indexOf('/') == 6) {
            System.out.print("\t");
            throw new BrunoEmptyException(task.split(" ")[0]);
        }
        if (!task.substring(task.indexOf("event") + 1).contains("/from") || !task.substring(
                task.indexOf("event") + 1).contains("/to")) {
            System.out.print("\t");
            throw new BrunoMissingEventException();
        }
        list.add(new Event(task.substring(task.indexOf(' ') + 1, task.indexOf('/') - 1),
                task.substring(task.indexOf("from") + 5, task.lastIndexOf('/') - 1),
                task.substring(task.indexOf("to") + 3)));
        storage.writeToFile();
        String s = "\tWoof. I have added this task:\n\t\t" + list.get(list.size() - 1).getString();
        ui.displayMessage(s);
    }

    public void markTask(String task) throws BrunoException {
        String markVal = task.split(" ")[1];
        if (!Character.isDigit(markVal.charAt(0))) {
            throw new BrunoIntegerMismatchException("mark");
        }
        if (Integer.parseInt(markVal) > list.size()) {
            throw new BrunoIndexOutOfBoundsException("mark");
        }
        if (Integer.parseInt(markVal) < 0) {
            throw new BrunoNegativeArgException("mark");
        }
        list.get(Integer.parseInt(markVal) - 1).markAsDone();
        storage.writeToFile();
        String s =
                "\tWoof Woof! I have marked the task as done.\n\t" + list.get(Integer.parseInt(markVal) - 1)
                        .getString();
        ui.displayMessage(s);
    }

    public void unmarkTask(String task) throws BrunoException {
        String unmarkVal = task.split(" ")[1];
        if (!Character.isDigit(unmarkVal.charAt(0))) {
            throw new BrunoIntegerMismatchException("unmark");
        }
        if (Integer.parseInt(unmarkVal) > list.size()) {
            throw new BrunoIndexOutOfBoundsException("unmark");
        }
        if (Integer.parseInt(unmarkVal) < 0) {
            throw new BrunoNegativeArgException("unmark");
        }
        list.get(Integer.parseInt(unmarkVal) - 1).unMark();
        storage.writeToFile();
        String s = "\tOK, I have marked the task as not done yet.\n\t" + list.get(
                Integer.parseInt(unmarkVal) - 1).getString();
        ui.displayMessage(s);
    }

    public void deleteTask(String task) throws BrunoException {
        String deleteVal = task.split(" ")[1];
        if (!Character.isDigit(deleteVal.charAt(0))) {
            throw new BrunoIntegerMismatchException("delete");
        }
        if (Integer.parseInt(deleteVal) > list.size()) {
            throw new BrunoIndexOutOfBoundsException("delete");
        }
        if (Integer.parseInt(deleteVal) < 0) {
            throw new BrunoNegativeArgException("delete");
        }
        String s1 = list.get(Integer.parseInt(deleteVal) - 1).getString();
        list.remove(Integer.parseInt(deleteVal) - 1);
        storage.writeToFile();
        String s = "\tI have removed this task from your list:\n\t" + s1;
        ui.displayMessage(s);
    }

    public void displayListSum() {
        String s = "\tNow you have " + list.size() + (list.size() == 1 ? " task" : " tasks") + " in your "
                + "list.";
        ui.displayMessage(s);
    }

    public void displayList() {
        String s = "\tHere are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            s += "\t\t" + (i + 1) + ". " + list.get(i).getString() + "\n";
        }
        ui.displayMessage(s);
    }

    public void showSchedule(String task) throws DateTimeException {
        String s = "";
        int counter = 0;
        LocalDate d = LocalDate.parse(task.split(" ")[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        for (Task t : list) {
            if (t instanceof Deadline) {
                if (d.isEqual(((Deadline) t).by.toLocalDate())) {
                    s = s + "\t" + (++counter) + ". " + t.getString() + "\n";
                }
            } else if (t instanceof Event) {
                if ((d.isAfter(((Event) t).from.toLocalDate()) && d.isBefore(((Event) t).by.toLocalDate()))
                        || d.isEqual(((Event) t).from.toLocalDate()) || d.isEqual(
                        ((Event) t).by.toLocalDate())) {
                    s = s + "\t" + (++counter) + ". " + t.getString() + "\n";
                }
            }
        }
        if (counter == 0) {
            ui.displayMessage("\tYou have no deadlines or events on this date.");
        }
        ui.displayMessage(s);
    }
}

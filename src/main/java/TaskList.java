import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
public class TaskList {

    protected final ArrayList<Task> taskArray;
    private final String[] dateFormats = {
            "dd-MM-yyyy", "yyyy-MM-dd", "dd/MM/yyyy", "yyyy/MM/dd"
    };

    private final String[] timeFormats = { "HHmm", "HH:mm" };
    public TaskList(ArrayList<Task> taskArray) {
        this.taskArray = taskArray;
    }

    public TaskList() {
        this.taskArray = new ArrayList<>();
    }

    private LocalDateTime findDateFormatInput(String input) throws DukeException {
        LocalDateTime dateTime = null;
        for (int i = 0; i < dateFormats.length; i++) {
            for (int j = 0; j < timeFormats.length; j++) {
                try {
                    dateTime = LocalDateTime.parse(input,
                            DateTimeFormatter.ofPattern(dateFormats[i] + " " + timeFormats[j]));
                } catch (DateTimeParseException e) {
                }
            }
        }
        if (dateTime == null){
            throw new DukeException("HOHOHO! The date/time format seems to be wrong!"
                    + "\nPermitted formats for date: dd-mm-yyyy | yyyy-mm-dd | dd/mm/yyyy | yyyy/mm/dd"
                    + "\nPermitted formats for time (Only 24-hours format): HH:MM | HHMM"
                    + "\nE.g. 22/09/2023 22:00 | 2023-08-30 0100");
        }
        return dateTime;
    }

    public void addTask(String task) {
        this.taskArray.add(new Todo(task));
    }
    public void listTasks() {
        if (this.taskArray.isEmpty()) {
            Ui.taskListEmpty();
        } else {
            Ui.taskListNotEmpty(this.taskArray);
        }
    }

    public void markTasks(String[] input, int taskNum) throws DukeException {
        try {
            if (input[0].equals("mark")) {
               /* if (this.taskArray.get(taskNum - 1).isDone) {
                    System.out.println("WEEYA! Task was already marked as done!");
                } else {
                    System.out.println("GOTCHYA! I've marked this task as done!");*/
                Ui.taskAlreadyMarked(this.taskArray.get(taskNum - 1).isDone);
                this.taskArray.get(taskNum - 1).markDone();
               // }
            } else {
               /* if (!this.taskArray.get(taskNum - 1).isDone) {
                    System.out.println("OOPSIE! Task was already marked as not done!");
                } else {
                    System.out.println("HONKHONK! I've marked this task as not done yet!");*/
                Ui.taskAlreadyNotMarked(!this.taskArray.get(taskNum - 1).isDone);
                this.taskArray.get(taskNum - 1).markNotDone();
               // }
            }
            Ui.print(this.taskArray.get(taskNum - 1).toString());
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new DukeException("WARBLE WARBLE! This task number does not exist!");
        }
    }

    public void deadlineOrEventTask(String action, String[] remainLine)
            throws DukeException {
        if (action.equals("deadline")) {
            try {
                String dateTime = remainLine[1].substring(3);
                this.taskArray.add(new Deadline(remainLine[0], findDateFormatInput(dateTime)));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("BEEPBEEP! You forgot to give a \"/by date/time\" for the deadline!");
            }
        } else {
            if (!remainLine[1].contains("from ")) {
                throw new DukeException("BEEPBEEP! You forgot to give a \"/from date/time\" for the event");
            }
            try {
                String[] splitTo = remainLine[1].split("/to ", 2);
                String fromDateTime = splitTo[0].substring(5, splitTo[0].length() - 1);
                this.taskArray.add(new Event(remainLine[0],
                        findDateFormatInput(fromDateTime), findDateFormatInput(splitTo[1])));
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                throw new DukeException("BEEPBEEP! You forget to give a \"/to date/time\" for the event!");
            } catch (DateTimeParseException e) {
                throw new DukeException("HOHOHO! The date/time format seems to be wrong! "
                        + "Please use the format yyyy-mm-dd HH:MM instead! E.g. 2023-08-21 01:00");
            }
        }
    }

    public void deleteTasks(int taskNum) throws DukeException {
        try {
            Task taskDeleted = this.taskArray.get(taskNum - 1);
            this.taskArray.remove(taskNum - 1);
            Ui.deleteTaskOutput(taskDeleted.toString(), this.taskArray.size());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("WARBLE WARBLE, this task number does not exist!");
        }
    }
}

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> userinputs = new ArrayList<>(100);

    private static int numberOfElements = 0;

    public static int getNumberOfElements() {
        return numberOfElements;
    }


    public static void store(Task task) {
        userinputs.add(task);
        numberOfElements++;
        System.out.println(Ui.horizontalLine
                + "Got it. I've added this task:\n"
                + "  "
                + task.toString() + "\n"
                + "Now you have " + numberOfElements + " tasks in the list.\n"
                + Ui.horizontalLine
        );
    }

    public static void store(Task task, boolean printToUser) {
        if (printToUser) {
            store(task);
        } else {
            userinputs.add(task);
            numberOfElements++;
        }
    }

    public static Task getTaskByIndex(int index) throws AlexException {
        if (index > numberOfElements) {
            String message = "OOPS!!! There is/are only " + numberOfElements + " task(s) stored";
            throw new AlexException(message);
        } else if (index < 0) {
            String message = "OOPS!!! Task number cannot be negative, task number starts from 0";
            throw new AlexException(message);
        }
        return userinputs.get(index - 1);
    }

    public static void printAllContent() {
        String tobePrinted = "";
        for (int i = 0; i < numberOfElements; i++) {
            tobePrinted = tobePrinted + (i + 1) + ". " + userinputs.get(i).toString() + "\n";
        }
        System.out.println(Ui.horizontalLine
                + tobePrinted
                + Ui.horizontalLine
        );
    }

    public static void delete(int index) throws AlexException{
        if (index > numberOfElements) {
            String message = "OOPS!!! There is/are only " + numberOfElements + " task(s) stored";
            throw new AlexException(message);
        } else if (index < 0) {
            String message = "OOPS!!! Task number cannot be negative, task number starts from 0";
            throw new AlexException(message);
        }
        Task tobeRemoved = userinputs.remove(index - 1);
        numberOfElements--;
        System.out.println(Ui.horizontalLine
                + "Noted. I've removed this task:\n"
                + "  " + tobeRemoved.toString() + "\n"
                + "Now you have 4 tasks in the list.\n"
                + Ui.horizontalLine
        );
    }

    public static void printTaskForDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        String tobePrinted = "";
        int count = 1;

        for (int i = 0; i < numberOfElements; i++) {
            Task task = userinputs.get(i);
            if (task instanceof ToDos) {
                continue;
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDueDate().equals(parsedDate)) {
                    tobePrinted = tobePrinted + count + ". " + deadline + "\n";
                    count++;
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getFromDate().equals(parsedDate) || event.getToDate().equals(parsedDate)) {
                    tobePrinted = tobePrinted + count + ". " + event + "\n";
                    count++;
                }
            }
        }
        System.out.println(
                Ui.horizontalLine
                        + "There is/are a total of " + (count - 1) + " task(s) on the give date " + date + "\n"
                        + tobePrinted
                        + Ui.horizontalLine
        );
    }

}

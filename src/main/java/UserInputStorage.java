import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class UserInputStorage {
    private static ArrayList<Task> userinputs = new ArrayList<>(100);
    private static int numberOfElements = 0;

    private static String horizontalLine = "_____________________________________________________________\n";

    public static final DateTimeFormatter TIMEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static void store(Task task) {
        userinputs.add(task);
        numberOfElements++;
        System.out.println(horizontalLine
                + "Got it. I've added this task:\n"
                + "  "
                + task.toString() + "\n"
                + "Now you have " + UserInputStorage.getNumOfElement() + " tasks in the list.\n"
                + horizontalLine
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
        System.out.println(horizontalLine
                + tobePrinted
                + horizontalLine
        );
    }

    public static int getNumOfElement() {
        return numberOfElements;
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
        System.out.println(horizontalLine
                         + "Noted. I've removed this task:\n"
                         + "  " + tobeRemoved.toString() + "\n"
                         + "Now you have 4 tasks in the list.\n"
                         + horizontalLine
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
                horizontalLine
                + "There is/are a total of " + (count - 1) + " task(s) on the give date " + date + "\n"
                + tobePrinted
                + horizontalLine
        );
    }

    public static void storeToFile() {
        try {
            FileWriter fw = new FileWriter("../data/Alex.txt");
            for (int i = 0; i < numberOfElements; i++) {
                Task task = userinputs.get(i);
                String taskInfo = "";
                if (task instanceof ToDos) {
                    taskInfo = "T " + task.getDescription() + (task.isDone() ? " 1" : " 0");
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    taskInfo = "D " + deadline.getDescription() + " /by " + deadline.getBy()
                            + (task.isDone() ? " 1" : " 0");
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    taskInfo = "E " + event.getDescription() + " /from " + event.getFromTime() + " /to "
                            + event.getToTime() + (task.isDone() ? " 1" : " 0");
                }

                if (i == numberOfElements - 1) {
                    fw.write(taskInfo);
                } else {
                    fw.write(taskInfo + "\n");
                }
            }
            fw.close();
            System.out.println("User data is successfully stored");
        } catch (IOException e) {
            System.out.println("Something went wrong when saving users data to Alex.txt: " + e.getMessage());
        }
    }
}

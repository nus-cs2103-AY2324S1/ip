import java.util.ArrayList;

public class UserInputStorage {
    private static ArrayList<Task> userinputs = new ArrayList<>(100);
    private static int numberOfElements = 0;

    private static String horizontalLine = "_____________________________________________________________\n";

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
}

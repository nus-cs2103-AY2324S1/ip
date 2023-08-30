import java.util.ArrayList;

public class Ui {
    private static final String TOP_BORDER = "================================================";
    private static final String MIDDLE_BORDER = "|                                              |";
    private static final String BOTTOM_BORDER = "================================================";


    public static void printBye() {
        System.out.println(TOP_BORDER);
        System.out.println(MIDDLE_BORDER);
        System.out.println("|        BouncyBob: Bye! Bounce back soon!     |");
        System.out.println(MIDDLE_BORDER);
        System.out.println(BOTTOM_BORDER);
    }

    public static void printDatabase(ArrayList<Task> database) {
        System.out.println(TOP_BORDER);
        if (database.isEmpty()) {
            System.out.println("Balls! You currently do not have any tasks!");
        } else {
            System.out.println("Here are your tasks!");
            for (Task curTask : database) {
                printTaskStatus(curTask);
            }
        }
        System.out.println(BOTTOM_BORDER);
    }

    public static void printTaskStatus(Task task, BouncyBob.Action action) {
        String marking = " ";
        if (task.isDone()) {
            marking = "X";
        }
        System.out.println(TOP_BORDER);
        switch(action) {
            case MARK:
                System.out.println("You've done it, very bouncy!");
                break;
            case UNMARK:
                System.out.println("Gotta pump for air! It's unmarked!");
                break;
            case DELETE:
                System.out.println("Task deleted: ");
                break;
        }
        System.out.println("[" + task.getSymbol() + "]" + "[" + marking + "]" + " " + task.getDescription());
        System.out.println(BOTTOM_BORDER);
    }

    public static void printTaskStatus(Task task) {
        String marking = " ";
        if (task.isDone()) {
            marking = "X";
        }
        System.out.println("[" + task.getSymbol() + "]" + "[" + marking + "]" + " " + task.getDescription());
    }

    public static void printTaskCount(int pointer, Task newTask) {
        System.out.println(TOP_BORDER);
        System.out.println("Added to database: " + newTask.getDescription());
        String s = pointer == 0 ? "task" : "tasks";
        String str = String.format("Currently, you have %s %s, start bouncing!", pointer + 1, s);
        System.out.println(str);
        System.out.println(BOTTOM_BORDER);
    }

    public static void printIntro() {
        System.out.println(TOP_BORDER);
        System.out.println(MIDDLE_BORDER);
        System.out.println("|                   Hey there!                 |");
        System.out.println("|      I'm BouncyBob, your bubbly chatbot!     |");
        System.out.println("| Wow you look very round today, like a ball!  |");
        System.out.println("|       Use yyyy-mm-dd hhmm for dates          |");
        System.out.println(MIDDLE_BORDER);
        System.out.println(BOTTOM_BORDER);
    }

    public static void printIllegalArgumentException(IllegalArgumentException e) {
        System.out.println(TOP_BORDER);
        System.out.println("Oops! " + e.getMessage());
        System.out.println(BOTTOM_BORDER);
    }

    public static void printDateTimeParseException() {
        System.out.println(TOP_BORDER);
        System.out.println("Date must be in yyyy-mm-dd hhmm format!");
        System.out.println(BOTTOM_BORDER);
    }

    public static void printIndexOutOfBound() {
        System.out.println(TOP_BORDER);
        System.out.println("Make sure your index is within the length of the list!");
        System.out.println(BOTTOM_BORDER);
    }
}

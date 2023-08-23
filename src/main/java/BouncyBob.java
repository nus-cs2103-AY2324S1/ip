import java.util.Scanner;
public class BouncyBob {
    private static final String TOP_BORDER = "╔══════════════════════════════════════════════╗";
    private static final String MIDDLE_BORDER = "║                                              ║";
    private static final String BOTTOM_BORDER = "╚══════════════════════════════════════════════╝";

    public static void printTaskStatus(Task task) {
        String marking = " ";
        if (task.isDone()) {
            marking = "X";
        }
        System.out.println("[" + marking + "]" + " " + task.getName());
    }

    public static void printBye() {
        System.out.println(TOP_BORDER);
        System.out.println(MIDDLE_BORDER);
        System.out.println("║        BouncyBob: Bye! Bounce back soon!     ║");
        System.out.println(MIDDLE_BORDER);
        System.out.println(BOTTOM_BORDER);
    }

    public static void printDatabase(Task[] database, int pointer) {
        System.out.println(TOP_BORDER);
        System.out.println("Here are your tasks! ");
        for (int i = 0; i < pointer; i++) {
            Task curTask = database[i];
            printTaskStatus(curTask);
        }
        System.out.println(BOTTOM_BORDER);
    }

    public static void modifyTask(String[] parts, Task[] database) {
        String action = parts[0];
        int index = Integer.parseInt(parts[1]); // This assumes that second part contains an int
        Task task = database[index];
        System.out.println(TOP_BORDER);
        switch(action) {
            case "mark":
                System.out.println("You've done it, very bouncy!");
                task.setDone();
                printTaskStatus(task);
                break;
            case "unmark":
                System.out.println("Gotta pump for air! It's unmarked! ");
                task.setUnDone();
                printTaskStatus(task);
                break;
        }
        System.out.println(BOTTOM_BORDER);
    }

    public static void printTaskAdded(String userInput, Task[] database, int pointer) {
        System.out.println(TOP_BORDER);
        System.out.println("Added to database: " + userInput);
        database[pointer] = new Task(userInput);
        System.out.println(BOTTOM_BORDER);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] database = new Task[100];
        int pointer = 0;
        System.out.println(TOP_BORDER);
        System.out.println(MIDDLE_BORDER);
        System.out.println("║                   Hey there! 🎈              ║");
        System.out.println("║      I'm BouncyBob, your bubbly chatbot!     ║");
        System.out.println("║ Wow you look very round today, like a ball!  ║");
        System.out.println(MIDDLE_BORDER);
        System.out.println(BOTTOM_BORDER);

        while (true) {
            System.out.println("Enter something: ");
            String userInput = scanner.nextLine();
            String[] parts = userInput.split(" ");
            if (userInput.equals("bye")) {
                printBye();
                break;
            } else if (userInput.equals("list")) {
                printDatabase(database, pointer);
            } else if (parts.length == 2) {
                modifyTask(parts, database);
            } else {
                printTaskAdded(userInput, database, pointer);
                pointer++;
            }
        }
    }
}

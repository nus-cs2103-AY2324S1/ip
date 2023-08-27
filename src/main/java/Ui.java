import java.util.ArrayList;

public class Ui {

    private static final int BOX_WIDTH = 85;

    public static void showToUser(String... messages) {
        System.out.println("+" + "-".repeat(BOX_WIDTH - 2) + "+");

        for (String message : messages) {
            String[] lines = message.split("\n");
            for (String line : lines) {
                StringBuilder paddedLine = new StringBuilder("| ");
                paddedLine.append(line);
                int paddingLength = BOX_WIDTH - 4 - line.length();
                if (paddingLength > 0) {
                    paddedLine.append(" ".repeat(paddingLength));
                }
                paddedLine.append(" |");
                System.out.println(paddedLine);
            }
        }

        System.out.println("+" + "-".repeat(BOX_WIDTH - 2) + "+");
    }

    public static void showGreetMessage() {
        showToUser(
                "Hello! I'm Atlas",
                "What can I do for you?",
                "Type 'help' to view available commands"
        );
    }

    public static void showExitMessage() {
        showToUser(
                "Bye. Hope to see you again soon!"
        );
    }

    public static void showHelpMessage() {
        showToUser(
                "Here are the available commands:",
                "1. bye - Exit the program",
                "2. list - List all tasks",
                "3. mark <taskNumber> - Mark a task as done",
                "4. unmark <taskNumber> - Mark a task as undone",
                "5. delete <taskNumber> - Delete a task",
                "6. todo <description> - Add a new todo task",
                "7. deadline <description> /by <dueDate> - Add a new deadline task",
                "8. event <description> /from <startDate> /to <endDate> - Add a new event task",
                "9. help - Displays the available commands"
        );
    }

    public static void showTodoMessage(Task task, int size) {
        showToUser(
                "Got it. I've added this task:",
                 task.toString(),
                "Now you have " + size + " task" + (size <= 1 ? "" : "s") + " in the list."
        );
    }

    public static void showDeadlineMessage(Task task, int size) {
        showToUser(
                "Got it. I've added this deadline task:",
                task.toString(),
                "Now you have " + size + " task" + (size <= 1 ? "" : "s") + " in the list."
        );
    }

    public static void showEventMessage(Task task, int size) {
        showToUser(
                "Got it. I've added this event task:",
                task.toString(),
                "Now you have " + size + " task" + (size <= 1 ? "" : "s") + " in the list."
        );
    }

    public static void showMarkMessage(Task task) {
        showToUser(
                "Nice! I've marked this task as done:",
                task.toString()
        );
    }

    public static void showUnmarkMessage(Task task) {
        showToUser(
                "OK, I've marked this task as not done yet:",
                task.toString()
        );
    }

    public static void showDeleteMessage(Task task, int size) {
        showToUser(
                "Noted. I've removed this task:",
                task.toString(),
                "Now you have " + size + " task" + (size <= 1 ? "" : "s") + " in the list."
        );
    }

    public static void showListMessage(ArrayList<Task> taskList) {
        ArrayList<String> msg = new ArrayList<>();
        int num = 1;
        for (Task task : taskList) {
            if (task != null) {
                msg.add(num + ": " + task);
                num ++;
            } else {
                break;
            }
        }
        if (taskList.size() == 0) {
            msg.add("You have no task currently.");
        }
        showToUser(
                msg.toArray(new String[0])
        );
    }

    public static void showError(DukeException e) {
        showToUser(
                e.toString()
        );
    }
}

import java.util.ArrayList;

public class Ui {
    private static final String LINEBREAK = "    ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿";

    /**
     * Displays the given message between horizontal lines
     *
     * @param msg String to output
     */
    public void displayMessage(String msg) {
        System.out.println(LINEBREAK);
        System.out.println(indent(msg));
        System.out.println(LINEBREAK);
    }

    public void displayError(String msg) {
        displayMessage("☹ OOPS!!! \n" + msg);
    }

    public void list(ArrayList<Task> allTasks) {
        String list = "";
        for (int i = 1; i <= allTasks.size(); i++) {
            list += i + ". " + allTasks.get(i - 1).toString() + "\n";
        }

        if (list.isBlank()) {
            displayMessage("Congrats! You have no tasks!");
            return;
        }

        displayMessage(list.trim());
    }

    private static String indent(String msg) {
        return msg.replaceAll("(?m)^", "\t");
    }
}

package pippi.ui;

/**
 * The Ui class groups information related to UI
 *
 * @author Nathan
 */
public class Ui {

    /**
     * Wraps responses in upper and lower lines
     */
    public static String wrapText(String content) {
        String line = "";
        String output = line + content + "\n" + line;
        System.out.println(output);
        return output;
    }

    public static String helloMessage() {
        return "Hello trainer, I'm Pippi!\nEnter 'help' to see my moveset!";
    }

    /**
     * Returns a help message response on the available commands
     */
    public static String helpMessage() {
        return ("Here is my moveset\n"
                + "1) list - to access the list\n"
                + "2) todo [task_name] - to create a todo task\n"
                + "3) deadline [task_name] /by [yyyy-mm-dd] - to create a deadline\n"
                + "4) event [task_name] /from [start] /to [end] - to schedule an event\n"
                + "5) mark [index] - to mark task as done\n"
                + "6) unmark [index] - to unmark task as not done\n"
                + "7) delete [index] - to delete tasks from the list\n"
                + "8) find [keyword] - to find tasks from the list\n"
            );
    }
}

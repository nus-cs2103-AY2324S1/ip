package jarvis.gui;

import java.util.ArrayList;

import jarvis.tasks.Task;

/**
 * The user interface class responsible for displaying information to the user on the CLI.
 */
public class Ui {

    /**
     * ASCII Art Generated from <a href="http://patorjk.com/software/taag/">...</a>
     */
    private static final String LOGO = "\t        ██  █████  ██████  ██    ██ ██ ███████ \n"
        + "\t    ██ ██   ██ ██   ██ ██    ██ ██ ██      \n"
        + "\t    ██ ███████ ██████  ██    ██ ██ ███████\n"
        + "\t██  ██ ██   ██ ██   ██  ██  ██  ██      ██ \n"
        + "\t█████  ██   ██ ██   ██   ████   ██ ███████ \n";

    private static final String LOGO_TEST =
            "\t┏┳┏┓┳┓┓┏┳┏┓\n" +
            "\t   ┃┣┫┣┫┃┃┃┗┓\n" +
            "\t┗┛┛┗┛┗┗┛┻┗┛\n";

    public static final String DATE_TIME_FORMAT = "MMM dd yyyy HHmm";

    public String printIntro() {
        return "Hi Master! I'm your personal assistant: JARVIS! \n"
                + "\n" + LOGO_TEST + "\n" + "    How can I serve you today? \n";
    }

    public String printResponse(String response) {
        return response;
    }

    public String printBye() {
        return "Bye Master. It has been my honour to serve you!";
    }
    
    public String printTasks(ArrayList<Task> tasks) {
        String outputString;
        outputString = "    Tasks:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            outputString += (i + 1) + ". " + task.toString();
        }
        return outputString;
    }

    public String printTaskStatus(Task task) {
        return "Understood Master. I've marked this task as "
                + (task.isCompleted() ? "completed" : "uncompleted") + "\n"
                + "\t" + task.toString();
    }

    public String printError(String error) {
        return error + "\n";
    }

    public static String breakLine() {
        return "    ____________________________________________________________\n";
    }
}

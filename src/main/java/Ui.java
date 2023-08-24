import java.util.ArrayList;

public class Ui {
    /**
     * ASCII Art Generated from http://patorjk.com/software/taag/
     */
    private static final String logo = 
    "    ██  █████  ██████  ██    ██ ██ ███████ \n" +
    "    ██ ██   ██ ██   ██ ██    ██ ██ ██      \n" +
    "    ██ ███████ ██████  ██    ██ ██ ███████ \n" +
    "██  ██ ██   ██ ██   ██  ██  ██  ██      ██\n" +
    "█████  ██   ██ ██   ██   ████   ██ ███████ \n";

    public void printIntro() {
        breakLine();
        System.out.println("    Hi Master! I'm your personal assistant: JARVIS! \n" +
                            "\n" +
                            logo +
                            "\n" +
                            "    How can I serve you today? \n");
        breakLine();
    }

    public void printResponse(String response) {
        breakLine();
        System.out.println("    " + response);
        breakLine();
    }

    public void printBye() {
        breakLine();
        System.out.println("    Bye Master. It has been my honour to serve you!");
        breakLine();
    }
    
    public void printTasks(ArrayList<Task> tasks) {
        breakLine();
        System.out.println("    Tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i).getDetails());
        }
        breakLine();
    }

    public static void breakLine() {
        System.out.println("    ____________________________________________________________\n");
    }
}

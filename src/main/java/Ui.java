import java.time.LocalDateTime;

public class Ui {

    private final String LINE = "_______________________________________";
    private final String INDENTATION = "  ";

    public void showWelcome() {
        String logo = "                     _                 _      \n" +
                " _ __ ___  ___ _ __ (_)_ __ ___  _ __ (_)_  __\n" +
                "| '__/ _ \\/ __| '_ \\| | '__/ _ \\| '_ \\| \\ \\/ /\n" +
                "| | |  __/\\__ \\ |_) | | | | (_) | | | | |>  < \n" +
                "|_|  \\___||___/ .__/|_|_|  \\___/|_| |_|_/_/\\_\\\n" +
                "              |_|                             ";
        System.out.println(LINE);
        System.out.println(logo);
        System.out.println(LINE);
        System.out.println("Hello! I'm your personal AI");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public void showOutro() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void showAddMessage(Task task, int size) {
        System.out.println("Got it!. I've added this task:");
        System.out.println(INDENTATION + task);
        System.out.printf("Now you have %d tasks in the list%n", size);
        System.out.println(LINE);
    }

    public void printTasks(TaskList tasks) {
        System.out.println("Here are your list of tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf(INDENTATION + "%d. %s%n", i + 1, task);
        }
        System.out.println(LINE);
    }

    public void printScheduledTasks(TaskList tasks, LocalDateTime datetime) {
        System.out.println("Here are your list of tasks:");
        int index = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.isWithinDateRange(datetime)) {
                System.out.printf(INDENTATION + "%d. %s%n", index++, task);
            }
        }
        System.out.println(LINE);
    }

}

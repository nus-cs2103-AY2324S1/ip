package Ui;
import java.util.Scanner;
import Storage.TaskList;
import TaskManager.Tasks;
import TaskManager.Events;
import TaskManager.ToDos;
import TaskManager.Deadlines;

public class Ui {
    private final String name = "Yours";
    private TaskList taskList;
    private String word;

    public Ui(TaskList taskList) {
        this.taskList = taskList;
        if (taskList.size() < 1) {
                this.word = "task";
            } else {
                this.word = "tasks";
            }
    }

    public void showWelcome() {
        String logo  =   "____    ____  ________   ___    ___   __________    _____\n"
                + "\\   \\  /   / |  ____  |  |  |   |  |  |  _____  |  / ____|\n"
                + " \\   \\/   /  | |    | |  |  |   |  |  |  |___|  |  | (___\n"
                + "  \\      /   | |    | |  |  |   |  |  |   ______|  \\ ___ \\\n"
                + "   |    |    | |    | |  |  |   |  |  |  \\  \\           | |\n"
                + "   |    |    | |____| |  |  |   |  |  |  | \\  \\     ____) |\n"
                + "   |____|    |________|  \\_________/  |__|   \\__\\  |_____/\n";

        String name = "Yours";

        System.out.println("Hello! I'm " + name);
        System.out.println(logo);
        System.out.println("____________________________________________________________________________________");
        System.out.println("What can I do for you?");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        return command;
    }

    public void addedTodo(ToDos newTodo) {
        System.out.println("   ____________________________________________________________________________________");
        System.out.println("   " +  name + ": Help you added a new to-do.\n            " + newTodo.toString());
        System.out.println("          Now you have " + taskList.size() + String.format(" %s in the list.", word));
        System.out.println("   ____________________________________________________________________________________");
    }

    public void addedDeadlines(Deadlines newDeadLine) {
        System.out.println("   ____________________________________________________________________________________");
        System.out.println("   " + name + ": Help you added a new deadline.\n           " + newDeadLine.toString());
        System.out.println("          Now you have " + taskList.size() + String.format(" %s in the list.", word));
        System.out.println("   ____________________________________________________________________________________");
    }

    public void addedEvent(Events newEvent) {
        System.out.println("   ____________________________________________________________________________________");
        System.out.println("   " + name + ": Help you added a new deadline.\n           " + newEvent.toString());
        System.out.println("          Now you have " + taskList.size() + String.format(" %s in the list.", word));
        System.out.println("   ____________________________________________________________________________________");
    }

    public void List() {
        System.out.println("   ____________________________________________________________________________________");
        System.out.println("   " + name + ": Here are the tasks in your list.");
        for (int i = 0 ; i < taskList.size(); i ++) {
            int j = i + 1;
            System.out.println("     " + j + ". " +  taskList.get(i).toString());
        }
        System.out.println("   ____________________________________________________________________________________");
    }

    public void delete(Tasks deleted) {
        System.out.println("   ____________________________________________________________________________________");
        System.out.println("   " + name + ": Helped you deleted this task\n           " + deleted.toString());
        System.out.println("          Now you have " + taskList.size() + String.format(" %s in the list.", word));
        System.out.println("   ____________________________________________________________________________________");
    }

    public void mark(int index) {
        System.out.println("   ____________________________________________________________________________________");
        System.out.println("   " + name + ": Well done! I've marked this task as done :");
        System.out.println("   " + taskList.get(index - 1).toString());
        System.out.println("   ____________________________________________________________________________________");
    }

    public void unmark(int index) {
        System.out.println("   ____________________________________________________________________________________");
        System.out.println("   " + name + ": Alright, I've marked this task as not done yet");
        System.out.println("   " + taskList.get(index - 1).toString());
        System.out.println("   ____________________________________________________________________________________");
    }

    public void bye() {
        System.out.println("   ____________________________________________________________________________________");
        System.out.println("   " + name + ": Bye. Hope to see you again soon!");
        System.out.println("   ____________________________________________________________________________________");
    }

    public void empty() {
        System.out.println("   ____________________________________________________________________________________");
        System.out.println("   " + name + ": Please enter something!");
        System.out.println("   ____________________________________________________________________________________");
    }

    public void misc() {
        System.out.println("   ____________________________________________________________________________________");
        System.out.println("   " + name + ": I don't really understand what you mean.");
        System.out.println("   ____________________________________________________________________________________");
    }

    public void IOOBExceptionMessage() {
        System.out.println("Please enter the correct task's index number.");
    }
}

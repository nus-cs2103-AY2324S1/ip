import java.util.ArrayList;

public class Jarvis {

    private TaskList taskList;
    private Ui ui;

    public Jarvis() {
        taskList = new TaskList();
        ui = new Ui();
    }

    public void start() {
        ui.printIntro();
    }

    public void respond(String userInput) {
        if (userInput.equalsIgnoreCase("bye")) {
            ui.printBye();
            System.exit(0);
        } else if (userInput.equalsIgnoreCase("list")) {
            listTasks();
        } else {
            addTask(userInput);
        }
    }

    private void listTasks() {
        ArrayList<Task> tasks = taskList.getTask();
        if (tasks.isEmpty()) {
            ui.printResponse("Congratulations Master! There is no task at the moment!");
        } else {
            ui.printTasks(tasks);
        }
    }

    private void addTask(String taskTitle) {
        Task task = new Task(taskTitle);
        taskList.addTask(task);
        ui.printResponse("added: " + taskTitle);
    }
}

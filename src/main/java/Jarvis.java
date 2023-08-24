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
        String[] userInputSpilt = userInput.split(" ");

        if (userInput.equalsIgnoreCase("bye")) {
            ui.printBye();
            System.exit(0);
        } else if (userInput.equalsIgnoreCase("list")) {
            listTasks();
        } else if (userInputSpilt[0].equalsIgnoreCase("mark")) {
            int index = Integer.parseInt(userInputSpilt[1]);
            updateTask(index, true);
        } else if (userInputSpilt[0].equalsIgnoreCase("unmark")) {
            int index = Integer.parseInt(userInputSpilt[1]);
            updateTask(index, false);
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

    private void updateTask(int index, boolean isCompleted) {
        ArrayList<Task> tasks = taskList.getTask();
         if (index >= 1 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            
            if (isCompleted) {
                task.markCompleted();
            } else {
                task.unmarkCompleted();
            }
            
            ui.printTaskStatus(task);
        } else {
            ui.printResponse("Invalid index.");
        }
    }
}

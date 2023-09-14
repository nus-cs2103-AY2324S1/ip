package joi.parser;

import joi.loader.TaskLoader;
import joi.ui.Ui;
import joi.utils.InvalidCommandException;
import joi.utils.InvalidInputException;
import joi.utils.Task;
import joi.utils.TaskList;

public class Command {
    private boolean isRunning;
    private final String task;

    public Command(String task) {
        this.task = task;
        this.isRunning = true;
    }

    public void execute(Ui ui, TaskLoader loader, TaskList taskList) throws InvalidCommandException, InvalidInputException {
        if (task.equals("bye") || task.equals("exit")) {
            ui.printToScreen("Bye. Hope to see you again soon!");
            this.isRunning = false;

        } else if (task.equals("list")) {
            taskList.listTasks(ui);

        } else if (task.startsWith("mark")){
            int taskIdx = Integer.parseInt(task.substring(5)) - 1;
            taskList.markAsDoneVerbose(taskIdx, ui);

        } else if (task.startsWith("unmark")) {
            int taskIdx = Integer.parseInt(task.substring(7)) - 1;
            taskList.unmarkAsDoneVerbose(taskIdx, ui);

        } else if (task.startsWith("event") || task.startsWith("todo") || task.startsWith("deadline")){
            Task newTask;
            try {
                newTask = Task.parseInputAsTask(task);
                taskList.addTaskVerbose(newTask);

            } catch (InvalidCommandException e) {
                System.err.println("Cannot create a valid task.");
                throw(e);
            }

        } else if (task.startsWith("delete")) {
            int taskIdx = Integer.parseInt(task.substring(7)) - 1;
            taskList.deleteTaskVerbose(taskIdx);

        } else {
            throw new InvalidInputException(task);
        }
    }

    public boolean getIsRunning() {
        return this.isRunning;
    }
}

package jarvis;

import exceptions.InvalidCommandException;
import exceptions.JarvisException;

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

    public void respond(String userInput){
        try {
            String[] userInputSpilt = userInput.split(" ");
            Action action = new Action(taskList, ui);

            if (userInput.equalsIgnoreCase("bye")) {
                ui.printBye();
                System.exit(0);
            } else if (userInput.equalsIgnoreCase("list")) {
                action.listTasks();
            } else if (userInputSpilt[0].startsWith("mark")) {
                int index = Integer.parseInt(userInputSpilt[1]);
                action.updateTask(index, true);
            } else if (userInputSpilt[0].equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(userInputSpilt[1]);
                action.updateTask(index, false);
            } else if (userInputSpilt[0].equalsIgnoreCase("delete")) {
                int index = Integer.parseInt(userInputSpilt[1]);
                action.deleteTask(index);
            } else if (userInputSpilt[0].equalsIgnoreCase("todo") ||
                        userInputSpilt[0].equalsIgnoreCase("deadline") || 
                        userInputSpilt[0].equalsIgnoreCase("event")) {
                action.addTask(userInput, userInputSpilt[0]);
            } else {
                throw new InvalidCommandException(null);
            }
        } catch (JarvisException e) {
            ui.printError(e.getMessage());
        }
    }
}

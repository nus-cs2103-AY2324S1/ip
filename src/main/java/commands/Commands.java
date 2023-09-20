package commands;
import tasks.Task;
import tasks.Todo;
import ui.Ui;
import main.Duke;
import storage.Storage;
import actions.TaskList;

import java.util.ArrayList;


public class Commands {
    private Duke chad;
    private Ui ui;
    private Storage storage;
    private TaskList tasklist;

    public Commands(Duke chad, ArrayList<Task> taskArrayList){
        this.chad = chad;
        this.ui = new Ui();
        this.tasklist = new TaskList(taskArrayList);
        this.storage = new Storage();
    }


    public String byeCommand() {
        return ui.displayChadBye();
    }

    public String listCommand(){
        return ui.displaychadListTask(chad.taskArrayList);
    }

    public String markCommand(String[] inputArray) {
        try {
            Integer index = Integer.valueOf(inputArray[1]);
            tasklist.chadMarkTask(index);
            storage.writeFile(chad.taskArrayList);
            return ui.displayChadMarkTaskOutput(chad.taskArrayList.get(index - 1).getName(),
                    chad.taskArrayList.get(index - 1).getMark());


            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                return "The task index is invalid! Try again!";
        }
    }

    public String unmarkCommand(String[] inputArray) {
        try {
            Integer index = Integer.valueOf(inputArray[1]);
            tasklist.chadUnmarkTask(index);
            storage.writeFile(chad.taskArrayList);
            return ui.displayChadUnmarkTaskOutput(chad.taskArrayList.get(index - 1).getName(),
                    chad.taskArrayList.get(index - 1).getMark());

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "The task index is invalid! Try again!";
        }
    }

    public String findCommand(String[] inputArray) {
        String name = inputArray[1];
        return tasklist.displayChadFindTask(name);
    }

    public String todoCommand(String[] inputArray) {
        try {
            if (inputArray.length == 1 || inputArray[1].isEmpty()) {
                throw new Duke.DukeException("Hey! You forgot what you needed to do?");
            }
            Todo newTodo = new Todo(inputArray[1]);
            tasklist.chadAddList(newTodo);
            storage.writeFile(chad.taskArrayList);
            return ui.displayChadAddListOutput(newTodo.toString());

        } catch (Duke.DukeException e) {
            return  e.getMessage() + "\n";
        }
    }


}

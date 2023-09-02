package duke.assets.commands;

import duke.data.TaskList;

public class EditTaskStatus extends Command {
    public EditTaskStatus(String input) {
        super(input);
    }

    protected boolean isValid() {
        String[] delimited = this.input.split(" ");
        try {
            if (Integer.parseInt(delimited[1]) >= 1) {
                return true;
            } else {
                System.out.println("ChadGPT: Please input a valid positive index.");
            };
        } catch (NumberFormatException formatExcept) {
            System.out.println("ChadGPT: Please ensure you input a numerical value for " +
                    "the index of the task to be edited");
        }
        return false;
    }

    protected void completeOperation(TaskList taskList) {
        if (this.input.toLowerCase().startsWith("mark")) {
            taskList.markTaskAt(Integer.parseInt(input.split(" ")[1]) - 1);
        } else {
            taskList.unmarkTaskAt(Integer.parseInt(input.split(" ")[1]) - 1);
        }
    }
}

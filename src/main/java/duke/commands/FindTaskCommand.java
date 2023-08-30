package duke.commands;

import java.util.ArrayList;

import duke.tasks.TaskList;
import duke.ui.Ui;

public class FindTaskCommand extends Command {

    public FindTaskCommand(TaskList taskList, String args) {
        super(CommandType.FIND_TASK, taskList, args);
    }

    @Override
    public void execute() {
        ArrayList<Integer> found = this.taskList.findTasks(this.args);
        if (found.size() == 0) {
            Ui.println("No tasks found containing your query.");
        } else {
            int[] indexes = found.stream().mapToInt(i -> i).toArray();
            ArrayList<String> lines = new ArrayList<>();
            lines.add("Nice! I've found some related tasks:");
            for (int i : indexes) {
                lines.add((i + 1) + ". " + this.taskList.getTask(i).toString());
            }
            Ui.printlns(lines.toArray(new String[lines.size()]));
        }
    }
}

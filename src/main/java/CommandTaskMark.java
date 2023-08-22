import java.util.List;

public class CommandTaskMark extends Command {
    boolean isMarking;
    CommandTaskMark(boolean isMarking) {
        super();
        this.isMarking = isMarking;
    }
    @Override
    public void accept(Parser input) throws IllegalArgumentException{
        String inputString = input.getDefaultString();
        List<Task> taskList = Rock.taskList;
        try {
            int taskIdx = Integer.parseInt(inputString);
            if (taskIdx < 1 || taskIdx > taskList.size()) {
                throw new IllegalArgumentException("Invalid index given!");
            } else if (taskList.get(taskIdx - 1).isCompleted()){
                if (this.isMarking) {
                    throw new IllegalArgumentException("Task already marked!");
                } else {
                    throw new IllegalArgumentException("Task already unmarked!");
                }

            } else {
                taskList.get(taskIdx - 1).setCompleted(this.isMarking);
                String response = "";
                if (this.isMarking) {
                    response += "Task marked successfully: \n";
                } else {
                    response += "Task unmarked successfully: \n";
                }
                response += taskList.get(taskIdx - 1).toString();
                Rock.respond(response);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid index given!");
        }
    }
}

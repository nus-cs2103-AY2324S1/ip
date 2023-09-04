package chatbot.commands;


import chatbot.task.TaskList;
import chatbot.storage.Storage;
import chatbot.ui.Ui;

public class FindTask extends Command{

    public FindTask(String input, CommandType commandType) {
        super(input, commandType);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        String[] validStrings = new String[100];
        validStrings[0] = "Here are items that match your search:";

        int index = 1;
        for (int i = 1; i < tasks.getLength() + 1; i++) {
            String taskString = tasks.getTask(i).toString();
            if (taskString.indexOf(input) != -1) {
                validStrings[index] = index + ". " + taskString;
                index++;
            }
        }

        ui.print(validStrings);
    }

    
}

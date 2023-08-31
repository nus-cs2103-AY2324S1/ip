package commands;

import java.util.List;

import client.Rock;
import io.Parser;
import tasks.Task;

public class CommandTaskFind extends Command {
    public CommandTaskFind(Rock client) {
        super(client);
    }
    public void accept(Parser input) {
        String keyword = input.getDefaultString();
        List<Task> taskList = this.client.taskList.toList();
        String response = "";
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getName().contains(keyword)) {
                response += "\n" + taskList.get(i).toString();
            }
        }
        if (response == "") {
            this.client.ui.respond("No tasks found!");
        } else {
            this.client.ui.respond(response);
        } 
    }
}

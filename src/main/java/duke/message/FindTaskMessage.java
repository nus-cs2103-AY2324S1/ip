package duke.message;

import duke.task.Task;
import duke.templates.MessageTemplates;

import java.util.ArrayList;

public class FindTaskMessage extends Message {
    private final ArrayList<Task> tasks;
    public FindTaskMessage(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    private String[] formatList(ArrayList<Task> list) {
        String[] ls = new String[list.size()];
        int index = 1;
        for (Task item: list) {
            ls[index - 1] = String.format("%d. %s", index, item.toString());
            index++;
        }
        return ls;
    }
    @Override
    public void send() {
        System.out.println(MessageTemplates.MESSAGE_FOUND_TASKS);
        System.out.println(
                createMessage(
                        formatList(this.tasks)
                )
        );
    }
}

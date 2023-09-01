package Commands;

import Others.StorageManager;
import Others.Ui;
import Tasks.Event;
import Tasks.TaskManager;

public class EventCommand extends Command {
    private String[] eventArr;
    EventCommand (String input) {
        super(false);
        this.eventArr = input.replaceFirst("event ", "").split(" /from "); // eventArr have description
    }
    @Override
    public void execute(TaskManager taskManager, Ui ui, StorageManager storageManager) {
        String eventDescription = eventArr[0];
        String[] eventArr2 = eventArr[1].split(" /to "); // eventArr2 have the from & to
        String from = eventArr2[0];
        String to = eventArr2[1];
        Event event = new Event(eventDescription, from, to);

        // add to storage
        taskManager.addTask(event);
        storageManager.save(taskManager.getTasks());

        // add to the reply
        ui.displayReply(event.replyString(taskManager.getTasksSize()));
    }
}

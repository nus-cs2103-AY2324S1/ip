package duke.commands;

import duke.records.ChatRecord;
import duke.storage.SaveData;
import duke.task.Todo;

public class TodoCommand extends Command {
    public static final String COMMAND_PHRASE = "todo";

    private static final String COMMAND_DESC = "New Todo Task added to list!";
    private String name;
    public TodoCommand(String name) {
        this.name = name;
    }

    @Override
    public void init(ChatRecord records) {
        this.chatRecord = records;
    }

    /**
     * Executes the created Todo Command.
     * A new Todo Task will be added to the records.
     *
     * @return The string to be displayed as feedback to the user.
     */
    @Override
    public String execute() {
        Todo td = new Todo(this.name);
        this.chatRecord.addTask(td);
        SaveData.saveData(this.chatRecord.toSave());
        return COMMAND_DESC + " " + td.toString();
    }

    @Override
    public String toString() {
        return COMMAND_PHRASE + " " + name;
    }
}

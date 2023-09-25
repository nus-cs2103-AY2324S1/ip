package chat.commands;

import chat.exceptions.ChatException;
import chat.exceptions.IncorrectFormatException;
import chat.tasks.TaskList;
import chat.utils.Storage;

/**
 * Command used to perform sort operations.
 * @author juzzztinsoong
 */
public class SortCommand extends Command {
    
    public enum sortType {
        NAME,DATE,TYPE;
    }

    private sortType type;

    public SortCommand (sortType type) {
        this.type = type;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws ChatException {
        try {
            String tempString = "";
            switch (type) {
            case NAME:
                tempString = taskList.sortName();
                storage.writeToFile(taskList);
                return tempString;
            case DATE:
                tempString = taskList.sortDate();
                storage.writeToFile(taskList);
                return tempString;
            case TYPE:
                tempString = taskList.sortType();
                storage.writeToFile(taskList);
                return tempString;
            default:
                throw new IncorrectFormatException();
            }
        } catch (Exception e) {
            throw new ChatException(e.getMessage());
        }
    }
}

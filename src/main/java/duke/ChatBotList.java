package duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.InvalidPathException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
/**
 * Abstracts a list. Note that items in the list cannot be removed as yet.
 */
public class ChatBotList {
    private ArrayList<Item> list;

    public ChatBotList() {
        this.list = new ArrayList<Item>();
    }
    public ChatBotList(ArrayList<Item> list) {
        this.list = list;
    }
    public int getLength() {
        return this.list.size();
    }

    /**
     * Adds a task to the list
     * @param queries An array of required fields for the task to add
     * @param type The type of task to add
     * @return The toString of the task
     */
    public String addToList(String[] queries, DukeEnvironmentConstants.taskType type) {
        switch (type) {
            case EVENT:
                this.list.add(new Event(queries[0], LocalDateTime.parse(queries[1],DukeEnvironmentConstants.FORMATTER1), LocalDateTime.parse(queries[2],DukeEnvironmentConstants.FORMATTER1)));
                break;
            case DEADLINE:
                this.list.add(new Deadline(queries[0], LocalDateTime.parse(queries[1], DukeEnvironmentConstants.FORMATTER1)));
                break;
            case TODO:
                this.list.add(new Todo(queries[0]));
                break;
            default:
                break;
        }
        return this.list.get(this.list.size()-1).toString();
    }
    /**
     * Marks item at index given by user as completed.  
     *
     * @param index Raw index given by user.
     * @return The toString() of the item marked.
     */
    public String markItem(int index) throws NotInChatBotListException {
        if (index <= 0 || index >= this.list.size() + 1 || this.list.size() == 0) {
            throw new NotInChatBotListException();
        }
        this.list.get(index - 1).markCompleted();
        return this.list.get(index - 1).toString();
    }

    /**
     * Removes "completed" mark of item at the index given by user.
     *
     * @param index Raw index given by user.
     * @return The toString() of the item marked.
     */
    public String unmarkItem(int index) throws NotInChatBotListException {
        if (index <= 0 || index >= this.list.size() + 1) {
            throw new NotInChatBotListException();
        }
        this.list.get(index - 1).unmarkCompleted();
        return this.list.get(index - 1).toString();
    }

    /**
     * Deletes the item at index given by user.
     *
     * @param index Raw index given by use
     * @return The toString() of the Item deleted.
     */
    public String deleteItem(int index) throws NotInChatBotListException {
        if (index <= 0 || index >= this.list.size() + 1) {
            throw new NotInChatBotListException();
        }
        Item itemToRemove = this.list.get(index - 1);
        this.list.remove(index - 1);
        return itemToRemove.toString();
    }


    @Override
    public String toString() {
        String rtnVal = "";
        if (this.list.size() == 0) {
            rtnVal = "The list is empty.";
        } else {
            for (int i = 0; i < this.list.size() - 1; i++) {
                rtnVal += (i + 1 + ".") + this.list.get(i).toString() + "\n";
            }
            rtnVal += (this.list.size() + ".") + this.list.get(this.list.size() - 1);
        }
        return rtnVal;
    }
}

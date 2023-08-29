import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.InvalidPathException;
/**
 * Abstracts a list. Note that items in the list cannot be removed as yet.
 */
public class ChatBotList {
    private ArrayList<Item> list;
    

    /**
     * Encapsulates an item in the list
     */
    private static abstract class Item {
        private String name;
        private boolean isCompleted;

        public Item(String name) {
            this.name = name;
            this.isCompleted = false;
        }

        /**
         * Mark this item as completed.
         */
        public void markCompleted() {
            this.isCompleted = true;
        }

        /**
         * Removes the "completed" mark on this item.
         */
        public void unmarkCompleted() {
            this.isCompleted = false;
        }

        @Override
        public String toString() {
            String rtnVal = "";
            if (this.isCompleted) {
                rtnVal += "[X] ";
            } else {
                rtnVal += "[ ] ";
            }
            return rtnVal + name;
        }
    }

    /**
     * Encapsulates an event with a start and end time/date.
     */
    private static class Event extends Item {
        private String from;
        private String to;

        /**
         * Creates a new event
         * @param name Name of event.
         * @param from Start time/date.
         * @param to End time/date.
         */
        public Event(String name, String from, String to) {
            super(name);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            String rtnVal = "";
            if (super.isCompleted) {
                rtnVal += "[E][X] ";
            } else {
                rtnVal += "[E][ ] ";
            }
            return rtnVal + super.name + " (from: " + this.from + " to: " + this.to + ")";
        }
    }
    /**
     * Encapsulates a deadline with a do-by time/date.
     */
    private static class Deadline extends Item {
        private String by;

        public Deadline(String input, String by) {
            super(input);
            this.by = by;
        }

        @Override
        public String toString() {
            String rtnVal = "";
            if (super.isCompleted) {
                rtnVal += "[D][X] ";
            } else {
                rtnVal += "[D][ ] ";
            }
            return rtnVal + super.name + " (by: " + this.by + ")";
        }
    }
    /**
     * Encapsulates a t0d0 task without time/date dependencies.
     */
    private static class Todo extends Item {
        public Todo(String input) {
            super(input);
        }

        @Override
        public String toString() {
            String rtnVal = "";
            if (super.isCompleted) {
                rtnVal += "[T][X] ";
            } else {
                rtnVal += "[T][ ] ";
            }
            return rtnVal + super.name;
        }
    }


    public ChatBotList() {
        this.list = new ArrayList<Item>();
    }

    public int getLength() {
        return this.list.size();
    }
    /**
     * Adds Item to List. Able to specify type.
     *
     * @param s    Substring of the input string, with the type removed.
     *             e.g. The user inputs "event a /from 2 /to 2". s will be "a /from 2 to 2"
     * @param type The type of item*
     * @return The toString() of the Item added.
     */
    public String addToList(String s, DukeEnvironmentConstants.taskType type) throws IllegalChatBotListArgumentException{
        switch (type) {
            case EVENT:
                String[] firstSplit = s.split(" +/from +");
                if (firstSplit.length != 2) {
                    throw new IllegalChatBotListArgumentException("Incorrect syntax!\n" +
                            "Please use this syntax:\n" +
                            "event <desc> /from <start> /to <end>");
                }
                String[] secondSplit = firstSplit[1].split(" +/to +");
                if (secondSplit.length != 2) {
                    throw new IllegalChatBotListArgumentException("Incorrect syntax!\n" +
                            "Please use this syntax:\n" +
                            "event <desc> /from <start> /to <end>");
                }
                this.list.add( new Event(firstSplit[0], secondSplit[0], secondSplit[1]));
                writeToSave();
                break;
            case DEADLINE:
                String[] splitInput = s.split(" +/by +", 2);
                if (splitInput.length != 2) {
                    throw new IllegalChatBotListArgumentException("Incorrect syntax!\n" +
                            "Please use this syntax:\n" +
                            "deadline <desc> /by <deadline>");
                }
                this.list.add(new Deadline(splitInput[0], splitInput[1]));
                writeToSave();
                break;
            case TODO:
                this.list.add(new Todo(s));
                writeToSave();
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
        writeToSave();
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
        writeToSave();
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
        writeToSave();
        return itemToRemove.toString();
    }

    /**
     * Loads the list from the given file
     */
    public void loadList() {
        this.list = new ArrayList<Item>();
        try {
            Scanner listReader = new Scanner(new File(DukeEnvironmentConstants.FILE_PATH));
            while (listReader.hasNextLine()) {
                String data = listReader.nextLine();
                switch (data.substring(data.indexOf("["), data.indexOf("]") + 1)) {
                    case "[T]":
                        this.list.add(new Todo(data.substring(data.indexOf("[") + 7)));
                        if (data.substring(data.indexOf("[") + 4, data.indexOf("[") + 5).equals("X")) {
                            try {
                                this.markItem(this.getLength());
                            } catch (NotInChatBotListException e) {
                                System.out.println(e.getMessage());
                            } 
                        }
                        break;
                    case "[E]":
                        data.indexOf("(from: ");
                        data.indexOf(" to: ");
                        this.list.add(new Event(data.substring(data.indexOf("[") + 7, data.indexOf(" (from: ")), 
                                data.substring(data.indexOf("(from: ") + 7, data.indexOf(" to: ")),
                                data.substring(data.indexOf("to: ") + 4, data.length() - 1)));
                        if (data.substring(data.indexOf("[") + 4, data.indexOf("[") + 5).equals("X")) {
                            try {
                                this.markItem(this.getLength());
                            } catch (NotInChatBotListException e) {
                                System.out.println(e.getMessage());
                            } 
                        }
                        break;
                    case "[D]":
                        this.list.add(new Deadline(data.substring(data.indexOf("[") + 7, data.indexOf(" (by: ")),
                                data.substring(data.indexOf("(by: ") + 5, data.length()-1)));
                        if (data.substring(data.indexOf("[") + 4, data.indexOf("[") + 5).equals("X")) {
                            try {
                                this.markItem(this.getLength());
                            } catch (NotInChatBotListException e) {
                                System.out.println(e.getMessage());
                            } 
                        }
                        break;
                    default: throw new LoadListException();
                }
            }
            listReader.close();
        } catch (FileNotFoundException e) {
            this.list = new ArrayList<Item>();
        } catch (LoadListException e) {
            this.list = new ArrayList<Item>();
        }
    }

    private void writeToSave() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(DukeEnvironmentConstants.FILE_PATH));
            writer.write(this.toString());
            writer.close();
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
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

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;  
import java.io.FileWriter;  
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;  


public class Duke {
    private static String GREETINGS = "Hello! I'm AChatBot\n" +
            "What can I do for you?";
    private static String FAREWELL = "Bye. Hope to see you again soon!";
    private static String FILE_PATH = "./data/duke.txt";
    private static String DATA_FOLDER = "./data/";
    private static DateTimeFormatter FORMATTER1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm'hrs'");

    /**
     * Exception when a wrong task query string is supplied to ChatBotList.
     */
    private static class IllegalChatBotListArgumentException extends IllegalArgumentException {
        public IllegalChatBotListArgumentException(String message) {
            super(message);
        }
        public IllegalChatBotListArgumentException() {
            super();
        }
    }
    /**
     * Exception when the task the user is attempting to access does not exist.
     */
    private static class NotInChatBotListException extends ArrayIndexOutOfBoundsException {
        public NotInChatBotListException(String message) {
            super(message);
        }
        public NotInChatBotListException() {
            super();
        }
    }

    private static class LoadListException extends IllegalArgumentException {
        public LoadListException(String message) {
            super(message);
        }
        public LoadListException() {
            super();
        }
    }

    /**
     * Abstracts a list. Note that items in the list cannot be removed as yet.
     */
    private static class ChatBotList {
        private ArrayList<Item> list;
        private enum taskType {
            EVENT,
            DEADLINE,
            TODO
        }

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
        public String addToList(String s, taskType type) {
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
        public String markItem(int index) {
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
        public String unmarkItem(int index) {
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
        public String deleteItem(int index) {
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
                Scanner listReader = new Scanner(new File(FILE_PATH));
                while (listReader.hasNextLine()) {
                    String data = listReader.nextLine();
                    switch (data.substring(data.indexOf("["), data.indexOf("]") + 1)) {
                        case "[T]":
                            this.list.add(new Todo(data.substring(data.indexOf("[") + 7)));
                            if (data.substring(data.indexOf("[") + 4, data.indexOf("[") + 5).equals("X")) {
                                this.markItem(this.getLength());
                            }
                            break;
                        case "[E]":
                            data.indexOf("(from: ");
                            data.indexOf(" to: ");
                            this.list.add(new Event(data.substring(data.indexOf("[") + 7, data.indexOf(" (from: ")), 
                                    data.substring(data.indexOf("(from: ") + 7, data.indexOf(" to: ")),
                                    data.substring(data.indexOf("to: ") + 4, data.length() - 1)));
                            if (data.substring(data.indexOf("[") + 4, data.indexOf("[") + 5).equals("X")) {
                                this.markItem(this.getLength());
                            }
                            break;
                        case "[D]":
                            this.list.add(new Deadline(data.substring(data.indexOf("[") + 7, data.indexOf(" (by: ")),
                                    data.substring(data.indexOf("(by: ") + 5, data.length()-1)));
                            if (data.substring(data.indexOf("[") + 4, data.indexOf("[") + 5).equals("X")) {
                                this.markItem(this.getLength());
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
                BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
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

    /**
     * Handles commands and messages to ChatBot
     * @param args CLI args. No implementation.
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ChatBotList list = new ChatBotList();
        list.loadList();
        System.out.println(new File(FILE_PATH));
        System.out.println(GREETINGS);
        while (true) {
            String input = s.nextLine().trim();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(list);
            } else {
                String[] splitInput = input.split(" +", 2);
                if (splitInput[0].equals("mark") || splitInput[0].equals("unmark") || splitInput[0].equals("delete")) {
                    if (splitInput.length == 1) {
                        System.out.println("☹ OOPS!!! You did not specify which task you want to " + splitInput[0] + ".\nPlease use this syntax:\n" +
                                splitInput[0] + " <index>");
                    } else {
                        try {
                            int idx = Integer.parseInt(splitInput[1]);
                            if (splitInput[0].equals("mark")) {
                                String res = list.markItem(idx);
                                System.out.println("Nice! I've marked this task as done:\n" + "  " + res);
                            } else if (splitInput[0].equals("unmark")) {
                                String res = list.unmarkItem(idx);
                                System.out.println("OK, I've marked this task as not done yet:\n" + "  " + res);
                            } else {
                                String res = list.deleteItem(idx);
                                System.out.println("Noted. I've removed this task:\n" + "  " + res);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please input a valid number for your index!");
                        } catch (NotInChatBotListException e) {
                            System.out.println("Make sure your item is in the list!\n" +
                                    "You may check using the \"list\" command.");
                        }
                    }
                } else if (splitInput[0].equals("event")) {
                    if (splitInput.length < 2) {
                        System.out.println("☹ OOPS!!! The description of a event cannot be empty.\nPlease use this syntax:\n" +
                                "event <desc> /from <start> /to <end>");
                    } else {
                        try {
                            String taskStr = list.addToList(splitInput[1], ChatBotList.taskType.EVENT);
                            System.out.println("Got it. I've added this task:\n" + taskStr);
                            System.out.println("Now you have " + list.getLength() + " tasks in the list.");
                        } catch (IllegalChatBotListArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (splitInput[0].equals("deadline")) {
                    if (splitInput.length < 2) {
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.\nPlease use this syntax:\n"+
                                "deadline <desc> /by <deadline>");
                    } else {
                        try {
                            String taskStr = list.addToList(splitInput[1], ChatBotList.taskType.DEADLINE);
                            System.out.println("Got it. I've added this task:\n" + taskStr);
                            System.out.println("Now you have " + list.getLength() + " tasks in the list.");
                        } catch (IllegalChatBotListArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (splitInput[0].equals("todo")) {
                    if (splitInput.length < 2) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        String taskStr = list.addToList(splitInput[1], ChatBotList.taskType.TODO);
                        System.out.println("Got it. I've added this task:\n" + taskStr);
                        System.out.println("Now you have " + list.getLength() + " tasks in the list.");
                    }
                } else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        }
        System.out.println(FAREWELL);
        s.close();
    }
}

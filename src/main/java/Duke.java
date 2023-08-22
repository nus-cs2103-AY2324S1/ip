import java.util.Scanner;
public class Duke {
    private static String GREETINGS = "Hello! I'm AChatBot\n" +
            "What can I do for you?";
    private static String FAREWELL = "Bye. Hope to see you again soon!";

    /**
     * Abstracts a list. Note that items in the list cannot be removed as yet.
     */
    private static class ChatBotList {
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

        private Item[] list;
        private int counter;

        public ChatBotList() {
            this.list = new Item[100];
            this.counter = 0;
        }

        public int getLength() {
            return counter;
        }
        /**
         * Adds Item to List. Able to specify type.
         *
         * @param s    Substring of the input string, with the type removed.
         *             e.g. The user inputs "event a /from 2 /to 2". s will be "a /from 2 to 2"
         * @param type The type of item
         */
        public String addToList(String s, String type) {
            switch (type) {
                case "event":
                    String[] firstSplit = s.split(" +/from +");
                    if (firstSplit.length != 2) {
                        throw new IllegalArgumentException("Incorrect syntax!\n" +
                                "Please use this syntax:\n" +
                                "event <desc> /from <start> /to <end>");
                    }
                    String[] secondSplit = firstSplit[1].split(" +/to +");
                    if (secondSplit.length != 2) {
                        throw new IllegalArgumentException("Incorrect syntax!\n" +
                                "Please use this syntax:\n" +
                                "event <desc> /from <start> /to <end>");
                    }
                    this.list[this.counter] = new Event(firstSplit[0], secondSplit[0], secondSplit[1]);
                    this.counter++;
                    break;
                case "deadline":
                    String[] splitInput = s.split(" +/by +", 2);
                    if (splitInput.length != 2) {
                        throw new IllegalArgumentException("Incorrect syntax!\n" +
                                "Please use this syntax:\n" +
                                "deadline <desc> /by <deadline>");
                    }
                    this.list[this.counter] = new Deadline(splitInput[0], splitInput[1]);
                    this.counter++;
                    break;
                case "todo":
                    this.list[this.counter] = new Todo(s);
                    this.counter++;
                    break;
                default:
                    break;
            }
            return this.list[this.counter-1].toString();
        }

        /**
         * Marks item at index given by user as completed.
         *
         * @param index Raw index given by user.
         */
        public void markItem(int index) {
            if (index <= -1 || index >= counter + 1 || counter == 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            this.list[index - 1].markCompleted();
        }

        /**
         * Removes "completed" mark of item at index given by user.
         *
         * @param index Raw index given by user.
         */
        public void unmarkItem(int index) {
            if (index < -1 || index >= counter + 1) {
                throw new ArrayIndexOutOfBoundsException();
            }
            this.list[index - 1].unmarkCompleted();
        }

        /**
         * Views item at index given by user.
         *
         * @param index Raw index given by user.
         */
        public String viewItem(int index) {
            if (index < -1 || index >= counter + 1) {
                throw new ArrayIndexOutOfBoundsException();
            }
            return this.list[index - 1].toString();
        }

        @Override
        public String toString() {
            String rtnVal = "";
            if (counter == 0) {
                rtnVal = "List is empty.";
            } else {
                for (int i = 0; i < this.counter - 1; i++) {
                    rtnVal += (i + 1 + ".") + this.list[i].toString() + "\n";
                }
                rtnVal += (counter + ".") + this.list[counter - 1];
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
        System.out.println(GREETINGS);
        while (true) {
            String input = s.nextLine().trim();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(list);
            } else {
                String[] splitInput = input.split(" +", 2);
                if (splitInput.length == 2 && splitInput[0].equals("mark") || splitInput[0].equals("unmark")) {
                    try {
                        int idx = Integer.parseInt(splitInput[1]);
                        if (splitInput[0].equals("mark")) {
                            list.markItem(idx);
                            System.out.println("Nice! I've marked this task as done:");
                        } else {
                            list.unmarkItem(idx);
                            System.out.println("OK, I've marked this task as not done yet:");
                        }
                        System.out.println("  " + list.viewItem(idx));
                    } catch (NumberFormatException e) {
                        System.out.println("Please input a valid number for your index!");
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Make sure your item is in the list!");
                    }
                } else if (splitInput[0].equals("event")) {
                    if (splitInput.length < 2) {
                        System.out.println("Incorrect syntax!\n" +
                                "Please use this syntax:\n" +
                                "event <desc> /from <start> /to <end>");
                    } else {
                        try {
                            String taskStr = list.addToList(splitInput[1], "event");
                            System.out.println("Got it. I've added this task:\n" + taskStr);
                            System.out.println("Now you have " + list.getLength() + " tasks in the list.");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (splitInput[0].equals("deadline")) {
                    if (splitInput.length < 2) {
                        System.out.println("Incorrect syntax!\n" +
                                "Please use this syntax:\n" +
                                "deadline <desc> /by <deadline>");
                    } else {
                        try {
                            String taskStr = list.addToList(splitInput[1], "deadline");
                            System.out.println("Got it. I've added this task:\n" + taskStr);
                            System.out.println("Now you have " + list.getLength() + " tasks in the list.");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (splitInput[0].equals("todo")) {
                    if (splitInput.length < 2) {
                        System.out.println("Incorrect syntax!\n" +
                                "Please use this syntax:\n" +
                                "todo <desc>");
                    } else {
                        String taskStr = list.addToList(splitInput[1], "todo");
                        System.out.println("Got it. I've added this task:\n" + taskStr);
                        System.out.println("Now you have " + list.getLength() + " tasks in the list.");
                    }
                } else {
                    list.addToList(input, "todo");
                    System.out.println("Not a command, but I'll add that task for you anyway.\nadded: " + input);
                }
            }
        }
        System.out.println(FAREWELL);
        s.close();
    }
}

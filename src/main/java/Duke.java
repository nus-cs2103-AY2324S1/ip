import java.util.Scanner;
public class Duke {
    private static String GREETINGS = "Hello! I'm AChatBot\n" +
            "What can I do for you?";
    private static String FAREWELL = "Bye. Hope to see you again soon!";

    /**
     * Abstracts a list. Note that items in the list cannot be removed as yet.
     */
    private static class ChatBotList {
        private Item[] list;
        private int counter;
        public ChatBotList() {
            this.list = new Item[100];
            this.counter = 0;
        }

        /**
         * Adds String to list
         * @param s The String to add to the list.
         */
        public void addToList(String s) {
            this.list[this.counter] = new Item(s);
            this.counter++;
        }

        /**
         * Marks item at index given by user as completed.
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
         * @param index Raw index given by user.
         */
        public String viewItem(int index) {
            if (index < -1 || index >= counter + 1) {
                throw new ArrayIndexOutOfBoundsException();
            }
            return this.list[index - 1].toString();
        }
        @Override
        public String toString(){
            String rtnVal = "";
            if (counter == 0) {
                rtnVal = "List is empty.";
            }
            else {
                for (int i = 0; i < this.counter - 1; i++) {
                    rtnVal += (i + 1 + ".") + this.list[i].toString() + "\n";
                }
                rtnVal += (counter + ".") + this.list[counter - 1];
            }
            return rtnVal;
        }
        /**
         * Encapsulates an item in the list
         */
        private static class Item {
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
                return  rtnVal + name;
            }
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
            String input = s.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(list);
            } else {
                String[] splitInput = input.split(" ", -1);
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
                } else {
                    list.addToList(input);
                    System.out.println("added: " + input);
                }
            }
        }
        System.out.println(FAREWELL);
        s.close();
    }
}

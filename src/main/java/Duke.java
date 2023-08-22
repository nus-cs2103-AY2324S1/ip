import java.util.Scanner;
public class Duke {
    private static String GREETINGS = "Hello! I'm AChatBot\n" +
            "What can I do for you?";
    private static String FAREWELL = "Bye. Hope to see you again soon!";

    /**
     * Abstracts a list. Note that items in the list cannot be removed as yet.
     */
    private static class ChatBotList {
        private String[] list;
        private int counter;
        public ChatBotList() {
            this.list = new String[100];
            this.counter = 0;
        }

        /**
         * Adds String to list
         * @param s The String to add to the list.
         */
        public void addToList(String s) {
            this.list[this.counter] = s;
            this.counter++;
        }
        @Override
        public String toString(){
            String rtnVal = "";
            if (counter == 0) {
                rtnVal = "List is empty.";
            }
            else {
                for (int i = 0; i < this.counter - 1; i++) {
                    rtnVal += this.list[i] + "\n";
                }
                rtnVal += this.list[counter - 1];
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
            String input = s.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(list);
            } else {
                list.addToList(input);
                System.out.println("added: " + input);
            }
        }
        System.out.println(FAREWELL);
        s.close();
    }
}

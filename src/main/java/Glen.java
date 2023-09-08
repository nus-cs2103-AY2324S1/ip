import java.util.Scanner;

/**
 * Glen is a chatbot that helps you keep track of your tasks.
 */
public class Glen {
    static final String HORLINE = "_____________________________________________________\n";
    private static Storage storage = new Storage("./data/tasks.txt");
    static TaskList tasks = new TaskList();
    
    /**
     * Main method that runs the chatbot.
     * 
     * @param args
     */
    public static void main(String[] args) {
        tasks = storage.read();

        System.out.println(intro());
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        String low = input.toLowerCase();
        while (!low.equals("bye")) {
            if (low.equals("list")) {
                System.out.println(tasks.lst());
            } else {
                int index = Math.max(0, low.indexOf(" "));
                String firstWord = low.substring(0, index);
                String end = ""; 
                try {
                    end = input.substring(++index);
                } catch (StringIndexOutOfBoundsException e) {}
                finally {
                    if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                        int taskIndex = -1;
                        try {
                            taskIndex = Integer.valueOf(end) - 1;
                        } catch (Exception NumberFormatException) {}
                        if (taskIndex >= 0 && taskIndex < tasks.size()) {
                            tasks.toggle(storage, firstWord, taskIndex);
                        } else {
                            System.out.println(HORLINE + "\u2639 OOPS!!! Please select a valid item to mark/unmark.\n" + HORLINE);
                        }   
                    } else if (firstWord.equals("deadline")) {
                        try {
                            int separatorIndex = end.indexOf("/by");
        
                            String string1 = end.substring(0, separatorIndex).trim();
                            String string2 = end.substring(separatorIndex + 3).trim();

                            System.out.println(tasks.addDeadline(storage, string1, string2));
                        } catch (Exception StringIndexOutOfBoundsException) {
                            System.out.println(HORLINE + "\u2639 OOPS!!! Your request needs to be formatted as:\ndeadline <task name> /by <time>\n" + HORLINE);
                        }
                    } else if (firstWord.equals("event")) {
                        try {
                            int fromIndex = end.indexOf("/from");
                            int toIndex = end.indexOf("/to");
            
                            String string1 = end.substring(0, fromIndex).trim();
                            String string2 = end.substring(fromIndex + 6, toIndex).trim();
                            String string3 = end.substring(toIndex + 4).trim();

                            System.out.println(tasks.addEvent(storage, string1, string2, string3));
                        } catch (Exception StringIndexOutOfBoundsException) {
                            System.out.println(HORLINE + "\u2639 OOPS!!! Your request needs to be formatted as:\nevent <event name> /from <start time> /to <end time>\n" + HORLINE);
                        }
                    } else if (firstWord.equals("todo")) {
                        String trimmed = end.trim();
                        if (trimmed.equals("")) {
                            System.out.println(HORLINE + "\u2639 OOPS!!! The description of a todo cannot be empty.\n" + HORLINE);
                        } else {
                            System.out.println(tasks.addTodo(storage, trimmed));
                        }
                    } else if (firstWord.equals("delete")) {
                        int taskIndex = -1;
                        try {
                            taskIndex = Integer.valueOf(end) - 1;
                        } catch (Exception NumberFormatException) {}
                        if (taskIndex >= 0 && taskIndex < tasks.size()) {
                            System.out.println(tasks.del(storage, taskIndex));
                        } else {
                            System.out.println(HORLINE + "\u2639 OOPS!!! Please select a valid item to delete.\n" + HORLINE);
                        }   
                    } else {
                        System.out.println(HORLINE + "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\n" + HORLINE);
                    }
                }
            }
            input = scan.nextLine();
            low = input.toLowerCase();
        }
        System.out.println(exit());
        scan.close();
    }
    
    /**
     * Returns the introduction text.
     * 
     * @return String of introduction text.
     */
    static String intro() {
        String logo = "  _____ _            \n" +
                      " / ____| |           \n" +
                      "| |  __| | ___ _ __  \n" +
                      "| | |_ | |/ _ \\  _ \\ \n" +
                      "| |__| | |  __/ | | |\n" +
                      " \\_____|_|\\___|_| |_|\n\n";
        String introText = "Hello, I'm Glen!\n" + 
                           "What can I do for you?\n";
        return HORLINE + logo + introText + HORLINE;
    }

    /**
     * Returns the exit text.
     * 
     * @return String of exit text.
     */
    static String exit() {
        return HORLINE + "Bye. Hope to see you again soon!\n" + HORLINE;
    }
}

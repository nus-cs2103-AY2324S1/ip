//public printer class that abstracts and does the printing of the outputs

public class Printer {

    static String line = "---------------------";

    static String greeting = " Hello! I'm Somebodyhaha\n" +
            " What can I do for you?";
    static String exiting = " Bye. Hope to See you again soon!";

    /**
     * Prints the greeting onto the console
     */
    public static void printGreeting(){
        print(greeting);
    }

    /**
     * Prints the exit message onto the console
     */
    public static void printExit() {
        print(exiting);
    }

    /**
     * Prints the input provided
     * @param input String to be printed
     */
    public static void print(String input) {
        System.out.println(line);
        System.out.println(input);
        System.out.println(line);
    }

    /**
     * Prints the items found in the task list
     * @param lst The list to be printed
     */
    public static void printList(Task[] lst){
        int count = 1;
        System.out.println(line);
        if(lst != null) {
            for (Task item: lst) {
                if(item == null) {break;}
                String str = count + ". [" + item.getStatus() + "] " + item.getDescription();
                System.out.println(str);
                count++;
            }
        }
        System.out.println(line);
    }
}

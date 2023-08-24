import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Chatbot b1 = new Chatbot("Zhen Dong");
        Scanner newScan = new Scanner(System.in);
        b1.greet();
        while (newScan.hasNextLine()) {
           String action = newScan.nextLine();
           if (action.equals("bye")) {
               // exit
               String echo = String.format("____________________________________________________________\n" +
                       "Bye. Hope to see you again soon!\n" +
                       "____________________________________________________________", action);
               System.out.println(echo);
               System.exit(0);
           } else {
               String echo = String.format("____________________________________________________________\n" +
                       "%s\n" +
                       "____________________________________________________________", action);
               System.out.println(echo);
           }

        }

    }
}

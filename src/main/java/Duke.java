import java.io.*;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) throws IOException{
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        String start = "Hello! I'm ChatBot" + "\n" + "What can I do for you?" + "\n";
        System.out.println(start);
        ArrayList<Task> tdlist = new ArrayList<Task>();
        while (true) {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            String str = bf.readLine();
            String parsed_str[] = str.split(" ");
            if (str.equals("list")) {
                //print out the list
                int size = tdlist.size();
                for (int i = 0; i < size; i++) {
                    Task curr = tdlist.get(i);
                    System.out.println((i + 1) + ".[" + curr.getStatusIcon() + "] " + curr.description);
                }
            } else if (parsed_str[0].equals("mark")) {
                //0-index
                System.out.println("Nice! I've marked this task as done:");
                int index = Integer.parseInt(parsed_str[1]) - 1;
                Task curr = tdlist.get(index);
                curr.markAsDone();
                System.out.println((index + 1) + ".[" + curr.getStatusIcon() + "] " + curr.description);
            } else if (parsed_str[0].equals("unmark")) {
                //0-index
                System.out.println("Ok, I've marked this task as not done yet:");
                int index = Integer.parseInt(parsed_str[1]) - 1;
                Task curr = tdlist.get(index);
                curr.markAsNotDone();
                System.out.println((index + 1) + ".[" + curr.getStatusIcon() + "] " + curr.description);
            } else if (str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                tdlist.add(new Task(str));
                System.out.println("added: " + str);
            }
        }

    }
}

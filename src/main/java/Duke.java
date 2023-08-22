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
                    System.out.println((i + 1) + ". " + curr);
                }
            } else if (parsed_str[0].equals("mark")) {
                //0-index
                System.out.println("Nice! I've marked this task as done:");
                int index = Integer.parseInt(parsed_str[1]) - 1;
                Task curr = tdlist.get(index);
                curr.markAsDone();
                System.out.println((index + 1) + ". " + curr);
            } else if (parsed_str[0].equals("unmark")) {
                //0-index
                System.out.println("Ok, I've marked this task as not done yet:");
                int index = Integer.parseInt(parsed_str[1]) - 1;
                Task curr = tdlist.get(index);
                curr.markAsNotDone();
                System.out.println((index + 1) + ". " + curr);
            } else if (str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (parsed_str[0].equals("todo")) {
                //for todo
                int size = parsed_str.length;
                String description = "";
                for (int i = 1; i < size; i++) {
                    description += parsed_str[i] + " ";
                }
                System.out.println(description);
                System.out.println("Got it. I've added this task:");
                Todo curr = new Todo(description);
                tdlist.add(curr);
                System.out.println(curr);
                System.out.println("Now you have " + tdlist.size() + " tasks in the list.");

            } else if (parsed_str[0].equals("deadline")) {
                //reach the date
                boolean reached = false;
                String by_date = "";
                String description = "";
                for (int i = 1; i < parsed_str.length; i++) {
                    if (parsed_str[i].equals("/by")) {
                        reached = true;
                        continue;
                    }
                    if (reached == true) {
                        by_date += parsed_str[i] + " ";
                    } else {
                        description += parsed_str[i] + " ";
                    }
                }
                System.out.println("Got it. I've added this task:");
                Deadline curr = new Deadline(description, by_date);
                tdlist.add(curr);
                System.out.println(curr);
                System.out.println("Now you have " + tdlist.size() + " tasks in the list.");
            } else if (parsed_str[0].equals("event")) {
                boolean reachFrom = false;
                boolean reachTo = false;
                String fromDate = "";
                String toDate = "";
                String description = "";
                for (int i = 1; i < parsed_str.length; i++) {
                    if (parsed_str[i].equals("/from")) {
                        reachFrom = true;
                        continue;
                    } else if (parsed_str[i].equals("/to")) {
                       reachTo = true;
                       continue;
                    }
                    if (reachFrom == true && reachTo == false) {
                        fromDate += parsed_str[i] + " ";
                    } else if (reachFrom == false && reachTo == false) {
                       //part for description
                       description += parsed_str[i] + " ";
                    } else {
                       toDate += parsed_str[i] + " ";
                    }
                }
                System.out.println("Got it. I've added this task:");
                //todo here
                Event curr = new Event(description, fromDate, toDate);
                tdlist.add(curr);
                System.out.println(curr);
                System.out.println("Now you have " + tdlist.size() + " tasks in the list.");

            }
        }

    }
}
// fix the string formating for the bracket at the back

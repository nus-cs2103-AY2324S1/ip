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
        ArrayList<String> tdlist = new ArrayList<String>();
        while (true) {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            String str = bf.readLine();
            if (str.equals("list")) {
                int size = tdlist.size();
                for (int i = 0; i < size; i++) {
                    System.out.println((i + 1) + ". " + tdlist.get(i));
                }
            } else if (str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                tdlist.add(str);
                System.out.println("added: " + str);
            }
        }

    }
}

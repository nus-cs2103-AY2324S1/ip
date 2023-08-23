import java.util.ArrayList;
import java.util.Scanner;
public class CheeChat {


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm CheeChat");
        System.out.println("What can I do for you?");
        Scanner input = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();
        String cmd = input.nextLine();
        int counter = 0;
        while(!cmd.equals("bye")){


            if (cmd.equals("list")) {
                for (int x = 0; x < counter; x++) {
                    int index = x + 1;
                    System.out.println(index + ". " + tasks.get(x));
                    }
            } else {
                System.out.println("added: " + cmd);
                tasks.add(cmd);
                counter++;
            }
            cmd = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}


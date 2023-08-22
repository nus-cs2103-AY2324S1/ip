import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Task[] list = new Task[100];
        int counter = 0;
        String logo = " _           _        \n"
                    + "| |    _   _| | _____ \n"
                    + "| |   | | | | |/ / _ \\\n"
                    + "| |___| |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hi, I'm \n" + logo);

        while (true) {
            String input = reader.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < counter; i++) {
                    System.out.println((i+1)+". " + list[i]);
                }
            } else if (input.startsWith("mark")) {
                int number = Integer.parseInt(input.substring(5));
                list[number - 1].markDone();
            } else if (input.startsWith("unmark")) {
                int number = Integer.parseInt(input.substring(7));
                list[number - 1].markUndone();
            }
            else {
                System.out.println("added: " + input);
                list[counter] = new Task(input);
                counter += 1;
            }
        }
        //System.out.println("What's up?\n");
        System.out.println("Ok byeee\n");
    }
}

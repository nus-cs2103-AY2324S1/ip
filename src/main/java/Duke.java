
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String[] arr = new String[100];
        int counter = 0;
        Scanner input = new Scanner(System.in);
        String line = "     ____________________________________________________________";
        System.out.println(line + "\n      Hello, I'm JavAI.\n      What can I do for you?\n" + line);
        String output = input.nextLine();
        while (!output.equals("bye")) {
            if (output.equals("list")) {
                System.out.println(line);
                for ( int i = 0; i < counter ; i++ ) {
                    System.out.println("       " + (i+1) + ". " + arr[i]);
                }
                System.out.println(line);
            } else {
                arr[counter] = output;
                System.out.println(line + "\n" + "     " + "  added: " + output + "\n" + line);
                counter++;
            }
            output = input.nextLine();
        }
        System.out.println(line + "\n" + "      Bye. Hope to see you again soon!\n" + line);

    }
}

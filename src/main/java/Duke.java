
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line = "____________________________________________________________";
        System.out.println(line + "\nHello, I'm JavAI.\nWhat can I do for you?\n" + line);
        String output = input.nextLine();
        while (!output.equals("bye")) {
            System.out.println("    " + line + "\n" + "     " + output + "    " + "\n" + "    " + line);
            output = input.nextLine();
        }
        System.out.println("    " + line + "\n     Bye. Hope to see you again soon!\n" + "    " + line);




    }
}

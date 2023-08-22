import java.util.ArrayList;
import java.util.Scanner;
public class Simon {
    public static void main(String[] args) {
        String inData = "";
        Scanner scan = new Scanner( System.in );
        ArrayList<String> tasks = new ArrayList<String>();
        String greetings = "____________________________________________________________\n" +
                "Hello! I'm Simon\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n";

        String bye = "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";

        // Start Program
        System.out.println(greetings);
        while (!inData.equals("bye")) {
            inData = scan.nextLine();
            if (inData.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                System.out.println("\n____________________________________________________________");
            } else {
                tasks.add(inData);
                System.out.println("added: " + inData + "\n____________________________________________________________");
            }
        }
        System.out.println(bye);
    }
}

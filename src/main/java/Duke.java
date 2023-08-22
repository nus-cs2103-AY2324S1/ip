import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm IPSVIJAYKUMARAAKOODAIRRUKALAM");
        System.out.println("What can I do for you?");
        String userinput;
        ArrayList<String> list = new ArrayList<>();
        Scanner scan = new Scanner( System.in );

        userinput = scan.nextLine();

        while (!userinput.equalsIgnoreCase("bye")) {
            if (userinput.equalsIgnoreCase("list")) {

                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + ". " + list.get(i));
                }
                userinput = scan.nextLine();

            } else {
                System.out.println("added: " + userinput);
                list.add(userinput);
                userinput = scan.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}

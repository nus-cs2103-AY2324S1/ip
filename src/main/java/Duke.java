import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm IPSVIJAYKUMARAAKOODAIRRUKALAM");
        System.out.println("What can I do for you?");
        String userinput;
        Scanner scan = new Scanner( System.in );

        userinput = scan.nextLine();

        while (!userinput.equalsIgnoreCase("bye")) {
            System.out.println(userinput);
            userinput = scan.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}

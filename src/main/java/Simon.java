import java.util.Scanner;
public class Simon {
    public static void main(String[] args) {
        String inData = "";
        Scanner scan = new Scanner( System.in );
        String greetings = "____________________________________________________________\n" +
                "Hello! I'm Simon\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n";

        String bye = "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";

        System.out.println(greetings);
        while (!inData.equals("bye")) {
            inData = scan.nextLine();
            System.out.println(inData + "\n____________________________________________________________");
        }
        System.out.println(bye);
    }
}

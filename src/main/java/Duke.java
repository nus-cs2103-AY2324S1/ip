import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greet = "____________________________________________________________\n" +
                " Hello! I'm Ruiz\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(greet);
        Scanner inputObj = new Scanner(System.in);
        while(true){
            String input = inputObj.nextLine();
            if (input.equals("bye")) {
                System.out.println(
                        "____________________________________________________________\n" +
                        "Bye! Comeback soon!\n" +
                        "____________________________________________________________");
                break;
            }
            System.out.println("____________________________________________________________\n" +
                    input +
                    "\n" +
                    "____________________________________________________________\n");
        }
    }
}

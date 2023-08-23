import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("─────────────────────");
        System.out.println("Hello I'm Robot!");
        System.out.println("What can I do for you?");
        System.out.println("─────────────────────");

        Scanner sc = new Scanner(System.in);
        while(true){
            String userInput = sc.nextLine();
            System.out.println("─────────────────────");
            if(userInput.equals("bye")) break;
            System.out.println(userInput);
            System.out.println("─────────────────────");
        }


        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("─────────────────────");
    }
}

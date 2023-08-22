import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Sara");
        System.out.println("What can I do for you?");
        Scanner scan = new Scanner(System.in);

        while(true) {
            String userInput = scan.nextLine();
            System.out.println(userInput);

            if(userInput.equalsIgnoreCase("bye")){
                break;
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}

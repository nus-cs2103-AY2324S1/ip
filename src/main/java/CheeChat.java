import java.util.Scanner;
public class CheeChat {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm CheeChat");
        System.out.println("What can I do for you?");
        Scanner input = new Scanner(System.in);
        String cmd = input.nextLine();
        while(!cmd.equals("bye")){
            System.out.println(cmd);
            cmd = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}


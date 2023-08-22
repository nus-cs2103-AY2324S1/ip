import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String name = "nic";
        String line = "___________________________________\n";
        String exit = line + "Bye Bye. Hope to see you again soon!\n" + line;

        System.out.println(line + "Hello I'm " + name + "\nWhat can i do for you?\n" + line);

        Scanner scan = new Scanner(System.in);


        while (true) {
            String input = scan.nextLine();
            if ("bye".equals(input)) {
                System.out.println(exit);
                break;
            } else {
                System.out.println(line + input + "\n" + line);
            }

        }


    }
}

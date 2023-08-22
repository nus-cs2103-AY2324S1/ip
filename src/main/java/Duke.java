import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String name = "nic";
        String line = "___________________________________\n";
        String exit = line + "Bye Bye. Hope to see you again soon!\n" + line;

        System.out.println(line + "Hello I'm " + name + "\nWhat can i do for you?\n" + line);

        Scanner scan = new Scanner(System.in);
        int counter = 0;
        String[] list = new String[100];

        while (true) {

            String input = scan.nextLine();
            if ("bye".equals(input)) {
                System.out.println(exit);
                break;
            } else if ("list".equals(input)) {
                System.out.println(line);
                for (int i = 0; i < list.length; i++) {
                    if (list[i] != null) {
                        System.out.println(i + 1 + ". " + list[i]);
                    }
                }
                System.out.println(line);
            } else {
                list[counter] = input;
                counter++;
                System.out.println(line + "added: " + input + "\n" + line);
            }

        }


    }
}

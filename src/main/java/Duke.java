import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String[] list = new String[100];
        int counter = 0;
        String logo = " _           _        \n"
                    + "| |    _   _| | _____ \n"
                    + "| |   | | | | |/ / _ \\\n"
                    + "| |___| |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hi, I'm \n" + logo);

        while (true) {
            String input = reader.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < counter; i++) {
                    System.out.println((i+1)+". " + list[i]);

                }
            } else {
                System.out.println("added: " + input);
                list[counter] = input;
                counter += 1;
            }
        }
        //System.out.println("What's up?\n");
        System.out.println("Ok byeee\n");
    }
}

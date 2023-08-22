import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String logo = " _           _        \n"
                    + "| |    _   _| | _____ \n"
                    + "| |   | | | | |/ / _ \\\n"
                    + "| |___| |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hi, I'm \n" + logo);

        while (true) {
            String input = reader.nextLine();
            if (input.equals("bye")){
                break;
            }
            System.out.println(input);
        }
        //System.out.println("What's up?\n");
        System.out.println("Ok byeee\n");
    }
}

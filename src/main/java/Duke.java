import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String divider = "   _________________________________ \n";
        String greeting = "   Hello! I'm Sana \n   What can I do for you? \n";
        String bye = "   Bye. Hope to see you again soon! \n";

        Scanner myObj = new Scanner(System.in);
        System.out.println(divider + greeting + divider);

        while(true) {
            String command = myObj.nextLine();
            switch(command) {
                case "bye":
                    System.out.println(divider + bye + divider);
                    break;
                default:
                    System.out.println(divider + "   " + command + "\n" + divider);
            }
        }
    }
}

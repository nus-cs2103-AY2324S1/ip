import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<String> array = new ArrayList<>();
        String horizontalLine = "   ------------------------\n";
        System.out.println(horizontalLine
                + "     GREETINGS HUMAN! I AM QLATZ! □ \n"
                + "     I AM NOW A LISTMAKER\n"
                + horizontalLine);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String text = scanner.nextLine();
            if (text.equalsIgnoreCase("bye"))
                break;
            else if(text.equalsIgnoreCase("list")) {
                System.out.println("\n" + horizontalLine);
                for (int i = 1; i <= array.size(); i++) {
                    System.out.println("     " + i + ". " + array.get(i - 1));
                }
                System.out.println("\n" + horizontalLine);
            } else {
                array.add(text);
                text = "     " + text;
                System.out.println("\n" + horizontalLine + text + "\n" + horizontalLine);
            }
        }

        System.out.println("     BYE!\n" + horizontalLine);
    }
}

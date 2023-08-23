import java.util.Scanner;

public class ChatterChicken {
    public static void main(String[] args) {
        String line = "\n    _____________________________________________________________________________\n    ";
        System.out.println(line + "Hello! I'm ChatterChicken!\n    What can I do for you?" + line);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while(!input.equals("bye")) {
            System.out.println(line + input + line);
            input = sc.nextLine();
        }
        System.out.println(line + "Bye. Hope to see you again soon!" + line);
    }
}

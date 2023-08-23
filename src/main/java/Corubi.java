import java.util.Scanner;

public class Corubi {
    public static void main(String[] args) {
        String name = "Corubi";
        Scanner sc = new Scanner(System.in);
        String divider = "---------------------------------------------------";
        System.out.println(divider);
        System.out.println("Hello! I am " + name + ". \nWhat can I do for you?");
        System.out.println(divider);
        String input = sc.nextLine();
        while (!input.equals("bye") && !input.equals("Bye")) {
            System.out.println(input);
            System.out.println(divider);
            input = sc.nextLine();
        }
        System.out.println(input + " " + input + "...please come back soon :(");
    }
}

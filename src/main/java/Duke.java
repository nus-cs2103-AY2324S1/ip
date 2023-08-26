import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        // Duke customisation
        String name = "misty";
        String logo = "|\\---/|\n" +
                "| o_o |\n" +
                " \\___/";
        String separator = "------------------------------------------------";

        // Welcome user
        System.out.println("Hello from " + name + "\n" + logo);
        System.out.println("What can I help you with?");
        System.out.println(separator);


        Scanner sc = new Scanner(System.in);
        while (true) {
            String currInput = sc.nextLine();
            if (currInput.equals("bye")) {
                System.out.println("ok thanks bye");
                System.out.println(separator);
                break;
            } else {
                System.out.println(">  " + currInput);
                System.out.println(separator);
            }
        }
        sc.close();
    }
}

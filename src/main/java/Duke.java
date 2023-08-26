import java.util.Scanner;
public class Duke {

    private static String[] currList = new String[100]; // array to store users' input
    private static int counter = 0; // current input

    public static void main(String[] args) {
        // Duke customisation
        String name = "misty";
        String logo = "|\\---/|\n" +
                "| o_o |\n" +
                " \\___/";
        String separator = "------------------------------------------------";

        // Welcome user
        System.out.println(">  Hello from " + name + "\n" + logo);
        System.out.println(">  What can misty help you with?");
        System.out.println(separator);


        Scanner sc = new Scanner(System.in);
        while (true) {
            String currInput = sc.nextLine();
            if (currInput.equals("bye")) {
                System.out.println(">  ok thanks bye");
                System.out.println(separator);
                break;
            } else if (currInput.equals("list")) {
                for (int i = 0; i < counter; i++) {
                    System.out.println((i+1) + ") " + currList[i]);
                }
                System.out.println(separator);
            } else {
                currList[counter] = currInput;
                counter++;
                System.out.println(">  added: " + currInput);
                System.out.println(separator);
            }
        }
        sc.close();
    }
}

import java.util.Scanner;

public class Veda {

    private final static String NAME = "Veda";

    public static void main(String[] args) {
        //Greet users upon initialisation
        System.out.println("____________________________________________________________");
        System.out.println(NAME + " initialised. How may I help you?");
        System.out.println("____________________________________________________________");

        Scanner inScanner = new Scanner(System.in);

        while (true) {
            String input = inScanner.nextLine();

            //User wishes to exit the program
            if (input.toLowerCase().equals("bye")) {
                break;
            }

            System.out.println(input);
        }


        //Exits the program
        System.out.println("Bye. All the best for your mission!");
    }
}

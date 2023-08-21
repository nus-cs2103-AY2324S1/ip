import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "Bacon Pancake";
        System.out.println(" Hello from " + logo + "\n What can I do for you? \n" + "---------------------------------- \n"
                + " Bye. Hope to see you again soon! \n"
                + "----------------------------------\n ");

        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("Enter your input : ");
            String input = sc.nextLine();
            System.out.println("Bacon Pancake : \n" + input);
        }

    }
}

import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "Bacon Pancake";
        System.out.println(" Hello from " + logo + "\n What can I do for you? \n" + "---------------------------------- \n"
                + " Bye. Hope to see you again soon! \n"
                + "----------------------------------\n ");
        String[] lists = new String[100];
        int length = 0;
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("Enter your input : ");
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bacon Pancake : \n" + "Bye! See you again soon ");
                sc.close();
                break;
            } else if (input.equals("list")) {
                System.out.println("Bacon Pancake : Below are the lists\n");
                for (int i = 0; i < length; i++) {
                    System.out.println((i + 1) + ". " + lists[i]);
                }
            } else {
                lists[length++] = input;
                System.out.println("Bacon Pancake : \n Added : " + input);
            }

        }

    }
}

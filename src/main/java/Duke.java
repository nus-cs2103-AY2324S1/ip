import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String LINE = "____________________________________________________________";
        Scanner sc = new Scanner(System.in);

        System.out.println("\t" + LINE + "\n" +
                "\t Hello I'm ADJ \n" +
                "\t What can I do for you? \n\t" +
                LINE);

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.toLowerCase().equals("bye")) {
                System.out.println("\t" + LINE + "\n" +
                        "\t Bye. Hope to see you again soon! \n\t" +
                        LINE);
                break;
            } else {
                System.out.println("\t" + LINE + "\n\t " +
                        userInput + "\n\t" +
                        LINE);
            }
        }

    }
}

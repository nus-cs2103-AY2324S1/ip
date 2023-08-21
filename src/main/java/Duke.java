import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "Nino!";
        System.out.println("Hello, my name is " + logo);
        System.out.println(wrapper("What can I do for you?"));
        reader();
    }

    private static void reader() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                System.out.println(wrapper("Goodbye."));
                sc.close();
                break;
            }
            System.out.println(wrapper(line));
        }
    }

    private static String wrapper(String line) {
        String frame = "=====================";
        return String.format("%s\n%s", line, frame);
    }
}

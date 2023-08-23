import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " _______________ \n"
                + "Eh, I'm Scarlet \n"
                + "No, I don't really want to know who you are \n\n"
                + " _______________ \n";
        System.out.println(logo);
        Duke duke = new Duke();
        duke.repeat();
    }

    public void repeat() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String entry = sc.nextLine();

            if (entry.equals("bye")) {
                break;
            }
            String message = " _______________ \n\n"
                    + String.format("%s \n", entry)
                    + " _______________ \n";
            System.out.println(message);
        }
        String logo = " _______________ \n\n"
                + "finally. \n"
                + " _______________ \n";
        System.out.println(logo);
    }

}

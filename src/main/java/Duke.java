import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "\n---------------------------------------\n";
        String intro = "Hey there! I'm BUTTER.\n" +
                "How can I help you today?";
        String bye = "Signing off, see you later!";

        System.out.println(line + intro + line); //greeting
        String task = "";
        while (true) {
            System.out.println("Add a task: ");
            Scanner scanner = new Scanner(System.in); //create Scanner object
            task = scanner.nextLine();
            if (task.equals("bye")) {
                break;
            } else {
                System.out.println("New task added: " + task + line);
            }
        }
        System.out.println(line + bye + line);
    }
}

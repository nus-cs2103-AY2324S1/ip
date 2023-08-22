import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String line = "---------------------------------------\n";
        String intro = "Hey there! I'm BUTTER.\n" +
                "How can I help you today?\n";
        String bye = "Signing off, see you later!\n";

        System.out.println(line + intro + line); //greeting
        String task = "";
        ArrayList<String> list = new ArrayList<>();

        while (true) {
            System.out.println("Add a task: ");
            Scanner scanner = new Scanner(System.in); //create Scanner object
            task = scanner.nextLine();
            if (task.equals("bye")) {
                break;
            } else if (task.equals("list")) {
                String result = "";
                for (int i = 0; i < list.size(); i++) {
                    int index = i + 1;
                    result += index + ". " + list.get(i) + "\n";
                }
                System.out.println(result + line);
            } else {
                list.add(task);
                System.out.println("New task added: " + task + "\n" + line);
            }
        }
        System.out.println(line + bye + line);
    }
}

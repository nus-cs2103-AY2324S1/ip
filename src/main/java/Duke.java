import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String line = "---------------------------------------\n";
        String intro = "Hey there! I'm BUTTER.\n" +
                "How can I help you today?\n";
        String bye = "Signing off, see you later!\n";

        System.out.println(line + intro + line); //greeting
        String input = "";
        String command = "";
        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            Scanner scanner = new Scanner(System.in); //create Scanner object
            input = scanner.nextLine(); //get user input
            command = input.split(" ", 2)[0]; //gets the first word of input

            if (input.equals("bye")) {
                break;

            } else if (input.equals("list")) {
                String result = "";
                for (int i = 0; i < list.size(); i++) {
                    int index = i + 1;
                    Task task = list.get(i);
                    result += index + ". " + task.toString() + "\n";
                }
                System.out.println("Here's your todo list: ");
                System.out.println(result + line);

            } else if (command.equals("mark")) {
                int taskID = Integer.parseInt(input.split(" ", 2)[1]) - 1; //because index starts from 0
                list.get(taskID).mark();
                System.out.println(line);

            } else if (command.equals("unmark")) {
                int taskID = Integer.parseInt(input.split(" ", 2)[1]) - 1; //because index starts from 0
                list.get(taskID).unmark();
                System.out.println(line);
                
            } else {
                list.add(new Task(0, input));
                System.out.println("New task added: " + input + "\n" + line);
            }
        }
        System.out.println(line + bye + line);
    }
}

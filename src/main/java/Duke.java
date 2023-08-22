import java.util.Scanner;
import java.util.ArrayList;

public class Duke {


    public static void main(String[] args) {

        System.out.println("Hello! I'm Mikhil" + '\n' + "What can I do for you");
        boolean flag = true;
        ArrayList<Task> items = new ArrayList<>();

        while (flag == true) {
            Scanner userInput = new Scanner(System.in);
            String input = userInput.nextLine();
            Task t = new Task(input);

            String[] splitStr = t.getDescription().trim().split("\\s+");


            if (t.getDescription().equals("bye")) {
                flag = false;
                System.out.println("Bye. Hope to see you again soon!");
            }
            else if (t.getDescription().equals("list")) {
                for (int i = 0; i < items.size(); i++) {
                    Task item = items.get(i);
                    System.out.println(i + 1 + ".[" + item.getStatusIcon()+ "] " + item.getDescription());
                }
            }
            else if(splitStr[0].equals("mark") || splitStr[0].equals("unmark")){
                int index = Integer.parseInt(splitStr[1]);
                Task item = items.get(index-1);
                item.setAction(splitStr[0]);
                System.out.println("[" + item.getStatusIcon()+ "] " + item.getDescription());

            }
            else {
                items.add(t);
                System.out.println("added: " + input);
            }
        }
    }
}

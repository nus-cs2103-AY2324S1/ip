import java.util.Scanner;
import java.util.ArrayList;

public class Duke {


    public static void main(String[] args) {

        System.out.println("Hello! I'm Mikhil" + '\n' + "What can I do for you");
        boolean flag = true;
        Task[] tasks = new Task[100];
        Scanner userInput = new Scanner(System.in);

        while (flag == true) {
            String input = userInput.nextLine();
            String[] splitStr = input.trim().split("\\s+");

            if (input.equals("bye")) {
                flag = false;
                System.out.println("Bye. Hope to see you again soon!");
            }
            else if (input.equals("list")) {
                System.out.println("  Here are the tasks in your list:");
                for (int i = 0; i < Task.getSize(); i++) {
                    int index = i+1;
                    System.out.println("  " + index + "." + tasks[i].toString()) ;

                }
            }
            else if (splitStr[0].equals("todo")){

                Todo t = new Todo(input.substring(5));
                int totalTasks = Task.getSize();
                tasks[totalTasks-1] = t;
            }
            else if (splitStr[0].equals("deadline")){

                String[] deadline = input.split("/");
                Deadline d = new Deadline(deadline[0].substring(9), deadline[1].substring(3));
                int totalTasks = Task.getSize();
                tasks[totalTasks-1] = d;

            }
            else if (splitStr[0].equals("event")){

                String[] event = input.split("/");
                Event e = new Event(event[0].substring(6),event[1].substring(5), event[2].substring(3));
                int totalTasks = Task.getSize();
                tasks[totalTasks-1] = e;

            }
            else if(splitStr[0].equals("mark") || splitStr[0].equals("unmark")){
                int index = Integer.parseInt(splitStr[1]);
                Task item = tasks[index-1];
                item.setAction(splitStr[0]);


            }
            System.out.println("----------------------------------------");
        }
    }
}

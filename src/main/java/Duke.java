import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private static String horiLine = "____________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        greetFunction("Jack");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        TaskArray taskArrayList = new TaskArray();

        while(!input.equals("bye")) {
            if (input.equals("list")) {
                taskArrayList.printTaskArrayList();
            } else if (input.split(" ")[0].equals("mark")){
                int index = Integer.parseInt(input.split(" ")[1]);
                taskArrayList.get(index - 1).mark();
            } else if (input.split(" ")[0].equals("unmark")){
                int index = Integer.parseInt(input.split(" ")[1]);
                taskArrayList.get(index - 1).unmark();
            }else{
                Task newTask =new Task(input);
                taskArrayList.add(newTask);
            }

            input = scanner.nextLine();
        }
        byeFunction();
        scanner.close();

    }

    public static void greetFunction(String name){
        String greetings = horiLine +"\nHello! I'm " + name + "\n"
                + "What can I do for you?\n" + horiLine;
        System.out.println(greetings);
    }
    public static void byeFunction(){

        String byeword = horiLine + "\nBye. Hope to see you again soon\n" + horiLine;
        System.out.println(byeword);
    }
    public static void repeatFunction(String text){
        String byeword = horiLine +"\n" + text + "\n" + horiLine;
        System.out.println(byeword);
    }

    public static void printTaskList(ArrayList<Task> taskArray){

    }





}

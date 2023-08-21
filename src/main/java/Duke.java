import java.util.Scanner;
public class Duke {
    String[] Tasklist;
    int counter;
    String lines;

    public Duke() {
        this.Tasklist = new String[100];
        this.counter = 0;
        this.lines = "______________________________";
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        String lines = "______________________________";
        Scanner scan = new Scanner(System.in);
        Duke meowBot = new Duke();

        System.out.println(lines);
        System.out.println("Hello! I'm MeowBot!");
        System.out.println("What can I do for you ?");
        System.out.println(lines);

        String command = scan.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println(lines);
                for (int i = 0; i < meowBot.counter; i++) {
                    System.out.println(i + ". " + meowBot.Tasklist[i]);
                }
                System.out.println(lines);
            }
            else{
                meowBot.addTask(command);
            }
            command = scan.nextLine();

        }


        System.out.println(lines);
        System.out.println("Bye. Hope to see you again soon!");
    }

    void addTask(String task){
        this.Tasklist[counter] = task;
        counter ++;
        System.out.println(lines);
        System.out.println("added: " + task);
        System.out.println(lines);
    }
}

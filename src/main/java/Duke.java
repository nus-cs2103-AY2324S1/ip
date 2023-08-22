import java.util.Scanner;

public class Duke  {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        MYBot MYBot = new MYBot("MYBot");
        MYBot.openGreeting();

        Scanner scanner = new Scanner(System.in);
        while(true){
            String input = scanner.nextLine();
            if(input.equals("bye")){
                MYBot.closeGreeting();
                break;
            } else if(input.equals("list")){
                MYBot.listTasks();
            } else {
                MYBot.addTask(input);
            }
        }
    }
}

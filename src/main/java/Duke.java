import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String inData = "";
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello! I'm Nicole");
        System.out.println("What can I do for you?");
        String[] list = new String[100];
        int count = 0;
        while (!inData.equals("bye")) {
            inData = scan.nextLine();
            if (inData.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (inData.equals("list")) {
                for(int i = 0; i < count; i++) {
                    System.out.println(i + 1 + ". " + list[i]);
                }
                continue;
            }
            list[count] = inData;
            count++;
            System.out.println("added: "+ inData);
        }
    }
}

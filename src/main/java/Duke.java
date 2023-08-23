import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("─────────────────────");
        System.out.println("Hello I'm Robot!");
        System.out.println("What can I do for you?");
        System.out.println("─────────────────────");

        Scanner sc = new Scanner(System.in);
        String[] store = new String[100];
        int counter = 0;
        while(true){
            String userInput = sc.nextLine();
            System.out.println("─────────────────────");
            if(userInput.equals("bye")) break;
            if(userInput.equals("list")){
                for(int i=0;i<counter;i++){
                    System.out.println(Integer.toString(i+1) + ". " + store[i]);
                }
                System.out.println("─────────────────────");
            }else{
                store[counter++] = userInput;
                System.out.println("added: " + userInput);
                System.out.println("─────────────────────");
            }

        }


        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("─────────────────────");
    }
}

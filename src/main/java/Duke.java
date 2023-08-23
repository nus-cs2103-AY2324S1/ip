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
        boolean[] completed = new boolean[100];
        int counter = 0;
        while(true){
            String userInput = sc.nextLine();
            String[] splitStr = userInput.split("\\s+");
            System.out.println("─────────────────────");
            if(userInput.equals("bye")) break;

            if(userInput.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for(int i=0;i<counter;i++){
                    System.out.println( Integer.toString(i+1)  + "." + "[" + (completed[i] ? "X" : " ") + "] " + store[i]);
                }
            }else if(splitStr.length == 2 && splitStr[0].equals("mark")){
                System.out.println("Nice! I've marked this task as done:");
                int x = Integer.parseInt(splitStr[1])-1;
                completed[x] = true;
                System.out.println( splitStr[1]  + "." + "[" + (completed[x] ? "X" : " ") + "] " + store[x]);

            }else if(splitStr.length == 2 && splitStr[0].equals("unmark")){
                System.out.println("Ok, I've marked this task as not done yet:");
                int x = Integer.parseInt(splitStr[1])-1;
                completed[x] = false;
                System.out.println( splitStr[1]  + "." + "[" + (completed[x] ? "X" : " ") + "] " + store[x]);

            }else{
                store[counter++] = userInput;
                System.out.println("added: " + userInput);
            }
            System.out.println("─────────────────────");

        }


        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("─────────────────────");
    }
}

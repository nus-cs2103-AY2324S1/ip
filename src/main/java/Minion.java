import java.util.Scanner;
public class Minion {
    public static String line = "\t____________________________________________________________\n";
    public static void main(String[] args) {
        sayHi();
        Scanner sc = new Scanner(System.in);
        while(true) {
            String command = sc.nextLine();
            if(command.equals("bye")) {
                sayBye();
                break;
            }
            prettyPrint(command);
        }
        sc.close();
    }

    /**
     * Function to say hi to the user.
     */
    public static void sayHi(){
        prettyPrint("Hello! I'm Minion!\n\tWhat can I do for you?");
    }

    /**
     * Function to say bye to the user.
     */
    public static void sayBye(){
        prettyPrint("Bye. Hope to see you again soon!");
    }
    /**
     * Prints the string between two horizontal lines.
     * @param s String to be printed.
     */
    public static void prettyPrint(String s){
        System.out.println(line + String.format("\t%s\n", s) + line);
    }
}

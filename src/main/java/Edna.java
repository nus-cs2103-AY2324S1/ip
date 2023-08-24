import java.util.*;
public class Edna {
    public static ArrayList<String> inputList = new ArrayList<String>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;

        String partition = "----------------------------------------";

        System.out.println(partition);
        System.out.println("Hello! I'm Edna.");
        System.out.println("What can I do for you?");
        System.out.println(partition);

        input = sc.nextLine();

        while(input.equals("bye") == false) {
            if (input.equals("list")) {
                 print();
                 System.out.println(partition);
            } else {
                add(input);
                System.out.println(partition);
            }
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(partition);
    }

    public static void add(String input) {
        inputList.add(input);
        System.out.println("added: " + input);
    }

    public static void print() {
        int num = 1;
        for(String temp: inputList) {
            System.out.println(num + ". " + temp);
            num++;
        }
    }
}

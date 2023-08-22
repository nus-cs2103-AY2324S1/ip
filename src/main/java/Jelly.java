import java.util.Scanner;

public class Jelly {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Jelly");
        System.out.println("What can I do for you?");
        while (sc.hasNext()) {
            String temp = sc.next();
            if (temp.equals("bye")) {
                System.out.println("Bye, have a nice day!");
                return;
            } else {
                System.out.println(temp);
            }
        }
    }
}

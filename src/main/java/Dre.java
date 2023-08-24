import java.util.ArrayList;
import java.util.Scanner;

public class Dre {
    ArrayList<String> list;

    public Dre() {
        list = new ArrayList<>();
    }
    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Dre");
        System.out.println("What can I do for you?");
    }

    public void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void echo() {
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        while (!next.equals("bye")) {
            if (next.equals("list")) {
                list();
            } else {
                System.out.println("added: " + next);
                list.add(next);
            }
            next = sc.nextLine();
        }
        sc.close();
        exit();
    }

    public void list() {
        for(int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }



    public static void main(String[] args) {
        Dre dre = new Dre();
        dre.greet();
        dre.echo();
    }
}

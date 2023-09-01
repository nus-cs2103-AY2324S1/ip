package duke;

import java.util.Scanner;

public class Addlist {
    private int counter = 0;
    private String[] storage = new String[100];

    public void addlist() {
        Scanner sn = new Scanner(System.in);
        String input = sn.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                listing();
            } else {
                this.storage[this.counter] = input;
                counter += 1;
            }
            input = sn.nextLine();
        }
    }

    private void listing() {
        for (int i = 1; i <= counter; i++) {
            System.out.println(i + ". " + storage[i - 1]);
        }
    }
}

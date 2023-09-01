package duke;

import java.util.Scanner;

public class Echoplex {

    public void echo() {
        Scanner sn = new Scanner(System.in);
        String input = sn.nextLine();

        while(!input.equals("bye")) {
            System.out.println(input);
            input = sn.nextLine();
        }
    }
}

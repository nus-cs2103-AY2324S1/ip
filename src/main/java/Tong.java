import java.util.Scanner;

public class Tong {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String currentLine;
        toDoList list = new toDoList();

        printHorizontalLine(35);
        System.out.println("Hello! I'm TONG.");
        System.out.println("Say something. Say anything.");
        System.out.println("Sometimes you say 'bye'.");
        printHorizontalLine(35);

        currentLine = input.nextLine();

        while (!currentLine.equalsIgnoreCase("bye")) {
            if (currentLine.equalsIgnoreCase("list")) {
                System.out.println(list);
            } else if (currentLine.contains("unmark")) {
                String[] split = currentLine.split(" ");
                int order = Integer.parseInt(split[1]);
                list.unmarkTask(order);
            } else if (currentLine.contains("mark")) {
                String[] split = currentLine.split(" ");
                int order = Integer.parseInt(split[1]);
                list.markTask(order);
            } else {
                Task task = new Task(currentLine);
                list.addTask(task);
            }

            currentLine = input.nextLine();
        }

        System.out.println("Bye.");
        printHorizontalLine(35);
        input.close();
    }

    public static void printHorizontalLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}

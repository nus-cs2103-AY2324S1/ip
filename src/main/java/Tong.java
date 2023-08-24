import java.util.Scanner;

public class Tong {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String currentLine;

        printHorizontalLine(35);
        System.out.println("Hello! I'm TONG.");
        System.out.println("Say something. Say anything.");
        System.out.println("Sometimes you say 'bye'.");
        printHorizontalLine(35);

        currentLine = input.nextLine();

        while (!currentLine.equalsIgnoreCase("bye")) {
            System.out.println("TONG: " + currentLine);
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

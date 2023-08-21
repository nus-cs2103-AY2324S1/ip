//public printer class that abstracts and does the printing of the outputs

public class Printer {

    static String line = "---------------------";

    public static void print(String input) {
        System.out.println(line);
        System.out.println(input);
        System.out.println(line);
    }
}

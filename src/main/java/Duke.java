public class Duke {
    public static void main(String[] args) {

        String linebreak = "——————————————————————————————————————————————————————————";

        String greeting = linebreak +
                "\n——Fingerprint match found. Verification complete. Welcome home.\n" +
                "PRTS, at your service. What would you like to do today?\n" + linebreak;

        String farewell = "Farewell. See you again soon.\n" + linebreak;

        System.out.println(greeting);
        System.out.println(farewell);
    }
}

public class Peko {
    static String lineBreak = "------------------------------------------"; //42
    static String introText = "Konpeko, Konpeko, Konpeko! \n" +
            "Usada Pekora-peko! almondo almondo!";
    static String exitText = "Otsupeko! Bye bye!";
    public static void main(String[] args) {
        String pekoLogo = " _____      -\n"
                + "|     |___ | | ______\n"
                + "|  ___/ _ \\| |/ /    \\\n"
                + "| |  <  __/|   <  <>  |\n"
                + "|_|   \\___||_|\\_\\____/";
        System.out.println(pekoLogo);

        System.out.println(lineBreak);
        System.out.println(introText);
        System.out.println(lineBreak);
        exit();
    }

    public static void exit() {
        System.out.println(exitText);
    }
}

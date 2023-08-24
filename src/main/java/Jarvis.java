public class Jarvis {
    
    /**
     * ASCII Art Generated from http://patorjk.com/software/taag/
     */
    private static final String logo = 
"    ██  █████  ██████  ██    ██ ██ ███████ \n" +
"    ██ ██   ██ ██   ██ ██    ██ ██ ██      \n" +
"    ██ ███████ ██████  ██    ██ ██ ███████ \n" +
"██  ██ ██   ██ ██   ██  ██  ██  ██      ██\n" +
"█████  ██   ██ ██   ██   ████   ██ ███████ \n";

    private void breakLine() {
        System.out.println("____________________________________________________________ \n");
    } 

    public void printIntro() {
        breakLine();
        System.out.println("Hi Master! I'm your personal assistant: JARVIS! \n" +
                            "\n" +
                            logo +
                            "\n" +
                            "How can I serve you today? \n");
        breakLine();
    }

    public void echo(String toEcho) {
        breakLine();
        System.out.println(toEcho);
        breakLine();
    }

    public void printBye() {
        breakLine();
        System.out.println("Bye Master. It has been my honour to serve you!");
        breakLine();
    }
}

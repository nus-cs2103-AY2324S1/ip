public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scripts script = new Scripts();
        script.sayhi();
        Echoplex echoplex = new Echoplex();
        echoplex.echo();
        script.saybye();
    }
}

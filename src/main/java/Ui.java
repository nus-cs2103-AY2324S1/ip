public class Ui {
    public void greet() {
        String logo = "\n" +
                "__________\n" +
                "\\______   \\ ____   ____\n" +
                " |    |  _// __ \\_/ __ \\\n" +
                " |    |   \\  ___/\\  ___/\n" +
                " |______  /\\___  >\\___  >\n" +
                "        \\/     \\/     \\/\n";
        System.out.println("Hello! I'm" + logo);
        System.out.println("~Bzzzz~ What may I assist you with today? ~Bzzzz~\n");
    }

    public void showLoadingError() {
        System.out.println("~Bzzzz~ Oh no! We failed to load the tasks\n");
    }

    public void farewell() {
        System.out.println("Bye-bye! Have a great day! ~Bzzz~");
    }
}

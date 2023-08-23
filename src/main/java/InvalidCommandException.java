class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }

    public void printExceptionMessage() {
        System.out.println(Duke.HORIZONTAL_LINE);
        System.out.println("     ☹ OOPS!!! " + this.getMessage());
        System.out.println("     Please input valid commands. Currently SeeWhyAre bot supports:");
        System.out.println("     todo \n     deadline \n     event \n     list");
        System.out.println(Duke.HORIZONTAL_LINE);
    }
}
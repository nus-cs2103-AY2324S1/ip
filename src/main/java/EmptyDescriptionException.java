class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String message) {
        super(message);
    }

    public void printExceptionMessage() {
        System.out.println(Duke.HORIZONTAL_LINE);
        System.out.println("     ☹ OOPS!!! " + this.getMessage());
        System.out.println(Duke.HORIZONTAL_LINE);
    }
}
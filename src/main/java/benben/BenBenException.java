package benben;

/**
 * The exception thrown form the chatbot benben
 * prints an error message to the screen without crashing the program
 */
class BenBenException extends RuntimeException {
    /**
     * Instantiates a new Ben Ben exception.
     *
     * @param message the message to be printed on tghe screen
     */
    BenBenException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "WOOF! " + getMessage();
    }

}

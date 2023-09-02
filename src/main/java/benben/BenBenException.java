package benben;
class BenBenException extends RuntimeException {
    BenBenException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "WOOF! " + getMessage();
    }

}

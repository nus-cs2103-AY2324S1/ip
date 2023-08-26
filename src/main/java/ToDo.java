class ToDo extends Task {
    ToDo(String description) {
        super(description);
    }

    @Override
    String stringToFile() {
        return String.format("T | %s", super.stringToFile());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}

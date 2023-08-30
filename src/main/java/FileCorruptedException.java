public class FileCorruptedException extends LocalFileException{
    private String filePath;

    public FileCorruptedException(String filePath) {
        super(filePath);
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "\tOOPS, the file " + this.filePath + " seems to be corrupted.\n" +
                "\tPlease try correcting the file content, or removing it.";
    }
}

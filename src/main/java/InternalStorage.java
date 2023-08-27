import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;

/**
 * A class that manages the program's internal storage's read-write operations.
 */
public class InternalStorage {

    private static final java.nio.file.Path BASE_JAVA_NIO_FILE_PATH = java.nio.file.Path.of(".","data");

    /**
     * A path wrapper that represents the path from the base directory of the program.
     */
    public static class Path {
        String[] components;

        /**
         * Constructs a new path with path components representing the internal relative path
         * starting from the base directory.
         *
         * <p>
         *     All components are separated by path separators automatically, do not supply them.
         * </p>
         *
         * @param components The path components.
         */
        public Path(String... components) {
            this.components = components;
        }

        /**
         * Returns a new path relative to the configured base directory, while removing the last
         * component in the path.
         *
         * @return The path after removing the last path component.
         */
        public Path excludingLastComponent() {
            // Create parent directories first.
            String[] directoryComponents = Arrays.copyOf(this.components, Math.max(0, this.components.length - 1));
            return new Path(directoryComponents);
        }

        /**
         * Returns the given path's full path relative to the configured base directory, as a
         * {@link java.nio.file.Path} instance.
         *
         * @return The path for the given file as a {@link java.nio.file.Path} instance.
         */
        public java.nio.file.Path toJavaNioFilePath() {
            return java.nio.file.Path.of(BASE_JAVA_NIO_FILE_PATH.toString(), this.components);
        }

        /**
         * Returns a new File instance constructed from this path.
         */
        public File toFile() {
            return this.toJavaNioFilePath().toFile();
        }

        @Override
        public String toString() {
            return this.toJavaNioFilePath().toString();
        }
    }



    /**
     * Saves the given String data into the given path, relative to the configured base directory.
     *
     * @param path The path to save to.
     * @param data The data to save in the file with.
     * @throws IOException if the file cannot be written for any reason.
     */
    public static void saveTo(Path path, String data) throws IOException {

        // Auto-create directory if necessary.
        java.nio.file.Path nioPath = path.excludingLastComponent().toJavaNioFilePath();
        Files.createDirectories(nioPath);

        // Write to the file.
        File file = path.toFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(data);
        fileWriter.close();

    }

    /**
     * Reads the string data from the given filename, relative to the configured base directory.
     *
     * @param path The path to read from.
     * @return The resulting data read from the file.
     * @throws IOException if the file cannot be read for any reason.
     */
    public static String loadFrom(Path path) throws IOException {

        // Reads from the file.
        File file = path.toFile();

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        return stringBuilder.toString();

    }
}

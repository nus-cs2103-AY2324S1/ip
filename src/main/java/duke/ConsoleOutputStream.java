package duke;

import java.io.IOException;
import java.io.OutputStream;

/**
 * A custom OutputStream that redirects its output to a StringBuilder.
 */
public class ConsoleOutputStream extends OutputStream {

    private final StringBuilder content = new StringBuilder();

    /**
     * Writes the specified byte to this output stream.
     * @param b  the byte to be written.
     * @throws IOException
     */
    @Override
    public void write(int b) throws IOException {
        content.append((char) b);
    }

    /**
     * Returns the content of the OutputStream as a String.
     * @return  the content of the OutputStream as a String.
     */
    public String getContent() {
        return content.toString();
    }

    /**
     * Clears the content of the OutputStream.
     */
    public void clearContent() {
        content.setLength(0);
    }

}

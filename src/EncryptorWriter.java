import java.io.IOException;
import java.io.Writer;

public class EncryptorWriter extends Writer {
    private final Writer writer;
    private final int shift;

    public EncryptorWriter(Writer writer) {
        this(writer, 0);
    }

    public EncryptorWriter(Writer writer, int shift) {
        this.writer = writer;
        this.shift = shift;
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        // Convert each character in the buffer to an int, add the shift value, and convert back to a char
        for (int i = off; i < off + len; i++) {
            cbuf[i] = (char)(cbuf[i] + shift);
        }
        // Write the encrypted characters to the underlying writer
        writer.write(cbuf, off, len);
    }

    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}

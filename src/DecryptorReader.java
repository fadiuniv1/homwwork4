import java.io.IOException;
import java.io.Reader;

public class DecryptorReader extends Reader {
    private final Reader reader;
    private final int shift;

    public DecryptorReader(Reader reader) {
        this(reader, 0);
    }

    public DecryptorReader(Reader reader, int shift) {
        this.reader = reader;
        this.shift = shift;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        // Read the characters from the underlying reader
        int numRead = reader.read(cbuf, off, len);
        // Convert each character in the buffer to an int, subtract the shift value, and convert back to a char
        for (int i = off; i < off + numRead; i++) {
            cbuf[i] = (char)(cbuf[i] - shift);
        }
        return numRead;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}

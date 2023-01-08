import java.io.*;

public class TestEncoder {
    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Missing input file argument");
        }
        String inputFile = args[0];
        int shift = 0;
        if (args.length >= 2) {
            shift = Integer.parseInt(args[1]);
        }

        // Read the input file
        StringBuilder inputBuilder = new StringBuilder();
        try (Reader reader = new FileReader(inputFile)) {
            int c;
            while ((c = reader.read()) != -1) {
                inputBuilder.append((char)c);
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            return;
        }
        String input = inputBuilder.toString();

        // Encrypt the input and write it to an encrypted file
        String encryptedFile = "encrypted_" + inputFile;
        try (Writer writer = new EncryptorWriter(new FileWriter(encryptedFile), shift)) {
            writer.write(input);
        } catch (IOException e) {
            System.err.println("Error writing encrypted file: " + e.getMessage());
            return;
        }

        // Read the encrypted file, decrypt it, and write it to a decrypted file
        StringBuilder decryptedBuilder = new StringBuilder();
        try (Reader reader = new DecryptorReader(new FileReader(encryptedFile), shift)) {
            int c;
            while ((c = reader.read()) != -1) {
                decryptedBuilder.append((char)c);
            }
        } catch (IOException e) {
            System.err.println("Error reading encrypted file: " + e.getMessage());
            return;
        }
        String decrypted = decryptedBuilder.toString();

        // Write the decrypted text to a decrypted file
        String decryptedFile = "decrypted_" + inputFile;
        try (Writer writer = new FileWriter(decryptedFile)) {
            writer.write(decrypted);
        } catch (IOException e) {
            System.err.println("Error writing decrypted file: " + e.getMessage());
            return;
        }
    }
}

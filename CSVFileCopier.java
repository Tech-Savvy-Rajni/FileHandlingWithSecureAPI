import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CSVFileCopier {
    public static void copyPaste(String sourceFilePath, String destinationFilePath) {
        try {
            Path source = Paths.get(sourceFilePath);
            Path destination = Paths.get(destinationFilePath);
            Files.copy(source, destination);
            System.out.println("File Copied successfully");
        } catch (IOException e) {
            System.out.println("Error occured while copying the file: " + e.getMessage());
        }
    }
}

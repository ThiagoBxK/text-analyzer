import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextAnalyzer {
    private final String text;
    private final Path baseDirectory;
    private static final String dirname = "metrics";

    public TextAnalyzer(String text) {
        this.text = text;
        this.baseDirectory = Paths.get(System.getProperty("user.dir"));
    }

    public long getTotalBytes() {
        return text.getBytes(StandardCharsets.UTF_8).length;
    }

    public long getTotalChars() {
        return text.length();
    }

    public long getTotalLetters() {
        return text.chars().filter(Character::isLetter).count();
    }

    public long getTotalNumbers() {
        return text.chars().filter(Character::isDigit).count();
    }

    public long getTotalWhiteSpace() {
        return text.chars().filter(Character::isWhitespace).count();
    }

    public long getTotalLines() {
        return text.split("[\n\r]+").length;
    }

    private String generateMetricsContent() {
        return String.format("""
                ------- Metrics -------
                Total of bytes: %s
                Total of characters: %s
                Total of letters: %s
                Total of numbers: %s
                Total of white space: %s
                Total of lines: %s
                -----------------------
                """, getTotalBytes(), getTotalChars(), getTotalLetters(),
                getTotalNumbers(), getTotalWhiteSpace(), getTotalLines());
    }

    public void createMetricsFolder(Path path) {
        try {
            Files.createDirectory(path);

            System.out.println("Metrics folder created.");
        } catch (Exception e) {
            System.err.printf("Error creating metrics folder: %s\n", e.getMessage());
        }
    }

    public void createMetricsFile(String fileName) {
        Path metricsPath = baseDirectory.resolve(dirname);
        Path filePath = baseDirectory.resolve(dirname).resolve(fileName);
        String content = generateMetricsContent();

        if (!Files.exists(metricsPath))
            createMetricsFolder(metricsPath);

        try (FileWriter fileWriter = new FileWriter(filePath.toFile())) {
            fileWriter.write(content);
            System.out.printf("Metrics file created: %s\n", filePath);
        } catch (Exception e) {
            System.err.printf("Error creating metrics file: %s\n", e.getMessage());
        }
    }
}

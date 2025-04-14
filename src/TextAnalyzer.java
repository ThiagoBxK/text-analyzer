import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextAnalyzer {
    private final Path baseDirectory;

    TextAnalyzer(String text) {
        this.baseDirectory = Paths.get(System.getProperty("user.dir"));
    }

    public long getTotalBytes(String text) {
        return text.getBytes(StandardCharsets.UTF_8).length;
    }

    public long getTotalChars(String text) {
        return text.length();
    }

    public long getTotalLetters(String text) {
        return text.chars().filter(Character::isLetter).count();
    }

    public long getTotalWords(String text) {
        return text.trim().isEmpty() ? 0 : text.trim().split("\\s+").length;
    }

    public long getTotalNumbers(String text) {
        return text.chars().filter(Character::isDigit).count();
    }

    public long getTotalWhiteSpace(String text) {
        return text.chars().filter(Character::isWhitespace).count();
    }

    public long getTotalLines(String text) {
        return text.split("[\n\r]+").length;
    }

    private String generateMetricsContent(String text) {
        return String.format("""
                ------- Metrics -------
                Total of bytes: %s
                Total of characters: %s
                Total of letters: %s
                Total of words: %s
                Total of numbers: %s
                Total of white space: %s
                Total of lines: %s
                -----------------------
                """, getTotalBytes(text), getTotalChars(text), getTotalLetters(text), getTotalWords(text),
                getTotalNumbers(text), getTotalWhiteSpace(text), getTotalLines(text));
    }

    public void createMetricsFolder(Path path) {
        try {
            Files.createDirectory(path);
            System.out.println("Metrics folder created.");
        } catch (Exception e) {
            System.err.printf("Error creating metrics folder: %s \n", e.getMessage());
        }
    }

    public void analyzeText(String text) {
        System.out.println(generateMetricsContent(text));
    }

    public void analyzeFile(String filePath) {
        Path metricsFolder = Paths.get(baseDirectory.resolve("metrics").toString());
        Path inputPath = Paths.get(filePath);

        if (!Files.exists(inputPath)) {
            System.err.printf("Input file not found: %s \n", inputPath.toString());
            return;
        }

        if (!filePath.toLowerCase().endsWith(".txt")) {
            System.out.println("The file is not '.txt'!");
            return;
        }

        if (!Files.exists(metricsFolder) || !Files.isDirectory(metricsFolder))
            createMetricsFolder(metricsFolder);


        try {
            String content = Files.readString(inputPath, StandardCharsets.UTF_8);
            String metrics = generateMetricsContent(content);
            Path outputPath = metricsFolder.resolve(metricsFolder.resolve("metrics.txt"));

            Files.writeString(outputPath, metrics, StandardCharsets.UTF_8);
            System.out.println("Metrics file created!");
        } catch (Exception e) {
            System.err.printf("Error to create metrics file: %s \n", e.getMessage());
        }
    }
}

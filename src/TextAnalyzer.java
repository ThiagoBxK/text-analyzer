import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextAnalyzer {
    private static String text;

    TextAnalyzer(String text) {
        this.text = text;
    }

    public long totalBytes(String text) {
        return text.getBytes(StandardCharsets.UTF_8).length;
    }

    public long totalChars(String text) {
        return text.length();
    }

    public long totalLetters(String text) {
        return text.chars()
                .filter(Character::isLetter)
                .count();
    }

    public long totalNumbers(String text) {
        return text.chars()
                .filter(Character::isDigit)
                .count();
    }

    public long totalWhiteSpace(String text) {
        return text.chars()
                .filter(Character::isWhitespace)
                .count();
    }

    private String metricsFileContent() {
        return String.format("""
                        ------- Metrics -------
                        Total of bytes: %s
                        Total of characters: %s
                        Total of letters: %s
                        Total of numbers: %s
                        Total of white space: %s
                        -----------------------
                        """,
                this.totalBytes(this.text),
                this.totalChars(this.text),
                this.totalLetters(this.text),
                this.totalNumbers(this.text),
                this.totalWhiteSpace(this.text)
        );
    }

    public void createMetricsFile(String fileName) {
        Path currentDir = Paths.get(System.getProperty("user.dir"));
        Path filePath = Paths.get(currentDir.toString(), "metrics", fileName);
        String content = this.metricsFileContent();

        try (FileWriter fileWriter = new FileWriter(filePath.toString())) {
            fileWriter.write(content);

            System.out.printf("Metrics file has been created: %s \n", filePath.toString());
            System.out.println(content);
        } catch (Exception e) {
            System.err.printf("Ops, an error occurred while trying to create the metrics file: %s \n", e);
        }
    }


}
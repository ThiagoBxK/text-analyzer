import java.nio.charset.StandardCharsets;

public class TextAnalyzer extends Analyzer {

    @Override
    public long totalBytes(String text) {
        return text.getBytes(StandardCharsets.UTF_8).length;
    }

    @Override
    public long totalChars(String text) {
        return text.length();
    }

    @Override
    public long totalLetters(String text) {
        return text.chars()
                .filter(Character::isLetter)
                .count();
    }

    @Override
    public long totalNumbers(String text) {
        return text.chars()
                .filter(Character::isDigit)
                .count();
    }

    @Override
    public long totalWhiteSpace(String text) {
        return text.chars()
                .filter(Character::isWhitespace)
                .count();
    }
}
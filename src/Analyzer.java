public abstract class Analyzer {
    public abstract long totalBytes(String text);

    public abstract long totalChars(String text);

    public abstract long totalLetters(String text);

    public abstract long totalNumbers(String text);

    public abstract long totalWhiteSpace(String text);

    public void analyze(String text) {
        long totalBytes = this.totalBytes(text);
        long totalChars = this.totalChars(text);
        long totalLetters = this.totalLetters(text);
        long totalNumbers = this.totalNumbers(text);
        long totalWhiteSpace = this.totalWhiteSpace(text);

        System.out.printf("Total of bytes: %s \n", totalBytes);
        System.out.printf("Total of characters: %s \n", totalChars);
        System.out.printf("Total of letters: %s \n", totalLetters);
        System.out.printf("Total of numbers: %s \n", totalNumbers);
        System.out.printf("Total of white space: %s \n", totalWhiteSpace);
    }
}
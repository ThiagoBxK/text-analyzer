import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Type your text to generate metrics: ");
        String text = scanner.nextLine();
        TextAnalyzer analyzer = new TextAnalyzer(text);

        analyzer.createMetricsFile("metrics.txt");
       }
}
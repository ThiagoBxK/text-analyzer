import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextAnalyzer analyzer = new TextAnalyzer();

        System.out.print("Write your text: ");
        String text = scanner.nextLine();

        analyzer.analyze(text);
       }
}
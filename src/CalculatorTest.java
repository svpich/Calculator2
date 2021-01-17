import java.util.Scanner;

public class CalculatorTest {
    public static void main(String[] args) throws DataException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Калькулятор выполняет сложение(+), вычитание(-), деление(/), умножение(*) арабских или римских чисел от 1 до 10.");
        System.out.println("Для выхода из калькулятора введите \"exit\".");


        while (true) {
            System.out.print("Вычислить: ");
            String arithmeticExpression = scanner.nextLine();

            if (arithmeticExpression.equals("exit")) {
                System.out.println("Вы вышли из калькулятора");
                return;
            }

            String result = Calculator.calculate(arithmeticExpression);
            System.out.println("Результат: " + result);
        }
    }
}

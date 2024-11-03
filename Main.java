import java.io.PrintStream;
import java.util.Scanner;
public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    // Подпрограмма для вычисления результата логического выражения
    public static boolean evaluateExpression(String array, int x) {
        String[] parts = array.split(" "); // Разделяем строку на части
        String oper = parts[1];             // Получаем оператор (например, ">", "<", "=")
        int num = Integer.parseInt(parts[2]); // Получаем правую часть выражения (число)

        // Проверяем оператор
        if (oper.equals(">"))
            return x > num;
        else if (oper.equals("<"))
            return x < num;
        else
            return x == num;
    }

    // Метод для подсчета уникальных правых частей
    public static int cntRightParts(String[][] array) {
        String[] rightParts = new String[array.length * array[0].length]; // Массив для хранения уникальных правых частей
        int cnt = 0; // Счетчик
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                String rParts = array[i][j].split(" ")[2]; // Получаем правую часть выражения
                // Проверяем, есть ли уже такая правая часть в массиве
                boolean res = true;
                for (int k = 0; k < cnt; k++) {
                    if (rightParts[k].equals(rParts)) {
                        res = false;
                        break; // Если нашли такую же, прекращаем проверку
                    }
                }
                // Если правая часть уникальна, добавляем её в массив
                if (res) {
                    rightParts[cnt] = rParts;
                    cnt++;
                }
            }
        }

        return cnt; // Возвращаем общее количество уникальных правых частей
    }
    public static void main(String[] args) {
        // Вводим размер массива
        int N = in.nextInt();
        int M = in.nextInt();
        String[][] array = new String[N][M];// Создаем двумерный массив строк для хранения логических выражений
        in.nextLine();
        // Ввод логических выражений
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                array[i][j] = in.nextLine();
        int K = in.nextInt(); // Ввод значения переменной K
        // Создаем массив для хранения результатов вычислений
        boolean[][] res = new boolean[N][M];
        // 2. Вычисляем результат каждого логического выражения и выводим его
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // Вычисляем результат для каждого выражения и сохраняем его
                res[i][j] = evaluateExpression(array[i][j], K);
                if (res[i][j] == true)
                    out.print("Истина");
                else
                    out.print("Ложь");
                //out.print(res[i][j] ? "Истина " : "Ложь ");
            }
            out.println();
        }

        // Подсчитываем уникальные правые части
        out.println(cntRightParts(array));
    }
}
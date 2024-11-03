import java.io.PrintStream;
import java.util.Scanner;
public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    // Подпрограмма для сортировки массива по количеству истинных и ложных значений в строках
    public static void sortRes(boolean[][] res) {
        int N = res.length;
        // Сортировка
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - 1 - i; j++) {
                int trueCnt1 = cntTrue(res[j]);
                int trueCnt2 = cntTrue(res[j + 1]);
                int falseCnt1 = res[j].length - trueCnt1;
                int falseCnt2 = res[j + 1].length - trueCnt2;

                // Сравнение по количеству "Истина", если одинаково — по количеству "Ложь"
                if (trueCnt1 < trueCnt2 || (trueCnt1 == trueCnt2 && falseCnt1 > falseCnt2)) {
                    // Меняем строки местами, если они не в правильном порядке
                    boolean[] temp = res[j];
                    res[j] = res[j + 1];
                    res[j + 1] = temp;
                }
            }
        }
    }
    // Подпрограмма для подсчета количества истинных значений в строке
    public static int cntTrue(boolean[] row) {
        int cnt = 0;
        for (int i = 0; i < row.length; i++) {
            boolean value = row[i];
            if (value) {
                cnt++;
            }
        }
        return cnt;
    }
    // Подпрограмма для вычисления результата логического выражения
    public static boolean evaluateArray(String array, int x) {
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

    // Подпрограмма для подсчета уникальных правых частей
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
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // Вычисляем результат для каждого выражения, выводим и сохраняем его
                res[i][j] = evaluateArray(array[i][j], K);
                out.print(res[i][j] + " ");
            }
            out.println();
        }
        // Сортировка строк по количеству истинных и ложных значений
        sortRes(res);
        // Вывод отсортированного массива
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (res[i][j] == true)
                    out.print("Истина ");
                else
                    out.print("Ложь ");
            }
            out.println();
        }
        // Подсчитываем уникальные правые части и выводим
        out.println(cntRightParts(array));
    }
}
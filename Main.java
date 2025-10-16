import java.math.BigInteger;
import java.util.Scanner;
/**
 * Клас Liukas представляє обчислення чисел Люка.
 * Зберігає n, L_n та L_(n+1), а також надає методи
 * для обчислення ряду і перевірки нерівності.
 */
class Liukas {
    private int n;
    private BigInteger Ln;
    private BigInteger Lnastupne;

    // обчислення числа Люка
    private BigInteger obchyslytyLiuka(int k) {
        if (k == 0)
            return BigInteger.valueOf(2);
        if (k == 1)
            return BigInteger.ONE;
        BigInteger a = BigInteger.valueOf(2);
        BigInteger b = BigInteger.ONE;
        BigInteger c;
        for (int i = 2; i <= k; i++) {
            c = a.add(b);
            a = b;
            b = c;
        }
        return b;
    }
    /**
     * Конструктор створює об'єкт Liukas для заданого n.
     * @param znachenniaN номер числа ряду Люка
     */
    public Liukas(int znachenniaN) {
        n = znachenniaN;
        Ln = obchyslytyLiuka(n);
        Lnastupne = obchyslytyLiuka(n + 1);
    }

    // вивести ряд Люка до n-го з номерами
    public void vyvestyRyad() {
        System.out.println("Ряд Люка до L_" + n + ":");
        if (n >= 0) {
            BigInteger a = BigInteger.valueOf(2);
            System.out.println("L_0 = " + a);  // L0
            if (n >= 1) {
                BigInteger b = BigInteger.ONE;
                System.out.println("L_1 = " + b); // L1
                for (int i = 2; i <=( n-1); i++) {
                    BigInteger c = a.add(b);
                    System.out.println("L_" + i + " = " + c);
                    a = b;
                    b = c;
                }
            }
        }
    }

    public void perevirytyNerivnist() {
        System.out.println("L_" + n + " = " + Ln);
        System.out.println("L_" + (n + 1) + " = " + Lnastupne);

        BigInteger liva = BigInteger.valueOf(n).multiply(Lnastupne);
        BigInteger prava = BigInteger.valueOf(n + 1).multiply(Ln);

        System.out.println("Перевірка: " + n + "*L_" + (n+1) + " > " + (n+1) + "*L_" + n + " ?");
        System.out.print(liva + " > " + prava + " → ");
        if (liva.compareTo(prava) > 0) {
            System.out.println("так");
        } else {
            System.out.println("ні");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner skaner = new Scanner(System.in);

        while (true) {
            int n;

            // Перевірка на коректність вводу
            while (true) {
                System.out.print("Введіть n (n ≥ 0): ");
                if (skaner.hasNextInt()) {
                    n = skaner.nextInt();
                    if (n >= 0) {
                        break;
                    } else {
                        System.out.println("Введіть додатнє число!");
                    }
                } else {
                    System.out.println("Введеіть ціле число!");
                    skaner.next();
                }
            }

            Liukas liukas = new Liukas(n);
            liukas.vyvestyRyad();           //  вивід ряду
            liukas.perevirytyNerivnist();   //  перевірка нерівності

            // питання про продовження
            String vidpovid;
            while (true) {
                System.out.print("Бажаєте продовжити? (т/н): ");
                vidpovid = skaner.next();
                if (vidpovid.equalsIgnoreCase("т") || vidpovid.equalsIgnoreCase("н")) {
                    break;
                } else {
                    System.out.println("Введіть 'т' або 'н'.");
                }
            }

            if (vidpovid.equalsIgnoreCase("н")) {
                System.out.println("Програму завершено.");
                break;
            }
        }

        skaner.close();
    }
}

package lab1;

import java.util.Scanner;

class SotrudnikSDetmi {
    String fam, im, otch, doljnost;
    int kolDet;
    Rebenok[] reb = null;
}

class Rebenok {
    String imaR;
    int vozrastR;
}

public class Lab1Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, "cp1251");
        System.out.print("Введите количество сотрудников => ");
        int kol = sc.nextInt();
        sc.nextLine();

        SotrudnikSDetmi[] sotr = new SotrudnikSDetmi[kol];
        System.out.println("Введите информацию о каждом сотруднике: ");
        for (int i = 0; i < sotr.length; i++) {
            sotr[i] = new SotrudnikSDetmi();
            System.out.print("Введите фамилию " + (i + 1) + " сотрудника => ");
            sotr[i].fam = sc.nextLine();

            System.out.print("Введите его имя => ");
            sotr[i].im = sc.nextLine();

            System.out.print("Введите его отчество => ");
            sotr[i].otch = sc.nextLine();

            System.out.print("Введите его должность => ");
            sotr[i].doljnost = sc.nextLine();

            System.out.print("Введите количество детей => ");
            sotr[i].kolDet = sc.nextInt();
            sc.nextLine();

            if (sotr[i].kolDet != 0) {
                sotr[i].reb = new Rebenok[sotr[i].kolDet];
                System.out.println("Дети => ");
                for (int j = 0; j < sotr[i].reb.length; j++) {
                    sotr[i].reb[j] = new Rebenok();

                    System.out.print("Введите имя " + (i + 1) + " ребенка => ");
                    sotr[i].reb[j].imaR = sc.nextLine();

                    System.out.print("Введите его возраст => ");
                    sotr[i].reb[j].vozrastR = sc.nextInt();
                    sc.nextLine();
                }
            }
        }

        System.out.println("\nСотрудники фирмы:");
        System.out.printf("%-10s %-10s %-10s %-10s\n\n", "фам", "имя", "отч", "должность");

        for (SotrudnikSDetmi s : sotr) {
            System.out.printf("%-10s %-10s %-10s %-10s\n", s.fam, s.im, s.otch, s.doljnost);
            System.out.println("Дети: ");

            if (s.reb != null) {
                for (Rebenok r : s.reb) {
                    System.out.printf("%-10s %-10s\n", r.imaR, r.vozrastR);
                }
            }
            
            System.out.println("\n");
        }
    }
}

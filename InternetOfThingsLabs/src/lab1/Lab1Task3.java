package lab1;

import java.util.Scanner;

public class Lab1Task3 { // Task option 3 Bulhakov
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, "cp1251");
        System.out.print("Введите количество человек => ");
        int kol = sc.nextInt();
        sc.nextLine();

        Person[] people = new Person[kol];
        System.out.println("Введите информацию о каждом человеке: ");

        for (int i = 0; i < people.length; i++) {
            people[i] = new Person();
            System.out.print("Введите фамилию " + (i + 1) + " человека => ");
            people[i].surname = sc.nextLine();

            System.out.print("Введите его имя => ");
            people[i].name = sc.nextLine();

            System.out.print("Введите его отчество => ");
            people[i].lastname = sc.nextLine();

            System.out.print("Введите его возраст => ");
            people[i].age = sc.nextInt();

            System.out.print("Введите размер его одежды => ");
            people[i].clothesSize = sc.nextInt();

            sc.nextLine();
        }

        System.out.println("\nВведенные данные:\n");
        System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", "Фамилия", "Имя", "Отчество", "Возраст", "Размер одежды");

        for (Person h : people) {
            System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", h.surname, h.name, h.lastname, h.age, h.clothesSize);
        }

        // Find person with the largest clothes size
        int largestClothesPersonId = 0;
        int largestClothesSize = people[largestClothesPersonId].clothesSize;

        for (int i = 0; i < people.length; i++) {
            if (people[i].clothesSize > largestClothesSize) {
                largestClothesSize = people[i].clothesSize;
                largestClothesPersonId = i;
            }
        }

        System.out.println("\nЧеловек с самым большим размером одежды:");
        System.out.printf("Id = %-10s %-10s %-10s %-10s %-10s %-10s\n\n", largestClothesPersonId,
                people[largestClothesPersonId].surname, people[largestClothesPersonId].name,
                people[largestClothesPersonId].lastname, people[largestClothesPersonId].age,
                people[largestClothesPersonId].clothesSize);

        // Average clothing size for people over 40
        double sizesSum = 0;
        int peopleOverFortyCount = 0;

        for (Person person : people) {
            if (person.age >= 40) {
                peopleOverFortyCount += 1;
                sizesSum += person.clothesSize;
            }
        }

        if (peopleOverFortyCount > 0) {
            System.out.println("Средний размер одежды для людей после 40: " + sizesSum / peopleOverFortyCount);
        } else {
            System.out.println("\nНет людей с возрастом больше 40 для подсчета среднего размера одежды!");
        }

        // Sort array by clothes size n^2

        for (int i = 0; i < people.length - 1; i++)
            for (int j = 0; j < people.length - 1 - i; j++)
                if (people[j].clothesSize > people[j + 1].clothesSize) {
                    Person rab = people[j];
                    people[j] = people[j + 1];
                    people[j + 1] = rab;
                }

        System.out.println("\nДанные после сортировки:\n");
        System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", "Фамилия", "Имя", "Отчество", "Возраст", "Размер одежды");

        for (Person h : people) {
            System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", h.surname, h.name, h.lastname, h.age, h.clothesSize);
        }

        // Find by surname, edit and print

        sc.nextLine();
        System.out.println("Введите название искомого человека => ");
        String surnameQuery = sc.nextLine();
        int foundPersonId = -1;

        for (int i = 0; i < people.length; i++) {
            if (surnameQuery.equalsIgnoreCase(people[i].name)) {
                foundPersonId = i;
            }
        }

        if (foundPersonId != -1) {
            System.out.println("Найден человек с фамилией " + surnameQuery);
            System.out.printf("Id = %-10s %-10s %-10s %-10s %-10s %-10s\n",
                    foundPersonId, people[foundPersonId].surname,
                    people[foundPersonId].name, people[foundPersonId].lastname,
                    people[foundPersonId].age, people[foundPersonId].clothesSize);

            sc.nextLine();
            System.out.println("Введите новую фамилию человека => ");
            people[foundPersonId].surname = sc.nextLine();

            System.out.println("Данные после исправления:");
            System.out.printf("Id = %-10s %-10s %-10s %-10s %-10s %-10s\n",
                    foundPersonId, people[foundPersonId].surname,
                    people[foundPersonId].name, people[foundPersonId].lastname,
                    people[foundPersonId].age, people[foundPersonId].clothesSize);

        } else {
            System.out.println("Человек с фамилией" + surnameQuery + " не найден!");
        }
    }
}

package lab1;

import java.util.Scanner;

public class Lab1Task4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, "cp1251");
        System.out.print("Введите количество человек => ");
        int peopleCount = sc.nextInt();
        sc.nextLine();


        if (peopleCount <= 0) {
            return;
        }


        Person[] people = new Person[peopleCount];
        for (int i = 0; i < peopleCount; i++) {
            people[i] = readPersonFromConsole(sc);
        }

        System.out.println("\nВведенные данные:\n");
        printPeople(people);

        // Find person with the largest clothes size
        Person personWithLargestClothingSize = findMaxByClothingSize(people);
        printPerson(personWithLargestClothingSize);

        // Sort array by clothes size n^2
        sortPeopleByClothingSize(people);
        System.out.println("\nДанные после сортировки:\n");
        printPeople(people);

        // Find by surname, edit and print
        System.out.println("Введите фамилию искомого человека => ");
        String surnameQuery = sc.nextLine();
        Person foundPerson = findPersonBySurname(people, surnameQuery);

        if (foundPerson == null) {
            System.out.println("Человек с фамилией" + surnameQuery + " не найден!");
            return;
        }

        printPerson(foundPerson);
        System.out.println("Введите новую фамилию человека => ");
        foundPerson.surname = sc.nextLine();
        System.out.println("Данные после исправления:");
        printPerson(foundPerson);
    }

    private static Person readPersonFromConsole(Scanner sc) {
        Person newPerson = new Person();
        System.out.print("Введите фамилию => ");
        newPerson.surname = sc.nextLine();

        System.out.print("Введите его имя => ");
        newPerson.name = sc.nextLine();

        System.out.print("Введите его отчество => ");
        newPerson.lastname = sc.nextLine();

        System.out.print("Введите его возраст => ");
        newPerson.age = sc.nextInt();

        System.out.print("Введите размер его одежды => ");
        newPerson.clothesSize = sc.nextInt();

        sc.nextLine();

        return newPerson;
    }


    private static void printPerson(Person person){
        printPeople(new Person[] {person});
    }
    private static void printPeople(Person[] people) {
        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s\n", "Id", "Фамилия", "Имя", "Отчество", "Возраст", "Размер одежды");

        if (people == null || people.length == 0) {
            System.out.println("Нет данных для вывода");
        }

        for (int i = 0; i < people.length; i++) {
            System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s\n", i, people[i].surname, people[i].name, people[i].lastname, people[i].age, people[i].clothesSize);
        }
    }

    private static Person findMaxByClothingSize(Person[] people) {
        if (people == null || people.length == 0) {
            return  null;
        }

        int largestClothesPersonId = 0;
        int largestClothesSize = people[largestClothesPersonId].clothesSize;

        for (int i = 0; i < people.length; i++) {
            if (people[i].clothesSize > largestClothesSize) {
                largestClothesSize = people[i].clothesSize;
                largestClothesPersonId = i;
            }
        }

        return people[largestClothesPersonId];
    }

    private static void sortPeopleByClothingSize(Person[] people) {
        for (int i = 0; i < people.length - 1; i++)
            for (int j = 0; j < people.length - 1 - i; j++)
                if (people[j].clothesSize > people[j + 1].clothesSize) {
                    Person rab = people[j];
                    people[j] = people[j + 1];
                    people[j + 1] = rab;
                }
    }

    private static Person findPersonBySurname(Person[] people, String surnameQuery) {
        int foundPersonId = -1;

        for (int i = 0; i < people.length; i++) {
            if (surnameQuery.equalsIgnoreCase(people[i].surname)) {
                foundPersonId = i;
            }
        }

        return foundPersonId != -1 ? people[foundPersonId] : null;
    }
}

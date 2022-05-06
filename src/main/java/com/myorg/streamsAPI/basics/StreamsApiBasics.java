
package com.myorg.streamsAPI.basics;

import com.myorg.functionalProgramming.common.statics.Gender;
import com.myorg.streamsAPI.common.model.Person;

import java.util.*;
import java.util.stream.Collectors;

public class StreamsApiBasics {

    public static List<Person> getPeople() {
        return List.of(
                new Person("James Bond", 20, Gender.MALE),
                new Person("Alina Smith", 33, Gender.FEMALE),
                new Person("Helen White", 57, Gender.FEMALE),
                new Person("Alex Boz", 14, Gender.MALE),
                new Person("Alex Boz", 13, Gender.FEMALE),
                new Person("Jimmy Goa", 99, Gender.MALE),
                new Person("Anna Cook", 7, Gender.FEMALE),
                new Person("Zelda Brown", 120, Gender.FEMALE),
                new Person("Justin Bieber", 120, Gender.MALE),
                new Person("George Flood", 122, Gender.PREFER_NOT_TO_SAY)
        );
    }

    public static void main(String[] args) {
        List<Person> people = getPeople();

        /** Imperative approach: #################################################################################### */

        /** ListIterator approach (filtering out females) : */
        List<Person> females = new ArrayList<>();
        ListIterator<Person> listIterator = people.listIterator();// acquiring the list iterator of people list

        while (listIterator.hasNext()) {
            Person person = listIterator.next();

            if (Gender.FEMALE.equals(person.getGender())) {
                females.add(person);
            }
        }

        System.out.println("Females : " + females + "\n");

        /** for-each loop approach (filtering out males) : */
        List<Person> males = new ArrayList<>();

        for (Person person : people) {
            if (Gender.MALE.equals(person.getGender())) {
                males.add(person);
            }
        }

        System.out.println("Males : " + males + "\n");


        /**  Declarative approach: ###################################################################################*/

        people.stream().forEach(System.out::println);

        /**  filter --------------------------------------------------------------------------------------------------*/
        System.out.println("\nfilter\n");

        List<Person> femalesList = people.stream()
                .filter(Objects::nonNull)
                .filter(person -> Gender.FEMALE.equals(person.getGender()))
                .collect(Collectors.toList());

        femalesList.forEach(System.out::println);

        /**  sort ----------------------------------------------------------------------------------------------------*/
        System.out.println("\nsorted\n");

        // Sorting people by name according to ascending order and if the name is the same, then sorted by age in descending order
        List<Person> peopleSortedByNameAndThenAge = people.stream()
                .sorted(Comparator.comparing(Person::getName)
                        .thenComparing(Comparator.comparing(Person::getAge).reversed()))
                .collect(Collectors.toList());

        peopleSortedByNameAndThenAge.forEach(System.out::println);

        /**
         * OUTPUT:
         * -------
         * Consider the two people with same name but different ages ->  'Alex Boz'
         * First the name is sorted according to the alphabetical order and the if it is the same name, age is sorted
         * in descending order
         *
         * Person{name='Alex Boz', age=14, gender=MALE}
         * Person{name='Alex Boz', age=13, gender=FEMALE}
         * Person{name='Alina Smith', age=33, gender=FEMALE}
         * Person{name='Anna Cook', age=7, gender=FEMALE}
         * Person{name='George Flood', age=122, gender=PREFER_NOT_TO_SAY}
         * Person{name='Helen White', age=57, gender=FEMALE}
         * Person{name='James Bond', age=20, gender=MALE}
         * Person{name='Jimmy Goa', age=99, gender=MALE}
         * Person{name='Justin Bieber', age=120, gender=MALE}
         * Person{name='Zelda Brown', age=120, gender=FEMALE}
         * */


        /**  allMatch ------------------------------------------------------------------------------------------------*/
        System.out.println("\nallMatch\n");

        // Checking whether everyone is over the age of 10
        boolean isAllOver10 = people.stream()
                .allMatch(person -> person.getAge() > 10);

        System.out.println("isAllOver10 : " + isAllOver10);


        /** anyMatch -------------------------------------------------------------------------------------------------*/
        System.out.println("\nanyMatch\n");

        // Checks if at least one person is below the age of 30 years
        boolean atLeastOnePersonIsAbove30 = people.stream()
                .anyMatch(person -> person.getAge() > 30);

        System.out.println("atLeastOnePersonIsAbove30 : " + atLeastOnePersonIsAbove30);

        /**  nonMatch ------------------------------------------------------------------------------------------------*/
        System.out.println("\nnonMatch\n");

        // returns true if no person matches the provided predicate (not at least one record satisfies condition)
        boolean nonMatchAboveTheAge100 = people.stream()
                .noneMatch(person -> person.getAge() > 100); // false

        System.out.println("nonMatchAboveTheAge100 : " + nonMatchAboveTheAge100);

        boolean noneMatchWithNameAntonio = people.stream()
                .noneMatch(person -> person.getName().equalsIgnoreCase("antonio")); //true

        System.out.println("noneMatchWithNameAntonio : " + noneMatchWithNameAntonio);

        /**  max -----------------------------------------------------------------------------------------------------*/
        System.out.println("\nmax\n");

        // Person with the max age/ the oldest person
        Optional<Person> max = people.stream().max(Comparator.comparing(Person::getAge));
        max.ifPresent(System.out::println);

        // Person who is having the lengthiest name
        Optional<Person> personWithMaxNameLength = people.stream()
                .max(Comparator.comparing(person -> person.getName().length()));

        personWithMaxNameLength.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("NOT_FOUND"));

        /**  min -----------------------------------------------------------------------------------------------------*/
        System.out.println("\nmin\n");

        // The youngest person
        Optional<Person> min = people.stream()
                .min(Comparator.comparing(Person::getAge));

        min.ifPresent(System.out::println);

        // Person with the minimal length of name
        Optional<Person> personWithMinNameLength = people.stream()
                .min(Comparator.comparing(person -> person.getName().length()));

        personWithMinNameLength.ifPresent(System.out::println);

        /**  group ---------------------------------------------------------------------------------------------------*/
        System.out.println("\ngroup\n");

        // Grouping information based on a filed that we have. Here based on Gender
        Map<Gender, List<Person>> groupedByGenderMap = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        groupedByGenderMap.entrySet().forEach(System.out::println);
        System.out.println();

        // Another approach for printing
        groupedByGenderMap.forEach(((gender, personList) -> {
            System.out.println(gender);
            personList.stream().forEach(System.out::println);
            System.out.println();
        }));

        System.out.println();

        // Find out every single female and grab the oldest of them and return the name
        Optional<String> firstNameOfOldestPerson = people.stream()
                .filter(person -> Gender.FEMALE.equals(person.getGender()))
                .max(Comparator.comparing(Person::getAge))
                .map(person -> person.getName());

        firstNameOfOldestPerson.ifPresent(System.out::println);
    }
}

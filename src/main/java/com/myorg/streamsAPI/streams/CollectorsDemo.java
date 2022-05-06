package com.myorg.streamsAPI.streams;

import com.myorg.streamsAPI.common.model.Employee;
import com.myorg.streamsAPI.common.statics.Gender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class CollectorsDemo {

    public static void execute(String methodName, Runnable runnable) {
        Consumer<String> stringConsumer = System.out::println;

        stringConsumer.accept(methodName);
        runnable.run();
        System.out.println();
    }

    public static List<Employee> getEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        try (FileReader fileReader = new FileReader("src/main/resources/employee-data.csv");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            employeeList = bufferedReader
                    .lines()
                    .skip(1)
                    .filter(s -> Objects.nonNull(s) && !s.isBlank() && !s.isEmpty())
                    .map(s -> {
                        String[] employeeRawData = s.split(",");
                        return (employeeRawData.length == 5)
                                ? new Employee(Integer.valueOf(employeeRawData[0]),
                                employeeRawData[1],
                                Boolean.valueOf(employeeRawData[2]),
                                Long.valueOf(employeeRawData[3]),
                                Gender.valueOf(employeeRawData[4].toUpperCase()))
                                : null;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return employeeList;
    }

    public static void basicCollectorOperations() {
        // Collect all the male employee names to a list
        List<String> employeeNamesList = getEmployees().stream()
                .filter(e -> Gender.MALE.equals(e.getGender()))
                .map(Employee::getName)
                .collect(Collectors.toList());

        employeeNamesList.stream().limit(10).forEach(System.out::println);
        System.out.println();


        // Collect all the female distinct employee names to a set
        Set<String> employeeNamesSet = getEmployees().stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());

        employeeNamesSet.stream().limit(10).forEach(System.out::println);
        System.out.println();

        // Collect all the employee names against their respective employee ids to a map
        Map<Integer, String> employeeIdAndDataMap = getEmployees().stream()
                .collect(Collectors.toMap(Employee::getId, Employee::getName));

        employeeIdAndDataMap.entrySet().stream().limit(20).forEach(System.out::println);
    }

    public static void employeesWithSameSalaryGroup() {
        /**
         * Find the name of the employees who is paid with the same salary and put their names to a map against
         * their salary
         * */
        Map<Long, List<String>> sameSalaryMap = getEmployees()
                .stream()
                .collect(Collectors.groupingBy(Employee::getSalary,
                        Collectors.mapping(Employee::getName, Collectors.toList())));

        sameSalaryMap.entrySet().forEach(System.out::println);

        System.out.println();

        /** From above map, find only the employees whose salaries are equal */
        sameSalaryMap.entrySet().stream().filter(e -> e.getValue().size() > 1).forEach(System.out::println);
    }

    public static void genderBasedCount() {
        /** Get employees count by each gender (either MALE or FEMALE) */
        Map<Gender, Long> genderCountMap = getEmployees().stream()
                .filter(e -> Gender.MALE.equals(e.getGender()) || Gender.FEMALE.equals(e.getGender()))
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));

        genderCountMap.entrySet().forEach(System.out::println);
    }

    /**
     * Receives an int array, converts it into the form of String, and then display
     *
     * @param values int[] array
     * @return void
     */
    public static void getIntArrayAsAString(int[] values) {
        String arrayString = Arrays.stream(values)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));

        System.out.println(arrayString);
    }

    public static void main(String[] args) throws IOException {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        execute("basicCollectorOperations", CollectorsDemo::basicCollectorOperations);
        execute("employeesWithSameSalaryGroup", CollectorsDemo::employeesWithSameSalaryGroup);
        execute("genderBasedCount", CollectorsDemo::genderBasedCount);
        execute("getIntArrayAsAString", () -> CollectorsDemo.getIntArrayAsAString(array));
    }
}

package com.myorg.streamsAPI.streams;

import com.myorg.streamsAPI.common.model.Employee;
import com.myorg.streamsAPI.common.statics.Gender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class StreamAdvancedPracticals {

    public static void execute(String methodName, Runnable runnable) {
        long start;
        long end;

        Consumer<String> stringConsumer = System.out::println;
        Consumer<Runnable> runnableConsumer = Runnable::run;

        stringConsumer.accept(methodName);

        start = System.currentTimeMillis();
        runnableConsumer.accept(runnable);
        end = System.currentTimeMillis();

        stringConsumer.accept("Execution time is : " + (end - start) + " milliseconds\n");
    }

    public static List<Employee> getEmployeesList() {
        List<Employee> employeeList = new ArrayList<>();

        final String FILE_PATH = "src/main/resources/employee-data.csv";
        final String COMMA_SEPARATOR = ",";
        final int EMPLOYEE_RAW_DATA_ARRAY_LENGTH = 5;

        try (FileReader fileReader = new FileReader(FILE_PATH);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            employeeList = bufferedReader.lines()
                    .skip(1)
                    .filter(s -> Objects.nonNull(s) && !s.isEmpty() && !s.isBlank() && s.contains(COMMA_SEPARATOR))
                    .map(s -> s.split(COMMA_SEPARATOR))
                    .map(employeeRawData -> (employeeRawData.length == EMPLOYEE_RAW_DATA_ARRAY_LENGTH)
                            ? new Employee(Integer.valueOf(employeeRawData[0]), employeeRawData[1],
                            Boolean.valueOf(employeeRawData[2]), Long.valueOf(employeeRawData[3]),
                            Gender.valueOf(employeeRawData[4]))
                            : null)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return employeeList;
    }

    public static void findThreeHighestPaidActiveEmployees_imperative() {

        List<Employee> threeHighestPaidActiveEmployees = new ArrayList<>();

        for (Employee employee : StreamAdvancedPracticals.getEmployeesList()) {
            if (employee.isActive()) {
                threeHighestPaidActiveEmployees.add(employee);
            }
        }

        threeHighestPaidActiveEmployees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return Long.valueOf(e2.getSalary()).compareTo(Long.valueOf(e1.getSalary()));
            }
        });

        threeHighestPaidActiveEmployees = threeHighestPaidActiveEmployees.subList(0, 3);

        System.out.println(threeHighestPaidActiveEmployees);
    }

    public static void findThreeHighestPaidActiveEmployees_declarative() {
        List<Employee> threeHighestPaidActiveEmployees = getEmployeesList().stream()
                .filter(Employee::isActive)
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println(threeHighestPaidActiveEmployees);
    }

    public static void findThreeHighestPaidActiveEmployeeNames() {
        // If only the employee names are required ---------------------------------------------------------------------
        List<String> threeHighestPaidActiveEmployeeNames = getEmployeesList().stream()
                .filter(Employee::isActive)
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(3)
                .map(Employee::getName)
                .collect(Collectors.toList());

        System.out.println(threeHighestPaidActiveEmployeeNames);
    }

    /**
     * Collectors ---------------------------------------------------------------------------------------------------
     */

    public static void collectingToCollections() {
        List<String> femalesList = getEmployeesList().stream()
                .filter(employee -> Gender.FEMALE.equals(employee.getGender()))
                .map(Employee::getName)
                .collect(Collectors.toList());

        femalesList.stream().forEach(System.out::println);

        Set<String> activeMales = getEmployeesList().stream()
                .filter(employee -> Gender.MALE.equals(employee.getGender()))
                .filter(Employee::isActive)
                .map(Employee::getName)
                .collect(Collectors.toSet());

        System.out.println();
        activeMales.stream().forEach(System.out::println);

        Map<Integer, Long> employeeSalary = getEmployeesList().stream()
                .limit(20)
                .filter(Employee::isActive) // This filter is applied to first 20 employees only
                .collect(Collectors.toMap(Employee::getId, Employee::getSalary));

        System.out.println();
        employeeSalary.entrySet().stream().forEach(System.out::println);
    }

    public static void joiningAndGroupingWithCollectors() {
        String names = getEmployeesList().stream()
                .limit(5)
                .map(Employee::getName)
                .collect(Collectors.joining(" / "));

        System.out.println(names + "\n");

        // Grouping by gender
        Map<Gender, List<Employee>> employeesByGender = getEmployeesList().stream()
                .limit(100)
                .collect(Collectors.groupingBy(Employee::getGender));

        employeesByGender.entrySet().forEach(System.out::println);

        // Count by gender. Count is returned in Long
        Map<Gender, Long> countByGender = getEmployeesList().stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));

        System.out.println();
        countByGender.entrySet().forEach(System.out::println);
        /** Output:
         * ---------
         * MALE=490
         * FEMALE=510
         * */
    }

    public static void reduceOperations() {
        Optional<Long> totalSalaryOfFirstFive = getEmployeesList().stream()
                .limit(5)
                .map(Employee::getSalary)
                .reduce((s1, s2) -> s1 + s2);

        System.out.println(totalSalaryOfFirstFive.orElse(Long.valueOf(0)));
    }

    public static void parallelStreaming() {
        /**
         * Parallel streaming is recommended when we are having a large number of elements (Ex: more than 10000)
         * we can call either,
         *      getEmployeesList().stream().parallel()
         * or
         *       getEmployeesList().parallelStream()
         * */

        // Using getEmployeesList().stream().parallel()
        Map<String, Long> employeesCountWithSameNameMap = getEmployeesList().stream()
                .parallel()
                .collect(Collectors.groupingBy(Employee::getName, Collectors.counting()));

        employeesCountWithSameNameMap.entrySet().forEach(System.out::println);

        System.out.println();

        // Using getEmployeesList().parallelStream()
        employeesCountWithSameNameMap = getEmployeesList().parallelStream()
                .collect(Collectors.groupingBy(Employee::getName, Collectors.counting()));

        employeesCountWithSameNameMap.entrySet().forEach(System.out::println);
    }

    // Concurrency bean

    public static void getAverageSalaryByGender() {
        Map<Gender, Double> genderWiseSalaryAverageMap = getEmployeesList().stream().limit(10).collect(
                Collectors.groupingBy(Employee::getGender, Collectors.averagingLong(Employee::getSalary)));

        genderWiseSalaryAverageMap.entrySet().stream().forEach(System.out::println);
    }

    // emp table id, name (database), same name filter out - SQL query
    // distinct


    // Spring profiles
    // application - dev
    // application - qa
    // application - perf

    public static void idTotalFromIntStream() {
        int total = getEmployeesList()
                .stream()
                .limit(10)
                .mapToInt(Employee::getId)
                .sum();

        System.out.println(total);
    }

    /**
     * Extracts and displays the meaningful word hidden within a mixed character String
     *
     * @param rawText String value which contains mixed characters
     * @return void
     */
    public static void filterCharsAndComposeWord(String rawText) {
        String derivedText = rawText.chars()
                .mapToObj(c -> (char) c)
                .filter(Character::isAlphabetic)
                .map(String::valueOf)
                .collect(Collectors.joining());

        System.out.println(rawText);
        System.out.println(derivedText);
    }

    /**
     * Extracts and displays the total of numbers which are hidden within a mixed character String
     *
     * @param rawText String value which contains mixed characters
     * @return void
     */
    public static void filterAndGetValue(String rawText) {
        int value = rawText
                .chars()
                .mapToObj(c -> (char) c)
                .filter(Character::isDigit)
                .mapToInt(Character::getNumericValue)
                .sum();

        System.out.println(value);
    }

    /**
     * Simple tip
     * -----------
     * DO NOT concurrently modify source of the stream. (Means while a stream is iterated by one thread, we should not
     * allow any other thread to modify concurrently)
     */

    public static void main(String[] args) {
        execute("findThreeHighestPaidActiveEmployees_imperative",
                StreamAdvancedPracticals::findThreeHighestPaidActiveEmployees_imperative);
        execute("findThreeHighestPaidActiveEmployees_declarative",
                StreamAdvancedPracticals::findThreeHighestPaidActiveEmployees_declarative);
        execute("findThreeHighestPaidActiveEmployeeNames",
                StreamAdvancedPracticals::findThreeHighestPaidActiveEmployeeNames);
        execute("collectingToCollections", StreamAdvancedPracticals::collectingToCollections);
        execute("joiningAndGroupingWithCollectors", StreamAdvancedPracticals::joiningAndGroupingWithCollectors);
        execute("reduceOperations", StreamAdvancedPracticals::reduceOperations);
        execute("parallelStreaming", StreamAdvancedPracticals::parallelStreaming);
        execute("idTotalFromIntStream", StreamAdvancedPracticals::idTotalFromIntStream);
        execute("getAverageSalaryByGender", StreamAdvancedPracticals::getAverageSalaryByGender);
        execute("filterCharsAndComposeWord", () -> StreamAdvancedPracticals
                .filterCharsAndComposeWord("O@1s4tr4[]ic3h;"));
        execute("filterAndGetValue", () -> StreamAdvancedPracticals
                .filterAndGetValue("O@1s4tr4[]ic3h;"));
    }
}

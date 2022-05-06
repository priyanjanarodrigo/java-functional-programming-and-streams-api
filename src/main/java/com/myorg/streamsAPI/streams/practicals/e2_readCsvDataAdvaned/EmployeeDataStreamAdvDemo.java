package com.myorg.streamsAPI.streams.practicals.e2_readCsvDataAdvaned;

import com.myorg.streamsAPI.streams.practicals.e2_readCsvDataAdvaned.model.Employee;
import com.myorg.streamsAPI.streams.practicals.e2_readCsvDataAdvaned.model.Gender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeDataStreamAdvDemo {

    private static final String EMPLOYEE_FILE_PATH = "src/main/resources/employee-data-advanced.csv";

    /**
     * Fetches employee raw data from the csv file to which the file path is provided, maps them into
     * a List of type Employee and returns
     *
     * @return List<Employee> employee data list
     */
    public static List<Employee> getEmployeesList() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(EMPLOYEE_FILE_PATH))) {
            return bufferedReader
                    .lines()
                    .skip(1)
                    .map(EmployeeDataStreamAdvDemo::mapToEmployeeObject)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

        } catch (IOException ioException) {
            throw new RuntimeException("Exception occurred while fetching employee data", ioException);
        }
    }

    /**
     * Converts the provided String text line into an Employee object and returns
     *
     * @param textLine String line of text which contains comma separated employee details
     * @return
     */
    public static Employee mapToEmployeeObject(String textLine) {
        Employee employee = null;
        if (Objects.nonNull(textLine) && !textLine.isBlank() && !textLine.isEmpty()) {
            String[] employeeDataArray = textLine.split(",");
            if (employeeDataArray.length == 12) {
                employee = new Employee.Builder()
                        .id(Integer.parseInt(employeeDataArray[0]))
                        .name(employeeDataArray[1])
                        .dateOfBirth(LocalDate.parse(employeeDataArray[2]))
                        .gender(Gender.valueOf(employeeDataArray[3].toUpperCase()))
                        .isMarried(Boolean.valueOf(employeeDataArray[4]))
                        .phone(employeeDataArray[5])
                        .email(employeeDataArray[6])
                        .department(employeeDataArray[7])
                        .salary(Double.parseDouble(employeeDataArray[8]))
                        .city(employeeDataArray[9])
                        .isPermanent(Boolean.valueOf(employeeDataArray[10]))
                        .shirtSize(employeeDataArray[11])
                        .build();
            }
        }
        return employee;
    }

    /**
     * Finds and returns the names of first 10 employees according to reverse alphabetical order
     *
     * @return List<String> Employee names list
     */
    public static List<String> getLimited10NamesOnReverseAlphabeticalOrder() {
        return getEmployeesList()
                .stream()
                .map(Employee::getName)
                .sorted(Comparator.reverseOrder())
                .limit(10)
                .collect(Collectors.toList());
    }

    public static Map<String, List<String>> getDepartmentWiseEmployees() {
        return getEmployeesList()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment
                        , Collectors.mapping(Employee::getName, Collectors.toList())));
    }

    public static Map<String, List<Employee>> getEmployeesByCity() {
        return getEmployeesList()
                .stream()
                .collect(Collectors.groupingBy(Employee::getCity));
    }

    public static Map<String, Double> getAverageSalaryByDepartment() {
        return getEmployeesList()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));
    }

    public static Set<String> getDistinctCityNamesOnAlphabeticalOrder() {
        return getEmployeesList()
                .stream()
                .map(Employee::getCity)
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static Map<String, Long> getTheAnnualSalaryOfTop10HighestPaidPermanentEmployees() {
        return getEmployeesList()
                .stream()
                .filter(Employee::isPermanent)
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(10)
                .collect(Collectors.toMap(Employee::getName,
                        e -> Double.valueOf(e.getSalary() * 12).longValue()));
    }

    public static Map<String, Map<Integer, Integer>> processRoundedCashPayment() {
        return getEmployeesList()
                .stream()
                .collect(Collectors.toMap(
                        employee -> employee.getId() + "_" + employee.getName(),
                        EmployeeDataStreamAdvDemo::getMostEffectiveSalaryPaymentInCash));
    }

    public static TreeMap<Integer, Integer> getMostEffectiveSalaryPaymentInCash(Employee employee) {
        final TreeSet<Integer> currency = new TreeSet<>(Comparator.reverseOrder());
        currency.addAll(Set.of(1000, 1, 5000, 2, 100, 5, 10, 500, 20, 50, 2000));

        int amountPaid = Long.valueOf(Math.round(employee.getSalary())).intValue();
        TreeMap<Integer, Integer> payMap = new TreeMap<>(Comparator.reverseOrder());

        for (Integer note : currency) {
            if (amountPaid % note >= 0 && (amountPaid / note) != 0) {
                payMap.put(note, (amountPaid / note));
                amountPaid = amountPaid % note;
            }
        }
        return payMap;
    }

    public static Map<String, Double> getTotalCostAllocationForEachDepartment() {
        return getEmployeesList().stream()
                .limit(20) // Just to short data set
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.summingDouble(Employee::getSalary)));
    }

    public static Map<String, Long> getEmployeeCountByDepartment() {
        return getEmployeesList()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                            Collectors.mapping(Employee::getId, Collectors.counting())));
    }

    public static void main(String[] args) {
        System.out.println("getDepartmentWiseEmployees");
        getDepartmentWiseEmployees().entrySet().forEach(System.out::println);

        System.out.println("\ngetEmployeesByCity");
        getEmployeesByCity().entrySet().stream().forEach(System.out::println);

        System.out.println("\ngetAverageSalaryByDepartment");
        getAverageSalaryByDepartment().entrySet().stream().forEach(System.out::println);

        System.out.println("\ngetDistinctCityNames");
        getDistinctCityNamesOnAlphabeticalOrder().stream().forEach(System.out::println);

        System.out.println("\ngetTheAnnualSalaryOfTop10HighestPaidPermanentEmployees");
        getTheAnnualSalaryOfTop10HighestPaidPermanentEmployees().entrySet().stream().forEach(System.out::println);

        System.out.println("\ngetMostEffectiveCashPayment");
        processRoundedCashPayment()
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(System.out::println);

        System.out.println("\ngetTotalCostAllocationForEachDepartment");
        getTotalCostAllocationForEachDepartment().entrySet().stream().forEach(System.out::println);

        System.out.println("\ngetEmployeeCountByDepartment");
        getEmployeeCountByDepartment().entrySet().stream().forEach(System.out::println);
    }
}

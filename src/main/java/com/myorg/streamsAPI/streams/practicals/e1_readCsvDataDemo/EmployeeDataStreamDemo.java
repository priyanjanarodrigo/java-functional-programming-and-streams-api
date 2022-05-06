package com.myorg.streamsAPI.streams.practicals.e1_readCsvDataDemo;

import com.myorg.streamsAPI.common.model.Employee;
import com.myorg.streamsAPI.common.statics.Gender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

public class EmployeeDataStreamDemo {

    private static final String EMPLOYEE_DATA_CSV_FILE_PATH = "src/main/resources/employee-data.csv";

    public static List<Employee> getEmployeeData() {
        List<Employee> employeeList = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(EMPLOYEE_DATA_CSV_FILE_PATH))) {
            employeeList = bufferedReader
                    .lines()
                    .skip(1)
                    .filter(line -> Objects.nonNull(line) && !line.isEmpty() && !line.isBlank())
                    .map(line -> {
                        String[] employeeRawData = line.split(",");
                        return Objects.nonNull(employeeRawData) && employeeRawData.length == 5
                                ? new Employee(Integer.valueOf(employeeRawData[0]), employeeRawData[1],
                                Boolean.valueOf(employeeRawData[2]), Long.valueOf(employeeRawData[3]),
                                Gender.valueOf(employeeRawData[4]))
                                : null;
                    }).filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while reading csv data", e);
        }
        return employeeList;
    }

    public static Employee findFirstEmployeeWhenOrderedReversAlphabetically() {
        return getEmployeeData().stream()
                .sorted(Comparator.comparing(Employee::getName).reversed())
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public static List<Gender> findDistinctGendersOfEmployees() {
        return getEmployeeData().stream()
                .map(Employee::getGender)
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<String> findTheNamesOfTop10HighestPaidEmployees() {
        return getEmployeeData().stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(10)
                .map(Employee::getName)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        getEmployeeData().stream().forEach(System.out::println);
        System.out.println();

        System.out.println(findFirstEmployeeWhenOrderedReversAlphabetically());
        System.out.println();

        System.out.println(findDistinctGendersOfEmployees());
        System.out.println();

        System.out.println(findTheNamesOfTop10HighestPaidEmployees());
    }
}

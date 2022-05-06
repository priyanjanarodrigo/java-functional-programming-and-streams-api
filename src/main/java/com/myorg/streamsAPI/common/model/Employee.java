package com.myorg.streamsAPI.common.model;


import com.myorg.streamsAPI.common.statics.Gender;

public class Employee {
    private final int id;

    private final String name;

    private final boolean isActive;

    private final long salary;

    private final Gender gender;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public long getSalary() {
        return salary;
    }

    public Gender getGender() {
        return gender;
    }

    public Employee(int id, String name, boolean isActive, long salary, Gender gender) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.salary = salary;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                ", salary=" + salary +
                ", gender=" + gender +
                '}';
    }
}

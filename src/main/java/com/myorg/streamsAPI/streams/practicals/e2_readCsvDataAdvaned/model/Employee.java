package com.myorg.streamsAPI.streams.practicals.e2_readCsvDataAdvaned.model;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
    private Boolean isMarried;
    private String phone;
    private String email;
    private String department;
    private double salary;
    private String city;
    private Boolean isPermanent;
    private String shirtSize;

    public Employee(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.dateOfBirth = builder.dateOfBirth;
        this.gender = builder.gender;
        this.isMarried = builder.isMarried;
        this.phone = builder.phone;
        this.email = builder.email;
        this.department = builder.department;
        this.salary = builder.salary;
        this.city = builder.city;
        this.isPermanent = builder.isPermanent;
        this.shirtSize = builder.shirtSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Boolean getMarried() {
        return isMarried;
    }

    public void setMarried(Boolean married) {
        isMarried = married;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean isPermanent() {
        return isPermanent;
    }

    public void isPermanent(Boolean permanent) {
        isPermanent = permanent;
    }

    public String getShirtSize() {
        return shirtSize;
    }

    public void setShirtSize(String shirtSize) {
        this.shirtSize = shirtSize;
    }

    public static class Builder {
        private int id;
        private String name;
        private LocalDate dateOfBirth;
        private Gender gender;
        private Boolean isMarried;
        private String phone;
        private String email;
        private String department;
        private double salary;
        private String city;
        private Boolean isPermanent;
        private String shirtSize;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder isMarried(Boolean married) {
            isMarried = married;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder department(String department) {
            this.department = department;
            return this;
        }

        public Builder salary(double salary) {
            this.salary = salary;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder isPermanent(Boolean permanent) {
            isPermanent = permanent;
            return this;
        }

        public Builder shirtSize(String shirtSize) {
            this.shirtSize = shirtSize;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", isMarried=" + isMarried +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", city='" + city + '\'' +
                ", isPermanent=" + isPermanent +
                ", shirtSize='" + shirtSize + '\'' +
                '}';
    }
}

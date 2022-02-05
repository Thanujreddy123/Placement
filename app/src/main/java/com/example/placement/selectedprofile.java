package com.example.placement;

public class selectedprofile {
    private String name,company,rollno,salary;

    public selectedprofile() {
    }

    public selectedprofile(String name, String rollno, String company, String salary) {
        this.name = name;
        this.company = company;
        this.rollno = rollno;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}

package com.example.placement;

public class Studentprofile {
    private String name,password,email,percentage,branch,placed;

    public Studentprofile() {

    }

    public Studentprofile(String name, String password, String email, String percentage, String branch, String placed) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.percentage = percentage;
        this.branch = branch;
        this.placed = placed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getPlaced() {
        return placed;
    }

    public void setPlaced(String placed) {
        this.placed = placed;
    }
}

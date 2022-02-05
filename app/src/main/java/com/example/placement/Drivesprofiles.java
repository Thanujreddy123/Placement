package com.example.placement;

public class Drivesprofiles
{
    String company,date,ctc,role,percentage;

    public Drivesprofiles(String company, String date, String ctc, String role, String percentage) {
        this.company = company;
        this.date = date;
        this.ctc = ctc;
        this.role = role;
        this.percentage = percentage;
    }

    public Drivesprofiles() {
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCtc() {
        return ctc;
    }

    public void setCtc(String ctc) {
        this.ctc = ctc;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}

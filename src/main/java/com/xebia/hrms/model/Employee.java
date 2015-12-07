package com.xebia.hrms.model;

import java.util.Date;

/**
 * Created by gurinder on 2/12/15.
 */
public class Employee {

    private String employeeId;
    private String name;
    private String department;
    private String employment_type;
    private Date DOB;
    private Date DOJ;
    private Date probationEndDate;
    private String designation;
    private String reportingManager;
    private String emailId;

    public Employee(String employeeId, String name, String department, String employment_type, Date DOB, Date DOJ, Date probationEndDate, String designation, String reportingManager, String emailId) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.employment_type = employment_type;
        this.DOB = DOB;
        this.DOJ = DOJ;
        this.probationEndDate = probationEndDate;
        this.designation = designation;
        this.reportingManager = reportingManager;
        this.emailId = emailId;
    }

    public Employee() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmployment_type() {
        return employment_type;
    }

    public void setEmployment_type(String employment_type) {
        this.employment_type = employment_type;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public Date getDOJ() {
        return DOJ;
    }

    public void setDOJ(Date DOJ) {
        this.DOJ = DOJ;
    }

    public Date getProbationEndDate() {
        return probationEndDate;
    }

    public void setProbationEndDate(Date probationEndDate) {
        this.probationEndDate = probationEndDate;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getReportingManager() {
        return reportingManager;
    }

    public void setReportingManager(String reportingManager) {
        this.reportingManager = reportingManager;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


}

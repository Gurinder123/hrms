package com.xebia.hrms.utility;

import com.xebia.hrms.model.Employee;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by gurinder on 8/12/15.
 */
public class EmployeeStatusChecker {

    public static boolean checkConfirmation(Employee emp) {
        Date empProbationEndDate = emp.getProbationEndDate();
        if (empProbationEndDate != null) {

            Date todaysDate = new Date();

            DateTime empProbationEndDateJoda = new DateTime(empProbationEndDate);
            DateTime todaysDateJoda = new DateTime(todaysDate);

            int empProbationEndDateMonth = empProbationEndDateJoda.getMonthOfYear();
            int empProbationEndDateYear = empProbationEndDateJoda.getYear();

            int currentDay = todaysDateJoda.getDayOfMonth();
            int currentMonth = todaysDateJoda.getMonthOfYear();
            int currentYear = todaysDateJoda.getYear();

            if (currentMonth == 12) {
                currentMonth = 0;
                currentYear++;
            }

            if (currentDay == 15 && empProbationEndDateMonth == currentMonth + 1 && empProbationEndDateYear == currentYear) {
                return true;
            }

        }
        return false;
    }

    public static int checkAnniversary(Employee emp) {
        Date empDoj = emp.getDOJ();
        if (empDoj != null) {
            Date todaysDate = new Date();

            DateTime empDojJoda = new DateTime(empDoj);
            DateTime todaysDateJoda = new DateTime(todaysDate);

            int empDojDay = empDojJoda.getDayOfMonth();
            int empDojMonth = empDojJoda.getMonthOfYear();

            int currentDay = todaysDateJoda.getDayOfMonth();
            int currentMonth = todaysDateJoda.getMonthOfYear();

            if (empDojDay == currentDay && currentMonth == empDojMonth) {
                return todaysDateJoda.getYear() - empDojJoda.getYear();
            }

        }

        return 0;
    }

    public static boolean checkBirthday(Employee emp) {
        Date empDob = emp.getDOB();
        if (empDob != null) {
            Date todaysDate = new Date();

            DateTime empDobJoda = new DateTime(empDob);
            DateTime todaysDateJoda = new DateTime(todaysDate);

            int empDobDay = empDobJoda.getDayOfMonth();
            int empDobMonth = empDobJoda.getMonthOfYear();

            int currentDay = todaysDateJoda.getDayOfMonth();
            int currentMonth = todaysDateJoda.getMonthOfYear();

            if (empDobDay == currentDay && currentMonth == empDobMonth) {
                return true;
            }
        }
        return false;
    }
}

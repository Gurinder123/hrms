package com.xebia.hrms.utility;

import com.xebia.hrms.constant.Constant;
import com.xebia.hrms.model.Employee;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by gurinder on 2/12/15.
 */

@Component
public class ExcelParser {


    private static final Logger logger = Logger.getLogger(ExcelParser.class);

    public Map<Employee, List> read() {

        Map<Employee, List> map = new HashMap<>();

        try {

            FileInputStream file = new FileInputStream(new File(Constant.XLSX_FILE_LOCATION));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            int count = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                Iterator<Cell> cellIterator = row.cellIterator();
                Employee emp = new Employee();
                boolean birthDay = false;
                int anniversary = 0;
                boolean confirmation = false;
                if (count >= 2) {
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();

                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC:
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    if (cell.getColumnIndex() == 5) {
                                        emp.setDOB(cell.getDateCellValue());
                                    } else if (cell.getColumnIndex() == 6) {
                                        emp.setDOJ(cell.getDateCellValue());
                                    } else if (cell.getColumnIndex() == 7) {
                                        emp.setProbationEndDate(cell.getDateCellValue());
                                    }
                                }
                                break;
                            case Cell.CELL_TYPE_STRING:
                                if (cell.getColumnIndex() == 2) {
                                    emp.setName(cell.getStringCellValue());
                                } else if (cell.getColumnIndex() == 10) {
                                    emp.setEmailId(cell.getStringCellValue());
                                } else if (cell.getColumnIndex() == 9) {
                                    emp.setReportingManager(cell.getStringCellValue());
                                }
                                break;
                        }
                    }
                }
                count++;

                birthDay = EmployeeStatusChecker.checkBirthday(emp);
                anniversary = EmployeeStatusChecker.checkAnniversary(emp);
                confirmation = EmployeeStatusChecker.checkConfirmation(emp);

                if (birthDay) {
                    List<String> list = new ArrayList<>();
                    list.add("birthday");
                    map.put(emp, list);

                }

                if (anniversary != 0) {
                    if (!map.containsKey(emp)) {
                        List<String> list = new ArrayList<>();
                        list.add("anniversary " + anniversary);
                        map.put(emp, list);
                    } else {
                        List list = map.get(emp);
                        list.add("anniversary");
                    }
                }

                if (confirmation) {
                    if (!map.containsKey(emp)) {
                        List<String> list = new ArrayList<>();
                        list.add("confirmation");
                        map.put(emp, list);
                    } else {
                        List list = map.get(emp);
                        list.add("confirmation");
                    }
                }

                file.close();
            }
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }

        return map;
    }


}
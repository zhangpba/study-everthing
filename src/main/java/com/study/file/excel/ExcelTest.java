package com.study.file.excel;

import com.study.file.PathUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析excel
 * 2020-06-31
 */
public class ExcelTest {


    /**
     * <p>
     * 1.import org.apache.poi.ss.usermodel.Workbook,对应Excel文档；
     * <p>
     * 　　2.import org.apache.poi.hssf.usermodel.HSSFWorkbook，对应xls格式的Excel文档；
     * <p>
     * 　　3.import org.apache.poi.xssf.usermodel.XSSFWorkbook，对应xlsx格式的Excel文档；
     * <p>
     * 　　4.import org.apache.poi.ss.usermodel.Sheet，对应Excel文档中的一个sheet；
     * <p>
     * 　　5.import org.apache.poi.ss.usermodel.Row，对应一个sheet中的一行；
     * <p>
     * 　　6.import org.apache.poi.ss.usermodel.Cell，对应一个单元格。
     */

    public static void main(String[] args) {


        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;
        List<Map<String, String>> list = null;
        String cellData = null;
        // String filePath = "D:\\test.xlsx";
        String filePath = getPathAndFile();
        String columns[] = {"name", "age", "score"};


        wb = readExcel(filePath);
        if (wb != null) {
            // 存放表中数据
            list = new ArrayList<Map<String, String>>();
            // 获取第一个sheet
            sheet = wb.getSheetAt(0);
            // 获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            // 获取第一行
            row = sheet.getRow(0);
            // 获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 0; i < rownum; i++) {
                Map<String, String> map = new LinkedHashMap<String, String>();
                row = sheet.getRow(i);
                if (row != null) {
                    for (int j = 0; j < colnum; j++) {
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        map.put(columns[j], cellData);
                    }
                } else {
                    break;
                }
                list.add(map);
            }
        }


        // 遍历出来的list
        for (Map<String, String> map : list) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue() + ",");
            }

            System.out.println("-------------------------------------------");
        }

    }

    // 获取excel的位置
    private static String getPathAndFile() {
        String filePath = PathUtil.getPackagePath() + "excel" + System.getProperty("file.separator") + "test.xlsx";

        System.out.println(filePath);

        return filePath;
    }


    //读取excel
    public static Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                return wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                return wb = new XSSFWorkbook(is);
            } else {
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public static Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {
            //判断cell类型
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA: {
                    //判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    } else {
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }

}

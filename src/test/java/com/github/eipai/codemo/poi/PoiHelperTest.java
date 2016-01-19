package com.github.eipai.codemo.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class PoiHelperTest {

    //@Test
    public void testPrint() throws Exception {
        String f = "test/2-1.xls";
        Workbook wb = PoiHelper.getWorkbook(f, false);
        Sheet sheet = wb.getSheetAt(0);

        String[][] values = new String[5][37];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 37; j++) {
                values[i][j] = PoiHelper.getObjectValue(sheet, 2 + i, j) + "";
                System.out.print(values[i][j] + ";   ");
            }
            System.out.println();
        }
        FileOutputStream fileOut = new FileOutputStream(f);
        wb.write(fileOut);
        fileOut.close();
    }

    //@Test
    public void testCreate() throws Exception {
        String f = "tmp_" + new Date().getTime() + ".xls";
        // String f = "tmp_" + new Date().getTime() + ".xlsx";
        Workbook wb = PoiHelper.getWorkbook(f, true);
        wb.createSheet();
        Sheet sheet = wb.getSheetAt(0);
        PoiHelper.setValue(sheet, 2, 1, "some", null);
        PoiHelper.setValue(sheet, 2, 2, new Date(), new PoiStyle(wb).setDataFormat(wb, "yyyy-MM-dd HH:mm:ss"));

        FileOutputStream fileOut = new FileOutputStream(f);
        wb.write(fileOut);
        fileOut.close();
    }

    // @Test
    public void testUpdate() throws Exception {
        // String f = "any.xlsx";
        String f = "any.xls";
        Workbook wb = PoiHelper.getWorkbook(f, false);
        Sheet sheet = wb.getSheetAt(0);
        PoiHelper.setValue(sheet, 1, 1, new Date(), new PoiStyle(wb).setDataFormat(wb, "yyyy-MM-dd HH:mm:ss"));

        FileOutputStream fileOut = new FileOutputStream(f);
        wb.write(fileOut);
        fileOut.close();
    }

    // @Test
    public void testRefreshFormulas() throws Exception {
        // File f = new File("formulas.xls");
        // FileInputStream fi = new FileInputStream(f);
        Workbook wb = PoiHelper.getWorkbook("formulas.xlsx", false);
        // XSSFWorkbook wb = new XSSFWorkbook(fi);
        Sheet sheet = wb.getSheetAt(0);
        System.out.println(PoiHelper.getString(sheet, 1, 1));
        PoiHelper.setValue(sheet, 1, 1, "234", null);
        sheet = wb.getSheetAt(1);
        PoiHelper.refreshFormulas(sheet);
        FileOutputStream foStream = new FileOutputStream(new File("formulas.xlsx"));
        wb.write(foStream);
    }

    // @Test
    public void testGetValue() throws IOException {
        File f = new File("demo.xls");
        FileInputStream fi = new FileInputStream(f);
        HSSFWorkbook wb = new HSSFWorkbook(fi);
        // XSSFWorkbook wb = new XSSFWorkbook(fi);
        HSSFSheet sheet = wb.getSheetAt(0);
        System.out.println(PoiHelper.getString(sheet, 1, 1));
        PoiHelper.setValue(sheet, 1, 1, "234", null);
        sheet = wb.getSheetAt(1);
        PoiHelper.setFormula(sheet, 3, 2, "Sheet1!b2", null);
        FileOutputStream foStream = new FileOutputStream(f);
        wb.write(foStream);
        wb.close();
    }

    @Test
    public void testAll() throws IOException {
        String f = "TEMP-" + new Date().getTime() + ".xlsx";
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet();
        PoiHelper.setValue(sheet, 8, 0, 1.23D, new PoiStyle(wb).setBorder(HSSFCellStyle.BORDER_THIN));
        PoiHelper.setValue(sheet, 8, 2, new Date(), new PoiStyle(wb).setBorder(HSSFCellStyle.BORDER_THIN));
        PoiHelper.setValue(sheet, 8, 4, 123456789, new PoiStyle(wb).setBorder(HSSFCellStyle.BORDER_THIN));
        PoiHelper.setValue(sheet, 10, 0, 1.23D, new PoiStyle(wb).setBorder(HSSFCellStyle.BORDER_THIN).setDataFormat(wb, "000.000"));
        PoiHelper.setValue(sheet, 10, 2, new Date(), new PoiStyle(wb).setBorder(HSSFCellStyle.BORDER_THIN).setDataFormat(wb, "yy-M"));
        PoiHelper.setValue(sheet, 10, 4, 123456789, new PoiStyle(wb).setBorder(HSSFCellStyle.BORDER_THIN).setDataFormat(wb, "#,###"));

        PoiHelper.setValue(sheet, 0, 0, "OK", new PoiStyle(wb).setAlignmentHorizontal(HSSFCellStyle.ALIGN_LEFT).setFontFamily("Courier New").setFontSize(400).setFontWeight(HSSFFont.BOLDWEIGHT_BOLD));
        PoiHelper.setValue(sheet, 1, 1, "color", new PoiStyle(wb).setFontColor(HSSFColor.LIGHT_BLUE.index));
        PoiHelper.setValue(sheet, 1, 2, "OK", new PoiStyle(wb).setBorderTop(HSSFCellStyle.BORDER_THIN));
        PoiHelper.setValue(sheet, 2, 2, "OK", new PoiStyle(wb).setBorder(HSSFCellStyle.BORDER_THIN, HSSFCellStyle.BORDER_DASHED, HSSFCellStyle.BORDER_THICK, HSSFCellStyle.BORDER_DASH_DOT_DOT));
        PoiHelper.setValue(sheet, 2, 0, "OK", new PoiStyle(wb).setBorderTop(HSSFCellStyle.BORDER_THIN).setBorderWeight(HSSFFont.BOLDWEIGHT_BOLD));
        PoiHelper.setValue(sheet, 2, 1, "OK", new PoiStyle(wb).setBorderLeft(HSSFCellStyle.BORDER_THIN).setBorderWeight(HSSFFont.BOLDWEIGHT_NORMAL));
        PoiHelper.setValue(sheet, 4, 0, "OK", new PoiStyle(wb).setBorderTop(HSSFCellStyle.BORDER_THIN).setBorderTopColor(HSSFColor.RED.index));
        PoiHelper.setValue(sheet, 4, 2, "OK",
                new PoiStyle(wb).setBorder(HSSFCellStyle.BORDER_THIN, HSSFCellStyle.BORDER_DASHED, HSSFCellStyle.BORDER_THICK, HSSFCellStyle.BORDER_DASH_DOT_DOT).setBorderColor(HSSFColor.RED.index));
        PoiHelper.setValue(sheet, 4, 4, "OK", new PoiStyle(wb).setFillPattern(HSSFCellStyle.BIG_SPOTS).setBackgroundColor(HSSFColor.RED.index));
        PoiHelper.setValue(sheet, 4, 6, "OK", new PoiStyle(wb).setFillPattern(HSSFCellStyle.BIG_SPOTS).setForegroundColor(HSSFColor.LIGHT_BLUE.index));
        PoiHelper.setValue(sheet, 4, 8, "OK", new PoiStyle(wb).setFillPattern(HSSFCellStyle.BIG_SPOTS).setForegroundColor(HSSFColor.RED.index).setBackgroundColor(HSSFColor.LIGHT_BLUE.index));
        PoiHelper.setValue(sheet, 6, 0, "OK", new PoiStyle(wb).setFillPattern(HSSFCellStyle.SOLID_FOREGROUND).setForegroundColor(new HSSFColor.RED().getIndex()));

        PoiHelper.merge(sheet, 12, 13, 3, 7);
        PoiHelper.merge(sheet, 15, 16, 3, 8, "Table Header");
        PoiHelper.merge(sheet, 18, 19, 3, 8, "Table Header",
                new PoiStyle(wb).setAlignmentHorizontal(HSSFCellStyle.ALIGN_CENTER).setBorder(HSSFCellStyle.BORDER_THIN).setFontSize(300).setFontWeight(HSSFFont.BOLDWEIGHT_BOLD));

        PoiHelper.setValue(sheet, 21, 1, "OK", new PoiStyle(wb).setFillPattern(HSSFCellStyle.SOLID_FOREGROUND).setForegroundColor(HSSFColor.RED.index).setBackgroundColor(HSSFColor.LIGHT_BLUE.index));
        PoiHelper.setFormula(sheet, 23, 1, "3*4",
                new PoiStyle(wb).setFillPattern(HSSFCellStyle.SOLID_FOREGROUND).setForegroundColor(HSSFColor.RED.index).setBackgroundColor(HSSFColor.LIGHT_BLUE.index));

        PoiHelper.setValue(sheet, 25, 3, "some height", new PoiStyle(wb).setBorder(HSSFCellStyle.BORDER_THIN));
        PoiHelper.setRowHeight(sheet, 25, 500);
        PoiHelper.setRowHeight(sheet, new int[] { 27, 29 }, 500);
        PoiHelper.setRowHeight(sheet, 35, 45, 500);

        PoiHelper.setStyle(sheet, 47, 48, 2, 3, new PoiStyle(wb).setBorder(HSSFCellStyle.BORDER_THIN));

        File file = new File(f);
        System.out.println(file.getAbsoluteFile().getAbsolutePath());
        FileOutputStream foStream = new FileOutputStream(file);
        wb.write(foStream);
    }
}

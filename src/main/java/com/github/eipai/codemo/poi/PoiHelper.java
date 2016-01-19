package com.github.eipai.codemo.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiHelper {
    public static final String SUFFIX_XLS = "xls";
    public static final String SUFFIX_XLSX = "xlsx";

    public static Workbook getWorkbook(String file, boolean createIfNotExist) throws Exception {
        if (null == file || "".equals(file)) throw new Exception("File name cannot be empty.");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(URLDecoder.decode(file, "UTF-8")));
            fis.close();
            return getWorkbookFromFile(file);
        } catch (FileNotFoundException e) {
            if (!createIfNotExist) throw e;
            Workbook wb = getWorkbookByCreate(file);
            FileOutputStream fileOut = new FileOutputStream(file);
            wb.write(fileOut);
            fileOut.close();
            return getWorkbookFromFile(file);
        }
    }

    private static Workbook getWorkbookFromFile(String file) throws Exception {
        if (file.endsWith(SUFFIX_XLS)) return new HSSFWorkbook(new FileInputStream(file));
        else if (file.endsWith(SUFFIX_XLSX)) return new XSSFWorkbook(new FileInputStream(file));
        else throw new Exception("File name '" + file + "' must end with '.xls' or '.xlsx'.");
    }

    private static Workbook getWorkbookByCreate(String file) throws Exception {
        if (file.endsWith(SUFFIX_XLS)) return new HSSFWorkbook();
        else if (file.endsWith(SUFFIX_XLSX)) return new XSSFWorkbook();
        else throw new Exception("File name '" + file + "' must end with '.xls' or '.xlsx'.");
    }

    public static void refreshFormulas(Sheet sheet) {
        if (null == sheet) return;
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            if (null == sheet.getRow(i)) continue;
            refreshFormulas(sheet, i, i, 0, sheet.getRow(i).getLastCellNum());
        }
    }

    public static void refreshFormulas(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        for (int i = firstRow; i <= lastRow; i++) {
            for (int j = firstCol; j <= lastCol; j++) {
                refreshFormulas(sheet, i, j);
            }
        }
    }

    public static void refreshFormulas(Sheet sheet, int row, int column) {
        Cell cell = getCellFromSheetByCell(sheet, row, column);
        if (cell != null) {
            if (Cell.CELL_TYPE_FORMULA == cell.getCellType()) {
                cell.setCellFormula(cell.getCellFormula());
            }
        }
    }

    public static Object getObjectValue(Sheet sheet, int row, int column) {
        Object s = null;
        if (null == s) s = getBoolean(sheet, row, column);
        if (null == s) s = getDate(sheet, row, column);
        if (null == s) s = getDouble(sheet, row, column);
        if (null == s) s = getInteger(sheet, row, column);
        if (null == s) s = getString(sheet, row, column);
        if (null == s) s = getFormula(sheet, row, column);
        return (null == s) ? null : s;
    }

    public static String getFormula(Sheet sheet, int row, int column) {
        Cell cell = getCellFromSheetByCell(sheet, row, column);
        if (null == cell) return null;
        if (Cell.CELL_TYPE_FORMULA != cell.getCellType()) return null;
        return cell.getCellFormula();
    }

    public static Boolean getBoolean(Sheet sheet, int row, int column) {
        Cell cell = getCellFromSheetByCell(sheet, row, column);
        if (null == cell) return null;
        if (Cell.CELL_TYPE_BOOLEAN != cell.getCellType()) return null;
        return cell.getBooleanCellValue();
    }

    public static Date getDate(Sheet sheet, int row, int column) {
        Cell cell = getCellFromSheetByCell(sheet, row, column);
        if (null == cell) return null;
        if (Cell.CELL_TYPE_NUMERIC != cell.getCellType()) return null;
        if (!DateUtil.isCellDateFormatted(cell)) return null;
        return cell.getDateCellValue();
    }

    public static Double getDouble(Sheet sheet, int row, int column) {
        Cell cell = getCellFromSheetByCell(sheet, row, column);
        if (null == cell) return null;
        if (Cell.CELL_TYPE_NUMERIC != cell.getCellType()) return null;
        Double d = cell.getNumericCellValue();
        return d;
    }

    public static Integer getInteger(Sheet sheet, int row, int column) {
        Cell cell = getCellFromSheetByCell(sheet, row, column);
        if (null == cell) return null;
        if (Cell.CELL_TYPE_NUMERIC != cell.getCellType()) return null;
        Double d = cell.getNumericCellValue();
        //if (null == d) return null;
        return d.intValue();
    }

    public static String getString(Sheet sheet, int row, int column) {
        Cell cell = getCellFromSheetByCell(sheet, row, column);
        if (null == cell) return null;
        if (Cell.CELL_TYPE_STRING != cell.getCellType()) return null;
        return cell.getStringCellValue();
    }

    public static void setStyle(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol, PoiStyle style) {
        for (int i = firstRow; i <= lastRow; i++) {
            for (int j = firstCol; j <= lastCol; j++) {
                Cell c = getCellFromSheetByCell(sheet, i, j);
                c.setCellStyle(style.getCellstyle());
            }
        }
    }

    /**
     * set the row's height or set to ff (-1) for undefined/default-height. Set
     * the height in "twips" or 1/20th of a point.
     * 
     * @param sheet
     * @param row
     * @param height
     */
    public static void setRowHeight(Sheet sheet, int row, int height) {
        Row r = getRowFromSheetByRow(sheet, row);
        r.setHeight((short) height);
    }

    /**
     * set the row's height or set to ff (-1) for undefined/default-height. Set
     * the height in "twips" or 1/20th of a point.
     * 
     * @param sheet
     * @param row
     * @param height
     */
    public static void setRowHeight(Sheet sheet, int[] row, int height) {
        for (int i = 0; i < row.length; i++)
            setRowHeight(sheet, row[i], height);
    }

    /**
     * set the row's height or set to ff (-1) for undefined/default-height. Set
     * the height in "twips" or 1/20th of a point.
     * 
     * @param sheet
     * @param firstRow
     * @param lastRow
     * @param height
     */
    public static void setRowHeight(Sheet sheet, int firstRow, int lastRow, int height) {
        for (int i = firstRow; i <= lastRow; i++)
            setRowHeight(sheet, i, height);
    }

    public static void merge(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }

    public static void merge(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol, String value) {
        for (int i = firstRow; i <= lastRow; i++) {
            for (int j = firstCol; j <= lastCol; j++) {
                Cell c = getCellFromSheetByCell(sheet, i, j);
                if (null != value) c.setCellValue(value);
            }
        }
        merge(sheet, firstRow, lastRow, firstCol, lastCol);
    }

    public static void merge(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol, PoiStyle style) {
        for (int i = firstRow; i <= lastRow; i++) {
            for (int j = firstCol; j <= lastCol; j++) {
                Cell c = getCellFromSheetByCell(sheet, i, j);
                if (style instanceof PoiStyle) c.setCellStyle(style.getCellstyle());
            }
        }
        merge(sheet, firstRow, lastRow, firstCol, lastCol);
    }

    public static void merge(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol, String value, PoiStyle style) {
        for (int i = firstRow; i <= lastRow; i++) {
            for (int j = firstCol; j <= lastCol; j++) {
                Cell c = getCellFromSheetByCell(sheet, i, j);
                if (null != value) c.setCellValue(value);
                if (style instanceof PoiStyle) c.setCellStyle(style.getCellstyle());
            }
        }
        merge(sheet, firstRow, lastRow, firstCol, lastCol);
    }

    public static final void setValue(Sheet sheet, int row, int col, Integer value, PoiStyle style) {
        Cell c = getCellFromSheetByCell(sheet, row, col);
        if (null != value) c.setCellValue(value);
        if (style instanceof PoiStyle) c.setCellStyle(style.getCellstyle());
    }

    public static final void setValue(Sheet sheet, int row, int col, Long value, PoiStyle style) {
        Cell c = getCellFromSheetByCell(sheet, row, col);
        if (null != value) c.setCellValue(value);
        if (style instanceof PoiStyle) c.setCellStyle(style.getCellstyle());
    }

    public static final void setValue(Sheet sheet, int row, int col, Date value, PoiStyle style) {
        Cell c = getCellFromSheetByCell(sheet, row, col);
        if (null != value) c.setCellValue(value);
        if (style instanceof PoiStyle) c.setCellStyle(style.getCellstyle());
    }

    public static final void setValue(Sheet sheet, int row, int col, Float value, PoiStyle style) {
        Cell c = getCellFromSheetByCell(sheet, row, col);
        if (null != value) c.setCellValue(value);
        if (style instanceof PoiStyle) c.setCellStyle(style.getCellstyle());
    }

    public static final void setValue(Sheet sheet, int row, int col, Double value, PoiStyle style) {
        Cell c = getCellFromSheetByCell(sheet, row, col);
        if (null != value) c.setCellValue(value);
        if (style instanceof PoiStyle) c.setCellStyle(style.getCellstyle());
    }

    public static final void setValue(Sheet sheet, int row, int col, String value, PoiStyle style) {
        Cell c = getCellFromSheetByCell(sheet, row, col);
        if (null != value) c.setCellValue(value);
        if (style instanceof PoiStyle) c.setCellStyle(style.getCellstyle());
    }

    public static final void setFormula(Sheet sheet, int row, int col, String formula, PoiStyle style) {
        Cell c = getCellFromSheetByCell(sheet, row, col);
        if (null != formula) c.setCellFormula(formula);
        if (style instanceof PoiStyle) c.setCellStyle(style.getCellstyle());
    }

    public static Cell getCellFromSheetByCell(Sheet sheet, int row, int cell) {
        Row r = getRowFromSheetByRow(sheet, row);
        return r.getCell(cell, Row.CREATE_NULL_AS_BLANK);
        // Cell hCell = r.getCell(cell);
        // if (hCell == null) {
        // hCell = r.createCell(cell);
        // }
        // return hCell;
    }

    public static Row getRowFromSheetByRow(Sheet sheet, int row) {
        if (sheet == null) return null;
        Row hRow = sheet.getRow(row);
        if (hRow == null) {
            hRow = sheet.createRow(row);
        }
        return hRow;
    }

    /**
     * @param column
     * @return Get column title from column index. e.g: 0->A, 1->B, ..., 26->AA,
     *         149->ET, ...702->AAA ...
     * @NOTE column should be smaller than ~1100,0000.
     */
    public static String getColumnTitle(final int column) {
        int col = column + 1;
        int system = 26;
        char[] digArr = new char[10];
        int ind = 0;
        while (col > 0) {
            int mod = col % system;
            if (mod == 0) mod = system;
            digArr[ind++] = dig2Char(mod);
            col = (col - 1) / 26;
        }
        StringBuffer bf = new StringBuffer(ind);
        for (int i = ind - 1; i >= 0; i--) {
            bf.append(digArr[i]);
        }
        return bf.toString();
    }

    private static char dig2Char(final int dig) {
        int acs = dig - 1 + 'A';
        return (char) acs;
    }
}

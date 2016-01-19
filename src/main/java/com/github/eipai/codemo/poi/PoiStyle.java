package com.github.eipai.codemo.poi;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

public class PoiStyle {
    private Workbook workbook;
    private CellStyle cellstyle;
    private Font font;

    public PoiStyle(Workbook workbook) {
        super();
        this.setWorkbook(workbook);
        this.setCellstyle(this.getWorkbook().createCellStyle());
        this.setFont(this.getWorkbook().createFont());
    }

    /**
     * @param workbook
     * @param format
     * @return
     */
    public PoiStyle setDataFormat(Workbook workbook, String format) {
        this.cellstyle.setDataFormat(workbook.createDataFormat().getFormat(format));
        return this;
    }

    /**
     * @param pattern
     *            e.g.: CellStyle.SOLID_FOREGROUND
     * @return
     */
    public PoiStyle setFillPattern(short pattern) {
        this.cellstyle.setFillPattern(pattern);
        return this;
    }

    /**
     * @param color
     *            e.g.: HSSFColor.RED.index
     * @note !IMPORTANT. You MUST call setFillPattern before setBackgroundColor
     *       to take effect.
     */
    public PoiStyle setBackgroundColor(short color) {
        this.cellstyle.setFillBackgroundColor(color);
        return this;
    }

    /**
     * @param color
     *            e.g.: HSSFColor.RED.index
     * @return
     */
    public PoiStyle setForegroundColor(short color) {
        this.cellstyle.setFillForegroundColor(color);
        return this;
    }

    /**
     * @param color
     *            e.g.: HSSFColor.RED.index
     * @return
     */
    public PoiStyle setBorderColor(short color) {
        this.cellstyle.setTopBorderColor(color);
        this.cellstyle.setLeftBorderColor(color);
        this.cellstyle.setBottomBorderColor(color);
        this.cellstyle.setRightBorderColor(color);
        return this;
    }

    /**
     * @param color
     *            e.g.: HSSFColor.RED.index
     * @return
     */
    public PoiStyle setBorderTopColor(short color) {
        this.cellstyle.setTopBorderColor(color);
        return this;
    }

    /**
     * @param color
     *            e.g.: HSSFColor.RED.index
     * @return
     */
    public PoiStyle setBorderLeftColor(short color) {
        this.cellstyle.setLeftBorderColor(color);
        return this;
    }

    /**
     * @param color
     *            e.g.: HSSFColor.RED.index
     * @return
     */
    public PoiStyle setBorderBottomColor(short color) {
        this.cellstyle.setBottomBorderColor(color);
        return this;
    }

    /**
     * @param color
     *            e.g.: HSSFColor.RED.index
     * @return
     */
    public PoiStyle setBorderRightColor(short color) {
        this.cellstyle.setRightBorderColor(color);
        return this;
    }

    /**
     * @param boldweight
     *            e.g.: Font.BOLDWEIGHT_BOLD
     * @note Do not take effect. Maybe something wrong.
     */
    public PoiStyle setBorderWeight(short boldweight) {
        font.setBoldweight(boldweight);
        this.cellstyle.setFont(font);
        return this;
    }

    /**
     * @param top
     *            e.g.: CellStyle.BORDER_NONE ...
     * @param left
     *            e.g.: ditto
     * @param bottom
     *            e.g.: ditto
     * @param right
     *            e.g.: ditto
     * @return
     */
    public PoiStyle setBorder(short top, short left, short bottom, short right) {
        this.cellstyle.setBorderTop(top);
        this.cellstyle.setBorderBottom(bottom);
        this.cellstyle.setBorderLeft(left);
        this.cellstyle.setBorderRight(right);
        return this;
    }

    /**
     * @param border
     *            e.g.: CellStyle.BORDER_THIN
     * @return
     */
    public PoiStyle setBorder(short border) {
        this.cellstyle.setBorderTop(border);
        this.cellstyle.setBorderBottom(border);
        this.cellstyle.setBorderLeft(border);
        this.cellstyle.setBorderRight(border);
        return this;
    }

    /**
     * @param border
     *            e.g.: CellStyle.BORDER_THIN
     * @return
     */
    public PoiStyle setBorderTop(short border) {
        this.cellstyle.setBorderTop(border);
        return this;
    }

    /**
     * @param border
     *            e.g.: CellStyle.BORDER_THIN
     * @return
     */
    public PoiStyle setBorderBottom(short border) {
        this.cellstyle.setBorderBottom(border);
        return this;
    }

    /**
     * @param border
     *            e.g.: CellStyle.BORDER_THIN
     * @return
     */
    public PoiStyle setBorderLeft(short border) {
        this.cellstyle.setBorderLeft(border);
        return this;
    }

    /**
     * @param border
     *            e.g.: CellStyle.BORDER_THIN
     * @return
     */
    public PoiStyle setBorderRight(short border) {
        this.cellstyle.setBorderRight(border);
        return this;
    }

    public PoiStyle setFontFamily(String fontFamily) {
        font.setFontName(fontFamily);
        this.cellstyle.setFont(font);
        return this;
    }

    public PoiStyle setFontSize(int fontSize) {
        font.setFontHeight((short) fontSize);
        this.cellstyle.setFont(font);
        return this;
    }

    /**
     * @param fontWeight
     *            e.g.: Font.BOLDWEIGHT_BOLD
     * @return
     */
    public PoiStyle setFontWeight(short fontWeight) {
        font.setBoldweight(fontWeight);
        this.cellstyle.setFont(font);
        return this;
    }

    public PoiStyle setFontItalic(boolean b) {
        font.setItalic(b);
        this.cellstyle.setFont(font);
        return this;
    }

    /**
     * @param color
     *            e.g.: HSSFColor.RED.index
     * @return
     */
    public PoiStyle setFontColor(short color) {
        font.setColor(color);
        this.cellstyle.setFont(font);
        return this;
    }

    /**
     * @param align
     *            e.g.: CellStyle.ALIGN_LEFT
     * @return
     */
    public PoiStyle setAlignmentHorizontal(short align) {
        this.cellstyle.setAlignment(align);
        return this;
    }

    /**
     * @param align
     *            e.g.: CellStyle.VERTICAL_TOP
     * @return
     */
    public PoiStyle setAlignmentVertical(short align) {
        this.cellstyle.setVerticalAlignment(align);
        return this;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    public CellStyle getCellstyle() {
        return cellstyle;
    }

    public void setCellstyle(CellStyle cellstyle) {
        this.cellstyle = cellstyle;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}

package com.wdd.test.comm.builder.excel;

import com.wdd.test.comm.config.ConfigContains;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 9:01 2018/9/18
 */
public abstract class Builder {
    //excel对象
    Excel excel;

    protected int rowNum = 1;

    /**
     * 实例excel对象
     *
     * @param headers 标题数据
     * @param datas   数据
     * @return bulider
     */
    public Builder excelInstance(List headers, List datas) {
        excel = new Excel(headers, datas);
        return this;
    }

    public Builder setDatas(List datas) {
        excel.setDatas(datas);
        return this;
    }


    /**
     * builder 工作簿
     *
     * @return
     */
    public Builder buildWorkBook() {
        excel.setWorkbook(new SXSSFWorkbook(10000));
        return this;
    }

    /**
     * builder Sheet
     *
     * @return
     */
    public Builder buildSheet() {
        excel.setSheet(excel.getWorkbook().createSheet());
        return this;
    }

    /**
     * builder 样式
     *
     * @return
     */
    public abstract Builder buildStyle();

    /**
     * builder 标题
     *
     * @return
     */
    public abstract Builder buildHeader();

    /**
     * builder 数据
     *
     * @return
     */
    public abstract Builder buildData();

    public int getRowNum() {
        return rowNum;
    }

    public Builder setRowNum(int rowNum) {
        this.rowNum = rowNum;
        return this;
    }

    /**
     * 得到excel对象
     *
     * @return
     */
    public abstract Excel getExcel();


    /**
     * 写入Excel到outputStream
     *
     * @param outputStream
     * @return
     * @throws IOException
     */
    public Builder write(OutputStream outputStream) throws IOException {
//		this.getExcel().getWorkbook().write(outputStream);
        SXSSFWorkbook sxssfWorkbook = (SXSSFWorkbook) this.getExcel().getWorkbook();
        sxssfWorkbook.write(outputStream);
        sxssfWorkbook.dispose();
        return this;
    }


    /**
     * 表头列样式
     *
     * @param workbook
     * @return
     */
    protected CellStyle getTitleStyle(Workbook workbook) {
        CellStyle titleStyle = workbook.createCellStyle();
        //HSSFCellStyle titleStyle = (HSSFCellStyle) workbook.createCellStyle();
        //字体
        Font titlefont = workbook.createFont();
        titlefont.setFontName("黑体");
        titlefont.setFontHeightInPoints((short) 12);
        titlefont.setColor(Font.SS_NONE);
        titleStyle.setFont(titlefont);
        //居中
//        titleStyle.setAlignment(CellStyle);
//        titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        //设置自动换行
        titleStyle.setWrapText(false);


        return titleStyle;
    }

    protected CellStyle getCellCommonStyle(Workbook workbook) {
        CellStyle contentStyle = workbook.createCellStyle();
       // contentStyle.setFillBackgroundColor(HSSFColor.RED.index);
        //字体
        Font titlefont = workbook.createFont();
        titlefont.setFontName("Arial");
        titlefont.setFontHeightInPoints((short) 10);
        contentStyle.setFont(titlefont);
        contentStyle.setWrapText(false);
        return contentStyle;
    }

    /**
     * 内容列样式
     *
     * @param workbook
     * @return
     */
    protected CellStyle getContentStyle(Workbook workbook) {
        CellStyle contentStyle = workbook.createCellStyle();
        createContentStyle(contentStyle, workbook);
        return contentStyle;
    }

    /**
     * 创建内容样式
     *
     * @param contentStyle
     * @param workbook
     */
    private void createContentStyle(CellStyle contentStyle, Workbook workbook) {
        //contentStyle.setFillBackgroundColor(HSSFColor.RED.index);
        //字体
        Font titlefont = workbook.createFont();
        titlefont.setFontName("Arial");
        titlefont.setFontHeightInPoints((short) 10);
        contentStyle.setFont(titlefont);

        DataFormat format = workbook.createDataFormat();
        contentStyle.setDataFormat(format.getFormat("@"));
        contentStyle.setWrapText(false);
    }

    /**
     * 数字格式样式
     *
     * @param workbook
     * @param format
     * @return
     */
    protected CellStyle getFormatStyle(Workbook workbook, String format) {
        CellStyle cellFormatStyle = workbook.createCellStyle();
        createFormatStyle(cellFormatStyle, workbook, format);
//        if(color!=-1){
//            cellFormatStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//            cellFormatStyle.setFillForegroundColor(color);
//        }
        return cellFormatStyle;
    }

    /**
     * 创建数据样式
     *
     * @param cellFormatStyle
     * @param workbook
     * @param format
     */
    protected void createFormatStyle(CellStyle cellFormatStyle, Workbook workbook, String format) {
        //字体
        Font titlefont = workbook.createFont();
        titlefont.setFontName("Arial");
        titlefont.setFontHeightInPoints((short) 10);
        cellFormatStyle.setFont(titlefont);
        DataFormat f1 = workbook.createDataFormat();
        cellFormatStyle.setDataFormat(f1.getFormat(format));
    }


    /**
     * 设置excel单元格 的值
     *
     * @param cell   单元格
     * @param value  值
     * @param format 类型
     */
    protected void setCellValue(Cell cell, String value, String format) {
        CellStyle cellStyle = excel.getCellStyle();
        //format表示为数字，需要格式化为保留两位小数点
        setCellValue(cell, value, format, cellStyle, excel.getCellFormatStyle());
    }

    protected void setCellValue(Cell cell, String value, String format, CellStyle cellStyle, CellStyle formatStyle) {
        if (StringUtils.equals(ConfigContains.FIELD_TYPE_NUMBER, format)) {//数字格式
            cell.setCellStyle(formatStyle);
            cell.setCellValue(Double.parseDouble(StringUtils.isBlank(value) ? "0" : value));
        } else if (StringUtils.equals(ConfigContains.FIELD_TYPE_INT, format)) {//数字格式
            cell.setCellStyle(excel.getCellCommonStyle());
            cell.setCellValue(Integer.parseInt(StringUtils.isBlank(value) ? "0" : value));
        } else if (StringUtils.equals(ConfigContains.FIELD_TYPE_DATE, format)) {//日期格式
            cell.setCellStyle(cellStyle);
            cell.setCellValue(value);
            cell.setCellType(CellType.STRING);
        } else {
            cell.setCellStyle(cellStyle);
            cell.setCellValue(value);
            cell.setCellType(CellType.STRING);
        }
    }
}

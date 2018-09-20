package com.wdd.test.comm.builder.excel;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 9:03 2018/9/18
 */
public class Excel {
    //工作簿
    protected Workbook workbook =null;
    //Sheet页
    protected Sheet sheet = null;
    //标题样式
    protected CellStyle titleStyle = null;
    //内容样式
    protected CellStyle cellStyle = null;
    //数字样式
    protected CellStyle cellFormatStyle = null;

    protected CellStyle cellCommonStyle = null;

    //数据
    protected List datas=null;
    //标题表头
    protected List headers = null;



    public Excel(List headers, List datas) {
        super();
        this.headers = headers;
        this.datas = datas;
    }
    public Workbook getWorkbook() {
        return workbook;
    }
    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }
    public Sheet getSheet() {
        return sheet;
    }
    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }
    public CellStyle getTitleStyle() {
        return titleStyle;
    }
    public void setTitleStyle(CellStyle titleStyle) {
        this.titleStyle = titleStyle;
    }
    public CellStyle getCellStyle() {
        return cellStyle;
    }
    public void setCellStyle(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }
    public CellStyle getCellFormatStyle() {
        return cellFormatStyle;
    }
    public void setCellFormatStyle(CellStyle cellFormatStyle) {
        this.cellFormatStyle = cellFormatStyle;
    }
    public List getDatas() {
        return datas;
    }
    public void setDatas(List datas) {
        this.datas = datas;
    }
    public List getHeaders() {
        return headers;
    }
    public void setHeaders(List headers) {
        this.headers = headers;
    }

    public CellStyle getCellCommonStyle() {
        return cellCommonStyle;
    }

    public Excel setCellCommonStyle(CellStyle cellCommonStyle) {
        this.cellCommonStyle = cellCommonStyle;
        return this;
    }
}

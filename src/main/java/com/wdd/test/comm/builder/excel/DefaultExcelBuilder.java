package com.wdd.test.comm.builder.excel;

import com.wdd.test.bean.ReportField;
import com.wdd.test.comm.util.ReadValueUtil;
import com.wdd.test.comm.util.ReflectHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;
import java.util.Map;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 9:07 2018/9/18
 */
public class DefaultExcelBuilder extends Builder {
    /**
     * builder 样式
     *
     * @return
     */
    @Override
    public Builder buildStyle() {
        excel.setTitleStyle(getTitleStyle(excel.getWorkbook()));
        excel.setCellStyle(getContentStyle(excel.getWorkbook()));
        excel.setCellFormatStyle(getFormatStyle(excel.getWorkbook(), "#,##0.00"));
        excel.setCellCommonStyle(getCellCommonStyle(excel.getWorkbook()));
        return this;
    }

    /**
     * builder 标题
     *
     * @return
     */
    @Override
    public Builder buildHeader() {
        Sheet sheet = excel.getSheet();
        Row row = sheet.createRow(0);
        List<ReportField> reportFieldList = excel.getHeaders();
        this.writeHeader(sheet,row,reportFieldList);
        return this;
    }

    /**
     * builder 数据
     *
     * @return
     */
    @Override
    public Builder buildData() {
        Sheet sheet = excel.getSheet();
        List list = excel.getDatas();
        List<ReportField> reportFieldList = excel.getHeaders();
        int len = list.size();
        //Map<String,String> dicMap = DataCache.getInstance().getDicNameOfTypeAndCode();
        for (int i = 0; i < len; i++,rowNum++) {
            writeData(reportFieldList, list.get(i), sheet.createRow(rowNum),null);
        }
        return this;
    }

    /**
     * 得到excel对象
     *
     * @return
     */
    @Override
    public Excel getExcel() {
        return excel;
    }

    /**
     * 写 excel 数据
     * @param reportFieldList 字段列表
     * @param obj 数据对象
     * @param row Excel雪茹行
     */
    protected void writeData(List<ReportField> reportFieldList, Object obj, Row row,Map<String,String> dicMap) {
        for (int i = 0; i < reportFieldList.size(); i++) {
            ReportField reportField = reportFieldList.get(i);
            Cell cell = row.createCell(i);
            String code = reportField.getFieldCode();
            String value = ReadValueUtil.getValue(ReflectHelper.getValueByGetMethod(obj, code));
            if (cell != null) {
                if (StringUtils.isNotBlank(reportField.getDictType())) {
                    //value = dicMap.getOrDefault(reportField.getDictType()+DataCache.CODE_KEY+value,value);
                }
                setCellValue(cell, value, reportField.getFormat());
            }
        }
    }

    /**
     * 写header数据
     * @param sheet
     * @param row
     * @param reportFieldList
     */
    protected void writeHeader(Sheet sheet, Row row, List<ReportField> reportFieldList) {
        for (int i = 0; i < reportFieldList.size(); i++) {
            ReportField reportField = reportFieldList.get(i);
            Cell cell = row.createCell(i);
            cell.setCellStyle(excel.getTitleStyle());
            HSSFRichTextString text = new HSSFRichTextString(reportField.getFieldName());
            cell.setCellValue(text);
            sheet.setColumnWidth(i, reportField.getWidth());
        }
    }
}

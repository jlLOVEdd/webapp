package com.wdd.test.comm.builder.excel;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 9:08 2018/9/18
 */
public class Director {

    /**
     * 构造Excel
     *
     * @param builder
     * @param headers
     * @param datas
     */
    public Director construct(Builder builder, List headers, List datas) {
        builder.excelInstance(headers, datas)
                .buildWorkBook()
                .buildSheet()
                .buildStyle()
                .buildHeader()
                .buildData();
        return this;
    }
    /**
     * 写入Excel 到流
     *
     * @param builder
     * @param os
     * @return
     * @throws IOException
     */
    public Director write(Builder builder, OutputStream os) throws IOException {
        SXSSFWorkbook sxssfWorkbook = (SXSSFWorkbook) builder.getExcel().getWorkbook();
        sxssfWorkbook.write(os);
        sxssfWorkbook.dispose();
        return this;
    }

    public Director construct(Builder builder, List headers, List datas, OutputStream os) throws IOException {
        this.construct(builder, headers, datas);
        this.write(builder, os);
        return this;
    }

}

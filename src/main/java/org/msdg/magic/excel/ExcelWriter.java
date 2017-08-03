package org.msdg.magic.excel;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.msdg.magic.excel.goods.ExcelException;
import org.msdg.magic.excel.goods.FieldColumn;
import org.msdg.magic.excel.tips.Excel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * Excel处理器
 * 用于导出
 * Created by mw4157 on 16/2/17.
 */
public class ExcelWriter {

    private Logger logger = LoggerFactory.getLogger(ExcelWriter.class);

    // 转化器, list数据与workbook之间的转化
    private ExcelConvertor excelConvertor;
    // 嗅探器, 发现实体类上的注解
    private Sniffer sniffer;

    // 单例起来
    private ExcelWriter() {
        excelConvertor = new ExcelConvertor();
        sniffer = new Sniffer();
    }

    private static class ExcelSingle {
        private static ExcelWriter instance = new ExcelWriter();
    }

    public static ExcelWriter instance() {
        return ExcelSingle.instance;
    }

    /**
     * 针对只参与一张excel导出的实体,可使用此方法
     * 即实体上只有一个@Excel注解
     *
     * @param response
     * @param data       将要导出的数据
     * @param modelClass 数据的实体类型信息, 需要使用@Excel
     * @param <T>        实体泛型
     */
    public <T> void exportToExcel(HttpServletResponse response, List<T> data, Class<T> modelClass) {
        exportToExcel(response, data, modelClass, null);
    }

    /**
     * 导出Excel报表
     *
     * @param response
     * @param data       将要导出的数据
     * @param modelClass 实体类型信息
     * @param fileName   导出后的文件名, 用于在一个实体参与多张excel导出时识别本次导出哪一张
     *                   如果实体只参与一张excel导出, 可填null提高效率; @Excel
     * @param <T>        实体泛型
     */
    public <T> void exportToExcel(HttpServletResponse response, List<T> data, Class<T> modelClass, String fileName) {

        // 找到导出文件的信息@Excel
        Excel excelAnnotation = sniffer.findExcel(modelClass, fileName);
        if (excelAnnotation.limit() < data.size()) {
            throw new ExcelException("导出数据数量超出最大限制,最大限制为:" + excelAnnotation.limit() + "条");
        }

        // 找到导出列的信息 @Column
        List<FieldColumn> fieldColumnList = sniffer.findFieldColumns(modelClass, fileName);

        // 文件名转码,如果发生意外就用当前毫秒数当文件名
        String encodingName = String.valueOf(System.currentTimeMillis());
        try {
            encodingName = URLEncoder.encode(fileName != null ? fileName : excelAnnotation.value()[0], "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.warn("导出excel时,文件名转码失败, 文件名:{}", fileName);
        }

        // 配置response
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + encodingName + ".xlsx");

        // 创建Covertor
        SXSSFWorkbook workbook = excelConvertor.createExcel(data, fieldColumnList);

        // 输出excel
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.flush();
            workbook.write(outputStream);
        } catch (IOException e) {
            logger.error("导出Excel异常 -- {}", e.getMessage());
        } finally {
            try {
                if (null != outputStream) {
                    outputStream.close();
                }
            } catch (IOException e) {
                logger.error("导出时关闭资源出错.{}", e.getMessage());
            }

            workbook.dispose();

            try {
                response.flushBuffer();
            } catch (IOException e) {
                logger.error("导出时关闭资源出错.{}", e.getMessage());
            }
        }
    }

    public <T> void exportToExcel(List<T> data, Class<T> modelClass, String outPath, String fileName) {
        // 找到导出文件的信息@Excel
        Excel excelAnnotation = sniffer.findExcel(modelClass, fileName);
        if (excelAnnotation.limit() < data.size()) {
            throw new ExcelException("导出数据数量超出最大限制,最大限制为:" + excelAnnotation.limit() + "条");
        }

        // 找到导出列的信息 @Column
        List<FieldColumn> fieldColumnList = sniffer.findFieldColumns(modelClass, fileName);

        // 文件名转码,如果发生意外就用当前毫秒数当文件名
        outPath += File.separator + (fileName != null ? fileName : excelAnnotation.value()[0]) + ".xlsx";

        // 创建Covertor
        SXSSFWorkbook workbook = excelConvertor.createExcel(data, fieldColumnList);

        // 输出excel
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(outPath));
            workbook.write(outputStream);
        } catch (IOException e) {
            logger.error("导出Excel异常 -- {}", e.getMessage());
        } finally {
            try {
                if (null != outputStream) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                logger.error("导出时关闭资源出错.{}", e.getMessage());
            }

            workbook.dispose();
        }
    }
}

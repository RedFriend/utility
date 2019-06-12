package com.phy.utility.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * PDF工具类
 *
 * @author penghongyou
 */
public class PDFUtils {


    /**
     * 多个图片合并成PDF
     * Gif,Jpeg,Png,Tif
     *
     * @param imagePaths 图片路径
     * @param destPath   生成PDF文件路径
     */
    public static void imagesToPdf(String[] imagePaths, String destPath) {
        try {
            // 第一步：创建一个document对象。
            Document document = new Document();
            document.setMargins(0, 0, 0, 0);
            // 第二步：
            // 创建一个PdfWriter实例，
            PdfWriter.getInstance(document, new FileOutputStream(destPath));
            // 第三步：打开文档。
            document.open();
            // 第四步：在文档中增加图片。
            for (String imagePath : imagePaths) {
                Image img = Image.getInstance(imagePath);
                img.setAlignment(Image.ALIGN_CENTER);
                // 根据图片大小设置页面，一定要先设置页面，再newPage（），否则无效
                document.setPageSize(new Rectangle(img.getWidth(), img
                        .getHeight()));
                document.newPage();
                document.add(img);
            }
            // 第五步：关闭文档。
            document.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PDFUtils.imagesToPdf("C:\\1.tif,C:\\2.tif,C:\\3.tif,C:\\4.tif,C:\\5.tif,C:\\6.tif,C:\\7.tif,C:\\8.tif,C:\\9.tif,C:\\10.tif".split(","), "C:\\Users\\pengh\\Pictures\\MyerSplash\\img2Pdf.pdf");
    }

    /**
     * 获取PDF中的文本内容
     *
     * @param pdfFile    PDF文件
     * @param pageNumber 获取的页面
     * @return
     */
    public String getPageContent(File pdfFile, Integer pageNumber) {
        String content = "";
        PdfReader reader = null;
        try {
            reader = new PdfReader(new FileInputStream(pdfFile));
            content = PdfTextExtractor.getTextFromPage(reader, pageNumber);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return content;
    }
}

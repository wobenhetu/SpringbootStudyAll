package com.dfw.easypoi.utils;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
public class WordPdfUtils {
    protected static final Logger logger = LoggerFactory.getLogger(WordPdfUtils.class);
    public static void main(String[] args) throws Exception{
        String filepath = "D:/testword.docx";
        String outpath = "D:/test.pdf";
        InputStream source = new FileInputStream(filepath);
        OutputStream target = new FileOutputStream(outpath);
        Map<String, String> params = new HashMap<String, String>();
        PdfOptions options = PdfOptions.create();
        wordConverterToPdf(source, target, options, params);
    }

    /**
     * 将word文档， 转换成pdf, 中间替换掉变量
     * @param source 源为word文档， 必须为docx文档
     * @param target 目标输出
     * @param params 需要替换的变量
     * @throws Exception
     */
    public static void wordConverterToPdf(InputStream source, OutputStream target, Map<String, String> params) throws Exception {
       wordConverterToPdf(source, target, null, params);
    }
    public static void wordConverter2003ToPdf(InputStream source, OutputStream target,
                                              PdfOptions options,
                                              Map<String, String> params) throws Exception {
        POIFSFileSystem fs = null;
        Document document = new Document();
        try {
            fs = new POIFSFileSystem(source);
            HWPFDocument doc = new HWPFDocument(fs);
            WordExtractor we = new WordExtractor(doc);
            PdfWriter writer = PdfWriter.getInstance(document, target);
            Range range = doc.getRange();
            document.open();
            writer.setPageEmpty(true);
            document.newPage();
            String[] paragraphs = we.getParagraphText();
            BaseFont baseFont = BaseFont.createFont("C:/windows/fonts/simsun.ttc,1", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
            Font font = new Font(baseFont, 14, Font.NORMAL);
            for (int i = 0; i < paragraphs.length; i++) {
                org.apache.poi.hwpf.usermodel.Paragraph pr = range.getParagraph(i);
                // CharacterRun run = pr.getCharacterRun(i);
                // run.setBold(true);
                // run.setCapitalized(true);
                // run.setItalic(true);
                paragraphs[i] = paragraphs[i].replaceAll("\\cM?\r?\n", "");
                System.out.println("Length:" + paragraphs[i].length());
                System.out.println("Paragraph" + i + ": " + paragraphs[i].toString());

                // add the paragraph to the document
                document.add(new Paragraph(paragraphs[i],font));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            document.close();
        }

    }
    /**
     * 将word文档， 转换成pdf, 中间替换掉变量
     * @param source 源为word文档， 必须为docx文档
     * @param target 目标输出
     * @param params 需要替换的变量
     * @param options PdfOptions.create().fontEncoding( "windows-1250" ) 或者其他
     * @throws Exception
     */
    public static void wordConverterToPdf(InputStream source, OutputStream target,
                                          PdfOptions options,
                                          Map<String, String> params) throws Exception {
        XWPFDocument doc = new XWPFDocument(source);
        /*paragraphReplace(doc.getParagraphs(), params);

        for (XWPFTable table : doc.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    paragraphReplace(cell.getParagraphs(), params);
                }
            }
        }*/
        PdfConverter.getInstance().convert(doc, target, options);
    }
    public static void searchAndReplace(String srcPath, String destPath,Map<String, String> map) {
        try {
            OPCPackage pack = POIXMLDocument.openPackage(srcPath);
            XWPFDocument doc = new XWPFDocument(pack);
            List<XWPFParagraph> paragraphs = doc.getParagraphs();
            for (XWPFParagraph tmp : paragraphs) {
                //System.out.println(tmp.getParagraphText());
                List<XWPFRun> runs = tmp.getRuns();
                for (XWPFRun r : runs) {
                    System.out.println("XWPFRun-Text:" + r.getText(0));
                    for (Entry<String, String> e : map.entrySet()) {
                        if (r.getText(0).equals(e.getKey())) {
                            r.setText(map.get(r.getText(0)),0);
                        }
                    }


                }
            }

            FileOutputStream fos = new FileOutputStream(destPath);
            doc.write(fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /** 替换段落中内容 */
    private static void paragraphReplace(List<XWPFParagraph> paragraphs, Map<String, String> params) {
        if (MapUtils.isNotEmpty(params)) {
            for (XWPFParagraph p : paragraphs){
                for (XWPFRun r : p.getRuns()){
                    String content = r.getText(r.getTextPosition());
                    logger.info(content);
                    if(StringUtils.isNotEmpty(content) && params.containsKey(content)) {
                        r.setText(params.get(content), 0);
                    }
                }
            }
        }
    }

}
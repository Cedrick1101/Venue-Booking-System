package com.example.labbooking.document;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;


@Service
public class DocumentGenerator2 {

    public String htmlToPdf(String processedHtml){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try{

            PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);

            DefaultFontProvider defaultFontProvider = new DefaultFontProvider(false,true,false);
            ConverterProperties converterProperties = new ConverterProperties();

            converterProperties.setFontProvider(defaultFontProvider);


            HtmlConverter.convertToPdf(processedHtml, pdfWriter,converterProperties);

            FileOutputStream fileOutputStream = new FileOutputStream("/Users/acer/Documents/Officer_Report/daily_bookings.pdf");

            byteArrayOutputStream.writeTo(fileOutputStream);
            byteArrayOutputStream.close();

            byteArrayOutputStream.flush();
            fileOutputStream.close();


            return null;


        } catch (Exception ex) {

            //exception occurred
        }

        return null;
    }



}

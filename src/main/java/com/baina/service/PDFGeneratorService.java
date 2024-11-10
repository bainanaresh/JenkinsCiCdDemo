package com.baina.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PDFGeneratorService {

    public byte[] generatePdfWithMultiplePages(List<String[]> tableData) throws IOException {
        PDDocument document = new PDDocument();
        
        try {
            int recordsPerPage = 25;  // Number of records per page
            float margin = 50;
            float tableHeight = 20f;
            float cellMargin = 5f;

            int numberOfColumns = tableData.get(0).length;
            float pageWidth =  PDRectangle.A4.getWidth();
            float tableWidth = pageWidth - 2 * margin;
            float columnWidth = tableWidth / numberOfColumns;
            int numberOfPages = (int) Math.ceil(tableData.size() / (double) recordsPerPage);

            int dataIndex = 0;
            for (int i = 0; i < numberOfPages; i++) {
                // Create a new page
                PDPage page = new PDPage();
                document.addPage(page);

                // Create a content stream to write to the page
                PDPageContentStream contentStream = new PDPageContentStream(document, page);

                // Set the font for the table content
                contentStream.setFont(PDType1Font.HELVETICA, 12);

                // Starting Y position for writing records
                float yPosition = page.getMediaBox().getHeight() - margin;

                // Draw the table header on each page
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                float xPosition = margin;
                for (String header : tableData.get(0)) {
                    contentStream.newLineAtOffset(xPosition + cellMargin, yPosition - 15);
                    contentStream.showText(header);
                    xPosition += columnWidth;
                }
                contentStream.endText();

                yPosition -= tableHeight;  // Move position down for rows

                // Write records for this page (100 records or less per page)
                for (int j = 0; j < recordsPerPage && dataIndex < tableData.size(); j++) {
                    String[] row = tableData.get(dataIndex++);
                    xPosition = margin;

                    // Draw row borders and text
                    for (String cell : row) {
                        contentStream.beginText();
                        contentStream.newLineAtOffset(xPosition + cellMargin, yPosition - 15);
                        contentStream.showText(cell);
                        contentStream.endText();

                        // Draw cell lines
                        contentStream.moveTo(xPosition, yPosition);
                        contentStream.lineTo(xPosition, yPosition - tableHeight);
                        contentStream.stroke();
                        xPosition += columnWidth;
                    }
                    // Draw bottom line of the row
                    contentStream.moveTo(margin, yPosition - tableHeight);
                    contentStream.lineTo(margin + tableWidth, yPosition - tableHeight);
                    contentStream.stroke();

                    yPosition -= tableHeight;  // Move position down for the next row
                }

                // Close the content stream for this page
                contentStream.close();
            }

            // Write the document to a byte array output stream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            return outputStream.toByteArray();

        } finally {
            document.close();
        }
    }
}


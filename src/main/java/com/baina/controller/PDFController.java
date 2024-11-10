package com.baina.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baina.service.PDFGeneratorService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PDFController {

    private final PDFGeneratorService pdfGeneratorService;

    public PDFController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping("/generate-pdf-with-multiple-pages")
    public ResponseEntity<byte[]> generatePdfWithMultiplePages() {
        try {
            // Sample data for the table (1000 records, with header row)
            List<String[]> tableData = new ArrayList<>();
            tableData.add(new String[]{"ID", "Name", "Role"});  // Header row
            for (int i = 1; i <= 1000; i++) {
                tableData.add(new String[]{String.valueOf(i), "User " + i, "Role " + i});
            }

            // Generate the PDF with multiple pages
            byte[] pdfBytes = pdfGeneratorService.generatePdfWithMultiplePages(tableData);

            // Set headers to indicate that this is a PDF file
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("inline", "multi-page-table.pdf");

            // Return the PDF as a response
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.garby.csv.service;

import com.garby.csv.model.InvoiceRecord;
import com.garby.csv.repository.InvoiceRepository;
import com.opencsv.CSVReader;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class FileUploadService {

    private final InvoiceRepository invoiceRepository;

    public FileUploadService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public String processFile(MultipartFile file) throws Exception {
        String filename = file.getOriginalFilename();

        if (filename == null) throw new IllegalArgumentException("Filename is missing!");
        if (filename.endsWith(".csv")) {
            processCsv(file);
        } else if (filename.endsWith(".xlsx") || filename.endsWith(".xls")) {
            processExcel(file);
        } else {
            throw new IllegalArgumentException("Unsupported file type. Only CSV or Excel files allowed.");
        }

        return "File " + filename + " uploaded and data saved successfully.";
    }

    private void processCsv(MultipartFile file) throws Exception {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
             CSVReader csvReader = new CSVReader(reader)) {

            String[] header = csvReader.readNext(); // skip header
            String[] row;

            while ((row = csvReader.readNext()) != null) {
                InvoiceRecord record = new InvoiceRecord();
                record.setInvoiceNumber(row[0]);
                record.setPayerName(row[1]);
                record.setPayerTIN(row[2]);
                record.setServiceDescription(row[3]);
                record.setAmount(Double.parseDouble(row[4]));
                record.setCurrency(row[5]);
                record.setIssueDate(row[6]);
                record.setDueDate(row[7]);
                record.setPaymentStatus(row[8]);

                invoiceRepository.save(record);
            }
        }
    }

    private void processExcel(MultipartFile file) throws Exception {
        try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0); // First sheet
            Iterator<Row> rows = sheet.iterator();

            // Skip header
            if (rows.hasNext()) rows.next();

            while (rows.hasNext()) {
                Row row = rows.next();
                InvoiceRecord record = new InvoiceRecord();

                record.setInvoiceNumber(getCellValue(row.getCell(0)));
                record.setPayerName(getCellValue(row.getCell(1)));
                record.setPayerTIN(getCellValue(row.getCell(2)));
                record.setServiceDescription(getCellValue(row.getCell(3)));
                record.setAmount(Double.parseDouble(getCellValue(row.getCell(4))));
                record.setCurrency(getCellValue(row.getCell(5)));
                record.setIssueDate(getCellValue(row.getCell(6)));
                record.setDueDate(getCellValue(row.getCell(7)));
                record.setPaymentStatus(getCellValue(row.getCell(8)));

                invoiceRepository.save(record);
            }
        }
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            default -> "";
        };
    }
}

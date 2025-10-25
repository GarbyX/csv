package com.garby.csv.model;

import jakarta.persistence.*;
// import lombok.Data;

// @Data
@Entity
@Table(name = "invoice_records")
public class InvoiceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String invoiceNumber;
    private String payerName;
    private String payerTIN;
    private String serviceDescription;
    private Double amount;
    private String currency;
    private String issueDate;
    private String dueDate;
    private String paymentStatus;


    public InvoiceRecord() {
    }

    public InvoiceRecord(Long id, String invoiceNumber, String payerName, String payerTIN, String serviceDescription, Double amount, String currency, String issueDate, String dueDate, String paymentStatus) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.payerName = payerName;
        this.payerTIN = payerTIN;
        this.serviceDescription = serviceDescription;
        this.amount = amount;
        this.currency = currency;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.paymentStatus = paymentStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerTIN() {
        return payerTIN;
    }

    public void setPayerTIN(String payerTIN) {
        this.payerTIN = payerTIN;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}


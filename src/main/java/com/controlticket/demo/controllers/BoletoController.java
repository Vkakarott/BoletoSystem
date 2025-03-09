package com.controlticket.demo.controllers;

import com.controlticket.demo.models.Ticket;
import com.controlticket.demo.services.TicketService;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/boleto")
public class BoletoController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/gerar")
    public ResponseEntity<byte[]> gerarBoleto(@RequestParam("id") Long id) {
        try {
            Optional<Ticket> tickOptional = ticketService.findTicketById(id);
            if (!tickOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            Ticket ticket = tickOptional.get();

            DecimalFormat valorFormatado = new DecimalFormat("###,##0.00");
            String valorBoleto = "R$ " + valorFormatado.format(ticket.getValue());

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataVencimento = ticket.getDueDate();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            document.add(new Paragraph("BOLETO BANCÁRIO").setBold().setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("==================================="));

            document.add(new Paragraph("Pagador: " + ticket.getPayMaster().getName()));
            document.add(new Paragraph("CPF: " + ticket.getPayMaster().getCpf()));

            document.add(new Paragraph("==================================="));

            document.add(new Paragraph("Banco: " + ticket.getBeneficiary().getName()));
            document.add(new Paragraph("Agência: " + ticket.getBeneficiary().getId() + " Conta: " + ticket.getBeneficiary().getId()));
            document.add(new Paragraph("Valor: " + valorBoleto));
            document.add(new Paragraph("Vencimento: " + dataVencimento.format(dateFormatter)));
            
            document.add(new Paragraph("Linha Digitável: " + ticket.getCode()));

            document.add(new Paragraph("==================================="));

            document.close();

            byte[] pdfBytes = outputStream.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=boleto.pdf");
            headers.add("Content-Type", "application/pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

package com.controlticket.demo.controllers;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/boleto")
public class BoletoController {

    @GetMapping("/gerar")
    public ResponseEntity<byte[]> gerarBoleto() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            // Cabeçalho do Boleto
            document.add(new Paragraph("BOLETO BANCÁRIO"));
            document.add(new Paragraph("==================================="));

            // Informações do pagador
            document.add(new Paragraph("Pagador: João da Silva"));
            document.add(new Paragraph("CPF: 123.456.789-00"));
            document.add(new Paragraph("Endereço: Rua Exemplo, 123 - Cidade/UF"));

            document.add(new Paragraph("==================================="));

            // Informações do boleto
            document.add(new Paragraph("Banco: Banco Exemplo S.A."));
            document.add(new Paragraph("Agência: 1234-5  Conta: 67890-1"));
            document.add(new Paragraph("Valor: R$ 250,00"));
            document.add(new Paragraph("Vencimento: 28/02/2025"));
            document.add(new Paragraph("Linha Digitável: 12345.67890 12345.678901 12345.678901 5 12345678901234"));

            document.add(new Paragraph("==================================="));
            document.add(new Paragraph("Este é um boleto fictício para fins de demonstração."));

            document.close();

            // Retornar o PDF como resposta HTTP
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

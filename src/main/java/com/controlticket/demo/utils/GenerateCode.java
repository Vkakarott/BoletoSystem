package com.controlticket.demo.utils;

import java.time.LocalDate;

public class GenerateCode {

    public static String generateLine(String bankCode, Long id, LocalDate dueDate, double value) {
        StringBuilder line = new StringBuilder();

        String paddedId = String.format("%012d", id);

        String field1 = bankCode + "9" + paddedId.toString();

        String field2 = paddedId.substring(4, 7) + paddedId.substring(7, 10);

        String field3 = paddedId.substring(10, 12);

        String field4 = formatDueDate(dueDate) + formatValue(value);

        String checkDigit = calculateCheckDigit(field1 + field2 + field3 + field4);

        line.append(field1).append(" ").append(field2).append(" ").append(field3).append(" ").append(field4).append(" ").append(checkDigit);

        return line.toString();
    }
    
    private static String formatDueDate(LocalDate dueDate) {
        return String.format("%02d%02d", dueDate.getDayOfMonth(), dueDate.getMonthValue());
    }

    private static String formatValue(double value) {
        int valueInCents = (int) (value * 100); 
        return String.format("%010d", valueInCents); 
    }

    private static String calculateCheckDigit(String fields) {
        int sum = 0;
        int weight = 2; 
        for (int i = fields.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(fields.charAt(i));
            sum += digit * weight;
            weight = (weight == 2) ? 1 : 2;
        }
        
        int remainder = sum % 10;
        int checkDigit = (remainder == 0) ? 0 : 10 - remainder;
        
        return String.valueOf(checkDigit);
    }
}

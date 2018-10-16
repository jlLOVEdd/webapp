package test;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Date {
    public static void main(String[] args) {
        /*Instant instant = Instant.now();
        instant.plus(1, ChronoUnit.DAYS);
        System.out.println(instant);
        LocalDate birthDate = LocalDate.of(1992, 05, 04);
        LocalDate current = LocalDate.now();
        System.out.println(current.minus(1,ChronoUnit.DAYS));
        MonthDay monthDay = MonthDay.from(birthDate);
        MonthDay currentDay = MonthDay.from(current);
        if (monthDay.equals(currentDay)) {
            System.out.println("this is you birthday");
        }*/

        LocalDateTime localDateTime = LocalDateTime.parse(LocalDateTime.now().toString(),DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(localDateTime);


        String testDate = "2018-07-05";
        String test = LocalDate.parse(testDate, DateTimeFormatter.ISO_DATE).plusDays(1).toString();
        System.out.println(test);

    }


}
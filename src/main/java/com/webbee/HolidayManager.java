package com.webbee;

import java.time.*;
import java.util.HashSet;
import java.util.Set;

public class HolidayManager {

    public static final Set<LocalDate> HOLIDAYS = new HashSet<>();

    static {
        HOLIDAYS.add(LocalDate.of(2024, 1, 1));
        HOLIDAYS.add(LocalDate.of(2024, 1, 2));
        HOLIDAYS.add(LocalDate.of(2024, 1, 3));
        HOLIDAYS.add(LocalDate.of(2024, 1, 4));
        HOLIDAYS.add(LocalDate.of(2024, 1, 5));
        HOLIDAYS.add(LocalDate.of(2024, 2, 23));
        HOLIDAYS.add(LocalDate.of(2024, 3, 8));
        HOLIDAYS.add(LocalDate.of(2024, 4, 29));
        HOLIDAYS.add(LocalDate.of(2024, 4, 30));
        HOLIDAYS.add(LocalDate.of(2024, 5, 1));
        HOLIDAYS.add(LocalDate.of(2024, 5, 9));
        HOLIDAYS.add(LocalDate.of(2024, 5, 10));
        HOLIDAYS.add(LocalDate.of(2024, 6, 12));
        HOLIDAYS.add(LocalDate.of(2024, 11, 4));
        HOLIDAYS.add(LocalDate.of(2024, 12, 30));
        HOLIDAYS.add(LocalDate.of(2024, 12, 31));
    }

    // 1st method
    public static boolean isHoliday(LocalDate date) {
        if (isNotWeekend(date)) {
            return HOLIDAYS.contains(date);
        } else return false;
    }

    // 2nd method
    public static boolean isHolidayByWorkSchedule(LocalDateTime dateTime) {
        ZoneId moscowZone = ZoneId.of("Europe/Moscow");
        return isHoliday(dateTime.toLocalDate()) ||
                dateTime.atZone(moscowZone).getDayOfWeek().getValue() >= 5 ||
                dateTime.toLocalTime().isBefore(LocalTime.of(9, 0)) ||
                dateTime.toLocalTime().isAfter(LocalTime.of(18, 0));
    }

    public static boolean isNotWeekend(LocalDate date) {
        if (!date.equals(LocalDate.of(2024, 4, 27)) || !date.equals(LocalDate.of(2024, 12, 28))) {
            DayOfWeek day = date.getDayOfWeek();
            return day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
        } else return true;
    }

    public static void main(String[] args) {
        System.out.println(isHoliday(LocalDate.of(2024, 6, 12))); // true
        System.out.println(isHoliday(LocalDate.of(2024, 6, 13))); // false
        System.out.println(isHolidayByWorkSchedule(LocalDateTime.of(2024, 6, 13, 15, 0))); // false
        System.out.println(isHolidayByWorkSchedule(LocalDateTime.of(2024, 6, 13, 20, 0))); // true
    }
}
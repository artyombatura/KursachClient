package Helpers.DateHelpers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.function.Predicate;
import java.util.stream.Stream;

import Constants.Constants;

public class DateHelpers {
    public static Integer getWorkHoursFromDateRange(LocalDate fromDate, LocalDate toDate) {
        Integer businessDays = (int) (long) DateHelpers.countBusinessDaysBetween(fromDate, toDate, Optional.empty());
        return businessDays * Constants.dailyWorkRate;
    }

    public static long countBusinessDaysBetween(LocalDate startDate, LocalDate endDate,
                                                 Optional<List<LocalDate>> holidays) {
        if (startDate == null || endDate == null || holidays == null) {
//            throw new IllegalArgumentException("Invalid method argument(s) to countBusinessDaysBetween(" + startDate
//                    + "," + endDate + "," + holidays + ")");
            return 0;
        }

        Predicate<LocalDate> isHoliday = date -> holidays.isPresent() ? holidays.get().contains(date) : false;

        Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        long businessDays = Stream.iterate(startDate, date -> date.plusDays(1)).limit(daysBetween)
                .filter(isHoliday.or(isWeekend).negate()).count();
        return businessDays;
    }
}

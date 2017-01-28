package ua.demitt.homework.hellogwthibernate.client;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PeriodTest {
    @Test
    public void testGetPeriod() throws Exception {
        //Given
        List<TimeAndPeriod> data = Arrays.asList(
            new TimeAndPeriod(6, Period.MORNING),
            new TimeAndPeriod(10, Period.MORNING),
            new TimeAndPeriod(11, Period.DAY),
            new TimeAndPeriod(12, Period.DAY),
            new TimeAndPeriod(16, Period.EVENING),
            new TimeAndPeriod(20, Period.EVENING),
            new TimeAndPeriod(21, Period.NIGHT),
            new TimeAndPeriod(23, Period.NIGHT),
            new TimeAndPeriod(0, Period.NIGHT),
            new TimeAndPeriod(2, Period.NIGHT)
        );
        int size = data.size();
        List<Period> result = new ArrayList<>(size);

        //When
        for (TimeAndPeriod current : data) {
            result.add( Period.getPeriod(current.getHours()) );
        }

        //Then
        for (int i = 0; i < size; i++) {
            assertEquals(
                "Invalid perid detection for hours: " + data.get(i).getHours(),
                data.get(i).getPeriod(),
                result.get(i)
            );
        }
    }


    private class TimeAndPeriod {
        private int hours;
        private Period period;

        public TimeAndPeriod(int hours, Period period) {
            this.hours = hours;
            this.period = period;
        }

        public int getHours() {
            return hours;
        }

        public Period getPeriod() {
            return period;
        }
    }
}
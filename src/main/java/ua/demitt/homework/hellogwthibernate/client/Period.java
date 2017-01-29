package ua.demitt.homework.hellogwthibernate.client;

import java.util.Arrays;
import java.util.List;

public enum Period {
    NIGHT(6),
    MORNING(11),
    DAY(16),
    EVENING(21)
    ;

    private int upperBound;
    private static List<Period> order;

    static {
        order = Arrays.asList(
            NIGHT, MORNING, DAY, EVENING
        );
    }

    Period(int upperBound) {
        this.upperBound = upperBound;
    }

    public static Period getPeriod(int hours) {

        for (Period period : order) {
            if ( hours < period.getUpperBound() ) {
                return period;
            }
        }
        return NIGHT;
    }

    private int getUpperBound() {
        return upperBound;
    }
}
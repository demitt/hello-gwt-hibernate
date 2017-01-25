package ua.demitt.homework.hellogwthibernate.client;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public enum Period {
    NIGHT(6),
    MORNING(11),
    DAY(16),
    EVENING(21)
    ;

    //private static final Logger LOG = LoggerFactory.getLogger("Period");

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
        //LOG.info("Искомое время " + hours + ", начинаем цикл по имеющимся промежуткам");

        for (Period period : order) {
            //LOG.info("Текущий промежуток = " + period);
            if ( hours < period.getUpperBound() ) {
                //LOG.info("Промежуток определен (" + period + "), завершаем цикл по имеющимся промежуткам");
                return period;
            }
        }
        return NIGHT;
    }

    private int getUpperBound() {
        return upperBound;
    }
}
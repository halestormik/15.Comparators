import java.util.Comparator;

public class TicketTimeComparator implements Comparator<Ticket> {

    @Override
    public int compare(Ticket t1, Ticket t2) {
        int flightTimeT1; // = t1.getTimeTo() - t1.getTimeFrom(); // время полета по первому рейсу
        int flightTimeT2; // = t2.getTimeTo() - t2.getTimeFrom(); // время полета по второму рейсу

        if (t1.getTimeFrom() < t1.getTimeTo()) { // проверка, что время вылета меньше времени пирилета (вылет и прилет в один день)
            flightTimeT1 = t1.getTimeTo() - t1.getTimeFrom();
        } else {                                   // время вылета больше времени прилета (вылет и прилет в разные дни)
            flightTimeT1 = t1.getTimeTo() + 24 * 100 - t1.getTimeFrom(); // тогда прибавляем сутки, умноженные на 100 (так как время в этой задаче - часы*100+минуты)
        }
        if (t2.getTimeFrom() < t2.getTimeTo()) {
            flightTimeT2 = t2.getTimeTo() - t2.getTimeFrom();
        } else {
            flightTimeT2 = t2.getTimeTo() + 24 * 100 - t2.getTimeFrom();
        }

        if (flightTimeT1 < flightTimeT2) {
            return -1;
        } else if (flightTimeT1 > flightTimeT2) {
            return 1;
        } else {
            return 0;
        }
    }
}
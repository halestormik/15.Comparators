import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class AviaSoulsTest {
    Ticket ticket1 = new Ticket("Москва", "Париж", 14870, 1420, 1822); // время полета 4 часа 2 минуты (402)
    Ticket ticket2 = new Ticket("Лиссабон", "Мадрид", 26520, 2100, 345); // время полета 6 часов 45 минут (645)
    Ticket ticket3 = new Ticket("Москва", "Париж", 8350, 1110, 1323); // время полета 2 часа 13 минут (213)
    Ticket ticket4 = new Ticket("Копенгаген", "Стамбул", 21980, 840, 1555); // время полета 7 часов 15 минут (715)
    Ticket ticket5 = new Ticket("Лондон", "Рим", 14430, 1610, 2055); // время полета 4 часа 45 минут (445)
    Ticket ticket6 = new Ticket("Москва", "Париж", 21980, 1016, 1418); //  время полета 4 часа 2 минуты (402)

    @Test
    public void shouldSearchOneTicket() { // поиск одного подходящего билета
        AviaSouls manager = new AviaSouls();

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);

        Ticket[] expected = {ticket5};
        Ticket[] actual = manager.search("Лондон", "Рим");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchZeroTickets() { // по результатам поиска нет ни одного подходящего билета
        AviaSouls manager = new AviaSouls();

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);

        Ticket[] expected = {};
        Ticket[] actual = manager.search("Лондон", "Варшава");

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldComparePricesIfPrice1AbovePrice2() { // сравнение цен на билеты, если цена одного больше цены другого
        int expected = 1;
        int actual = ticket1.compareTo(ticket3);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldComparePricesIfPrice1BelowPrice2() { // сравнение цен на билеты, если цена одного меньше цены другого
        int expected = -1;
        int actual = ticket5.compareTo(ticket4);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldComparePricesIfPrice1EqualsPrice2() { // сравнение цен на билеты, если цена одного равне цене другого
        int expected = 0;
        int actual = ticket6.compareTo(ticket4);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSortTicketsByIncreasePrices() { // поиск и сортировка по цене нескольких подходящих билетов
        AviaSouls manager = new AviaSouls();

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);

        Ticket[] expected = {ticket3, ticket1, ticket6};
        Ticket[] actual = manager.search("Москва", "Париж");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCompareIfTimeFlightT1AboveTimeFlightT2() { // сравнение билетов по времени перелета,
        TicketTimeComparator comparator = new TicketTimeComparator();

        int expected = 1;                                       // время перелета по билету 1 больше времени перелета по билету 2
        int actual = comparator.compare(ticket4, ticket2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCompareIfTimeFlightT1BelowTimeFlightT2() { // сравнение билетов по времени перелета,
        TicketTimeComparator comparator = new TicketTimeComparator();

        int expected = -1;                                       // время перелета по билету 1 меньше времени перелета по билету 2
        int actual = comparator.compare(ticket3, ticket5);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCompareIfTimeFlightT1EqualsTimeFlightT2() { // сравнение билетов по времени перелета,
        TicketTimeComparator comparator = new TicketTimeComparator();

        int expected = 0;                                       // время перелета по билету 1 равно времени перелета по билету 2
        int actual = comparator.compare(ticket1, ticket6);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSortByTimeFlight() { // сортировка массива билетов по возрастанию времени перелета
        AviaSouls manager = new AviaSouls();
        TicketTimeComparator comparator = new TicketTimeComparator();

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);

        Ticket[] expected = {ticket3, ticket1, ticket6};
        Ticket[] actual = manager.searchAndSortBy("Москва", "Париж", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }


}

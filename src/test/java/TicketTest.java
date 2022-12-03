import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketTest {

    TicketRepository repo = new TicketRepository();
    TicketManager manager = new TicketManager(repo);

    Ticket item1 = new Ticket(11, 60000, "DME", "LED", 100);
    Ticket item2 = new Ticket(22, 50000, "ТОХ", "CRZ", 200);
    Ticket item3 = new Ticket(333, 40000, "ТОХ", "CRZ", 300);
    Ticket item4 = new Ticket(4444, 30000, "ТОХ", "CRZ", 400);
    Ticket item5 = new Ticket(55555, 20000, "ТОХ", "CRZ", 500);
    Ticket item6 = new Ticket(666666, 10000, "ТОХ", "CRZ", 600);

    @BeforeEach
    public void Product() {
        repo.save(item1);
        repo.save(item2);
        repo.save(item3);
        repo.save(item4);
        repo.save(item5);
        repo.save(item6);
    }

    @Test
    public void RepositorySave() {

        Ticket[] expected = {item1, item2, item3, item4, item5, item6};
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void RepositoryRemoveById() {

        repo.removeById(22);

        Ticket[] expected = {item1, item3, item4, item5, item6};
        Ticket[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void ManagerSearchTicket() {

        Ticket[] actual = manager.searchBy("DME", "LED");
        Ticket[] expected = {item1};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void ManagerNothingFind() {

        Ticket[] actual = manager.searchBy("UKX", "UFA");
        Ticket[] expected = {};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void ManagerSort() {

        Ticket[] actual = manager.searchBy("ТОХ", "CRZ");
        Ticket[] expected = {item6, item5, item4, item3, item2};

        Assertions.assertArrayEquals(expected, actual);
    }
}


package be.vdab.fietsenjpa.services;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.EntityManager;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
//
//insert into artikels(naam, aankoopprijs, verkoopprijs)
//        values ('artikelTest', 15, 20);
@SuppressWarnings({"ConstantConditions", "unused"})
@DataJpaTest(showSql = false)
@Import(DefaultArtikelService.class)
@ComponentScan(value = "be.vdab.fietsenjpa.repositories", resourcePattern = "JpaArtikelRepository.class")
@Sql("/insertArtikel.sql")

public class DefaultArtikelServiceIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final static String ARTIKELS = "artikels";
    private final DefaultArtikelService service;
    private final EntityManager manager;

    public DefaultArtikelServiceIntegrationTest(DefaultArtikelService service, EntityManager manager) {
        this.service = service;
        this.manager = manager;
    }

    private long idVanTestArtikel() {
        return jdbcTemplate.queryForObject(
                "select id from artikels where naam = 'artikelTest'", Long.class);
    }

    @Test
    void verhoogVerkoopPrijs(){
        long id = idVanTestArtikel();
        service.verhoogVerkoopPrijs(id, BigDecimal.valueOf(100));
        manager.flush();
        assertThat(countRowsInTableWhere(ARTIKELS, "verkoopprijs = 120 and id = " + id)).isOne();
    }



}

























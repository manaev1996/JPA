package be.vdab.fietsenjpa.repositories;

import be.vdab.fietsenjpa.domain.Artikel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"ConstantConditions", "unused"})
@DataJpaTest
@Sql("/insertArtikel.sql")
@Import(JpaArtikelRepository.class)

class JpaArtikelRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    final JpaArtikelRepository repository;
    private static final String ARTIKELS = "artikels";


    public JpaArtikelRepositoryTest(JpaArtikelRepository repository) {
        this.repository = repository;
    }

    long idvantestArtikel(){
        return jdbcTemplate.queryForObject(
                "select id from artikels where naam = 'artikelTest'", Long.class);
    }

    @Test
    void findById(){
        assertThat(repository.findById(idvantestArtikel()))
                .hasValueSatisfying(
                        artikel -> assertThat(artikel.getNaam()).isEqualTo("artikelTest")
                );
    }

    @Test
    void createArtikel(){
        Artikel artikel = new Artikel("artikelCreeerder", BigDecimal.valueOf(10), BigDecimal.valueOf(26));
    }

    @Test
    void findByNaamContains(){
        assertThat(repository.findByNaamContains("es"))
                .hasSize(countRowsInTableWhere(ARTIKELS, "naam like '%es%'"))
                .extracting(Artikel::getNaam)
                .allSatisfy(naam -> assertThat(naam).containsIgnoringCase("es"))
                .isSortedAccordingTo(String::compareToIgnoreCase);

    }
//    @Test
//    void verhoogMetPercentage(){
//        assertThat(repository.verhoogMetPercentage(BigDecimal.TEN))
//                .isEqualTocountRowsInTable("artikels");
//        assertThat(countRowsInTableWhere(ARTIKELS, "verkoopprijs = 132 and id =" +
//                idvantestArtikel())).isOne();
//
//    }





}

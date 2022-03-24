import be.vdab.fietsenjpa.domain.Artikel;
import be.vdab.fietsenjpa.domain.ArtikelGroep;
import be.vdab.fietsenjpa.domain.FoodArtikel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.math.BigDecimal;

class ArtikelTest {
    private Artikel artikel;
    private ArtikelGroep groep1, groep2;

    @BeforeEach
    void beforeEach(){
        groep1 = new ArtikelGroep("test");
        groep2 = new ArtikelGroep("test2");
        artikel = new FoodArtikel("test", BigDecimal.ONE, BigDecimal.TEN, 1, groep1);
    }

    @Test
    void verhoogVerkoopPrijs(){
        artikel.verhoogVerkoopPrijs(BigDecimal.ONE);
        assertThat(artikel.getVerkoopprijs()).isEqualByComparingTo(BigDecimal.valueOf(21));
    }
    @Test
    void verhoogVerkoopPrijsMetNull(){
        assertThatNullPointerException().isThrownBy(() -> artikel.verhoogVerkoopPrijs(null));

    }
    @Test
    void groep1EnArtikelZijnVerbonden() {
        assertThat(groep1.getArtikels()).containsOnly(artikel);
        assertThat(artikel.getArtikelGroep()).isEqualTo(groep1);
    }
    @Test
    void artikelVerhuistNaarGroep2() {
        artikel.setArtikelGroep(groep2);
        assertThat(groep1.getArtikels()).doesNotContain(artikel);
        assertThat(groep2.getArtikels()).containsOnly(artikel);
    }
    @Test
    void nullAlsArtikelGroepInDeConstructorMislukt() {
        assertThatNullPointerException().isThrownBy(
                () -> new FoodArtikel("test", BigDecimal.ONE, BigDecimal.ONE, 1, null));
    }
    @Test
    void nullAlsArtikelGroepInDeSetterMislukt() {
        assertThatNullPointerException().isThrownBy(
                ()-> artikel.setArtikelGroep(null));
    }
}

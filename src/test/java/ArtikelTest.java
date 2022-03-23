import be.vdab.fietsenjpa.domain.Artikel;
import be.vdab.fietsenjpa.domain.FoodArtikel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.math.BigDecimal;

class ArtikelTest {
    private Artikel artikel;

    @BeforeEach
    void beforeEach(){
        artikel = new FoodArtikel("test", BigDecimal.ONE, BigDecimal.TEN, 1);
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
}

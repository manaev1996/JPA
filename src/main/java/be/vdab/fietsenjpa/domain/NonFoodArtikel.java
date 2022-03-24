package be.vdab.fietsenjpa.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
@Entity
@DiscriminatorValue("NF")
public class NonFoodArtikel extends Artikel{
    int garantie;

    public NonFoodArtikel(String naam, BigDecimal aankoopprijs,
                          BigDecimal verkoopprijs, int garantie, ArtikelGroep artikelGroep) {
        super(naam, aankoopprijs, verkoopprijs, artikelGroep);
        this.garantie = garantie;
    }

    public NonFoodArtikel(int garantie) {
        this.garantie = garantie;
    }

    protected NonFoodArtikel(){}

    public int getGarantie() {
        return garantie;
    }
}

package be.vdab.fietsenjpa.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
@Entity
@DiscriminatorValue("F")
public class FoodArtikel extends Artikel{
    int houdbaarheid;

    public FoodArtikel(String naam, BigDecimal aankoopprijs,
                       BigDecimal verkoopprijs, int houdbaarheid, ArtikelGroep artikelGroep) {
        super(naam, aankoopprijs, verkoopprijs, artikelGroep);
        this.houdbaarheid = houdbaarheid;
    }

    public FoodArtikel(int houdbaarheid) {
        this.houdbaarheid = houdbaarheid;
    }
    protected FoodArtikel(){}

    public int getHoudbaarheid() {
        return houdbaarheid;
    }
}

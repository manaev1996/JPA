package be.vdab.fietsenjpa.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
@Access(AccessType.FIELD)
@Table(name = "kortingen")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Korting {

    int vanafAantal;
    BigDecimal percentage;

    public Korting( int vanafAantal, BigDecimal percentage) {
        this.vanafAantal = vanafAantal;
        this.percentage = percentage;
    }
    protected Korting(){}



    public int getVanafAantal() {
        return vanafAantal;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Korting){
            Korting andereKorting = (Korting) object;
            return vanafAantal == andereKorting.vanafAantal;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return vanafAantal;
    }
}

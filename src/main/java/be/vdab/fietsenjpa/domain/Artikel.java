package be.vdab.fietsenjpa.domain;


import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "artikels")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "soort")

public abstract class Artikel {
    @ElementCollection @OrderBy("vanafAantal")

    @CollectionTable(name = "kortingen", joinColumns = @JoinColumn(name = "artikelId"))
    private Set<Korting> kortingen;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String naam;
    BigDecimal aankoopprijs;
    BigDecimal verkoopprijs;

    public Artikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs) {
        this.naam = naam;
        this.aankoopprijs = aankoopprijs;
        this.verkoopprijs = verkoopprijs;
        this.kortingen = new LinkedHashSet<>();
    }
    protected Artikel(){}

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public BigDecimal getAankoopprijs() {
        return aankoopprijs;
    }

    public BigDecimal getVerkoopprijs() {
        return verkoopprijs;
    }

    public  Set<Korting> getKortingen(){
        return Collections.unmodifiableSet(kortingen);
    }

    public void verhoogVerkoopPrijs(BigDecimal bedrag){
        verkoopprijs = verkoopprijs.add(bedrag);
    }
}

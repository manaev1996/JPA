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
@NamedEntityGraph(name = Artikel.MET_ARTIKELGROEP,
        attributeNodes = @NamedAttributeNode("artikelGroep"))

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artikelgroepId")
    ArtikelGroep artikelGroep;
    public static final String MET_ARTIKELGROEP = "Artikel.metArtikelGroep";


    public Artikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs,
                   ArtikelGroep artikelGroep) {
        this.naam = naam;
        this.aankoopprijs = aankoopprijs;
        this.verkoopprijs = verkoopprijs;
        this.kortingen = new LinkedHashSet<>();
        setArtikelGroep(artikelGroep);
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

    public ArtikelGroep getArtikelGroep(){
        return artikelGroep;
    }

    public void setArtikelGroep(ArtikelGroep artikelGroep) {
        if (!artikelGroep.getArtikels().contains(this)) {
            artikelGroep.add(this);
        }
        this.artikelGroep = artikelGroep;
    }

    public void verhoogVerkoopPrijs(BigDecimal bedrag){
        verkoopprijs = verkoopprijs.add(bedrag);
    }
}

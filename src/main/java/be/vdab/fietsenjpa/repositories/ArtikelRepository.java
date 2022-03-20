package be.vdab.fietsenjpa.repositories;

import be.vdab.fietsenjpa.domain.Artikel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ArtikelRepository {
    public Optional<Artikel> findById(long id);
    public void create(Artikel artikel);
    List<Artikel> findByNaamContains(String woord);
    int verhoogMetPercentage(BigDecimal percentage);
}

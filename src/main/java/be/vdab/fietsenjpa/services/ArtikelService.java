package be.vdab.fietsenjpa.services;

import be.vdab.fietsenjpa.domain.Artikel;

import java.math.BigDecimal;
import java.util.Optional;

public interface ArtikelService {
    void verhoogVerkoopPrijs(long id, BigDecimal waarde);
}

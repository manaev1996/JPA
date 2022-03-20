package be.vdab.fietsenjpa.services;

import be.vdab.fietsenjpa.domain.Artikel;
import be.vdab.fietsenjpa.repositories.ArtikelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class DefaultArtikelService implements ArtikelService{
    private final ArtikelRepository repository;

    public DefaultArtikelService(ArtikelRepository repository) {
        this.repository = repository;
    }


    @Override
    public void verhoogVerkoopPrijs(long id, BigDecimal waarde) {
        repository.findById(id)
                .orElseThrow(RuntimeException::new)
                .verhoogVerkoopPrijs(waarde);
    }
}

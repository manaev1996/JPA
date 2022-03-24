package be.vdab.fietsenjpa.services;
import be.vdab.fietsenjpa.domain.Artikel;
import be.vdab.fietsenjpa.domain.ArtikelGroep;
import be.vdab.fietsenjpa.domain.FoodArtikel;
import be.vdab.fietsenjpa.repositories.ArtikelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DefaultArtikelServiceTest {
    private DefaultArtikelService service;
    @Mock
    private ArtikelRepository repository;
    private Artikel artikel;
    private ArtikelGroep artikelGroep;



    @BeforeEach
    void beforeEach() {
        service = new DefaultArtikelService(repository);
        artikelGroep = new ArtikelGroep("test");
        artikel = new FoodArtikel("test",BigDecimal.ONE,BigDecimal.TEN,1,artikelGroep);
    }
    //String naam, BigDecimal aankoopprijs,
    //                       BigDecimal verkoopprijs, int houdbaarheid, ArtikelGroep artikelGroep

    @Test
    void verhoogVerkoopPrijs(){
        when(repository.findById(1)).thenReturn(Optional.of(artikel));
        service.verhoogVerkoopPrijs(1, BigDecimal.valueOf(100));
        assertThat(artikel.getVerkoopprijs()).isEqualByComparingTo("130");
        verify(repository).findById(1);
    }



}
/*
    public Optional<Artikel> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public void create(Artikel artikel) {
        repository.create(artikel);
    }
 */
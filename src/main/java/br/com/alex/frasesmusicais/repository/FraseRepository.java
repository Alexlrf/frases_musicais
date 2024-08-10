package br.com.alex.frasesmusicais.repository;

import br.com.alex.frasesmusicais.model.entity.Frase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FraseRepository extends JpaRepository<Frase, Long>, PagingAndSortingRepository<Frase, Long> {

    @Query(value="select f from Frase f where f.artista.idArtista = :idArtista")
    List<Frase> buscarFrasesArtistaSelecionado(Long idArtista);

    List<Frase> findByTextoContaining(String fragmento);

}

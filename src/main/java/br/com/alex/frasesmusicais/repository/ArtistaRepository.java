package br.com.alex.frasesmusicais.repository;

import br.com.alex.frasesmusicais.model.entity.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    Artista findArtistaByNome(String nome);
}

package br.com.alex.frasesmusicais.repository;

import br.com.alex.frasesmusicais.model.entity.Frase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraseRepository extends JpaRepository<Frase, Long> {
}

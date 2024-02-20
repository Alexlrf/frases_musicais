package br.com.alex.frasesmusicais.service;

import br.com.alex.frasesmusicais.model.dto.FraseDTO;
import br.com.alex.frasesmusicais.model.entity.Frase;
import br.com.alex.frasesmusicais.repository.FraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FraseService {

    @Autowired
    private FraseRepository fraseRepository;
    @Transactional
    public Frase incluirFrase(Frase fraseDTO) {

        return this.fraseRepository.save(fraseDTO);
    }
}

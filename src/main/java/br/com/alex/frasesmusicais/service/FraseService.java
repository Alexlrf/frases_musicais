package br.com.alex.frasesmusicais.service;

import br.com.alex.frasesmusicais.model.dto.FraseDTO;
import br.com.alex.frasesmusicais.model.entity.Frase;
import br.com.alex.frasesmusicais.repository.FraseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FraseService {

    @Autowired
    private FraseRepository fraseRepository;
    @Transactional
    public FraseDTO incluirFrase(FraseDTO fraseDTO) {
        Frase frase = new Frase();
        BeanUtils.copyProperties(fraseDTO, frase);
        BeanUtils.copyProperties(fraseDTO.getArtista(), frase.getArtista());
        frase = this.fraseRepository.save(frase);
        FraseDTO fraseDtoEditada = new FraseDTO();
        BeanUtils.copyProperties(frase, fraseDtoEditada);
        BeanUtils.copyProperties(frase.getArtista(), fraseDtoEditada.getArtista());
        return fraseDtoEditada;
    }

    public List<FraseDTO> buscarFrases() {
        List<Frase> frases = this.fraseRepository.findAll();
        List<FraseDTO> frasesDto = new ArrayList<>();
        for (Frase frase: frases) {
            FraseDTO dto = new FraseDTO();
            BeanUtils.copyProperties(frase, dto);
            BeanUtils.copyProperties(frase.getArtista(), dto.getArtista());
            frasesDto.add(dto);

        }
//        frases.forEach(frase -> {
//            FraseDTO dto = new FraseDTO();
//            BeanUtils.copyProperties(frase, dto);
//            BeanUtils.copyProperties(frase.getArtista(), dto.getArtista());
//            frasesDto.add(dto);
//        });
        return frasesDto;
    }
}

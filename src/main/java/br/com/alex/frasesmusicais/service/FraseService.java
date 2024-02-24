package br.com.alex.frasesmusicais.service;

import br.com.alex.frasesmusicais.exception.GenericException;
import br.com.alex.frasesmusicais.model.dto.ArtistaDTO;
import br.com.alex.frasesmusicais.model.dto.FraseDTO;
import br.com.alex.frasesmusicais.model.entity.Artista;
import br.com.alex.frasesmusicais.model.entity.Frase;
import br.com.alex.frasesmusicais.repository.ArtistaRepository;
import br.com.alex.frasesmusicais.repository.FraseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FraseService {

    @Autowired
    private FraseRepository fraseRepository;
    @Autowired
    private ArtistaRepository artistaRepository;

    @Transactional
    public FraseDTO incluirFrase(FraseDTO fraseDTO) {
        Frase frase = new Frase();
        BeanUtils.copyProperties(fraseDTO, frase);
        frase.setArtista(tratarArtistaParaInsert(fraseDTO.getArtista()));
        Frase frasePersistida = this.fraseRepository.save(frase);
        FraseDTO fraseDtoEditada = new FraseDTO();
        fraseDtoEditada.setArtista(new ArtistaDTO());
        BeanUtils.copyProperties(frasePersistida, fraseDtoEditada);
        BeanUtils.copyProperties(frasePersistida.getArtista(), fraseDtoEditada.getArtista());
        return fraseDtoEditada;
    }

    private Artista tratarArtistaParaInsert(ArtistaDTO artistaDto) {
        Artista artistaDB = this.artistaRepository.findArtistaByNome(artistaDto.getNome().toUpperCase());
        artistaDto.setNome(artistaDto.getNome().toUpperCase());
        if (artistaDB == null) {
            assert false;
            artistaDB = new Artista();
            BeanUtils.copyProperties(artistaDto, artistaDB);
        }
        return artistaDB;
    }

    public List<FraseDTO> buscarFrases() {
        List<Frase> frases = this.fraseRepository.findAll();
        List<FraseDTO> frasesDto = new ArrayList<>();
        frases.forEach(frase -> {
            FraseDTO dto = new FraseDTO();
            dto.setArtista(new ArtistaDTO());
            BeanUtils.copyProperties(frase, dto);
            BeanUtils.copyProperties(frase.getArtista(), dto.getArtista());
            frasesDto.add(dto);
        });
        return frasesDto;
    }

    public FraseDTO buscarFrase(Long idFrase) {
        Frase fraseDB = this.fraseRepository.findById(idFrase).orElseThrow(() -> new RuntimeException("Erro ao buscar frase por ID"));
        FraseDTO fraseDto = new FraseDTO();
        fraseDto.setArtista(new ArtistaDTO());
        BeanUtils.copyProperties(fraseDB, fraseDto);
        BeanUtils.copyProperties(fraseDB.getArtista(), fraseDto.getArtista());
        return fraseDto;
    }

    @Transactional
    public FraseDTO alterarFrase(FraseDTO fraseDTO) {
        return null;
    }

    public void deletarFrase(Long idFrase) {
        Optional<Frase> fraseDB = this.fraseRepository.findById(idFrase);
        fraseDB.ifPresentOrElse(
            frase -> this.fraseRepository.deleteById(idFrase),
            () -> {
                throw new GenericException(
                      String.format("Frase de identificador %s não encontrada para ser deletada", idFrase)
                );
            }
        );
    }
}

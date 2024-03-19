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
        return retornarResponse(frasePersistida, fraseDtoEditada);
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
        return retornarListaFrasesDto(frases);
    }

    public FraseDTO buscarFrase(Long idFrase) {
        Frase fraseDB = this.fraseRepository.findById(idFrase).orElseThrow(() -> new RuntimeException("Erro ao buscar frase por ID"));
        FraseDTO fraseDto = new FraseDTO();
        fraseDto.setArtista(new ArtistaDTO());
        return retornarResponse(fraseDB, fraseDto);
    }

    @Transactional
    public FraseDTO alterarFrase(FraseDTO fraseDTO) {
        Frase fraseDB = buscarFraseDB(fraseDTO);
        tratarArtista(fraseDTO, fraseDB);
        BeanUtils.copyProperties(fraseDTO, fraseDB);
        Frase fraseAlterada = this.fraseRepository.save(fraseDB);
        FraseDTO fraseResponse = new FraseDTO();
        fraseResponse.setArtista(new ArtistaDTO());
        return retornarResponse(fraseAlterada, fraseResponse);
    }

    private void tratarArtista(FraseDTO fraseDTO, Frase fraseDB) {
        Artista artistaDB = this.artistaRepository.findArtistaByNome(fraseDTO.getArtista().getNome().toUpperCase());
        if (artistaDB != null) {
            fraseDB.setArtista(artistaDB);
        } else {
            fraseDTO.getArtista().setIdArtista(null);
            Artista artistaNovo = new Artista();
            BeanUtils.copyProperties(fraseDTO.getArtista(), artistaNovo);
            artistaNovo.setNome(fraseDTO.getArtista().getNome().toUpperCase());
            artistaNovo= this.artistaRepository.save(artistaNovo);
            fraseDB.setArtista(artistaNovo);
        }
    }

    private Frase buscarFraseDB(FraseDTO fraseDTO) {
        if (fraseDTO.getIdFrase() == null || fraseDTO.getArtista().getIdArtista() == null) {
            throw new GenericException("Frase ou Artista não encontrado para alteração");
        }
        return this.fraseRepository.findById(fraseDTO.getIdFrase())
                .orElseThrow(() -> new RuntimeException("Frase não encontrada para alteração"));
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

    public List<FraseDTO> buscarFrasesArtistaSelecionado(Long idArtista) {
        List<Frase> frasesArtistaSelecionado = this.fraseRepository.buscarFrasesArtistaSelecionado(idArtista);
        return retornarListaFrasesDto(frasesArtistaSelecionado);
    }
    public List<FraseDTO> buscarFrasesPorTrecho(String fragmento) {
        List<Frase> frases = this.fraseRepository.findByTextoContaining(fragmento);
        return retornarListaFrasesDto(frases);
    }

    private List<FraseDTO> retornarListaFrasesDto(List<Frase> frases) {
        List<FraseDTO> frasesDto = new ArrayList<>();

        if (!frases.isEmpty()) {
            frases.forEach((frase -> {
                FraseDTO fra = new FraseDTO();
                ArtistaDTO art = new ArtistaDTO();
                fra.setArtista(art);
                frasesDto.add(retornarResponse(frase, fra));
            }));
        }
        return frasesDto;
    }

    private FraseDTO retornarResponse(Frase frase, FraseDTO fraseDto) {
        BeanUtils.copyProperties(frase.getArtista(), fraseDto.getArtista());
        BeanUtils.copyProperties(frase, fraseDto);
        return fraseDto;
    }

}

package br.com.alex.frasesmusicais.service;

import br.com.alex.frasesmusicais.exception.GenericException;
import br.com.alex.frasesmusicais.model.dto.ArtistaDTO;
import br.com.alex.frasesmusicais.model.dto.FraseDTO;
import br.com.alex.frasesmusicais.model.entity.Artista;
import br.com.alex.frasesmusicais.model.entity.Frase;
import br.com.alex.frasesmusicais.repository.ArtistaRepository;
import br.com.alex.frasesmusicais.repository.FraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
        Frase frase = new Frase(fraseDTO);
        frase.setArtista(tratarArtistaParaInsert(fraseDTO.getArtista()));
        Frase frasePersistida = this.fraseRepository.save(frase);
        return new FraseDTO(frasePersistida);
    }

    private Artista tratarArtistaParaInsert(ArtistaDTO artistaDto) {
        Artista artistaDB = this.artistaRepository.findArtistaByNome(artistaDto.getNome().toUpperCase());
        artistaDto.setNome(artistaDto.getNome().toUpperCase());
        if (artistaDB == null) {
            artistaDB = new Artista(artistaDto);
        }
        return artistaDB;
    }
    
    @Transactional(readOnly = true)
    public List<FraseDTO> buscarFrases() {
        List<Frase> frases = this.fraseRepository.buscarFrasesCadastradas();
        return retornarListaFrasesDto(frases);
    }

    public List<FraseDTO> buscarFrasesPaginado(Pageable pageable) {
        List<Frase> frases = this.fraseRepository.findAll(pageable).getContent();
        return retornarListaFrasesDto(frases);
    }

    public FraseDTO buscarFrase(Long idFrase) {
        Frase fraseDB = this.fraseRepository
        						.findById(idFrase)
        						.orElseThrow(
        								() -> new RuntimeException("Erro ao buscar frase por ID")
        						);
        return new FraseDTO(fraseDB);
    }

    @Transactional
    public FraseDTO alterarFrase(FraseDTO fraseDTO) {
        Frase fraseDB = this.buscarFraseDB(fraseDTO);
        tratarArtista(fraseDTO, fraseDB);
        Frase fraseAlterada = this.fraseRepository.save(new Frase(fraseDTO));
        return new FraseDTO(fraseAlterada);
    }
    
    private void tratarArtista(FraseDTO fraseDTO, Frase fraseDB) {
        Artista artistaDB = this.artistaRepository.findArtistaByNome(fraseDTO.getArtista().getNome().toUpperCase());
        if (artistaDB != null) {
            fraseDB.setArtista(artistaDB);
        } else {
            fraseDTO.getArtista().setIdArtista(null);
            Artista artistaNovo = new Artista(fraseDTO.getArtista());
            artistaNovo.setNome(fraseDTO.getArtista().getNome().toUpperCase());
            artistaNovo= this.artistaRepository.save(artistaNovo);
            fraseDB.setArtista(artistaNovo);
        }
    }

    private Frase buscarFraseDB(FraseDTO fraseDTO) {
        if (fraseDTO.getIdFrase() == null) {
            throw new GenericException("Frase não encontrada para alteração");
        }
        
        if(fraseDTO.getArtista().getIdArtista() == null) {
        	throw new GenericException("Artista não encontrado para alteração");
        }
        
        return this.fraseRepository.findById(fraseDTO.getIdFrase())
                				   .orElseThrow(
                						() -> new RuntimeException("Frase não encontrada para alteração")
                					);
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
        confirmarUltimaFraseArtista(fraseDB.get());
    }

    private void confirmarUltimaFraseArtista(Frase fraseDB) {
        List<Frase> frases = this.fraseRepository.buscarFrasesArtistaSelecionado(fraseDB.getArtista().getIdArtista());
        if (frases.isEmpty()) {
            this.artistaRepository.deleteById(fraseDB.getArtista().getIdArtista());
        }
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
                frasesDto.add(new FraseDTO(frase));
            }));
        }
        return frasesDto;
    }

}

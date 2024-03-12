package br.com.alex.frasesmusicais.service;

import br.com.alex.frasesmusicais.model.dto.ArtistaDTO;
import br.com.alex.frasesmusicais.model.entity.Artista;
import br.com.alex.frasesmusicais.repository.ArtistaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;
    public List<ArtistaDTO> buscarArtistas() {
        List<ArtistaDTO> artistasDto = new ArrayList<>();
        List<Artista> artistasDB = artistaRepository.findAll();
        if (!artistasDB.isEmpty()) {
            artistasDB.forEach(artista -> {
                ArtistaDTO artistaDTO = new ArtistaDTO();
                BeanUtils.copyProperties(artista, artistaDTO);
                artistasDto.add(artistaDTO);
            });
        }
        return artistasDto;
    }
}

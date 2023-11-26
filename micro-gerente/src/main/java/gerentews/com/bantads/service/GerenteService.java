package gerentews.com.bantads.service;

import java.util.List;

import gerentews.com.bantads.model.Gerente;

public interface GerenteService {

    Gerente getById(long id);
    Gerente salvarGerente (Gerente gerente);
    List<Gerente> listaGerentes();
    void deletarGerente(long id);
    boolean existeGerente(long id);
}

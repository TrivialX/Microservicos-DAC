package gerentews.com.bantads.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List; 
import gerentews.com.bantads.model.Gerente;
import gerentews.com.bantads.repository.GerenteRepository;

@Service
public class GerenteServiceImp implements GerenteService {
    @Autowired
    private GerenteRepository repo;

    @Override
    public Gerente getById(long id) {
        Gerente gerente = repo.getReferenceById(id);
        return gerente;
    }
    @Override
    public Gerente salvarGerente(Gerente gerente) {
        return repo.save(gerente);
    }

    @Override
    public List<Gerente> listaGerentes() {
        return repo.findAll();
    }

    @Override
    public void deletarGerente(long id) {
            repo.deleteById(id);
    }
    @Override
    public boolean existeGerente(long id) {
        return repo.existsById(id);
    }
    

}

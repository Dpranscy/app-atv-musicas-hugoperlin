package ifpr.pgua.eic.colecaomusicas.models.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.entities.Artista;

public interface ArtistaDAO {
    //create
    Resultado criar(Artista artista);
    
    //read
    Resultado listar();
    
    Resultado getById(int id);
    Resultado buscarArtistaMusica(int musicaId);
    
    //update
    Resultado atualizar(int id, Artista novo);
    
    //delete
    Resultado deletar(int id);
}

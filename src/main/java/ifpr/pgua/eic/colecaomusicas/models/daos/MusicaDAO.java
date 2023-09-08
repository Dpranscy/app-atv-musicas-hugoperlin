package ifpr.pgua.eic.colecaomusicas.models.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.entities.Musica;

public interface MusicaDAO {
    Resultado criar(Musica musica);
    Resultado listar();
    Resultado atualizar(int id, Musica nova);
    Resultado deletar(int id);
}

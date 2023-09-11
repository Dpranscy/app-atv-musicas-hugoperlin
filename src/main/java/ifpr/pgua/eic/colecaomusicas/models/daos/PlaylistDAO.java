package ifpr.pgua.eic.colecaomusicas.models.daos;

import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.entities.Playlist;
import ifpr.pgua.eic.colecaomusicas.models.entities.Musica;

public interface PlaylistDAO {
    Resultado criar(Playlist playlist);
    Resultado conectarMusicaPlaylist(List <Integer> musicasId, Integer playlistId);
    Resultado listar();
    Resultado getById(int id);
    Resultado atualizar(int id, Playlist novo);
    Resultado delete(int id);
}

package ifpr.pgua.eic.colecaomusicas.models.daos;

import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.entities.Playlist;

public class PlaylistDAO {
    Resultado criar(Playlist playlist);
    Resultado insertConexaoMusicasPlaylist(List <Integer> musicasId, Integer playlistId);
    Resultado lista();
    Resultado getById(int id);
    Resultado atualizar(int id, Playlist novo);
    Resultado delete(int id);
}

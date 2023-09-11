package ifpr.pgua.eic.colecaomusicas.models.repositories;

import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

//daos
import ifpr.pgua.eic.colecaomusicas.models.daos.PlaylistDAO;
import ifpr.pgua.eic.colecaomusicas.models.daos.FabricaConexoes;

//entities
import ifpr.pgua.eic.colecaomusicas.models.entities.Musica;
import ifpr.pgua.eic.colecaomusicas.models.entities.Playlist;

public class RepositorioPlaylists {
    
    private ArrayList<Playlist> playlist;
    private ArrayList<Musica> musica;
    private PlaylistDAO dao;

    public RepositorioPlaylists(PlaylistDAO dao){
        playlist=new ArrayList<>();
        this.dao=dao;
    }

    public Resultado cadastrarPlaylist(String nome){
        if(nome.isEmpty() || nome.isBlank()){
            return Resultado.erro("Nome inválido!!!");
        }
        Playlist playlist=new Playlist(nome);

        return dao.criar(playlist);
    }

    public Resultado conexaoPlaylistMusica(List<Integer> musicaId, Integer playlistId){
        return dao.conectarMusicaPlaylist(musicaId, playlistId);
    }

    public Resultado listarPlaylists(){
        return dao.listar();
    }
}

package ifpr.pgua.eic.colecaomusicas.models.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.daos.FabricaConexoes;
import ifpr.pgua.eic.colecaomusicas.models.daos.ArtistaDAO;
import ifpr.pgua.eic.colecaomusicas.models.daos.GeneroDAO;
import ifpr.pgua.eic.colecaomusicas.models.daos.MusicaDAO;
import ifpr.pgua.eic.colecaomusicas.models.daos.PlaylistDAO;

import ifpr.pgua.eic.colecaomusicas.models.entities.Artista;
import ifpr.pgua.eic.colecaomusicas.models.entities.Genero;
import ifpr.pgua.eic.colecaomusicas.models.entities.Musica;
import ifpr.pgua.eic.colecaomusicas.models.entities.Playlist;

public class RepositorioPlaylists {
    
    private ArrayList<Playlist> playlist;
    private PlaylistDAO dao;

    public RepositorioPlaylists(PlaylistDAO dao){
        playlist=new ArrayList<>();
        this.dao=dao;
    }

    public Resultado cadastrarPlaylist(String nome){
        if(nome.isBlank() || nome.isEmpty()){
            return Resultado.erro("Nome inválido!!!");
        }
        Playlist playlist=new Playlist(nome);

        return dao.criar(playlist);
    }

    public Resultado linklPlaylistMusica(List<Integer> musicaId, Integer playlistId){
        return dao.insertConexaoMusicasPlaylist(musicaId, playlistId);
    }

    public Resultado listaPlaylist(){
        return dao.listar();
    }

    public Resultado listarPlaylistMusica(Interger idPlaylist){
        Resultado resultado=dao.listarPlaylistMusica(idPlaylist);

        if(resultado. foiSucesso()){
            List<Musica> listar=(List<Musica>) resultado.comoSucesso().getObj();

            for(Musica musica : lista){
                Resultado r1=artistaDAO.buscarArtistaMusica(musica.getId();
                if(r1.foiErro()){
                    return r1;
                }
                Artista artista=(Artista) r1.comoSucesso().getObj;
                musica.setArtista(artista);

                Resultado r2=generoDAO.buscarGeneroMusica(musica.getObj());
                if(r2.foiErro()){
                    return r2;
                }
                Genero genero=(Genero) r2.comoSucesso().getObj();
                musica.setGeneto(genero);
            }
        }
    }
}

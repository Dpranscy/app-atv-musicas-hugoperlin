package ifpr.pgua.eic.colecaomusicas.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.entities.Genero;
import ifpr.pgua.eic.colecaomusicas.models.entities.Musica;
import ifpr.pgua.eic.colecaomusicas.models.entities.Playlist;

public class JDBCPlaylistDAO implements PlaylistDAO{
    
    private static final String INSERTSQL = "INSERT INTO MS_musicas(nome,duracao,anoLancamento,artistaId,generoId) VALUES (?,?,?,?,?)";
    private static final String SELECTSQL = "SELECT * FROM MS_musicas";
    private static final String SELECTFROMPLAYLISTSQL = "SELECT m.* FROM MS_musicas m, MS_playlist_musicas pm, MS_artistas a WHERE m.id = pm.musicaId AND pm.playlistId = ? AND a.id = m.artistaId";

    private FabricaConexoes fabrica;

    public JDBCPlaylistDAO(FabricaConexoes fabrica){
        this.fabrica=fabrica;
    }

    @Override
    public Resultado criar(Playlist playlist){
        try(Connection con=fabrica.getConnection()){
            PreparedStatement pstm=con.prepareStatement("insert into MS_playlist(nome) values (?)");
            pstm.setString(1,playlist.getNome());
            int ret=pstm.executeUpdate();
            con.close();

            if(ret==1){
                return Resultado.sucesso("Playlist Cadastrada!", playlist);
            }
            return Resultado.erro(e.getMessage());
        }
        catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar(){
        try(Connection con=fabrica.getConnection()){
            PreparedStatement pstm=con.prepareStatement("SELECT * FROM MS_playlists")
            ResultSet rs=pstm.executeQuery();
            ArrayList<Playlist> lista=new ArrayList<>();

            while(rs.next()){
                int id=rs.getInt("id");
                String nome=rs.getString("nome");

                Playlist playlist=new Playlist(id,nome);
                lista.add(playlist);
            }

            rs.close();
            pstm.close();
            con.close();
        
            return Resultado.sucesso("Musicas da Playlist", lista);
        }
        catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }

    public Resultado conectarMusicaPlaylist(List<Integer> playlistId, List<Integer> musicasId){
        try(Connection con=fabrica.getConnection()){
            int ret;
            PreparedStatement pstm=con
                .prepareStatement("INSERT INTO MS_playlist_musicas(playlistId, musicaId) VALUES (?, ?)")
            
            //REVER
            for(Integer i : musicasId){
                pstm.setInt(1,playlistId);
                pstm.setInt(2,i);

                ret=pstm.executeUpdate();
                if(ret!=1){
                    return Resultado.erro("Erro não identificado!!!");
                }
            }

            con.close();
            return Resultado.sucesso("Playlist e musicas conectadas", pstm);
        }
        catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado getById(int id){
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }
    
    @Override
    public Resultado atualizar(int id, Playlist novo) {
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public Resultado delete(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}

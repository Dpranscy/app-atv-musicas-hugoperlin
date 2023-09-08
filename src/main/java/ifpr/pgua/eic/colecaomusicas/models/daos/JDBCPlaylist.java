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


public class JDBCPlaylist implements PlaylistDAO{
    
    private FabricaConexoes fabrica;

    public JDBCPlaylistDAO(FabricaConexoes fabrica){
        this.fabrica=fabrica;
    }

    @Override
    public Resultado criar(Playlist playlist){

        Connection con;
        try{
            con=fabr
        }
    }
}

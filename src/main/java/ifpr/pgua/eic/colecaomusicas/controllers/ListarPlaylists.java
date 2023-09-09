package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;

//repositories
import ifpr.pgua.eic.colecaomusicas.models.repositories.RepositorioMusicas;
import ifpr.pgua.eic.colecaomusicas.models.repositories.RepositorioPlaylists;

//entities
import ifpr.pgua.eic.colecaomusicas.models.entities.Musica;
import ifpr.pgua.eic.colecaomusicas.models.entities.Playlist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class ListarPlaylists implements Initializable {

    private RepositorioPlaylists repositorioPlaylist;
    private RepositorioMusicas repositorioMusicas;

    @FXML
    private Button btVoltar;

    @FXML
    private ListView<Playlist> lstPlaylists;

    @FXML
    private TextArea taMusicasPlaylist;

    public ListarPlaylists(RepositorioPlaylists repositorioPlaylist2, RepositorioMusicas repositorioMusicas2) {
        this.repositorioPlaylist=repositorioPlaylist2;
        this.repositorioMusicas=repositorioMusicas2;
    }

    @FXML
    void voltarTela(ActionEvent event) {
        App.popScreen();
    }

    @FXML
    void selecionarPlaylist(MouseEvent event) {
        Integer idPlaylist=lstPlaylists.getSelectionModel().getSelectedItem().getId();

        Resultado musicas=repositorioMusicas.listarPlaylist_musica(idPlaylist);
        List<Musica> lista=(List) musicas.comoSucesso().getObj();

        if (lista!=null) {
            taMusicasPlaylist.clear();

            for (Musica musica : lista) {
                taMusicasPlaylist.appendText(musica.getNome()+" - ");
                taMusicasPlaylist.appendText(musica.getArtista().getNome() + "\n");
            }
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lstPlaylists.getItems().clear();
        Resultado resultado=repositorioPlaylist.listarPlaylist();

        if (resultado.foiErro()) {
            Alert alert=new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
        } else {
            List lista=(List) resultado.comoSucesso().getObj();
            lstPlaylists.getItems().addAll(lista);
        }
    }
}

package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
//entities
import ifpr.pgua.eic.colecaomusicas.models.entities.Artista;
import ifpr.pgua.eic.colecaomusicas.models.entities.Genero;
import ifpr.pgua.eic.colecaomusicas.models.entities.Musica;
import ifpr.pgua.eic.colecaomusicas.models.entities.Playlist;

//repositories
import ifpr.pgua.eic.colecaomusicas.models.repositories.RepositorioMusicas;
import ifpr.pgua.eic.colecaomusicas.models.repositories.RepositorioPlaylists;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

public class CadastroPlaylist {

    @FXML
    private Button btCancelar;

    @FXML
    private Button btInserir;

    @FXML
    private Button btSalvar;

    @FXML
    private ComboBox<Playlist> cbEscolherPlaylist;

    @FXML
    private ListView<Musica> lstMusicas;

    @FXML
    private Tab tabAdicionarMusica;

    @FXML
    private TextField tfNome;

    private RepositorioPlaylists repositorioPlaylists;
    private RepositorioMusicas repositorioMusicas;

    public CadastroPlaylist(RepositorioPlaylists repositorioPlaylists, RepositorioMusicas repositorioMusicas) {
        this.repositorioPlaylists=repositorioPlaylists;
        this.repositorioMusicas=repositorioMusicas;
    }

    @FXML
    void cancelarPlaylistNome(ActionEvent event) {
        App.popScreen();
    }

    @FXML
    void criarPLaylistNome(ActionEvent event) {
        String nome=tfNome.getText();
        tfNome.clear();

        Resultado resultado=repositorioPlaylists.cadastrarPlaylist(nome);
        Alert alert;

        if(resultado.foiErro()) {
            alert=new Alert(AlertType.ERROR, resultado.getMsg());
        } 
        else{
            alert=new Alert(AlertType.INFORMATION, resultado.getMsg());
            updateCbox();
        }
        alert.showAndWait();
    }

    private void updateCbox(){
        cbEscolherPlaylist.getItems().clear();
        Resultado r2=repositorioPlaylists.listarPlaylist();

        if(r2.foiSucesso()) {
            List<Playlist> listaPlaylists=(List) r2.comoSucesso().getObj();
            cbEscolherPlaylist.getItems().addAll(listaPlaylists);
        } 
        else{
            Alert alert=new Alert(AlertType.ERROR, r2.getMsg());
            alert.showAndWait();
        }
    }

    @FXML
    void InserirNaPlaylist(ActionEvent event) {
        int idPlaylist=cbEscolherPlaylist.getValue().getId();

        List<Musica> selecionadoMusicas=lstMusicas.getSelectionModel().getSelectedItems();
        List<Integer> getMusicaIdLista=new ArrayList<Integer>();

        for (Musica musica : selecionadoMusicas) {
            getMusicaIdLista.add(musica.getId());
        }

        Resultado r=repositorioPlaylists.linkPlaylistMusica(getMusicaIdLista, idPlaylist);
        if (r.foiSucesso()) {
            Alert alert=new Alert(AlertType.INFORMATION, r.getMsg());
            alert.showAndWait();

        } 
        else{
            Alert alert=new Alert(AlertType.ERROR, r.getMsg());
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){

        lstMusicas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Resultado r1=repositorioMusicas.listarMusica();

        if(r1.foiSucesso()){
            List<Musica> listaMusicas=(List) r1.comoSucesso().getObj();
            lstMusicas.getItems().addAll(listaMusicas);
        } 
        else{
            Alert alert=new Alert(AlertType.ERROR, r1.getMsg());
            alert.showAndWait();
        }
        updateCbox();
    }
}

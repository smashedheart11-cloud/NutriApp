/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package nutricionalappfx.controlador;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nutricionalappfx.NutricionalAppFX;
import nutricionalappfx.dominio.pojo.Colaborador;

/**
 * FXML Controller class
 *
 * @author smash
 */
public class FXMLPrincipalController implements Initializable {

    private Colaborador colaborador;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbRol;
    @FXML
    private Button btnAdministrarPacientes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    
    public void inicializarInformacion(Colaborador colaborador){
        this.colaborador = colaborador;
        mostrarInfoPantalla();
    }
    
    public void mostrarInfoPantalla(){
        if(colaborador != null){
            lbNombre.setText("Bienvenido/a " + colaborador.toString());
            lbRol.setText("Rol: " + colaborador.getRol());
        }
    }

    @FXML
    private void handleBtnAdministrarPacientes(javafx.event.ActionEvent event) {
        try{
            Stage escenario = new Stage();
            FXMLLoader loader = new FXMLLoader(NutricionalAppFX.class.getResource("vista/FXMLAdministrarPacientes.fxml"));
            Parent vista = loader.load();
            FXMLAdministrarPacientesController controlador = loader.getController();
            Scene escena = new Scene(vista);
            escenario.setScene(escena);
            escenario.setTitle("Administrador de pacientes");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
    
}

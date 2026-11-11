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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nutricionalappfx.NutricionalAppFX;
import nutricionalappfx.dominio.AutenticacionDM;
import nutricionalappfx.dominio.pojo.Autenticacion;
import nutricionalappfx.dominio.pojo.Colaborador;
import nutricionalappfx.modelo.dao.InicioSesionDAO;
import nutricionalappfx.utilidad.Utilidades;

/**
 * FXML Controller class
 *
 * @author smash
 */
public class FXMLInicioSesionController implements Initializable {

    @FXML
    private TextField tfNoPersonal;
    @FXML
    private PasswordField pfContraseña;
    @FXML
    private Button iniciarSesion;
    @FXML
    private Label lbErrorNoPersonal;
    @FXML
    private Label lbErrorContraseña;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void handle(javafx.event.ActionEvent event) {
        String noPersonal = tfNoPersonal.getText();
        String password = pfContraseña.getText();
        if(sonCamposValidos()){
            validarCredenciales(noPersonal, password);
        }
    }
    
    private void validarCredenciales(String noPersonal, String password){
        AutenticacionDM autenticacionDM =
                new AutenticacionDM(new InicioSesionDAO());
        try{
            Autenticacion respuesta = 
                    autenticacionDM.verificarCredencialesColaborador(noPersonal, password);
            if(respuesta.isCredencialesCorrectas() && respuesta.isActivo()){
                Utilidades.mostrarAlertaSimple("Credenciales correctas",
                        respuesta.getMensaje(), Alert.AlertType.INFORMATION);
                //TODO: ENviar a pantalla principal
            } else {
                Utilidades.mostrarAlertaSimple("Lo sentimos",
                        respuesta.getMensaje(), Alert.AlertType.WARNING);
            }
        }catch(RuntimeException e){
            Utilidades.mostrarAlertaSimple("Error",
                    e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private boolean sonCamposValidos(){
        boolean esValido = true;
        lbErrorNoPersonal.setText("");
        lbErrorContraseña.setText("");
        if(tfNoPersonal.getText().isEmpty()){
            lbErrorNoPersonal.setText("El número de personal es obligatorio");
            esValido = false;
        }
        if(pfContraseña.getText().isEmpty()){
            lbErrorContraseña.setText("La contraseña es obligatoria");
            esValido=false;
        }
        return esValido;
    }

    private void irPantallaPrincipal(Colaborador colaborador){
        try{
            Stage escenario = (Stage) tfNoPersonal.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(NutricionalAppFX.class.getResource("vista/FXMLPrincipal.fxml"));
            Parent vista = loader.load();
            FXMLPrincipalController controlador = loader.getController();
            controlador.inicializarInformacion(colaborador);
            Scene escena = new Scene(vista);
            escenario.setScene(escena);
            escenario.setTitle("Control Nutricional");
            escenario.show();
        } catch (IOException ex){
            ex.printStackTrace();
        }
        
    }
    
}

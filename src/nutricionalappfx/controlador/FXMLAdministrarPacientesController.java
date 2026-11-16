/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package nutricionalappfx.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author smash
 */
public class FXMLAdministrarPacientesController implements Initializable {

    @FXML
    private TextField lbBusqueda;
    @FXML
    private Button btnBusqueda;
    @FXML
    private Button btnDarDeAlta;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnDarDeBaja;
    @FXML
    private ImageView btnRegresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

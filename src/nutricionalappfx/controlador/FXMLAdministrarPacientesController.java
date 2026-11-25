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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import nutricionalappfx.dominio.pojo.Paciente;

/**
 * FXML Controller class
 *
 * @author smash
 */
public class FXMLAdministrarPacientesController implements Initializable {

    private int idNutriologo;
    
    @FXML
    private TableView<?> tvPacientes;
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
    @FXML
    private TextField tfBuscarPaciente;
    
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colApellidoPaterno;
    @FXML
    private TableColumn colApellidoMaterno;
    @FXML
    private TableColumn colFechaNacimiento;
    @FXML
    private TableColumn<?, ?> colPeso;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarColumnasTabla();
        
    }    
    
    private void configurarColumnasTabla(){
        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory("fechaNacimiento"));
        colPeso.setCellValueFactory(new PropertyValueFactory("pesoInicial"));
    }
    
    public void recibirInformacion(int idNutriologo){
        this.idNutriologo = idNutriologo;
        
        
    }
}

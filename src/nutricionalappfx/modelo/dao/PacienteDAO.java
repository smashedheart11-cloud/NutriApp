/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nutricionalappfx.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nutricionalappfx.dominio.contrato.IPacienteRepository;
import nutricionalappfx.dominio.pojo.OperacionResultado;
import nutricionalappfx.dominio.pojo.Paciente;
import nutricionalappfx.modelo.ConexionBD;

/**
 *
 * @author smash
 */
public class PacienteDAO implements IPacienteRepository {

    @Override
    public List<Paciente> obtenerPacientesNutriologo(int idNutriologo) {
        List<Paciente> pacientes = new ArrayList<>();
        String consulta = "SELECT codigo, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, pesoInicial FROM pacientes WHERE idNutriologo = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setInt(1, idNutriologo);
            
            try (ResultSet resultado = ps.executeQuery()) {
                while (resultado.next()) {
                    Paciente paciente = new Paciente();
                    paciente.setCodigo(resultado.getString("codigo"));
                    paciente.setNombre(resultado.getString("nombre"));
                    paciente.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                    paciente.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                    paciente.setFechaNacimiento(resultado.getString("fechaNacimiento"));
                    paciente.setPesoInicial(resultado.getFloat("pesoInicial"));
               
                    pacientes.add(paciente);
                }
            }
            return pacientes;
        } catch (SQLException e) {
            throw new RuntimeException("Error de conexión: " + e.getMessage());
        }
    }

    @Override
    public OperacionResultado registrarPaciente(Paciente paciente) {
        OperacionResultado resultado = new OperacionResultado();
        String sentencia = "INSERT INTO pacientes (codigo, nombre, apellidoPaterno, apellidoMaterno, " +
                "fechaNacimiento, altura, pesoInicial, sexo, telefono, correo, idNutriologo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement ps = conexion.prepareStatement(sentencia);){
            ps.setString(1, paciente.getCodigo());
            ps.setString(2, paciente.getNombre());
            ps.setString(3, paciente.getApellidoPaterno());
            ps.setString(4, paciente.getApellidoMaterno());
            ps.setString(5, paciente.getFechaNacimiento());
            ps.setInt(6, paciente.getAltura());
            ps.setFloat(7, paciente.getPesoInicial());
            ps.setString(8, "" + paciente.getSexo());
            ps.setString(9, paciente.getTelefono());
            ps.setString(10, paciente.getCorreo());
            ps.setInt(11, paciente.getIdNutriologo());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas>0){
                resultado.setError(false);
                resultado.setMensaje("Información del paciente guardada correctamente");
            } else {
                resultado.setError(true);
                resultado.setMensaje("Se ha producido un error y no fue guardada la información");
            }
            return resultado;
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error");
        }
        
    }

    @Override
    public OperacionResultado editarPaciente(Paciente paciente) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public OperacionResultado eliminarPaciente(int idPaciente) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean verificarExistenciaCodigoPaciente(String codigo) throws RuntimeException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}

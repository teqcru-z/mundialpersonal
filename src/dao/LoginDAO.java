/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.CreateConection;
import java.sql.*;
import javax.swing.JOptionPane;
import modelo.modelLogin;
import modelo.modelLog;
import vista.CreateUser;
public class LoginDAO {
    private final CreateConection connFac = new CreateConection();
    
    
    
    public boolean guardarUsuario(modelLogin user){
        String sql = "INSERT INTO public.usuario(username, password, name, lastname, email, telefono, rol, estado) VALUES(?,?,?,?,?,?,?,?)";
        try {
            Connection con = connFac.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getLastname());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getTelefono());
            ps.setString(7, user.getRol());
            ps.setBoolean(8, user.isEstado());
            
            ps.executeUpdate();
            ps.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            System.getLogger(LoginDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }
    
    public boolean ValidarUser(modelLog user){
        String sql = "SELECT FROM public.usuario WHERE public.usuario.username = (?) and public.usuario.password = (?)";
        try {
            
            Connection con = connFac.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
            ps.close();
            con.close();
            
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Ingreso Exitoso");
                
            }else{
                JOptionPane.showMessageDialog(null, "El Usuario/Contraseña es incorrecto.");
                
            }
            
            
            return true;
        } catch (SQLException ex) {
            System.getLogger(LoginDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }
}

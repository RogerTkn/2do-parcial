package com.parcial.controlador;

import com.parcial.modelo.Producto;
import com.parcial.utiles.ConexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
            String opcion;
            opcion = (request.getParameter("opcion") != null) ? request.getParameter("opcion") : "list";

            ArrayList<Producto> lista = new ArrayList<Producto>();
            ConexionDB obj = new ConexionDB(); //OBTENIENDO OBJETO QUE TIENE CONEXION A BD
            
            //creando conexion
            Connection conn = obj.conectar();
          
            PreparedStatement ps; //para poder hacer consultas
            ResultSet rs; //resultado de las consultas

            if (opcion.equals("list")) {
                //si la opcion q ha enviado es "list"
                //obtener la lista de registros
                String sql = "select * from productos"; //consulta a la bd
                ps = conn.prepareStatement(sql);//preparando y guardando consulta
                rs = ps.executeQuery();

                while (rs.next()) {
                    Producto proc = new Producto();
                    //valores del registro
                    proc.setId(rs.getInt("id"));
                    proc.setDescripcion(rs.getString("descripcion"));
                    proc.setCantidad(rs.getInt("cantidad"));
                    proc.setPrecio(rs.getFloat("precio"));
                    proc.setCategoria(rs.getString("categoria"));
                    
                    lista.add(proc);
                }
                request.setAttribute("lista",lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);

            }
            if (opcion.equals("nuevo")) {//si es nuevo
                //hacer operacion de insercion de un nuevo registro
                //creando objeto 
                Producto prod =new Producto();
                request.setAttribute("producto", prod);
                request.getRequestDispatcher("editar.jsp").forward(request,response);
                
            }
            if (opcion.equals("eliminar")) {
                //eliminar   
            }int id = Integer.parseInt(request.getParameter("id"));
                String sql ="delete from productos where id=?";
                ps =conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                response.sendRedirect("MainController");
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
    }//fin doGet

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {     
    try{ 
        int id = Integer.parseInt(request.getParameter("id"));
        String descripcion =request.getParameter("descripcion");
        int cantidad=Integer.parseInt(request.getParameter("cantidad"));
        float precio =Float.parseFloat(request.getParameter("precio"));
        String categoria =request.getParameter("categoria");
        
        Producto proc =new Producto();
        proc.setId(id);
        proc.setDescripcion(descripcion);
        proc.setCantidad(cantidad);
        proc.setPrecio(precio);
        proc.setCategoria(categoria);          
        ConexionDB obj=new ConexionDB();
        Connection conn= obj.conectar();
        
        PreparedStatement ps;
        if(id==0){    
            //nuevo registro
            String sql ="insert into productos (descripcion,cantidad,precio,categoria)values(?,?,?,?)";
            ps= conn.prepareStatement(sql);
            ps.setString(1, proc.getDescripcion());
            ps.setInt(2, proc.getCantidad());
            ps.setFloat(3, proc.getPrecio());
            ps.setString(4,proc.getCategoria());
            
            ps.executeUpdate();
            response.sendRedirect("MainController");
        }
            }catch (SQLException e){
                System.out.println("ERROR EN SQL "+e.getMessage());
            }

    }
}
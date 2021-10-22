package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import modelo.ProveedorDAO;
import modelo.ProveedorDTO;


@WebServlet("/Proveedor")
public class Proveedor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Proveedor() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProveedorDAO pDAO= new ProveedorDAO();
		Gson json=new Gson();
		PrintWriter salida= response.getWriter();
		
		
		if(request.getParameter("insertar")!=null) {
			
			long nit;
			String ciudad, direccion, nombre, telefono, respuesta;
			
			nit = Long.parseLong(request.getParameter("nit"));
			ciudad = request.getParameter("ciudad");
			direccion = request.getParameter("direccion");
			nombre = request.getParameter("nombre");
			telefono = request.getParameter("telefono");
			
			ProveedorDTO dto = new ProveedorDTO(nit, ciudad, direccion, nombre, telefono);
			
			if(pDAO.insertarProveedor(dto)) {
				respuesta = "[{\"estado\":\"Ok\"}]";
				salida.println(respuesta);	
			}else {
				respuesta = "[{\"estado\":\"Error\"}]";
			}
		}
		
		if(request.getParameter("editar")!=null) {
			
			long nit;
			String ciudad, direccion, nombre, telefono, respuesta;
			
			nit = Long.parseLong(request.getParameter("nit"));
			ciudad = request.getParameter("ciudad");
			direccion = request.getParameter("direccion");
			nombre = request.getParameter("nombre");
			telefono = request.getParameter("telefono");
			
			ProveedorDTO dto = new ProveedorDTO(nit, ciudad, direccion, nombre, telefono);
			
			if(pDAO.actualizarProveedor(dto)) {
				respuesta = "[{\"estado\":\"Ok\"}]";
				salida.println(respuesta);	
				
			}else {
				respuesta = "[{\"estado\":\"Error\"}]";
			}
		}
		
		
		if(request.getParameter("borrar")!=null) {
			
			long nit;
			String respuesta;
			
			nit = Long.parseLong(request.getParameter("nit"));
			ProveedorDTO proveedor = null;
			proveedor = pDAO.obtenerPorNit(nit);
			if(pDAO.eliminarProveedor(proveedor)) {
				respuesta = "[{\"estado\":\"Ok\"}]";
				salida.println(respuesta);	
			}else {
				respuesta = "[{\"estado\":\"Error\"}]";
			}
		}
		
		if(request.getParameter("ver")!=null) {
			
			long nit;
			String respuesta;
			
			nit = Long.parseLong(request.getParameter("nit"));
			ProveedorDTO proveedor = null;
			proveedor= pDAO.obtenerPorNit(nit);
			if(proveedor!= null) {
				salida.println(json.toJson(proveedor));
			}else {
				respuesta = "[{\"estado\":\"Error\"}]";
				salida.println(respuesta);
			}
		}
		
		
		if(request.getParameter("listar")!=null) {
			ArrayList<ProveedorDTO> lista=new ArrayList<>();
			lista=pDAO.listarProveedores();
			salida.println(json.toJson(lista));	
		}
	}

}

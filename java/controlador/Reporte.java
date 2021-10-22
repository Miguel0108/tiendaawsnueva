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

import modelo.ClienteDTO;
import modelo.ReporteDAO;
import modelo.ReporteVentasClienteDTO;
import modelo.UsuarioDTO;


@WebServlet("/Reporte")
public class Reporte extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Reporte() {
        super();
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ReporteDAO rDAO = new ReporteDAO();
		Gson json=new Gson();
		PrintWriter salida= response.getWriter();
		
		/**
		 * usuarios en formato JSON
		 */
		if(request.getParameter("listarUsuarios")!=null) {
			ArrayList<UsuarioDTO> lista=new ArrayList<>();
			lista=rDAO.listarUsuarios();
			salida.println(json.toJson(lista));	
		}
		
		/**
		 * clientes formato JSON
		 */
		if(request.getParameter("listarClientes")!=null) {
			ArrayList<ClienteDTO> lista=new ArrayList<>();
			lista=rDAO.listarClientes();
			salida.println(json.toJson(lista));	
		}
		
		/**
		 * lista clientes en formato JSON
		 */
		if(request.getParameter("listarVentasCliente")!=null) {
			ArrayList<ReporteVentasClienteDTO> lista=new ArrayList<>();
			lista=rDAO.listarVentasPorCliente();
			salida.println(json.toJson(lista));	
		}
	
	}

}

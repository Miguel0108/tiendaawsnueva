package controlador;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	 String url="jdbc:mysql://goldclub.ckkx7z31jxfj.us-east-2.rds.amazonaws.com:3306/db_tienda_generica";
	 String user="root";
	 String pass="aceHack0108";

 Connection conn = null;
 
 public Connection Conecta() {
  try {
   Class.forName("com.mysql.cj.jdbc.Driver");
   conn = DriverManager.getConnection(url,user,pass);
   System.out.println( "Conexión con BD exitosa");
  }catch(Exception e) {
	  System.out.println("Error en la conexión: "+e);
  }
  return conn;
 }
}
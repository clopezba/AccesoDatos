package AnexoVI;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		AccesoBDatos abd = new AccesoBDatos();
		try {
			abd.conectar();
		//	abd.columnasMetadata("empleados", "departamentos");
		//	abd.columnasMetadata("pedidos", "detalles_pedido");
		//	abd.columnasMetadata("trabajadores", "departamentos");
			
			abd.rsMeta();
			abd.desconectar();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}

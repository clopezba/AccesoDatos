package PreparedStatement;

public class a1_Main {

	public static void main(String[] args) {
		a1_AccesoBDatos abd = new a1_AccesoBDatos();
		abd.conectar();
		
		abd.consultarPorLocalidad("Málaga");
		
		abd.desconectar();

	}

}

package PreparedStatement;

public class a2_Usuario {
	private String usuario;
	private String contrasenya;
	private String nombre;
	
	public a2_Usuario(String usuario, String contrasenya, String nombre) {
		this.usuario = usuario;
		this.contrasenya = contrasenya;
		this.nombre = nombre;
	}
	
	public a2_Usuario() {
		
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Usuario [usuario=" + usuario + ", contrasenya=" + contrasenya + ", nombre=" + nombre + "]";
	}
	
}

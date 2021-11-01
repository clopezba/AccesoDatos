package PreparedStatement;

public class a1_Socio {
	private int id;
	private String nombre;
	private int estatura; 
	private int edad;
	private String localidad;
	
	public a1_Socio(int id, String nombre, int estatura, int edad, String localidad) {
		this.id = id;
		this.nombre = nombre;
		this.estatura = estatura;
		this.edad = edad;
		this.localidad = localidad;
	}
	public a1_Socio() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEstatura() {
		return estatura;
	}
	public void setEstatura(int estatura) {
		this.estatura = estatura;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	@Override
	public String toString() {
		return "a1_Socio [id=" + id + ", nombre=" + nombre + ", estatura=" + estatura + ", edad=" + edad
				+ ", localidad=" + localidad + "]";
	}
	
	
	
}

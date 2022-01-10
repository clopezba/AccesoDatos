package manyToMany;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.TableGenerator;

@Entity
public class Proyecto {

//    @TableGenerator(name = "proyecto_gen", allocationSize = 1, pkColumnName = "gen_name", valueColumnName = "gen_val", table = "id_gen")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int idProyecto;
    private String nombre;

    @ManyToMany(mappedBy = "proyectos", cascade = CascadeType.PERSIST)
    private List<Empleado> empleados;

    
    public Proyecto(int idProyecto, String nombre) {
		this.idProyecto = idProyecto;
		this.nombre = nombre;
	}

	public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

}

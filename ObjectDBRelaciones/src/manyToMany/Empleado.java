package manyToMany;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.TableGenerator;

@Entity
public class Empleado {
	
//    @TableGenerator(name = "empleado_gen", table = "id_gen", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 100)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int idEmpleado;
    private String nombre;

    @ManyToMany(cascade = CascadeType.PERSIST)
//    @JoinTable(name = "empleado_proyecto", joinColumns = @JoinColumn(name = "idEmpleado"), inverseJoinColumns = @JoinColumn(name = "idProyecto"))
    private List<Proyecto> proyectos;

    
    public Empleado(int idEmpleado, String nombre) {
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
	}

	public Empleado() {

	}

	public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

}


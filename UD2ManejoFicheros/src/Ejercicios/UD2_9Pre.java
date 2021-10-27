package Ejercicios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class UD2_9Pre {

	public static void main(String[] args) throws IOException {
		File fichero = new File("Ficheros\\ProfesFPSierraGuara.dat");
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");
		String profesores[]= {"Alberto Carrera", "Ana Ereza", "Antonino Lasierra",
				"Pura Plo", "Belén Carrera"};
		int departamento[]= {10, 20, 20, 30, 40};
		Double antiguedad[]= {29.5, 18.0, 38.5, 28.0, 22.0};
		
		StringBuffer sb = null;
		int total = profesores.length;
		
		for (int i = 0; i < total; i++) {
			file.writeInt(i+1);
			sb = new StringBuffer(profesores[i]);
			sb.setLength(20);
			file.writeChars(sb.toString());
			file.writeInt(departamento[i]);
			file.writeDouble(antiguedad[i]);
		}
		file.close();
	}

}

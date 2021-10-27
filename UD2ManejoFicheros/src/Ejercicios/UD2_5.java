package Ejercicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class UD2_5 {

	public static void main(String[] args) {
		String linea;
		
		try {
			BufferedReader bf = new BufferedReader(new FileReader(args[0]));
			ArrayList<String> lista = new ArrayList<String>();
			while ((linea = bf.readLine())!= null) {
				lista.add(linea);
			}
			Collections.sort(lista);
			bf.close();
			
			
			int l = args[0].length();
			String nombre = args[0].substring(0, l - 4);
			String extension = args[0].substring(l - 4, l);
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(nombre + "_sort" + extension));
			for (String s : lista)
				bw.write(s + "\n");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

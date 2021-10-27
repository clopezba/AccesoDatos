package Ejercicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class UD2_4 {

	public static void main(String[] args) {
		int par = 0;
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("Ficheros//pares.txt"));
			bw.write(Integer.toString(par));
			while(par < 500) {
				bw.newLine();
				par += 2;	
				bw.write(Integer.toString(par));
						
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("Ficheros//pares.txt"));
			String leer;
			while((leer = br.readLine()) != null) {
				System.out.println(leer);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

}

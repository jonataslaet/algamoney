package br.com.jonataslaet;

import java.lang.reflect.Field;

import br.com.jonataslaet.model.Categoria;

public class Mainzao {
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		
		Categoria categoriaCarregada = new Categoria("Comercial");
		categoriaCarregada.setCodigo(1L);
		
		Categoria categoriaSubstituta = new Categoria("Financeiro");
		
		for (Field campo : categoriaCarregada.getClass().getDeclaredFields()) {
			campo.setAccessible(true);
			Object valor = campo.get(categoriaCarregada);
			System.out.println("1a: "+campo.getName() + "=" + valor);
			System.out.println("2a: "+campo.getName() + "=" + campo.get(categoriaSubstituta));
//			if (valor != null) {
//				System.out.println("1a: "+campo.getName() + "=" + valor);
//				System.out.println("2a: "+campo.getName() + "=" + campo.get(categoriaCarregada));
//			}
			System.out.println();
		}
		
	}

	
}

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
		atualizarObjetos(categoriaCarregada, categoriaSubstituta);
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

	public static void atualizarObjetos(Object titular, Object substituto) throws IllegalArgumentException, IllegalAccessException {
		for (Field campo : titular.getClass().getDeclaredFields()) {
			campo.setAccessible(true);
			Object valorSubstituto = campo.get(substituto);
			if (valorSubstituto != null) {
				campo.set(titular, valorSubstituto);
			}
		}
		System.out.println("Valores atualizados");
	}
	
}

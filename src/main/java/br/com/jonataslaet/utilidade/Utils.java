package br.com.jonataslaet.utilidade;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Utils {

	public static void atualizarObjeto(Object titular, Object substituto) throws IllegalArgumentException, IllegalAccessException {
		for (Field campo : titular.getClass().getDeclaredFields()) {
			campo.setAccessible(true);
			Object valorSubstituto = campo.get(substituto);
			if (valorSubstituto != null) {
				campo.set(titular, valorSubstituto);
			}
		}
	}
	
	public static List<String> getTodosOsNomesDePaises() {
		String[] locales = Locale.getISOCountries();
		List<String> nomesDosPaises = new ArrayList<String>();
		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);
			nomesDosPaises.add(obj.getDisplayCountry().toUpperCase());
		}
		return nomesDosPaises;
	}

	public static boolean existePais(String nome) {
		String nomeAlvo = nome.toUpperCase();
		return getTodosOsNomesDePaises().contains(nomeAlvo);
	}

	String formatLocalDateTime(LocalDateTime data, String format) {
		// Get current date time
		LocalDateTime now = LocalDateTime.now();

		System.out.println("Before : " + now);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		String formatDateTime = data.format(formatter);

		System.out.println("After : " + formatDateTime);
		return formatDateTime;
	}

	public static boolean emailValidado(String email) {
		boolean isEmailIdValid = false;
		if (email != null && email.length() > 0) {
			String expression = "^[a-z0-9._]+@^[a-z]\\.com\\.br$";
			Pattern pattern = Pattern.compile(expression);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				isEmailIdValid = true;
			}
		}
		return isEmailIdValid;
	}

	public static boolean ehVazio(String entrada) {
		String entradaVazia = entrada.replaceAll("\\s+", "");
		return entradaVazia.equals("");
	}

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("@ngul@r0"));
		System.out.println(encoder.encode("m0b1l30"));
		
	}

	public static String gerarPassword() {
		String[] carct = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h",
				"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
				"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
				"Y", "Z" };

		String senha = "";

		for (int x = 0; x < 10; x++) {
			int j = (int) (Math.random() * carct.length);
			senha += carct[j];
		}
		return senha;
	}

	public static String toJson(final Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String jsonRep = "";
		jsonRep = mapper.writeValueAsString(object);
		return jsonRep;
	}
}
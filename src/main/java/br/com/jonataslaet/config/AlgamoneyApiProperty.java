package br.com.jonataslaet.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("algamoney")
public class AlgamoneyApiProperty {
	
	private String originPermitida = "http://0.0.0.0:8082";
	
	public String getOriginPermitida() {
		return originPermitida;
	}
	public void setOriginPermitida(String originPermitida) {
		this.originPermitida = originPermitida;
	}
	private final Seguranca seguranca = new Seguranca();
	
	public Seguranca getSeguranca() {
		return seguranca;
	}
	public static class Seguranca {
		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
	}
}

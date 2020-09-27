package br.com.jonataslaet.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
public class TokenController {

	@Autowired
	private AlgamoneyApiProperty algamoneyApiProperty;
	
	@DeleteMapping(value = "/revoke")
	public void revogar(HttpServletRequest req, HttpServletResponse resp) {
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(algamoneyApiProperty.getSeguranca().isEnableHttps());
		cookie.setPath(req.getContextPath()+"/oauth/token");
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
		resp.setStatus(HttpStatus.NO_CONTENT.value());
	}
}

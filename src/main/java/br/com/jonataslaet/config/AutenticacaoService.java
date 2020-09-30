package br.com.jonataslaet.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.jonataslaet.model.Usuario;
import br.com.jonataslaet.repository.UsuarioRepository;
import br.com.jonataslaet.security.UsuarioSistema;

@Service
public class AutenticacaoService implements UserDetailsService{

	@Autowired
	UsuarioRepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = ur.findByEmail(username);
		if (!usuario.isPresent()) {
			throw new UsernameNotFoundException("Dados inválidos");
		}
		UsuarioSistema usuarioSistema = new UsuarioSistema(usuario.get());
		return usuarioSistema;
	}

}

package pe.com.edu.socisyamigos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pe.com.edu.socisyamigos.entity.Usuario;
import pe.com.edu.socisyamigos.repository.UsuarioRepository;
import pe.com.edu.socisyamigos.repository.Usuario_rolRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private Usuario_rolRepository usuarioRolRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario user = userRepository.findByUsername(username) .orElseThrow(() ->
                new UsernameNotFoundException("User not exists by Username or Email"));

        Set<GrantedAuthority> authorities = usuarioRolRepository.findByUsuario(user).stream()
                .map((role) -> new SimpleGrantedAuthority(role.getRol().getNombre()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                authorities
        );
    }
}

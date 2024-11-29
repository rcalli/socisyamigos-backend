package pe.com.edu.socisyamigos.service;


import pe.com.edu.socisyamigos.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}

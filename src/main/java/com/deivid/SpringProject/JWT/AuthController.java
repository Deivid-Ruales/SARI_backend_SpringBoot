package com.deivid.SpringProject.JWT;
        
import com.deivid.SpringProject.modelo.Usuario;
import com.deivid.SpringProject.modelo.Rol;
import com.deivid.SpringProject.servicio.UsuarioServicio;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping()
@CrossOrigin(value = "http://localhost:3000/")
public class AuthController {

    @Autowired
    private UsuarioServicio usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioService.Autenticar(request.getCedula(), request.getContrasena());
        String token = jwtUtil.generateToken(usuario);

        System.out.println("cedula usuario: "+usuario.getCedula()+" contrasena usuario: "+usuario.getContrasena());
        System.out.println("requerimiento CEDULA: "+ request.getCedula()+ "requerimiento CONTRASEÑA: "+request.getContrasena());
        
        return ResponseEntity.ok(new LoginResponse(token, usuario.getRol()));
    }
}

@Data
class LoginRequest {
    private String cedula;
    private String contrasena;
}

@Data
@AllArgsConstructor
class LoginResponse {
    private String token;
    private Rol rol;
}


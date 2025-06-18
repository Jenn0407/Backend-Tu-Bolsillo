package edu.tubolsillo_backend.utilities;

import edu.tubolsillo_backend.models.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    private static final String SECURITY_KEY = "n8$!e@4yM%G7pLsC&QzXvF#B2*J^R5+8a3d";
    private static final long EXPIRATION_TIME = 3600000;

    public String generarToken(Usuario usuario) {
        return Jwts.builder().setSubject(usuario.getCorreo())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(SECURITY_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extraerCorreo(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECURITY_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}

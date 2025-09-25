package Token;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwToken {


    private static JwToken jwTokenHelper = null;
    

  //  private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    private static String SECRET = "xK9vQ7s8Lp4wT2uZr1yE3cV5bH6nA0mD";
    private static final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    
    private JwToken() {
    	
    }

    
    public static JwToken getInstance() {
        if (jwTokenHelper == null) {
            jwTokenHelper = new JwToken();
        }
        
        return jwTokenHelper;
    }

    
    //GENERATION OF JWTOKEN
    public static String generateToken(String username, String password) 
    {
    	
    
    	//480 - 8 hours
    	//15 - 15 minutes
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + (1000 * 60 * 480); //  8 hours

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(nowMillis))
                .claim("pwd", password)
                .setExpiration(new Date(expMillis))
                .signWith(SECRET_KEY)
                .compact();
    }
    
   
    
}

package util;

import data.DTO.User;
import io.jsonwebtoken.*;

import javax.ws.rs.NotAuthorizedException;

/**
 * Created by magnus
 */

//Husk at ændre til at kunne tage Customer, admin og employee
public class JWTUtil {
    //skal autogenerates eller andet
    private String secret = "kqly";

    public String generateToken (User user) {
        Claims claims = Jwts.claims()
                .setSubject(user.getUsername());
        claims.put("id", user.getId());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims parseToken (String token) {
        try {
            Jws<Claims> jws = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return jws.getBody();
        }
        catch (ExpiredJwtException e) {
            throw new NotAuthorizedException("Too old token");
        }
        catch (UnsupportedJwtException e) {
            throw new NotAuthorizedException("Your token is not supported");
        }
        catch (MalformedJwtException e) {
            throw new NotAuthorizedException("Malformed token");
        }
        catch (SignatureException e) {
            throw new NotAuthorizedException("Token signature invalid");
        }
        catch (IllegalArgumentException e) {
            throw new NotAuthorizedException("Illegal argument");
        }
    }
}

package com.satwik.SecurityMFA.controller;

import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jwt.SignedJWT;
import com.satwik.SecurityMFA.dto.TokenRequest;
import com.satwik.SecurityMFA.service.MsalValidationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    MsalValidationService msalValidationService;

    @PostMapping("/validate")
    public String validateToken(@RequestBody TokenRequest tokenReq) {
        System.out.println(tokenReq.getToken());

        Claims claims = msalValidationService.decodeAndVerifyToken(tokenReq.getToken());
        System.out.println(claims.getSubject());

        return claims.getSubject();

    }

    /*private Claims extractAllClaims(String token) {
        URL jwksUrl;
        JWKSet publicKeys;
        try {
            jwksUrl = new URL("https://login.microsoftonline.com/5a51c258-1e5a-48db-a3d3-54abf2763414/discovery/v2.0/keys");
            publicKeys = JWKSet.load(jwksUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(getPublicKey(publicKeys, token))
                    .parseClaimsJws(token);

            return claimsJws.getBody();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static RSAPublicKey getPublicKey(JWKSet publicKeys, String token) throws Exception {
        Map<String, Object> jwtHeaders = getJwtHeaders(token);
        String kid = jwtHeaders.get("kid").toString();
        System.out.println(kid);
        JWK jwk = publicKeys.getKeyByKeyId(kid);
        return (RSAPublicKey) jwk.toRSAKey().toPublicKey();
    }

    private static Map<String, Object> getJwtHeaders(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSObject jwsObject = new JWSObject(signedJWT.getHeader(), signedJWT.getPayload());

            // Get the header as a JSON object
            return jwsObject.getHeader().toJSONObject();

        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse JWT token", e);
        }
    }*/

}

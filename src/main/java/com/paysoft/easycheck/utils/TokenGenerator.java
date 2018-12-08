package com.paysoft.easycheck.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.paysoft.easycheck.models.Customer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Stateless
@LocalBean
public class TokenGenerator {

    public String generateToken(Customer customer) {
        return createAndSignToken(customer);
    }

    protected Algorithm getAlgorithm() {
        return Algorithm.HMAC256("secret");
    }

    protected String createAndSignToken(Customer customer) {
        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("customer", customer);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 365 * 5);
        Date expiresAt = calendar.getTime();

        return JWT.create()
            .withIssuer("stores-app")
            .withHeader(headerClaims)
            .withSubject(customer.getEmail())
            .withExpiresAt(expiresAt)
            .sign(getAlgorithm());
    }

    protected JWTVerifier getVerifier() {
        return JWT.require(getAlgorithm())
            .withIssuer("stores-app")
            .build();
    }

    public DecodedJWT verifyToken(String token) throws JWTVerificationException {
        return getVerifier().verify(token);
    }

    public DecodedJWT decodeToken(String token) throws JWTDecodeException {
        return JWT.decode(token);
    }

    protected Customer getUserFromToken(DecodedJWT jwt) {
        Claim claim = jwt.getHeaderClaim("user");

        return claim.as(Customer.class);
    }
}

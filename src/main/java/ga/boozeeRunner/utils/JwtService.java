package ga.boozeeRunner.utils;

import ga.boozeeRunner.config.BaseException;
import ga.boozeeRunner.config.secret.Secret;
import ga.boozeeRunner.config.BaseResponseStatus;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class JwtService {
    /**
     * JWT 생성
     * @param userId
     * @return String
     */
    public String createJwt(Long userId) {
        Date now = new Date();
        return Jwts.builder()
                .claim("userId", userId)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, Secret.JWT_SECRET_KEY)
                .compact();
    }

    /**
     * Header에서 X-ACCESS-TOKEN 으로 JWT 추출
     * @return String
     */
    public String getJwt() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("X-ACCESS-TOKEN");
    }

    /**
     * JWT에서 userId 추출
     * @return int
     * @throws BaseException
     */
    public Long getUserId() throws BaseException {
        // 1. JWT 추출
        String accessToken = getJwt();
        System.out.println("1");
        if (accessToken == null || accessToken.length() == 0) {
            throw new BaseException(BaseResponseStatus.EMPTY_JWT);
        }
        System.out.println("2");
        // 2. JWT parsing
        Jws<Claims> claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(Secret.JWT_SECRET_KEY)
                    .parseClaimsJws(accessToken);
        } catch (Exception ignored) {
            throw new BaseException(BaseResponseStatus.INVALID_JWT);
        }

        // 3. userId 추출
        return Long.valueOf(claims.getBody().get("userId", Integer.class));
    }
}

package cn.qiluno.train.common.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.crypto.GlobalBouncyCastleProvider;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JWTUtil.class);
    private static final String key = "qiluno.cn/12306";

    public static String createToken(Map<String, Object> info) {
        GlobalBouncyCastleProvider.setUseBouncyCastle(false);
        DateTime now = DateTime.now();
        DateTime expTime = now.offsetNew(DateField.HOUR, 24);
        Map<String, Object> payload = new HashMap<>();
        // 签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        // 过期时间
        payload.put(JWTPayload.EXPIRES_AT, expTime);
        // 生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        // 信息
        payload.putAll(info);
        // token
        String token = cn.hutool.jwt.JWTUtil.createToken(payload, key.getBytes());
        LOG.info("生成 JWT token: {}", token);
        return token;
    }

    public static boolean validate(String token) {
        LOG.info("校验 JWT token: {}", token);
        GlobalBouncyCastleProvider.setUseBouncyCastle(false);
        JWT jwt = cn.hutool.jwt.JWTUtil.parseToken(token).setKey(key.getBytes());
        boolean validate = jwt.validate(0);
        LOG.info("token 校验结果: {}", validate);
        return validate;
    }

    public static JSONObject getJSONObject(String token) {
        GlobalBouncyCastleProvider.setUseBouncyCastle(false);
        JWT jwt = cn.hutool.jwt.JWTUtil.parseToken(token).setKey(key.getBytes());
        JSONObject payloads = jwt.getPayloads();
        payloads.remove(JWTPayload.ISSUED_AT);
        payloads.remove(JWTPayload.EXPIRES_AT);
        payloads.remove(JWTPayload.NOT_BEFORE);
        LOG.info("token 的原始信息: {}", payloads);
        return payloads;
    }
}

package learn.caojw.his.authorization.controller;

import learn.caojw.his.authorization.util.VerifyUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

/**
 * 验证码控制层
 *
 * @author 曹健伟
 */
@Controller
public class VerifyController {
    @RequestMapping("/verify")
    public ResponseEntity<Object> verify() {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            int result = VerifyUtil.generate(60, 40, 20, byteArrayOutputStream);
            String image = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
            return ResponseEntity.ok(Map.of("result", result, "image", image));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.springreact.springreact;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class Spa implements ErrorController {
    @RequestMapping("/error")
    public Object error(HttpServletRequest request, HttpServletResponse response) {
        // place your additional code here (such as error logging...)
        if (request.getMethod().equalsIgnoreCase(HttpMethod.GET.name())
                && response.getStatus() == HttpStatus.NOT_FOUND.value()) {
            try {
                File indexFile = ResourceUtils.getFile("/static/index.html");
                FileInputStream stream = new FileInputStream(indexFile);
                String body = StreamUtils.copyToString(stream, Charset.defaultCharset());
                return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(body);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.internalServerError().build();
            }
        } else {
            return response;
        }
    }
}
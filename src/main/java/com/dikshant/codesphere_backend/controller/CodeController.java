package com.dikshant.codesphere_backend.controller;

import com.dikshant.codesphere_backend.service.Judge0Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/code")
public class CodeController {

    @Autowired
    private Judge0Service judge0Service;

    // Model (record for request body)
    public record CodeRequest(String code, String language) {}

    @PostMapping("/run")
    public Map<String, Object> runCode(@RequestBody CodeRequest req) {
        return judge0Service.runCode(req.code(), req.language());
    }
}

package com.dikshant.codesphere_backend.controller;

import com.dikshant.codesphere_backend.model.Snippet;
import com.dikshant.codesphere_backend.model.User;
import com.dikshant.codesphere_backend.repository.SnippetRepository;
import com.dikshant.codesphere_backend.repository.UserRepository;
import com.dikshant.codesphere_backend.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/snippets")
public class SnippetController {

    @Autowired
    private SnippetRepository snippetRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/save")
    public ResponseEntity<?> saveSnippet(@RequestBody Snippet snippet, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(Map.of("error", "Missing token"));
        }

        String token = authHeader.substring(7);
        String username;
        try {
            username = jwtService.extractUsername(token);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid token"));
        }

        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
        }

        snippet.setUser(user); // link snippet to user
        snippetRepository.save(snippet);

        return ResponseEntity.ok(Map.of("message", "Snippet saved successfully"));
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMySnippets(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(Map.of("error", "Missing token"));
        }

        String token = authHeader.substring(7);
        String username;
        try {
            username = jwtService.extractUsername(token);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid token"));
        }

        System.out.println("🔹 Username from token: " + username);

        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            System.out.println("❌ No user found for username: " + username);
            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
        }

        System.out.println("✅ Found user ID: " + user.getId());

        List<Snippet> snippets = snippetRepository.findByUserId(user.getId());
        System.out.println("📦 Snippets found: " + snippets.size());
        return ResponseEntity.ok(snippets);
    }
}

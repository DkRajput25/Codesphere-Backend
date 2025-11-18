package com.dikshant.codesphere_backend.service;

import com.dikshant.codesphere_backend.model.Snippet;
import com.dikshant.codesphere_backend.model.User;
import com.dikshant.codesphere_backend.repository.SnippetRepository;
import com.dikshant.codesphere_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SnippetService {

    @Autowired
    private SnippetRepository snippetRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ Fetch all snippets belonging to the logged-in user
    public List<Snippet> getUserSnippets(String username) {
        // Step 1: Find user by username
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            System.out.println("⚠️ No user found with username: " + username);
            return Collections.emptyList();
        }

        // Step 2: Fetch snippets using user ID (✅ correct approach)
        List<Snippet> snippets = snippetRepository.findByUserId(user.getId());
        System.out.println("✅ Found " + snippets.size() + " snippets for user: " + username);
        return snippets;
    }
}

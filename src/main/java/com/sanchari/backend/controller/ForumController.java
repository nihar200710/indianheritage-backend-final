package com.sanchari.backend.controller;

import com.sanchari.backend.model.ForumThread;
import com.sanchari.backend.model.ThreadMessage;
import com.sanchari.backend.repository.ForumThreadRepository;
import com.sanchari.backend.repository.ThreadMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/threads")
public class ForumController {

    @Autowired
    private ForumThreadRepository forumThreadRepository;

    @Autowired
    private ThreadMessageRepository threadMessageRepository;

    @GetMapping
    public List<ForumThread> getAllThreads() {
        return forumThreadRepository.findAllByOrderByIdDesc();
    }

    @PostMapping
    public ForumThread createThread(@RequestBody ForumThread thread) {
        // Associate nested conversation messages to the parent thread
        if (thread.getConversation() != null) {
            for (ThreadMessage tm : thread.getConversation()) {
                tm.setThread(thread);
            }
        }
        return forumThreadRepository.save(thread);
    }

    @PostMapping("/{threadId}/messages")
    public ResponseEntity<ForumThread> addMessage(@PathVariable Long threadId, @RequestBody ThreadMessage message) {
        Optional<ForumThread> threadOpt = forumThreadRepository.findById(threadId);
        if (threadOpt.isPresent()) {
            ForumThread thread = threadOpt.get();
            message.setThread(thread);
            threadMessageRepository.save(message); // Saves the child

            // Update thread counters
            thread.setReplies((thread.getReplies() != null ? thread.getReplies() : 0) + 1);
            thread.setLastActive("Just now");
            ForumThread updated = forumThreadRepository.save(thread);

            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
}

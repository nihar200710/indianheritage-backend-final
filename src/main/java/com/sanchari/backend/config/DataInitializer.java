package com.sanchari.backend.config;

import com.sanchari.backend.model.*;
import com.sanchari.backend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(UserRepository userRepository, 
                               MonumentRepository monumentRepository,
                               BookingRepository bookingRepository,
                               ForumThreadRepository forumThreadRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                userRepository.save(new User(null, "Super Admin", "admin@test.com", "123", "admin"));
                userRepository.save(new User(null, "Rahul (Enthusiast)", "enthusiast@test.com", "123", "enthusiast"));
                userRepository.save(new User(null, "Anita (Creator)", "creator@test.com", "123", "creator"));
                userRepository.save(new User(null, "Ramesh (Guide)", "guide@test.com", "123", "guide"));
            }

            if (monumentRepository.count() == 0) {
                monumentRepository.save(new Monument(null, "Taj Mahal", "Agra, Uttar Pradesh", 
                "An immense mausoleum of white marble, built in Agra between 1631 and 1648 by order of the Mughal emperor Shah Jahan in memory of his favourite wife.", 
                "Commissioned in 1632, the Taj Mahal took over 20 years to build and employed roughly 20,000 artisans.", 
                "It features perfect symmetrical planning, a massive white marble dome, and intricate pietra dura using semi-precious stones.", 
                "Taj Mahal, Agra, India", "Rahul Sharma", null, null));
                
                monumentRepository.save(new Monument(null, "Hampi Monuments", "Vijayanagara, Karnataka", 
                "The grandiose site of Hampi was the last capital of the last great Hindu Kingdom of Vijayanagar.", 
                "Founded in the 14th century, Hampi was a prosperous, wealthy, and grand city.", 
                "Famous for its large-scale Dravidian architecture, particularly the Virupaksha Temple and the iconic Stone Chariot.", 
                "Hampi, Karnataka, India", "Priya Patel", null, null));
            }

            if (bookingRepository.count() == 0) {
                bookingRepository.save(Booking.builder().title("Taj Mahal: Sunset Architecture Tour").type("Heritage Site").guide("Rahul Sharma").date("2026-10-25").time("17:30").status("Upcoming").build());
                bookingRepository.save(Booking.builder().title("Hidden Secrets of Ajanta Caves").type("Heritage Site").guide("Priya Patel").date("2026-11-02").time("10:00").status("Upcoming").build());
            }

            if (forumThreadRepository.count() == 0) {
                ForumThread t1 = new ForumThread(null, "Best time of day to visit the Taj Mahal?", "TravelBug99", "Travel Advice", 1, "2 hours ago", null, null, null);
                t1 = forumThreadRepository.save(t1);
                t1.getConversation().add(new ThreadMessage(null, "TravelBug99", "I'm planning a virtual tour and a real visit later this year. Is sunrise or sunset better for photography?", t1, null, null));
                t1.getConversation().add(new ThreadMessage(null, "RahulGuide", "Definitely sunrise! The marble changes color from pink to white as the sun comes up. It's magical and much less crowded.", t1, null, null));
                forumThreadRepository.save(t1);
            }
        };
    }
}

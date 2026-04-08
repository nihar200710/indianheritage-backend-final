package com.sanchari.backend.config;

import com.sanchari.backend.model.*;
import com.sanchari.backend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(UserRepository userRepository, 
                               MonumentRepository monumentRepository,
                               BookingRepository bookingRepository,
                               ForumThreadRepository forumThreadRepository) {
        return args -> {
            
            // 1. Initialize Users
            if (userRepository.count() == 0) {
                userRepository.save(User.builder()
                    .name("Super Admin")
                    .email("admin@test.com")
                    .password("$2a$10$/ck7Fgd2hXz9wL376D0ONuD1VcmGtnSKMW/cuOUsIAbiJw3PsEmIe") // Hashed "123"
                    .role("admin")
                    .build());
                
                userRepository.save(User.builder()
                    .name("Rahul")
                    .email("guide@test.com")
                    .password("$2a$10$/ck7Fgd2hXz9wL376D0ONuD1VcmGtnSKMW/cuOUsIAbiJw3PsEmIe")
                    .role("guide")
                    .build());
            }

            // 2. Initialize Monuments (Using 'description' to match your DB)
            if (monumentRepository.count() == 0) {
                monumentRepository.save(Monument.builder()
                    .name("Taj Mahal")
                    .location("Agra, Uttar Pradesh")
                    .description("An immense mausoleum of white marble, built between 1631 and 1648.")
                    .history("Commissioned by Shah Jahan in memory of his favorite wife, Mumtaz Mahal.")
                    .architecture("A masterpiece of Mughal architecture combining Persian and Indian styles.")
                    .mapQuery("Taj Mahal, Agra, India")
                    .guide("Rahul Sharma")
                    .build());
                
                monumentRepository.save(Monument.builder()
                    .name("Hampi Monuments")
                    .location("Vijayanagara, Karnataka")
                    .description("A grand site containing the ruins of the capital of the Vijayanagara Empire.")
                    .history("A prosperous city from the 14th century, famous for its grand Hindu temples.")
                    .architecture("Famous for its Dravidian style, especially the Vitthala Temple stone chariot.")
                    .mapQuery("Hampi, Karnataka, India")
                    .guide("Priya Patel")
                    .build());
            }

            // 3. Initialize Bookings
            if (bookingRepository.count() == 0) {
                bookingRepository.save(Booking.builder()
                    .title("Taj Mahal: Sunset Architecture Tour")
                    .type("Heritage Site")
                    .guide("Rahul Sharma")
                    .date("2026-10-25")
                    .time("17:30")
                    .status("Upcoming")
                    .build());
            }

            // 4. Initialize Forum Threads & Messages
            if (forumThreadRepository.count() == 0) {
                ForumThread t1 = ForumThread.builder()
                    .title("Best time of day to visit the Taj Mahal?")
                    .author("TravelBug99")
                    .category("Travel Advice")
                    .replies(2)
                    .lastActive("2 hours ago")
                    .conversation(new ArrayList<>())
                    .build();
                
                t1 = forumThreadRepository.save(t1);

                // Adding Messages to the Thread
                t1.getConversation().add(ThreadMessage.builder()
                    .author("TravelBug99")
                    .content("Is sunrise or sunset better for photography at the Taj Mahal?")
                    .thread(t1)
                    .build());

                t1.getConversation().add(ThreadMessage.builder()
                    .author("RahulGuide")
                    .content("Definitely sunrise! The light on the white marble is magical.")
                    .thread(t1)
                    .build());

                forumThreadRepository.save(t1);
            }
            
            System.out.println("✅ Data Initialization Complete!");
        };
    }
}
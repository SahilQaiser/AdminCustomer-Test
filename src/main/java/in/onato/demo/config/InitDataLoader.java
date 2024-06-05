package in.onato.demo.config;

import in.onato.demo.entity.User;
import in.onato.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitDataLoader {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                userRepository.save(new User("admin", passwordEncoder.encode("admin123"), true));
            }
            if (userRepository.findByUsername("customer").isEmpty()) {
                userRepository.save(new User("customer", passwordEncoder.encode("customer123"), false));
            }
        };
    }
}

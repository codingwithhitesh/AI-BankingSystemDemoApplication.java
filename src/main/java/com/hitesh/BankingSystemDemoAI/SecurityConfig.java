package com.hitesh.BankingSystemDemoAI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Completely disable CSRF protection
                .csrf(csrf -> csrf.disable())

                // 2. Explicitly permit our banking endpoints and lockdown everything else
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/banking/**").permitAll()
                        .anyRequest().authenticated()
                )

                // 3. Disable default HTTP Basic authentication (Stops the password generation)
                .httpBasic(basic -> basic.disable())

                // 4. Disable default Form Login redirect behavior
                .formLogin(form -> form.disable());

        return http.build();
    }
}


//  ******   OLDER VERSIONS    *******


/*public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disables CSRF so Postman can send POST/PUT/DELETE requests
                .csrf(csrf -> csrf.disable())

                // Allows everyone to access your banking API endpoints without logging in
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/banking/**").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}


*/


/*public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Authorize requests to the H2 console path without login
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                )
                // 2. Disable CSRF protection for H2 console (H2 console uses its own mechanics)
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                )
                // 3. Allow frames so the H2 user interface panels can render properly
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                )
                // 4. Keep your standard Basic Auth active for your Postman API requests
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}*/
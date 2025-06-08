package com.chatapp.chatapp.Utils.SpringSecurity;

import com.chatapp.chatapp.Dataloader.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;
    public final List<String> allowedEndpoints = List.of(
            "/api/auth/**",
            "/public/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html");

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // ✅ Security filter chain using DB-backed user authentication
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 🔓 CSRF disabled only for allowed endpoints
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(allowedEndpoints.toArray(new String[0]))
                )

                // 🔓 Permit public endpoints
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(allowedEndpoints.toArray(new String[0])).permitAll()
                        .anyRequest().authenticated()
                )

                // 🌐 Enable CORS
                .cors(Customizer.withDefaults())

                // 🔐 Optional: Basic auth, form login, logout
                .httpBasic(Customizer.withDefaults())

                .formLogin(Customizer.withDefaults())  // ✅ Uses default login page at /login
                .logout(Customizer.withDefaults())

                // 🧠 Stateless session (for JWT/APIs)
                .sessionManagement(session
                        -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));   // ✅ Uses default logout at /logout

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*")); // You can limit this to your frontend domain
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers( allowedEndpoints.toArray(new String[0]));

    }

    // ✅ Expose AuthenticationManager bean (needed if you plan to use it elsewhere)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider()  {
        DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
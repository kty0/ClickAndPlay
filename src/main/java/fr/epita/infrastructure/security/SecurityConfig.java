package fr.epita.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.security.core.userdetails.User;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
        entryPoint.setRealmName("My Application Realm");
        return http
                .formLogin(login -> login
                        .disable()) // Disable form login
                .httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(entryPoint))
                // .formLogin(login -> login
                //         .defaultSuccessUrl("/swagger-ui.html", true) // Redirect to Swagger UI on successful login
                //         .permitAll()) // Enable form login
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Allow access to H2 console
                        .requestMatchers( "/api/seances", "/api/seances/**").hasRole("ADMIN") // Only animateurs can
                                                                                              // create seances
                        .anyRequest().permitAll())
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**", "/api/**"))
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin()) // autoriser iframe pour h2-console
                )
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN", "ANIMATEUR", "JOUEUR_C", "JOUEUR_NC")
                .build();

        UserDetails animateur = User.withUsername("animateur")
                .password(passwordEncoder().encode("anim"))
                .roles("ANIMATEUR", "JOUEUR_C", "JOUEUR_NC")
                .build();

        UserDetails meeple = User.withUsername("meeple")
                .password(passwordEncoder().encode("meeple"))
                .roles("JOUEUR_NC") // joueur non cotisant
                .build();

        UserDetails goldenmeeple = User.withUsername("goldenmeeple")
                .password(passwordEncoder().encode("gold"))
                .roles("JOUEUR_C") // joueur cotisant
                .build();

        return new InMemoryUserDetailsManager(admin, animateur, meeple, goldenmeeple);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("basicScheme",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .addSecurityItem(new SecurityRequirement().addList("basicScheme"));
    }

}

package com.example.practice_mobilelele.config;

import com.example.practice_mobilelele.repository.UserRepository;
import com.example.practice_mobilelele.service.Impl.ApplicationUserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
public class ApplicationSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           SecurityContextRepository securityContextRepository) throws Exception {

    /*  http exposes api that allows us configure the web security  */
        http
                /*  defines which pages will be authorized  */
                .authorizeHttpRequests()
                /*  with this line we allow access to all static resources (images, CSS, js)  */
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                /*  the next line allow access to the home page, login page and registration for everyone   */
                .requestMatchers("/", "/users/login", "/users/register", "/offers/all",
                        "/users/login-error").permitAll()
                /*  next we forbid all other pages for unauthenticated users.   */
                .anyRequest().authenticated()
                .and()
                /*  configure login and login HTML form with two input fields   */
                .formLogin()
                /*  our login page is located at http://<serveraddress>>:<port>/users/login     */
                .loginPage("/users/login")
                /*  this is the name of the <input...> in the login form where the user enters his email/username/etc
                 *   the value of this input will be presented to our User details service
                 *   those that want to name the input field differently, e.g. email may change the value  below     */
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                /*  the name of the <input...> HTML field that keeps the password   */
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                /*  The place where we should land in case that the login is successful    */
                .defaultSuccessUrl("/")
                /*  The place where we should land if the login is NOT successful   */
                .failureForwardUrl("/users/login-error")
                .and()
                /*  configure logout    */
                .logout()
                /*  this is the URL which spring wll implement for me and will log the user out. Must be POST request    */
                .logoutUrl("/users/logout")
                /*  where to go after the logout.   */
                .logoutSuccessUrl("/")
                /*  remove the session from server  */
                .invalidateHttpSession(true)
                /*  delete the cookie that references my session    */
                .deleteCookies("JSESSIONID")
                .and()
                .securityContext()
                .securityContextRepository(securityContextRepository);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new ApplicationUserDetailsServiceImpl(userRepository);
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }

}

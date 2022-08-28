package com.exam.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.service.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	// using jwt tokens util it checks whether the token is valid or invalid
	@Autowired
	private JwtUtils jwtutil;// it has all the methods which validates and generates token
	// by this request we can take out header and from then through response chain
	// we will forward the request

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");
		System.out.println(requestTokenHeader);
		String username = null;
		String jwtToken = null;
		// if this two condition satisfies then only token resides
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = this.jwtutil.extractUsername(jwtToken);// by the token the username comes
			} catch (ExpiredJwtException e) {
				e.printStackTrace();
				System.out.println("jwt Token has expired");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("error");
			}
		} else {
			System.out.println("Invalid Token, Doesn't start with Bearer");
		}

		// once we get the token validate it
		// if it gets validate then only set at security context holder
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			// now load the user service
			final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			// the subclass of it was user only so definitely it will work
			if (this.jwtutil.validateToken(jwtToken, userDetails)) {
				// token is valid set authentication in terms of security context
				// null is the credentials part
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		} else {
			System.out.println("Token is not valid");
		}
		filterChain.doFilter(request, response);
	}

}

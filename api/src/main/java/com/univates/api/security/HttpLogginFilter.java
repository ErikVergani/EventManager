/**
 * Filename:    HttpLogginFilter.java
 *
 * Description: Implementation of the HttpLogginFilter class.
 *
 * Revision:    23 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.univates.api.exception.UnauthorizedException;
import com.univates.api.services.LogsService;
import com.univates.api.services.UserService;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author ev
 */
@Component
public class HttpLogginFilter implements Filter
{

    @Autowired
    private LogsService logsService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private Authenticator auth;
    
    /**
     * doFilter
     *
     */
    @Override
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain )
            throws IOException, ServletException
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String login = httpServletRequest.getHeader( "login" );
        String pass = httpServletRequest.getHeader( "pass" );
        
        if ( !auth.authenticate( login, pass ) )
        {
            throw new UnauthorizedException();
        }
        
        String user = httpServletRequest.getHeader( "user" );
        String url = httpServletRequest.getRequestURL().toString();
        String method = httpServletRequest.getMethod();
        
        logsService.saveLog( userService.getUserByLogin( user != null && !user.isEmpty() ? user : "anonymous" ), url, method );
    
        chain.doFilter( httpServletRequest, httpServletResponse );
    }
    
}

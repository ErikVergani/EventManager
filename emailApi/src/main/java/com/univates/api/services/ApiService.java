/**
 * Filename:    ApiService.java
 *
 * Description: Implementation of the ApiService class.
 *
 * Revision:    24 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.univates.api.exeptions.UnauthorizedException;
import com.univates.api.records.response.EmailResponseRecord;

/**
 * @author ev
 */
@Service
public class ApiService
{
    @Autowired
    private RestTemplate restTemplate;
    
    @Value( "${api.authUrl}" )
    private String authUrl;
    
    @Value( "${api.user}" )
    private String apiUser;
    
    @Value( "${api.pass}" )
    private String apiPass;
    
    
    public EmailResponseRecord sendEmail( String url, HttpMethod method, String user )
    {
        try
        {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType( MediaType.APPLICATION_JSON );
            headers.set( "login", apiUser );
            headers.set( "pass", apiPass );
            headers.set( "user", user );
            
            HttpEntity<EmailResponseRecord> requestEntity = new HttpEntity<>( headers );
            
            ResponseEntity<EmailResponseRecord> responseEntity = restTemplate.exchange(
                                                                            url,
                                                                            method,
                                                                            requestEntity,
                                                                            EmailResponseRecord.class
                                                                          );
            
            return responseEntity.getBody();
        }
        
        catch ( HttpClientErrorException.Unauthorized | HttpServerErrorException e )
        {
            throw new UnauthorizedException();
        }
        
        catch ( HttpClientErrorException | ResourceAccessException e )
        {
            throw e;
        }
    }
    
    public boolean authenticateUser( String user, String pass )
    {
        try
        {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType( MediaType.APPLICATION_JSON );
            headers.set( "login", apiUser );
            headers.set( "pass", apiPass );
            headers.set( "userLogin", user );
            headers.set( "userPass", pass );
            headers.set( "user", user );
            
            HttpEntity<?> requestEntity = new HttpEntity<>( headers );
            
            ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
                                                                            authUrl,
                                                                            HttpMethod.POST,
                                                                            requestEntity,
                                                                            Boolean.class
                                                                          );
            
            return responseEntity.getBody();
        }
        
        catch ( HttpClientErrorException.Unauthorized | HttpServerErrorException e )
        {
            throw new UnauthorizedException();
        }
        
        catch ( HttpClientErrorException | ResourceAccessException e )
        {
            throw e;
        }
    }
}

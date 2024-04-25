/**
 * Filename:    EmailService.java
 *
 * Description: Implementation of the EmailService class.
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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.univates.api.records.response.EmailResponseRecord;


/**
 * @author ev
 */
@Service
public class EmailService
{
    
    @Autowired
    private ApiService apiService;
    
    @Value("${api.url}")
    private String apiUrl;
    
    public ResponseEntity <EmailResponseRecord> sendEmail( Integer userId, String user )
    {
        return ResponseEntity.status( HttpStatus.OK ).body( apiService.sendEmail( apiUrl + "/"+ userId, HttpMethod.POST, user ) );
    } 
}

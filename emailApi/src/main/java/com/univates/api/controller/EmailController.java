/**
 * Filename:    EmailController.java
 *
 * Description: Implementation of the EmailController class.
 *
 * Revision:    24 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.univates.api.exeptions.UnauthorizedInternalApiException;
import com.univates.api.records.response.EmailResponseRecord;
import com.univates.api.services.ApiService;
import com.univates.api.services.EmailService;

/**
 * @author ev
 */
@RestController
@RequestMapping( "/email" )
public class EmailController
{
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private ApiService authenticator;
    
    @PostMapping( "/{id}" )
    public ResponseEntity <EmailResponseRecord> sendEmail( @RequestHeader String login, @RequestHeader String pass, @PathVariable( value = "id" ) Integer userId )
    {
        if ( authenticator.authenticateUser( login, pass ) )
        {
            return emailService.sendEmail( userId, login );
        }
        
        throw new UnauthorizedInternalApiException();
    }
}

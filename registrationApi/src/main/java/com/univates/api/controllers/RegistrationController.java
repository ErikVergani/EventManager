/**
 * Filename:    RegistrationController.java
 *
 * Description: Implementation of the RegistrationController class.
 *
 * Revision:    21 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.univates.api.exeptions.UnauthorizedInternalApiException;
import com.univates.api.records.request.RegistrationRecord;
import com.univates.api.records.response.RegistrationResponseRecord;
import com.univates.api.services.ApiService;
import com.univates.api.services.RegistrationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

/**
 * @author ev
 */
@RestController
@RequestMapping( "/registration" )
public class RegistrationController
{
    @Autowired
    private RegistrationService registrationService;
    
    @Autowired
    private ApiService authenticator;
    
    @PostMapping
    @Operation( description = "Realiza uma nova inscrição em um determinado evento" )
    @ApiResponses( value = { @ApiResponse ( responseCode = "200", description = "Retorna as informações da inscrição" ),
                             @ApiResponse ( responseCode = "401", description = "Credenciais do usuário estão incorretas" ),
                             @ApiResponse ( responseCode = "400", description = "Evento/Usuário não encontrado" ),
                             @ApiResponse ( responseCode = "500", description = "Usuário já registrado no evento" )
                           } )
    public ResponseEntity <Object> addRegistration( @RequestHeader String login, @RequestHeader String pass, @RequestBody @Valid RegistrationRecord rr )
    {
        if ( authenticator.authenticateUser( login, pass ) )
        {
            return registrationService.addRegistration( rr, login );
        }
        
        throw new UnauthorizedInternalApiException();
    }
    
    @GetMapping( "/{userId}" )
    @Operation( description = "Retorna as incrições de um usuário" )
    @ApiResponses( value = { @ApiResponse ( responseCode = "200", description = "Retorna as inscrições de um determinado usuário" ),
                             @ApiResponse ( responseCode = "401", description = "Credenciais do usuário estão incorretas" ),
                             @ApiResponse ( responseCode = "400", description = "Usuário não encontrado" ),
                           } )
    public ResponseEntity <List<RegistrationResponseRecord>> getUserRegistrations( @RequestHeader String login, @RequestHeader String pass, @PathVariable( value = "userId" ) Integer userId )
    {
        if ( authenticator.authenticateUser( login, pass ) )
        {
            return ResponseEntity.status( HttpStatus.OK ).body( registrationService.getRegistrations( userId, login ) );
        }
        
        throw new UnauthorizedInternalApiException();
    }
    
    @PostMapping( "/cancel" ) 
    @Operation( description = "Cancela a inscrição de um usuário" )
    @ApiResponses( value = { @ApiResponse ( responseCode = "200", description = "Retorna a incrição com o status \"cancelada\"" ),
                             @ApiResponse ( responseCode = "401", description = "Credenciais do usuário estão incorretas" ),
                             @ApiResponse ( responseCode = "400", description = "Evento/Usuário não encontrado" ),
                             @ApiResponse ( responseCode = "500", description = "Usuário já cancelou a inscrição no evento" )
                           } )
    ResponseEntity<RegistrationResponseRecord> cancelRegistration( @RequestHeader String login, @RequestHeader String pass, @RequestParam( value = "eventId" ) Integer eventId, @RequestParam( value = "userId" ) Integer userId )
    {
        if ( authenticator.authenticateUser( login, pass ) )
        {
            return  registrationService.cancelRegistration( eventId, userId, login );
        }
        
        throw new UnauthorizedInternalApiException();
    }
    
    @PostMapping( "/checkin" ) 
    @Operation( description = "Realiza o check-in do usuário no evento" )
    @ApiResponses( value = { @ApiResponse ( responseCode = "200", description = "Retorna a incrição do evento com o status \"check-in realizado\"" ),
                             @ApiResponse ( responseCode = "401", description = "Credenciais do usuário estão incorretas" ),
                             @ApiResponse ( responseCode = "400", description = "Evento/Usuário não encontrado" ),
                             @ApiResponse ( responseCode = "500", description = "Check-in do usuário já foi realizado no evento" )
                           } )
    ResponseEntity<RegistrationResponseRecord> checkIn( @RequestHeader String login, @RequestHeader String pass, @RequestParam( value = "eventId" ) Integer eventId, @RequestParam( value = "userId" ) Integer userId )
    {
        if ( authenticator.authenticateUser( login, pass ) )
        {
            return registrationService.checkIn( eventId, userId, login );
        }
        
        throw new UnauthorizedInternalApiException();
    }
}

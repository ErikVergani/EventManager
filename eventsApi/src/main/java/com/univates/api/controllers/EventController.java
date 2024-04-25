/**
 * Filename:    EventController.java
 *
 * Description: Implementation of the EventController class.
 *
 * Revision:    20 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.univates.api.exeptions.UnauthorizedInternalApiException;
import com.univates.api.records.request.EventRecord;
import com.univates.api.records.response.EventResponseRecord;
import com.univates.api.records.response.EventUsersResponseRecord;
import com.univates.api.services.ApiService;
import com.univates.api.services.EventService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

/**
 * @author ev
 */
@RestController
@RequestMapping( "/event" )
public class EventController
{
    @Autowired
    private EventService eventService;
    
    @Autowired
    private ApiService authenticator;
    
    @PostMapping
    @Operation( description = "Adciona um novo evento" )
    @ApiResponses( value = { @ApiResponse ( responseCode = "200", description = "Retorna o novo evento" ),
                             @ApiResponse ( responseCode = "401", description = "Credenciais do usuário estão incorretas" ),
                           } )
    public ResponseEntity<EventResponseRecord> addEvent( @RequestHeader String login, @RequestHeader String pass, @RequestBody @Valid EventRecord er )
    {
        if ( authenticator.authenticateUser( login, pass ) )
        {
            return eventService.saveEvent( er, login );
        }
        
        throw new UnauthorizedInternalApiException();
    }
    
    @PutMapping( "/{id}" )
    @Operation( description = "Atualiza as informações de um evento ( Disponível apenas para ADM )" )
    @ApiResponses( value = { @ApiResponse ( responseCode = "200", description = "Retorna o evento atualizado" ),
                             @ApiResponse ( responseCode = "401", description = "Credenciais do usuário estão incorretas" ),
                             @ApiResponse ( responseCode = "400", description = "Evento não encontrado" ),
                           } )
    public ResponseEntity<EventResponseRecord> updateEvent( @RequestHeader String login, @RequestHeader String pass, @PathVariable( value = "id" ) Integer id, @RequestBody @Valid EventRecord er )
    {
        if ( authenticator.authenticateUser( login, pass ) )
        {
            return eventService.saveEvent( id, er, login );
        }
        
        throw new UnauthorizedInternalApiException();
    }
    
    @GetMapping
    @Operation( description = "Retorna os eventos disponíveis ( 1 dia de antecedencia )" )
    @ApiResponses( value = { @ApiResponse ( responseCode = "200", description = "Retorna os eventos disponíveis" ),
                             @ApiResponse ( responseCode = "401", description = "Credenciais do usuário estão incorretas" ),
                            } )
    public ResponseEntity<List<EventResponseRecord>> getEvents()
    {
        return eventService.getAll();
    }
    
    @GetMapping( "/{id}" )
    @Operation( description = "Retorna os eventos que um usuário está inscrito." )
    @ApiResponses( value = { @ApiResponse ( responseCode = "200", description = "Retorna os eventos de um usuário" ),
                             @ApiResponse ( responseCode = "401", description = "Credenciais do usuário estão incorretas" ),
                             @ApiResponse ( responseCode = "400", description = "Evento/Usuário não encontrado" ),
                           } )
    public ResponseEntity <EventUsersResponseRecord> getEventUsers(  @RequestHeader String login, @RequestHeader String pass, @PathVariable( value = "id" ) Integer id )
    {
        if ( authenticator.authenticateUser( login, pass ) )
        {
            return eventService.getEventUsers( id, login );
        }
           
        throw new UnauthorizedInternalApiException();
    }
}

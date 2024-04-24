/**
 * Filename:    UserController.java
 *
 * Description: Implementation of the UserController class.
 *
 * Revision:    19 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.univates.api.records.request.UserRecord;
import com.univates.api.records.response.UserResponseRecord;
import com.univates.api.services.ApiService;
import com.univates.api.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

/**
 * @author ev
 */
@RestController
@RequestMapping( "/user" )
public class UserController
{
    @Autowired
    private UserService userService;
    
    @Autowired
    private ApiService authenticator;
    
    @PostMapping
    @Operation( description = "Realiza o cadastro de um usuário" )
    @ApiResponses( value = { @ApiResponse ( responseCode = "200", description = "Retorna as informações do novo usuário" ),
                             @ApiResponse ( responseCode = "401", description = "Credenciais da API intermediária estão incorretas" ),
                             @ApiResponse ( responseCode = "400", description = "Login indisponível" ),
                           } )
    public ResponseEntity<UserResponseRecord> addUser( @RequestBody @Valid UserRecord ur )
    {
        return userService.saveUser( ur );
    }
    
    @PostMapping( "/auth" )
    @Operation( description = "Realiza a autenticação do usuário, garantindo que seu usuário e senha estão corretos" )
    @ApiResponses( value = { @ApiResponse ( responseCode = "200", description = "Retorna a informação que o usuário está autenticado" ),
                             @ApiResponse ( responseCode = "401", description = "Retorna que o usuário ou a senha estão incorretos" ),
                           } )
    public ResponseEntity<Object> auth( @RequestHeader String login, @RequestHeader String pass )
    {
        return authenticator.authenticateUser( login, pass ) ? ResponseEntity.status( HttpStatus.OK ).body( "Autenticado!" ) 
                                                             : ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( "Usuário ou senha inválidos!" );
    }
}
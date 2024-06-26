/**
 * Filename:    UserService.java
 *
 * Description: Implementation of the UserService class.
 *
 * Revision:    19 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.univates.api.records.request.UserRecord;
import com.univates.api.records.response.UserResponseRecord;

import jakarta.validation.Valid;

/**
 * @author ev
 */
@Service
public class UserService
{
    @Autowired
    private ApiService apiService;
    
    /**
     * saveUser
     *
     */
    public ResponseEntity <UserResponseRecord> saveUser( @Valid UserRecord ur )
    {
        return ResponseEntity.status( HttpStatus.OK ).body( apiService.callApi( ur ) );
    }
}

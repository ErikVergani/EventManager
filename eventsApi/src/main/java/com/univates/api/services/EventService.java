/**
 * Filename:    EventService.java
 *
 * Description: Implementation of the EventService class.
 *
 * Revision:    20 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.univates.api.records.request.EventRecord;
import com.univates.api.records.response.EventResponseRecord;
import com.univates.api.security.ApiConfig;

import jakarta.validation.Valid;

/**
 * @author ev
 */
@Controller
public class EventService
{
    @Autowired
    private ApiService apiService;
    
    /**
     * saveUser
     *
     */
    public ResponseEntity <EventResponseRecord> saveEvent( @Valid EventRecord er, String user )
    {
        return ResponseEntity.status( HttpStatus.OK ).body( apiService.returnEvent( er, ApiConfig.API_URL, HttpMethod.POST, user ) );
    }

    /**
     * saveEvent
     *
     */
    public ResponseEntity<EventResponseRecord> saveEvent( Integer id, @Valid EventRecord er, String user )
    {
        return ResponseEntity.status( HttpStatus.OK ).body( apiService.returnEvent( er, ApiConfig.API_URL + "/" + id, HttpMethod.PUT, user ) );
    }

    /**
     * getAll
     *
     */
    public ResponseEntity<List<EventResponseRecord>> getAll()
    {
        return ResponseEntity.status( HttpStatus.OK ).body( apiService.getEvents( ApiConfig.API_URL, HttpMethod.GET, null ) );
    }

    /**
     * getUserEvents
     *
     */
    public ResponseEntity<List<EventResponseRecord>> getUserEvents( Integer id, String user )
    {
        return ResponseEntity.status( HttpStatus.OK ).body( apiService.getEvents( ApiConfig.API_URL + "/" + id, HttpMethod.GET, user ) );
    }
}
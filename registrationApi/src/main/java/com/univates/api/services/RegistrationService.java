/**
 * Filename:    RegistrationService.java
 *
 * Description: Implementation of the RegistrationService class.
 *
 * Revision:    21 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.univates.api.records.request.RegistrationRecord;
import com.univates.api.records.response.RegistrationResponseRecord;

import jakarta.validation.Valid;

/**
 * @author ev
 */
@Service
public class RegistrationService
{
    
    @Autowired
    private ApiService apiService;

    @Value( "${api.url}" )
    private String apiUrl;
    
    public ResponseEntity <Object> addRegistration( @Valid RegistrationRecord rr, String user )
    {
        return ResponseEntity.status( HttpStatus.OK ).body( apiService.returnRegistration( rr, apiUrl, HttpMethod.POST, user ) );
    }

    public List<RegistrationResponseRecord> getRegistrations( Integer userId, String user )
    {
        return apiService.returnRegistrations( apiUrl + "/" + userId, HttpMethod.GET, user );
    }

    public ResponseEntity<RegistrationResponseRecord> checkIn( Integer eventId, Integer userId, String user )
    {
        return ResponseEntity.status( HttpStatus.OK ).body( apiService.returnRegistration( null, apiUrl + "/checkin?eventId=" + eventId + "&userId=" + userId, HttpMethod.POST, user ) );
    }

    public ResponseEntity <RegistrationResponseRecord> cancelRegistration( Integer eventId, Integer userId, String user )
    {
        return ResponseEntity.status( HttpStatus.OK ).body( apiService.returnRegistration( null, apiUrl + "/cancel?eventId=" + eventId + "&userId=" + userId, HttpMethod.POST, user ) );
    }
    
}

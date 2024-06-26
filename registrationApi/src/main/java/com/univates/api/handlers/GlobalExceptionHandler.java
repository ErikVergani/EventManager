/**
 * Filename:    GlobalExceptionHandler.java
 *
 * Description: Implementation of the GlobalExceptionHandler class.
 *
 * Revision:    22 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import com.univates.api.exeptions.UnauthorizedException;
import com.univates.api.exeptions.UnauthorizedInternalApiException;
import com.univates.api.records.response.ErrorResponseRecord;


/**
 * @author ev
 */
@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler( UnauthorizedException.class )
    @ResponseBody
    @ResponseStatus( HttpStatus.UNAUTHORIZED )
    public ErrorResponseRecord handler( UnauthorizedException e )
    {
        return new ErrorResponseRecord( e.getMessage() );
    }
    
    @ExceptionHandler( HttpClientErrorException.class )
    @ResponseBody
    public ResponseEntity<ErrorResponseRecord> handler( HttpClientErrorException e )
    {
        return new ResponseEntity<ErrorResponseRecord>( e.getResponseBodyAs( ErrorResponseRecord.class ), e.getStatusCode() );
    }
    
    @ExceptionHandler( ResourceAccessException.class )
    @ResponseBody
    public ResponseEntity<ErrorResponseRecord> handler( ResourceAccessException e )
    {
        return new ResponseEntity<ErrorResponseRecord>( new ErrorResponseRecord( e.getRootCause().getMessage() + " -> API indisponível no momento" ), HttpStatus.BAD_REQUEST );
    }
    
    @ExceptionHandler( UnauthorizedInternalApiException.class )
    @ResponseBody
    @ResponseStatus( HttpStatus.UNAUTHORIZED )
    public ErrorResponseRecord handler( UnauthorizedInternalApiException e )
    {
        return new ErrorResponseRecord( e.getMessage() );
    }
    
}

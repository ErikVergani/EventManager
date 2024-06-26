/**
 * Filename:    RegistrationResponseRecord.java
 *
 * Description: Implementation of the RegistrationResponseRecord class.
 *
 * Revision:    21 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.records.response;


/**
 * @author ev
 */
public record RegistrationResponseRecord( String userName, String eventName, Integer eventId, String status, String registrationDate )
{
    
}

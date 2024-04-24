/**
 * Filename:    EventResponseRecord.java
 *
 * Description: Implementation of the EventResponseRecord class.
 *
 * Revision:    15 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.records.response;

import java.time.LocalDateTime;

/**
 * @author ev
 */
public record EventResponseRecord( Integer eventId, String eventName, LocalDateTime eventDate )
{
    
}

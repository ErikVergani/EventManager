/**
 * Filename:    EventUsersResponseRecord.java
 *
 * Description: Implementation of the EventUsersResponseRecord class.
 *
 * Revision:    24 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.records.response;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ev
 */
public record EventUsersResponseRecord( Integer eventId, String eventName, LocalDateTime eventDate, List<String> users )
{
}

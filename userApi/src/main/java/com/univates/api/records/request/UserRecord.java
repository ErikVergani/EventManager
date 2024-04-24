/**
 * Filename:    UserRecord.java
 *
 * Description: Implementation of the UserRecord class.
 *
 * Revision:    15 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.records.request;

/**
 * @author ev
 */
public record UserRecord( String login, String password, String name, String email ){}

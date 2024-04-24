/**
 * Filename:    UnauthorizedInternalApiException.java
 *
 * Description: Implementation of the UnauthorizedInternalApiException class.
 *
 * Revision:    22 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.exception;


/**
 * @author ev
 */
public class UnauthorizedInternalApiException extends RuntimeException
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * UnauthorizedInternalApiException
     *
     */
    public UnauthorizedInternalApiException()
    {
        super( "Usuário ou senha inválidos" );
    }
}

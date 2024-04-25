/**
 * Filename:    UnauthorizedException.java
 *
 * Description: Implementation of the UnauthorizedException class.
 *
 * Revision:    21 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.exeptions;


/**
 * @author ev
 */
public class UnauthorizedException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public UnauthorizedException()
    {
        super( "Acesso negado!" );
    }
}

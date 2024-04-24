/**
 * Filename:    Log.java
 *
 * Description: Implementation of the Log class.
 *
 * Revision:    22 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author ev
 */
@Entity
@Table( name = "logs" )
public class Log
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Integer id;
    
    @ManyToOne
    @JoinColumn( name = "user_id" )
    private User user;
    
    private String url;
    private String method;
    private LocalDateTime dtAccess = LocalDateTime.now();
    
    
    /**
     * Log
     *
     */
    public Log( User user, String url, String method )
    {
        this.user = user;
        this.url = url;
        this.method = method;
    }

    
    /**
     * getMethod
     *
     * @return method String
     */
    public String getMethod()
    {
        return method;
    }
    
    /**
     * setMethod
     *
     * @param method String
     */
    public void setMethod( String method )
    {
        this.method = method;
    }

    /**
     * getId
     *
     * @return id Integer
     */
    public Integer getId()
    {
        return id;
    }

    
    /**
     * setId
     *
     * @param id Integer
     */
    public void setId( Integer id )
    {
        this.id = id;
    }

    /**
     * getUser
     *
     * @return user User
     */
    public User getUser()
    {
        return user;
    }
    
    /**
     * getUrl
     *
     * @return url String
     */
    public String getUrl()
    {
        return url;
    }
    
    /**
     * getDtAccess
     *
     * @return dtAccess LocalDateTime
     */
    public LocalDateTime getDtAccess()
    {
        return dtAccess;
    }
    
    /**
     * setUser
     *
     * @param user User
     */
    public void setUser( User user )
    {
        this.user = user;
    }
    
    /**
     * setUrl
     *
     * @param url String
     */
    public void setUrl( String url )
    {
        this.url = url;
    }
    
    /**
     * setDtAccess
     *
     * @param dtAccess LocalDateTime
     */
    public void setDtAccess( LocalDateTime dtAccess )
    {
        this.dtAccess = dtAccess;
    }
}
/**
 * Filename:    User.java
 *
 * Description: Implementation of the user class.
 *
 * Revision:    14 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * @author ev
 */
@Entity
@Table( name = "users" )
public class User
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    
    @Column( unique = true )
    private String login;
    private String password;
    private String name;
    private String email;
    
    @OneToMany( mappedBy = "user" )
    private List <Registration> registrations;
    
    @OneToMany( mappedBy = "user" )
    @JsonIgnore
    private List<Log> log;

    
    
    
    /**
     * getLog
     *
     * @return log List<Log>
     */
    public List <Log> getLog()
    {
        return log;
    }

    /**
     * setLog
     *
     * @param log List<Log>
     */
    public void setLog( List <Log> log )
    {
        this.log = log;
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
     * getLogin
     *
     * @return login String
     */
    public String getLogin()
    {
        return login;
    }

    
    /**
     * getPassword
     *
     * @return password String
     */
    public String getPassword()
    {
        return password;
    }

    
    /**
     * getName
     *
     * @return name String
     */
    public String getName()
    {
        return name;
    }

    
    /**
     * getEmail
     *
     * @return email String
     */
    public String getEmail()
    {
        return email;
    }

    
    /**
     * getRegistrations
     *
     * @return registrations List<Registration>
     */
    public List <Registration> getRegistrations()
    {
        return registrations;
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
     * setLogin
     *
     * @param login String
     */
    public void setLogin( String login )
    {
        this.login = login;
    }

    
    /**
     * setPassword
     *
     * @param password String
     */
    public void setPassword( String password )
    {
        this.password = password;
    }

    
    /**
     * setName
     *
     * @param name String
     */
    public void setName( String name )
    {
        this.name = name;
    }

    
    /**
     * setEmail
     *
     * @param email String
     */
    public void setEmail( String email )
    {
        this.email = email;
    }

    
    /**
     * setRegistrations
     *
     * @param registrations List<Registration>
     */
    public void setRegistrations( List <Registration> registrations )
    {
        this.registrations = registrations;
    }
    
    
}

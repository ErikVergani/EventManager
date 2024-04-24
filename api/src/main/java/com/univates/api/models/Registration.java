/**
 * Filename:    Registration.java
 *
 * Description: Implementation of the Registration class.
 *
 * Revision:    14 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.univates.api.enums.RegistrationState;
import com.univates.api.utils.RegistrationStateConverter;

import jakarta.persistence.Convert;
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
@Table( name = "registrations" )
public class Registration
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    
    @ManyToOne
    @JoinColumn( name = "user_id" )
    @JsonIgnore
    private User user;
    
    @ManyToOne
    @JoinColumn( name = "event_id" )
    @JsonIgnore
    private Event event;
    
    @Convert( converter = RegistrationStateConverter.class )
    private RegistrationState state = RegistrationState.NEW;
    
    private LocalDateTime registerDate = LocalDateTime.now();
    
    
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
     * getUser
     *
     * @return user User
     */
    public User getUser()
    {
        return user;
    }

    
    /**
     * getEvent
     *
     * @return event Event
     */
    public Event getEvent()
    {
        return event;
    }

    
    /**
     * getState
     *
     * @return state RegistrationState
     */
    public RegistrationState getState()
    {
        return state;
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
     * setUser
     *
     * @param user User
     */
    public void setUser( User user )
    {
        this.user = user;
    }

    
    /**
     * setEvent
     *
     * @param event Event
     */
    public void setEvent( Event event )
    {
        this.event = event;
    }

    
    /**
     * setState
     *
     * @param state RegistrationState
     */
    public void setState( RegistrationState state )
    {
        this.state = state;
    }


    
    /**
     * getRegisterDate
     *
     * @return registerDate LocalDateTime
     */
    public LocalDateTime getRegisterDate()
    {
        return registerDate;
    }


    
    /**
     * setRegisterDate
     *
     * @param registerDate LocalDateTime
     */
    public void setRegisterDate( LocalDateTime registerDate )
    {
        this.registerDate = registerDate;
    }
}

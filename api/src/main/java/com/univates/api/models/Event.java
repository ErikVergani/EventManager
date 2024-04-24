/**
 * Filename:    Event.java
 *
 * Description: Implementation of the Event class.
 *
 * Revision:    14 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.models;

import java.time.LocalDateTime;
import java.util.List;

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
@Table( name = "events" )
public class Event
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    
    private String name;
    private LocalDateTime date;
    
    @OneToMany( mappedBy = "event" )
    private List <Registration> registrations;

    
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
     * getName
     *
     * @return name String
     */
    public String getName()
    {
        return name;
    }

    
    /**
     * getDate
     *
     * @return date LocalDate
     */
    public LocalDateTime getDate()
    {
        return date;
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
     * setName
     *
     * @param name String
     */
    public void setName( String name )
    {
        this.name = name;
    }

    
    /**
     * setDate
     *
     * @param date LocalDate
     */
    public void setDate( LocalDateTime date )
    {
        this.date = date;
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

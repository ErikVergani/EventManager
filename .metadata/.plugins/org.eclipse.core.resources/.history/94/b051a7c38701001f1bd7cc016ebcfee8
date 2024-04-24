/**
 * Filename:    EmailService.java
 *
 * Description: Implementation of the EmailService class.
 *
 * Revision:    19 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.univates.api.models.Event;
import com.univates.api.models.User;

/**
 * @author ev
 */
@Service
public class EmailService
{
    @Autowired
    private JavaMailSender sender;
    
    @Value( "${spring.mail.username}" )
    private String from;
    
    public void sendEventRegister( User user, Event event, LocalDateTime registerDay )
    {
        String subject = "Inscrição realizada com sucesso!";
        
        String message = "Olá " + user.getName() + ", ficamos felizes com a sua participação! O evento " + event.getName()
                       + " ocorrerá no dia " + event.getDate().format( DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm") ) + ".\n"
                       + "Inscrição realizada no dia: " + registerDay.format( DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm") );
        
        sendEmail( user.getEmail(), subject, message );
      
    }
    
    public void sendCancelationEmail( User user, Event event )
    {
        String subject = "Cancelamento realizado com sucesso!";
        
        String message = "Olá " + user.getName() + ", sua participação para o evento " + event.getName() + " foi cancelada com sucesso!\n";
        
        sendEmail( user.getEmail(), subject, message );
    }
    
    public void sendCheckinConfirmation( User user, Event event )
    {
        String subject = "Check-in realizado com sucesso!";
        
        String message = "Olá " + user.getName() + ", seu check-in no evento " + event.getName() + " foi realizado com sucesso!\n";
        
        sendEmail( user.getEmail(), subject, message );
    }
    
    private void sendEmail( String to, String subject, String message )
    {
        try
        {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom( from );
            simpleMailMessage.setTo( to );
            simpleMailMessage.setSubject( subject );
            simpleMailMessage.setText( message );
            
            sender.send( simpleMailMessage );
        }
        
        catch ( Exception e )
        {
            throw new RuntimeException( "Cannot send email" );
        }
    }
}

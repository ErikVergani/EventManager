/**
 * Filename:    EventRecord.java
 *
 * Description: Implementation of the EventRecord class.
 *
 * Revision:    15 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.records.request;

import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author ev
 */
public record EventRecord( @Validated @NotBlank String name,  @Validated @NotNull LocalDateTime date ){}

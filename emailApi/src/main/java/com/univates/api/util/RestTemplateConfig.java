/**
 * Filename:    RequestProvider.java
 *
 * Description: Implementation of the RequestProvider class.
 *
 * Revision:    19 de abr. de 2024
 *
 * Author:      Erik Freire Vergani
 * EMail:       efvergani@hotmail.com.br
 *
 */

package com.univates.api.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author ev
 */
@Configuration
public class RestTemplateConfig
{
    @Bean
    RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}

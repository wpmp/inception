/*
 * Copyright 2018
 * Ubiquitous Knowledge Processing (UKP) Lab
 * Technische Universität Darmstadt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.tudarmstadt.ukp.inception.externalsearch.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import de.tudarmstadt.ukp.inception.externalsearch.ExternalSearchProviderFactory;
import de.tudarmstadt.ukp.inception.externalsearch.ExternalSearchProviderRegistry;
import de.tudarmstadt.ukp.inception.externalsearch.ExternalSearchProviderRegistryImpl;
import de.tudarmstadt.ukp.inception.externalsearch.ExternalSearchService;
import de.tudarmstadt.ukp.inception.externalsearch.ExternalSearchServiceImpl;

/**
 * Provides all back-end Spring beans for the external search functionality.
 */
@Configuration
@ConditionalOnProperty(prefix = "external-search", name = "enabled", havingValue = "true", 
        matchIfMissing = false)
public class ExternalSearchAutoConfiguration
{
    @Bean
    @Autowired
    public ExternalSearchService externalSearchService(ExternalSearchProviderRegistry aRegistry)
    {
        return new ExternalSearchServiceImpl(aRegistry);
    }
    
    @Bean
    public ExternalSearchProviderRegistry externalSearchProviderRegistry(
            @Lazy @Autowired(required = false) List<ExternalSearchProviderFactory> aProviders)
    {
        return new ExternalSearchProviderRegistryImpl(aProviders);
    }
}

package com.senac.gestao.config;

import com.senac.gestao.dtos.EstoqueRequest;
import com.senac.gestao.models.Estoque;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setAmbiguityIgnored(true);

        mapper.typeMap(EstoqueRequest.class, Estoque.class)
                .addMappings(m -> m.skip(Estoque::setId));

        return mapper;
    }
}

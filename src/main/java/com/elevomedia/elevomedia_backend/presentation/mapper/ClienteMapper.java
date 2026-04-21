package com.elevomedia.elevomedia_backend.presentation.mapper;

import com.elevomedia.elevomedia_backend.domain.model.Cliente;
import com.elevomedia.elevomedia_backend.presentation.dto.request.ClienteRequestDTO;
import com.elevomedia.elevomedia_backend.presentation.dto.response.ClienteResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "atualizadoEm", ignore = true)
    @Mapping(source = "oQueVende", target = "OQueVende")
    Cliente toEntity(ClienteRequestDTO dto);

    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "OQueVende", target = "oQueVende")
    ClienteResponseDTO toResponse(Cliente cliente);
}

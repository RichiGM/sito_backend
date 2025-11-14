package com.sito.sito.cqrs.queries;

import lombok.Data;

@Data
public class GetAlumnosGrupoQuery {
    private Integer grupoId;

    public GetAlumnosGrupoQuery(Integer grupoId) {
        this.grupoId = grupoId;
    }
}
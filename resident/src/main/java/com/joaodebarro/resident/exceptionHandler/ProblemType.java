package com.joaodebarro.resident.exceptionHandler;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public enum ProblemType {

    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
    INVALID_DATA("/invalid-data","Invalid data"),
    ACESSO_NEGADO("/acesso-negado", "Acesso negado"),
    ACCESS_DENIED("/access-denied","Access denied"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    SYSTEM_ERROR("/system-error","System error"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
    INVALID_PARAMETER("/invalid-parameter","Invalid parameter"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
    INCOMPREHENSIBLE_MESSAGE("/incomprehensible-message","Incomprehensible message"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    RESOURCE_NOT_FOUND("/resource-not-found","Resource not found"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ENTITY_IN_USE("/entity-in-use","Entity in use"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    BUSINESS_ERROR("/business_error","Business Rule Violation");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://site.com.br" + path;
        this.title = title;
    }

}
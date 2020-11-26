#!/usr/bin/env bash
# tag::adocSnippet[]
cd bbva-simulador/simulador
mvn io.quarkus:quarkus-maven-plugin:1.2.0.Final:create \
    -DprojectGroupId=bbva.ninjahack.lamina.simulador \
    -DprojectArtifactId=rest-usuario \
    -DclassName="bbva.ninjahack.lamina.simulador.usuario.UsuarioResource" \
    -Dpath="api/usuarios"
# end::adocSnippet[]

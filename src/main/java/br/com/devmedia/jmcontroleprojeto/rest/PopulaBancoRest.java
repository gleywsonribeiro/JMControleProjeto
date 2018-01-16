/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.jmcontroleprojeto.rest;

import br.com.devmedia.jmcontroleprojeto.entidades.Estado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Gleywson
 */
@Stateless
@Path(value = "/populaBancoRest")
public class PopulaBancoRest {

    @PersistenceContext(unitName = "ControleProjetoPU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response popularTabelasEstadosCidades() {

        Query query = em.createNamedQuery("Estado.findAll");

        List<Estado> estados = query.getResultList();

        if (estados.isEmpty()) {
            popularBanco();
            estados = query.getResultList();
        }

        return Response.ok().build();
    }

    private void popularBanco() {

        Estado saoPaulo = new Estado();
        saoPaulo.setNome("Sao Paulo");
        em.persist(saoPaulo);

        Estado minasGerais = new Estado();
        minasGerais.setNome("Minas Gerais");
        em.persist(minasGerais);

        Estado rioJaneiro = new Estado();
        rioJaneiro.setNome("Rio de Janeiro");
        em.persist(rioJaneiro);

        criarCidades();
    }

    private void criarCidades() {
        //Código para criar as cidades no banco de dados
    }
}

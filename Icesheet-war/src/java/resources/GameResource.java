/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import dtos.GameEJBDTO;
import ejbs.GamesFacadeBean;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author David
 */
@Path("game")
public class GameResource {
    GamesFacadeBean gamesFacadeBean = lookupGamesFacadeBeanBean();

    @Context
    private UriInfo context;

    /**
     * Retrieves representation of an instance of resources.TeamsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    @Path("/{gameid}")
    public GameEJBDTO getGameJson(@PathParam("gameid") Integer gameid) {
        return gamesFacadeBean.readGameById(gameid);
    }
    
    @PUT
    @Consumes("application/json")
    public GameEJBDTO updateGameFromJson(GameEJBDTO game) {
        return gamesFacadeBean.UpdateGame(game);
    }

    private GamesFacadeBean lookupGamesFacadeBeanBean() {
        try {
            javax.naming.Context c = new InitialContext();
            return (GamesFacadeBean) c.lookup("java:global/Icesheet/Icesheet-ejb/GamesFacadeBean!ejbs.GamesFacadeBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}

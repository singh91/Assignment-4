/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Connection.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

/**
 *
 * @author c0655613
 */
@Path("/products")
public class newServlet {

    @GET
    @Produces("application/json")
    public Response getAll() {

        return Response.ok(getResult("SELECT * FROM product")).build();
        // return Response.entity(getResult("SELECT * FROM product")).build();

    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") String id) {

        return Response.ok(getResult("SELECT * FROM product WHERE productID=?", String.valueOf(id))).build();

        // return Response.entity(getResult("SELECT * FROM product")).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response add(JsonObject json) {

        String name = json.getString("name");
        String description = json.getString("description");
        String quantity = String.valueOf(json.getInt("quantity"));

        System.out.println(name + '\t' + description + '\t' + quantity);

        int result = doUpdate("INSERT INTO product (name,description,quantity) VALUES (?,?,?)", name, description, quantity);
        if (result <= 0) {
            return Response.status(500).build();
        } else {
            return Response.ok(json).build();
        }
    }

}

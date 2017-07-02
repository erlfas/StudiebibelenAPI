package no.fasmer.studiebibelen.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import no.fasmer.studiebibelen.model.Kapittel;
import org.bson.Document;
import org.jboss.logging.Logger;

@Path("kapittel")
public class KapittelResource {

    @Inject
    private MongoClient mongoClient;
    
    @Inject
    private Logger logger;

    @GET
    @Path("{bok}")
    public List<Kapittel> getKapitler(@PathParam("bok") String bok) {
        logger.info("GET kapittel/" + bok);
        
        final ObjectMapper objectMapper = new ObjectMapper();
        
        final MongoDatabase db = mongoClient.getDatabase("studiebibelen");
        final MongoCollection<Document> results = db.getCollection("kapittel");
        
        final List<Kapittel> kapitler = new ArrayList<>();
        
        final Consumer<Document> consumer = d -> {
            final Kapittel kapittel = objectMapper.convertValue(d.toJson(), Kapittel.class);
            kapitler.add(kapittel);
        };
        
        results.find(eq("bok", bok)).forEach(consumer);
        
        return kapitler;
    }
    
    
}

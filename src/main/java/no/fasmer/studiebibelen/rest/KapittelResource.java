package no.fasmer.studiebibelen.rest;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import no.fasmer.studiebibelen.model.Kapittel;
import no.fasmer.studiebibelen.model.Vers;
import org.bson.Document;
import org.jboss.logging.Logger;

@Path("kapittel")
public class KapittelResource {

    @Inject
    private MongoClient mongoClient;

    @Inject
    private Logger logger;
    
    
    /**
     * Eksempel:
     * http://localhost:8080/Studiebibelen/rest/kapittel/Matt
     */
    @GET
    @Path("{bok}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Kapittel> getKapitler(@PathParam("bok") String bok) {
        logger.info("GET kapittel/" + bok);

        final MongoDatabase db = mongoClient.getDatabase("studiebibelen");
        final MongoCollection<Document> documents = db.getCollection("kapittel");

        final List<Kapittel> kapitler = new ArrayList<>();
        final Consumer<Kapittel> addToList = kapittel -> {
            kapitler.add(kapittel);
        };

        documents.find(eq("bok", bok)).map(doc -> {
            final Kapittel kapittel = new Kapittel();
            kapittel.setBok(doc.getString("bok"));
            kapittel.setKapittel(doc.getString("kapittel"));
            
            final Document versDocument = (Document) doc.get("vers");
            final Set<String> versKeys = versDocument.keySet();
            
            final List<Vers> versene = new ArrayList<>();
            for (String versKey : versKeys) {
                final Vers vers = new Vers();
                vers.setVers(versKey);
                vers.setTekst(versDocument.getString(versKey));
                versene.add(vers);
            }
            kapittel.setVers(versene);
            
            return kapittel;
        }).forEach(addToList);

        return kapitler;
    }

}

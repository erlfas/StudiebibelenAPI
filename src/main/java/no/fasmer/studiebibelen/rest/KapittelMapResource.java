package no.fasmer.studiebibelen.rest;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import no.fasmer.studiebibelen.model.KapittelMap;
import org.bson.Document;
import org.jboss.logging.Logger;

@Path("kapittelmap")
public class KapittelMapResource {

    @Inject
    private MongoClient mongoClient;

    @Inject
    private Logger logger;

    /**
     * http://localhost:8080/Studiebibelen/rest/kapittelmap/alle
     *
     * @return liste med alle KapittelMap.
     */
    @GET
    @Path("alle")
    @Produces(MediaType.APPLICATION_JSON)
    public List<KapittelMap> getAlleKapittelMaps() {
        logger.info("/Studiebibelen/rest/kapittelmap/alle");
        
        final MongoDatabase db = mongoClient.getDatabase("studiebibelen");
        final MongoCollection<Document> documents = db.getCollection("kapittelmap");

        final List<KapittelMap> kapittelMaps = new ArrayList<>();
        final Consumer<KapittelMap> addToList = kapittel -> {
            kapittelMaps.add(kapittel);
        };

        documents.find().map(doc -> {
            final KapittelMap kapittelMap = new KapittelMap();
            kapittelMap.setAntall(doc.getInteger("antall"));
            kapittelMap.setBok(doc.getString("bok"));

            final Document kapittel2AntVersDocument = (Document) doc.get("kapitler");
            final Map<String, Integer> kapittel2AntVers = new HashMap<>();

            if (kapittel2AntVersDocument != null) {
                final Set<String> kapitler = kapittel2AntVersDocument.keySet();

                for (String kapittel : kapitler) {
                    kapittel2AntVers.put(kapittel, kapittel2AntVersDocument.getInteger(kapittel));
                }
            }

            kapittelMap.setKapittel2AntVers(kapittel2AntVers);

            return kapittelMap;
        }).forEach(addToList);

        return kapittelMaps;
    }

}

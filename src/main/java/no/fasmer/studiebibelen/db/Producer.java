package no.fasmer.studiebibelen.db;

import com.mongodb.MongoClient;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class Producer {
    
    @Inject
    private Logger logger;
    
    @Produces
    public MongoClient mongoClient() {
        try {
            return new MongoClient("localhost", 27017);
        } catch (Exception e) {
            logger.error("Could not create mongodb client.", e);
        }
        
        return null;
    }
    
}

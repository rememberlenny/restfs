package it.at.restfs.http.api;

import com.google.inject.Inject;

import akka.http.javadsl.server.Route;
import it.at.restfs.http.services.PathHelper.Request;
import it.at.restfs.http.services.PerRequestContext;
import it.at.restfs.storage.dto.AssetType;
import it.at.restfs.storage.dto.FileStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class DeleteController implements Controller {

	private PerRequestContext x;            
	
    //operation = DELETE
    public Route delete(Request t) {
        return x.withFuture(() -> {
            final AssetType typeOf = x.getStorage().typeOf(t.getPath());        
            
            final FileStatus result = AssetType.FILE == typeOf ? 
                x.getStorage().getStatus(t.getPath()) :
                x.getStorage().listStatus(t.getPath());
            
            x.getStorage().delete(t.getPath());
            
            return result;                    
        });
    }
}

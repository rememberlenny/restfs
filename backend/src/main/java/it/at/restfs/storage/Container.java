package it.at.restfs.storage;

import java.util.UUID;
import lombok.Data;

@Data
public class Container {

    private String name;
    
    private UUID id;
    
}

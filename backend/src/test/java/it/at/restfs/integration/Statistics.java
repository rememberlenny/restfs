package it.at.restfs.integration;

import java.util.List;
import org.junit.Test;
import com.google.common.collect.Iterables;
import it.at.restfs.BaseTest;
import it.at.restfs.Operation;
import okhttp3.ResponseBody;

public class Statistics extends BaseTest {

    @Test
    public void simpleCase() throws Throwable {
        
        final ExecutionContext ctx = ExecutionContext.builder()
            .container(getContainer())
            .stopOnError(true)
//            .printResponse(true)
            .build();
        
        runCommands(
            ctx, 
            buildCommand("fileX", Operation.CREATE),
            buildCommand("dirX", Operation.MKDIRS),
            buildCommand("fileX", Operation.GETSTATUS),
            buildCommand("dirX", Operation.LISTSTATUS)
        );

        wait(5);
        
        final List<ResponseBody> r1 = runCommands(
            ctx, 
            buildStatsCommand()
        );
        
        System.out.println(Iterables.get(r1, 0).string());

        runCommands(
            ctx,
            buildCommand("file", Operation.CREATE),
            buildCommand("dir", Operation.MKDIRS),
            buildCommand("file", Operation.GETSTATUS),
            buildCommand("dir", Operation.LISTSTATUS),
            buildCommand("file", Operation.RENAME, queryBuilder("target", "file2")),
            buildCommand("dir", Operation.RENAME, queryBuilder("target", "dir2")),
            buildCommand("file2", Operation.GETSTATUS),
            buildCommand("dir2", Operation.LISTSTATUS)                        
        );
        
        wait(10);
        
        final List<ResponseBody> r2 = runCommands(
            ctx, 
            buildStatsCommand()
        );
        
        System.out.println(Iterables.get(r2, 0).string());
        
    }
    
}

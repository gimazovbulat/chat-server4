package myApp.config;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import lombok.Data;

@Parameters(separators = "=")
@Data
public class ServerArgs {
    @Parameter(names = {"-port"})
    private
    Integer port;
}

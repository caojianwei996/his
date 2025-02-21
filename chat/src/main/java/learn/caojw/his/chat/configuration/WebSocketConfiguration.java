package learn.caojw.his.chat.configuration;
import learn.caojw.his.chat.endpoint.DoctorEndpoint;
import learn.caojw.his.chat.endpoint.PatientEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebSocket配置类
 *
 * @author 曹健伟
 * @version 1.0-SNAPSHOT
 */
@Configuration
public class WebSocketConfiguration {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        ServerEndpointExporter serverEndpointExporter = new ServerEndpointExporter();
        serverEndpointExporter.setAnnotatedEndpointClasses(
                DoctorEndpoint.class,
                PatientEndpoint.class
        );
        return serverEndpointExporter;
    }
}

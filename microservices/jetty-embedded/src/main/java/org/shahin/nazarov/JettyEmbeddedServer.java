package org.shahin.nazarov;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import static org.eclipse.jetty.servlet.ServletContextHandler.NO_SESSIONS;

public class JettyEmbeddedServer {
    public static void main(String[] args) {
        Server server = new Server(8081);

        ServletContextHandler servletContextHandler = new ServletContextHandler(NO_SESSIONS);

        servletContextHandler.setContextPath("/");
        server.setHandler(servletContextHandler);

        ServletHolder servletHolder = servletContextHandler.addServlet(ServletContainer.class, "/*");
        servletHolder.setInitOrder(0);
        servletHolder.setInitParameter(
                "jersey.config.server.provider.packages",
                "org.shahin.nazarov"
        );
        servletHolder.setAsyncSupported(true);

        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            System.out.println("Error occurred while starting Jetty");
            System.exit(1);
        } finally {
            server.destroy();
        }
    }
}

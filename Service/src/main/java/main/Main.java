package main;

import accounts.AccountService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;
import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;
import chat.WebSocketChatServlet;


public class Main {
    public static void main(String[] args) throws Exception {
        DBService dbService = new DBService();
	    AccountService accountService = new AccountService(dbService);

        //dbService.printConnectInfo();

        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
        context.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/api/v1/sessions");
	    context.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");
        context.addServlet(new ServletHolder(allRequestsServlet), "/*");

        ResourceHandler resource_handler = new ResourceHandler();
	    resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }
}
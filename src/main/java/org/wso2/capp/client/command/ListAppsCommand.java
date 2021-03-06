package org.wso2.capp.client.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kohsuke.args4j.Option;
import org.wso2.capp.client.exception.CommandExecutionException;
import org.wso2.capp.client.executers.ClientExecutor;


public class ListAppsCommand implements Command {
    private static final Logger log = LogManager.getLogger(ListAppsCommand.class);

    @Option(name = "--server",
            usage = "Specify the server url",
            hidden = false,
            aliases = {"--server", "-S"},
            required = true)
    private String serverUrl = "";

    @Option(name = "--username",
            usage = "Specify the username",
            hidden = false,
            aliases = {"-U"},
            required = true)
    private String userName = "";

    @Option(name = "--password",
            usage = "Specify the password",
            hidden = false,
            aliases = {"-P"},
            required = true)
    private String password = "";

    @Option(name = "--trustore-location",
            usage = "Specify the truststore location",
            hidden = false,
            aliases = {"-T"},
            required = true)
    private String trustoreLocation = "";

    @Option(name = "--trustore-password",
            usage = "Specify the truststore password",
            hidden = false,
            aliases = {"-TP"},
            required = true)
    private String trustorePassword = "";

    public ListAppsCommand() {
    }

    @Override
    public void execute() throws CommandExecutionException {
        setSystemProperties(trustoreLocation, trustorePassword);
        try {
            ClientExecutor client = new ClientExecutor(serverUrl, userName, password);
            String[] appList = client.getExistingApplicationList();
            if(appList != null) {
                for (String app : appList) {
                    log.info("Capp was found with the name " + app);
                    System.out.println(app);
                }
                return;
            }
            log.warn("Unable to find any deployed CApps");
        } catch (Exception e) {
            throw new CommandExecutionException("Error while executing List command", e);
        }
    }

    private static void setSystemProperties(String trustStorePath, String trustStorePassword) {
        System.setProperty("javax.net.ssl.trustStore", trustStorePath);
        System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
        System.setProperty("javax.net.ssl.trustStoreType", "JKS");
    }
}

package Client_Server.server;

import Client_Server.client.ClientController;

import java.util.List;

public interface IServerController {

    boolean isWork();

    void setWork(boolean work);

    List<ClientController> getClientsList();

    boolean connectUser(ClientController client);

    void disconnectUser(ClientController client);

    void message(String text);

    String getHistory();
}

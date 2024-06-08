package Client_Server.server;

import Client_Server.client.ClientController;

import java.util.ArrayList;
import java.util.List;

public class ServerController implements IServerController{
    private List<ClientController> clientsList;
    private IServerRepository serverRepository;

    private IServerView IServerView;
    private boolean work;

    public ServerController(IServerView serverView, IServerRepository serverRepository) {
        this.clientsList = new ArrayList<>();
        this.serverRepository = serverRepository;
        this.IServerView = serverView;
        showHistory();
    }

    public boolean isWork() {
        return work;
    }

    public void setWork(boolean work) {
        this.work = work;
    }

    public List<ClientController> getClientsList() {
        return clientsList;
    }

    public boolean connectUser(ClientController client) {
        if (!work) {
            return false;
        }
        clientsList.add(client);
        return true;
    }

    public void disconnectUser(ClientController client) {
        clientsList.remove(client);
        if (client != null) {
            client.disconnectedFromServer();
        }
    }

    public void message(String text) {
        if (!work) {
            return;
        }
        IServerView.appendLog(text);
        answerAll(text);
        serverRepository.saveInLog(text);
    }

    private void answerAll(String text) {
        for (ClientController client : clientsList) {
            client.answerFromServer(text);
        }
    }


    public String getHistory() {
        return serverRepository.getLog();
    }

    private void showHistory() {
        String log = getHistory();
        if (log != null){
            IServerView.appendLog(log);
        }
    }
}

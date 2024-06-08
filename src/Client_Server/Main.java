package Client_Server;

import Client_Server.client.ClientController;
import Client_Server.client.ClientGUI;
import Client_Server.server.IServerView;
import Client_Server.server.ServerController;
import Client_Server.server.ServerGUI;
import Client_Server.server.ServerRepository;

public class Main {
    public static void main(String[] args) {

        //создание объектов сервера и создание связи между ними
        IServerView serverGUI = new ServerGUI();
        ServerRepository serverRepository = new ServerRepository();
        ServerController serverController = new ServerController(serverGUI, serverRepository);
        serverGUI.setServerController(serverController);

        //создание объектов клиента1 и создание связи между ними
        ClientGUI clientGUI1 = new ClientGUI();
        ClientController clientController1 = new ClientController(clientGUI1, serverController);
        clientGUI1.setClient(clientController1);
        clientController1.setServerController(serverController);

        //создание объектов клиента2 и создание связи между ними
        ClientGUI clientGUI2 = new ClientGUI();
        ClientController clientController2 = new ClientController(clientGUI2, serverController);
        clientGUI2.setClient(clientController2);
        clientController2.setServerController(serverController);
    }
}
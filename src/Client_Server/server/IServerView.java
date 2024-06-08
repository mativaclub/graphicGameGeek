package Client_Server.server;

public interface IServerView {

    void appendLog(String text);

    void setServerController(IServerController serverController);
}

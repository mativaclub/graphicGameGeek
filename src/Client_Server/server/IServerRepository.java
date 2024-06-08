package Client_Server.server;

public interface IServerRepository {

    String getLog();

    void saveInLog(String text);
}

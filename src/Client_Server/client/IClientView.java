package Client_Server.client;

/**
 * Интерфейс описывающий абстракцию GUI
 */
public interface IClientView {

    /**
     * Метод для отображения сообщения в GUI
     * @param message текст сообщения
     */
    void showMessage(String message);

    /**
     * Метод отключения от сервера со стороны сервера
     */
    void disconnectedFromServer();
}
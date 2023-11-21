package server.server.model;

import server.client.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Модель сервера
 */
public class Server {
    // статическое поле для создания Singleton объекта сервера.
    private static Server server;
    // Список подключенных клиентов.
    private final List<Client> clients;
    // Статус сервера
    private boolean isWork;

    /**
     * Закрытый конструктор, для контроля за созданием объекта сервера.
     */
    private Server() {
        clients = new ArrayList<>();
    }

    /**
     * Метод получения единственного объекта сервера.
     *
     * @return объект сервера.
     */
    public static Server getServer() {
        if (server == null)
            return new Server();
        return server;
    }

    /**
     * Получение списка подключенных клиентов.
     *
     * @return список клиентов.
     */
    public List<Client> getClients() {
        return clients;
    }

    /**
     * Получение статуса сервера.
     *
     * @return true - сервер работает, иначе false.
     */
    public boolean isWork() {
        return isWork;
    }

    /**
     * Изменение статуса работы сервера.
     *
     * @param work статус работы сервера.
     */
    public void setWork(boolean work) {
        isWork = work;
    }
}

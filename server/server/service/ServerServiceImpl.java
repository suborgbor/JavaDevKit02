package server.server.service;

import server.client.Client;
import server.server.gui.iServerGui;
import server.server.model.Server;
import server.server.reposirory.ServerRepositoryImpl;
import server.server.reposirory.iServerRepository;

/**
 * Сервисный класс реализующий взаимодействие модели сервера,
 * графического интерфейса и репозитория БД.
 */
public class ServerServiceImpl implements iServerService {
    // Поле модели сервера.
    private final Server server;
    // Поле объекта репозитория.
    private final iServerRepository serverRepository;
    // Поле графического интерфейса.
    private final iServerGui serverGui;

    /**
     * Конструктор класса.
     *
     * @param serverGui объект графического интерфейса.
     */
    public ServerServiceImpl(iServerGui serverGui) {
        server = Server.getServer();
        serverRepository = new ServerRepositoryImpl();
        this.serverGui = serverGui;
    }

    /**
     * Подключение клиента к серверу.
     *
     * @param client объект клиента.
     * @return true - при успешном подключении, иначе false.
     */
    @Override
    public boolean connectUser(Client client) {
        if (!server.isWork()) {
            return false;
        }
        server.getClients().add(client);
        return true;
    }

    /**
     * Отключение клиента от сервера.
     *
     * @param client объект клиента.
     */
    @Override
    public void disconnectUser(Client client) {
        server.getClients().remove(client);
        if (client != null) {
            client.disconnect();
        }
    }

    /**
     * Поведение при получении сообщения от клиента.
     *
     * @param text текст сообщения.
     */
    @Override
    public void sendMessage(String text) {
        if (!server.isWork()) {
            return;
        }
        serverGui.appendLog(text);
        answerAll(text);
        serverRepository.saveInLog(text);
    }

    /**
     * Рассылка сообщения всем подключенным клиентам.
     *
     * @param text текст сообщения.
     */
    @Override
    public void answerAll(String text) {
        for (Client client : server.getClients()) {
            client.serverAnswer(text);
        }
    }

    /**
     * Запуск сервера.
     */
    @Override
    public void startServer() {
        server.setWork(true);
        serverRepository.readLog();
    }

    /**
     * Остановка сервера.
     */
    @Override
    public void stopServer() {
        server.setWork(false);
        while (!server.getClients().isEmpty()) {
            server.getClients().get(server.getClients().size() - 1).disconnect();
        }
    }

    /**
     * Проверка состояния работы сервера.
     *
     * @return true - сервер запущен, иначе false.
     */
    @Override
    public boolean isServerWork() {
        return server.isWork();
    }

    /**
     * Поучение истории сообщений из репозитория БД.
     *
     * @return историю сообщений в строковом представлении.
     */
    public String getHistory() {
        return serverRepository.readLog();
    }
}

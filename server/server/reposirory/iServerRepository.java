package server.server.reposirory;

/**
 * Интерфейс взаимодействий с БД.
 */
public interface iServerRepository {
    /**
     * Чтение данных из БД.
     *
     * @return данные из БД.
     */
    String readLog();

    /**
     * Сохранение данных в БД.
     *
     * @param text данные для сохранения.
     */
    void saveInLog(String text);
}

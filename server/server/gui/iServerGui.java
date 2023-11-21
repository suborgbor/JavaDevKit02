package server.server.gui;

/**
 * Интерфейс графического отображения.
 */

public interface iServerGui {
    /**
     * Вывод сообщения в окно сервера
     *
     * @param text текст сообщения.
     */
    void appendLog(String text);
}

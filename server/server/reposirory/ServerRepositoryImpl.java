package server.server.reposirory;

import java.io.FileReader;
import java.io.FileWriter;

/**
 * Класс взаимодействия с БД.
 */
public class ServerRepositoryImpl implements iServerRepository {
    // Путь до файла имитирующего БД.
    public static final String LOG_PATH = "src/server/server/database/log.txt";

    /**
     * Получение данных из БД.
     *
     * @return данные из БД в строковом представлении.
     */
    @Override
    public String readLog() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH);) {
            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Сохранение данных в БД.
     *
     * @param text данные для сохранения.
     */
    @Override
    public void saveInLog(String text) {
        try (FileWriter writer = new FileWriter(LOG_PATH, true)) {
            writer.write(text);
            writer.write("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

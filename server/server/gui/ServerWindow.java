package server.server.gui;

import server.server.service.ServerServiceImpl;
import server.server.service.iServerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Окно графического отображения сервера.
 */
public class ServerWindow extends JFrame implements iServerGui {
    // Размеры окна.
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    // Сервисный класс для взаимодействия с сервером.
    private final iServerService service;

    // Кнопки и поле окна.
    JButton btnStart, btnStop;
    JTextArea log;

    /**
     * Конструктор окна.
     */
    public ServerWindow() {
        service = new ServerServiceImpl(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);

        createPanel();

        setVisible(true);
    }

    /**
     * Вывод сообщений в окно клиента.
     *
     * @param text текст сообщения.
     */
    @Override
    public void appendLog(String text) {
        log.append(text + "\n");
    }

    // Настройка отображения окна и реакций на взаимодействия пользователя.
    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (service.isServerWork()) {
                    appendLog("Сервер уже был запущен");
                } else {
                    service.startServer();
//                    work = true;
                    appendLog("Сервер запущен!");
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!service.isServerWork()) {
                    appendLog("Сервер уже был остановлен");
                } else {
                    service.stopServer();

                    //TODO поправить удаление
                    appendLog("Сервер остановлен!");
                }
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }

    /**
     * Вспомогательный метод для передачи конкретного сервиса окну клиента.
     *
     * @return объект сервиса сервера.
     */
    public iServerService getService() {
        return service;
    }
}

package server.client;

import server.server.service.ServerServiceImpl;
import server.server.service.iServerService;

public class Client {
    private String name;
    private ClientView clientView;
    private iServerService server;
    private boolean connected;

//    public Client(ClientView clientView, ServerWindow serverWindow) {
//        this.clientView = clientView;
//        this.server = serverWindow;
//    }

    public Client(ClientView clientView, iServerService service) {
        this.clientView = clientView;
        this.server = service;
    }

    public boolean connectToServer(String name){
        this.name = name;
        if (server.connectUser(this)){
            printText("Вы успешно подключились!\n");
            connected = true;
            String log = ((ServerServiceImpl) server).getHistory();
            if (log != null){
                printText(log);
            }
            return true;
        } else {
            printText("Подключение не удалось");
            return false;
        }
    }

    //мы посылаем
    public void sendMessage(String message){
        if (connected) {
            if (!message.isEmpty()) {
                server.sendMessage(name + ": " + message);
            }
        } else {
            printText("Нет подключения к серверу");
        }
    }
    //нам посылают
    public void serverAnswer(String answer){
        printText(answer);
    }

    public void disconnect(){
        if (connected) {
            connected = false;
            clientView.disconnectFromServer();
            server.disconnectUser(this);
            printText("Вы были отключены от сервера!");
        }
    }

    public String getName() {
        return name;
    }

    private void printText(String text){
        clientView.showMessage(text);
    }
}

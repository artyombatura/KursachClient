package ConnectToServer;

import Constants.Constants;
import Models.Employee;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

public class Client extends Constants {


    public void connectToServer() {
        try {
            Socket clientSocket = new Socket(Constants.host, Constants.port);
            clientConnect = new Client();
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendMsg(String message) {
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Employee> showAllContribution() throws IOException {
        sendMsg("все рабочии");
        LinkedList<Employee> workers = new LinkedList<>();

        int sizeList = Integer.parseInt(in.readLine());
        for(int i=0;i<sizeList;i++){
            parseStringInContributionAll(in.readLine(),workers);
        }
        return workers;
    }

    private void parseStringInContributionAll(String worker,LinkedList<Employee> list){
        String[] subStr;
        subStr = worker.split(" ");
        list.add(new Employee(Integer.parseInt(subStr[0]),subStr[1],subStr[2],subStr[3],Integer.parseInt(subStr[4])));
    }

    public boolean checkAdminAccount(String login,String password) throws IOException {
        sendMsg("авторизация");
        sendMsg(login+" "+password);
        if(in.readLine().equals("true")){
            return true;
        }
        else{
            return false;
        }
    }

    public void deleteWorker(int id) {
        sendMsg("удалить рабочего");
        sendMsg(String.valueOf(id));
    }

    public void addWorker(String name, String firstNameTextFieldText, String patronymicTextFieldText, String salaryTextFieldText) {
       sendMsg("добавить рабочего");
       sendMsg(name+" "+firstNameTextFieldText+" "+patronymicTextFieldText+" "+salaryTextFieldText);
    }


    public Integer calculate(Employee selectedEmployee) {
        sendMsg("расчет зарплаты");
        sendMsg(selectedEmployee.getHoursWorkedTextField().getText()+" "+selectedEmployee.getWorkRateTextField().getText());
        try {
            return Integer.valueOf(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

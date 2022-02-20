package boxgym;

public final class Constants {
    //Credenciais de login
    public static final String DEFAULT_LOGIN_USERNAME = "admin";
    public static final String DEFAULT_LOGIN_PASSWORD = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";

    //Caminhos FXML
    public static final String PATH_LOGIN_VIEW = "/boxgym/view/Login.fxml";
    public static final String PATH_MAINSCREEN_VIEW = "/boxgym/view/MainScreen.fxml";
    public static final String PATH_HOME_VIEW = "/boxgym/view/Home.fxml";
    public static final String PATH_PLANS_VIEW = "/boxgym/view/Plans.fxml";
    public static final String PATH_CLIENTS_VIEW = "/boxgym/view/Clients.fxml";
    public static final String PATH_SUPPLIERS_VIEW = "/boxgym/view/Suppliers.fxml";
    public static final String PATH_EMPLOYEES_VIEW = "/boxgym/view/Employees.fxml";    
    public static final String PATH_BILLSTOPAY_VIEW = "/boxgym/view/BillsToPay.fxml";
    public static final String PATH_BILLSTORECEIVE_VIEW = "/boxgym/view/BillsToReceive.fxml";
    public static final String PATH_BANKS_VIEW = "/boxgym/view/Banks.fxml";
    public static final String PATH_SALES_VIEW = "/boxgym/view/Sales.fxml";
    public static final String PATH_PRODUCTS_VIEW = "/boxgym/view/Products.fxml";
    public static final String PATH_STOCK_VIEW = "/boxgym/view/Stock.fxml";
    public static final String PATH_FILES_VIEW = "/boxgym/view/Files.fxml";
    public static final String PATH_EXERCISES_VIEW = "/boxgym/view/Exercises.fxml";
    public static final String PATH_MEASUREMENTS_VIEW = "/boxgym/view/Measurements.fxml";
    public static final String PATH_ABOUT_VIEW = "/boxgym/view/About.fxml";
            
    //Títulos dos stages
    public static final String TITLE_LOGIN = "Login";
    public static final String TITLE_MAINSCREEN = "Tela Principal";
    
    //JDBC
    public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/academia";
    public static final String CONNECTION_USERNAME = "root";
    public static final String CONNECTION_USERPASSWORD = "phpMyAdm1n$QLp@sswd";
            
    //Janela de aviso
    public static final String WARNING_ALERT_TITLE = "Atenção";
    public static final String WARNING_ALERT_HEADER = "Não foi possível efetuar o login!";
    public static final String WARNING_ALERT_CONTENT_EMPTY = "Por favor, preencha todos os campos!";
    public static final String WARNING_ALERT_CONTENT_WRONG = "Usuário e/ou senha inválidos!";
}

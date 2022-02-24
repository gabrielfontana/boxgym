package boxgym;

public final class Constant {

    //Credenciais de login
    public static final String DEFAULT_LOGIN_USERNAME = "admin";
    public static final String DEFAULT_LOGIN_PASSWORD = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";

    //Caminhos FXML
    public static final String LOGIN_VIEW = "/boxgym/view/Login.fxml";
    public static final String MAINSCREEN_VIEW = "/boxgym/view/MainScreen.fxml";
    public static final String HOME_VIEW = "/boxgym/view/Home.fxml";
    public static final String PLANS_VIEW = "/boxgym/view/Plans.fxml";
    public static final String CLIENTS_VIEW = "/boxgym/view/Clients.fxml";
    public static final String SUPPLIERS_VIEW = "/boxgym/view/Suppliers.fxml";
    public static final String SUPPLIERS_ADD_VIEW = "/boxgym/view/SuppliersAddScreen.fxml";
    public static final String EMPLOYEES_VIEW = "/boxgym/view/Employees.fxml";
    public static final String BILLSTOPAY_VIEW = "/boxgym/view/BillsToPay.fxml";
    public static final String BILLSTORECEIVE_VIEW = "/boxgym/view/BillsToReceive.fxml";
    public static final String BANKS_VIEW = "/boxgym/view/Banks.fxml";
    public static final String SALES_VIEW = "/boxgym/view/Sales.fxml";
    public static final String PRODUCTS_VIEW = "/boxgym/view/Products.fxml";
    public static final String STOCK_VIEW = "/boxgym/view/Stock.fxml";
    public static final String FILES_VIEW = "/boxgym/view/Files.fxml";
    public static final String EXERCISES_VIEW = "/boxgym/view/Exercises.fxml";
    public static final String MEASUREMENTS_VIEW = "/boxgym/view/Measurements.fxml";
    public static final String ABOUT_VIEW = "/boxgym/view/About.fxml";

    //Títulos dos stages
    public static final String LOGIN_TITLE = "Login";
    public static final String MAINSCREEN_TITLE = "Tela Principal";
    public static final String SUPPLIERS_ADD_TITLE = "Adicionando Fornecedor";

    //JDBC
    public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/boxgym";
    public static final String CONNECTION_USERNAME = "root";
    public static final String CONNECTION_USERPASSWORD = "phpMyAdm1n$QLp@sswd";

    //Janela de aviso
    public static final String WARNING_ALERT_TITLE = "Atenção";
    public static final String WARNING_ALERT_HEADER = "Não foi possível efetuar o login!";
    public static final String WARNING_ALERT_CONTENT_EMPTY = "Por favor, preencha todos os campos!";
    public static final String WARNING_ALERT_CONTENT_WRONG = "Usuário e/ou senha inválidos!";
    
    //Lista de unidades federativas
    public static final String[] FEDERATIVE_UNITS_LIST = {
        "AC", "AL", "AP", "AM", "BA", "CE",
        "DF", "ES", "GO", "MA", "MT", "MS",
        "MG", "PA", "PB", "PR", "PE", "PI",
        "RJ", "RN", "RS", "RO", "RR", "SC",
        "SP", "SE", "TO"
    };
    
    public static final String COMBO_BOX_PROMPT_TEXT = "Selecione";
}

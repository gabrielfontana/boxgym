package boxgym;

public final class Constant {
      
    //JDBC
    public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/boxgym";
    public static final String CONNECTION_USERNAME = "root";
    public static final String CONNECTION_USERPASSWORD = "phpMyAdm1n$QLp@sswd";

    //Janela de aviso
    public static final String LOGIN_WARNING_ALERT_TITLE = "Atenção";
    public static final String LOGIN_WARNING_ALERT_HEADER = "Não foi possível efetuar o login!";
    public static final String LOGIN_WARNING_ALERT_EMPTY_CONTENT = "Por favor, preencha todos os campos!";
    public static final String LOGIN_WARNING_ALERT_WRONG_CONTENT = "Usuário e/ou senha inválidos!";

    //Lista de unidades federativas
    public static final String[] FEDERATIVE_UNITS_LIST = {
        "AC", "AL", "AP", "AM", "BA", "CE",
        "DF", "ES", "GO", "MA", "MT", "MS",
        "MG", "PA", "PB", "PR", "PE", "PI",
        "RJ", "RN", "RS", "RO", "RR", "SC",
        "SP", "SE", "TO"
    };

    //ComboBox
    public static final String COMBO_BOX_PROMPT_TEXT = "Selecione";

    //Validação de dados 
    public static final String STANDARD_REGEX = "[a-zA-Z\\u00C0-\\u00FF0-9 ._-]";
    public static final String LETTERS_REGEX = "[a-zA-Z\\u00C0-\\u00FF ]";
    public static final String POSITIVE_INTEGERS_NUMBERS_REGEX = "[0-9]";
    public static final String EMAIL_REGEX = "[A-Za-z0-9@._-]";
    public static final int STANDARD_MAX_LENGTH = 255;
    public static final int CNPJ_MAX_LENGTH = 14;
    public static final int PHONE_MAX_LENGTH = 11;
    public static final int CEP_MAX_LENGTH = 8;
    public static final String TOOLTIP_TEXT = "Sem pontuação!";

}

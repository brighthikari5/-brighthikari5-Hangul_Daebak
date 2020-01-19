package valeriamoscoso.ioc.hanguldaebak.data.entity;

/**
 * Class to save Token response
 * @author Valeria Moscoso León
 * */
public class TokenResponse {

    private Token success;

    public TokenResponse(Token success) {
        this.success = success;
    }

    private TokenResponse() {}

    public String getToken() {
        return success.getToken();
    }
}

/**

 { "success": {"token": "..-TldrUULYKCp..." } }

 */
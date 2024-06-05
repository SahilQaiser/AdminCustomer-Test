package in.onato.demo.dto;

public class GetTokenResponse {
    private String token;

    public GetTokenResponse(){}

    public GetTokenResponse(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

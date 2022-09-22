package br.com.alura.forum.controller.dto;

public class TokenDto {
    private String token;
    private String bearer;

    public String getToken() {
        return token;
    }

    public String getBearer() {
        return bearer;
    }

    public TokenDto(String token, String bearer) {
        this.token = token;
        this.bearer = bearer;
    }
}

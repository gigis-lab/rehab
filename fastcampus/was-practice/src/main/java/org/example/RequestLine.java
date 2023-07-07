package org.example;

import java.util.Objects;

public class RequestLine {

    private final String method; // GET

    private final String urlPath; // /calculate

    private QueryStrings queryStrings; // operand1=11&operator=*&operand2=55

    public RequestLine(String method, String urlPath, String queryStrings) {
        this.method = method;
        this.urlPath = urlPath;
        this.queryStrings = new QueryStrings(queryStrings);

    }

    /** 요청 들어오는 방식
     * GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1
     * */
    public RequestLine(String requestLine) {
        String[] tokens = requestLine.split(" ");
        this.method = tokens[0];
        // urlPathTokens[0] = /calculate, urlPathTokens[1] = operand1~~
        String[] urlPathTokens = tokens[1].split("\\?");
        // urlPath = /calculate
        this.urlPath = urlPathTokens[0];

        if(urlPathTokens.length == 2){ //query가 있다면
            this.queryStrings = new QueryStrings(urlPathTokens[1]);
        }
    }

    public boolean isGetRequest() {
        return "GET".equals(this.method);
    }

    public boolean matchPath(String requestPath) {
        return urlPath.equals(requestPath);
    }

    public QueryStrings getQueryStrings() {
        return this.queryStrings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestLine that = (RequestLine) o;
        return Objects.equals(method, that.method) && Objects.equals(urlPath, that.urlPath) && Objects.equals(queryStrings, that.queryStrings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, urlPath, queryStrings);
    }
}

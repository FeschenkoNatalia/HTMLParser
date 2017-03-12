import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        HtmlHandler handler = new HtmlHandler();
        StringBuilder htmlDocument = handler.handleWebPage("https://eda.ru/recepty/osnovnye-blyuda/kurica-pikasso-25902");
        ParseHtml parseHtml = new ParseHtml();
        String resultedDocument = parseHtml.symbolsReplacement(htmlDocument.toString());
        System.out.println(parseHtml.parseHtmlDoc(resultedDocument));
    }
}

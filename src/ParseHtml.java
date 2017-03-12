    import java.util.HashMap;
    import java.util.Map;
    import java.util.Stack;

    public class ParseHtml {
        public String symbolsReplacement(String htmlDocument) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("&quot;", "\"");
            map.put("&amp;", "&");
            map.put("&lt;", "<");
            map.put("&gt;", ">");
            map.put("&nbsp;", " ");
            map.put("&ndash;", "–");
            map.put("&mdash;", "—");
            map.put("&lsquo;", "‘");
            map.put("&rsquo;", "’");
            map.put("&sbquo;", "‚");
            map.put("&ldquo;", "“");
            map.put("&laquo;", "[");
            map.put("&raquo;", "]");
            map.put("&rdquo;", "”");
            map.put("&bdquo;", "„");
            map.put("&frac14;", "1/4");
            map.put("&frac12;", "1/2");
            map.put("&frac34;", "3/4");
            map.put("&copy;", "©");
            map.put("&bull;", ".");
            map.put("&#8209;", "-");

            for (Map.Entry<String, String> item : map.entrySet()) {
                htmlDocument = htmlDocument.replace(item.getKey(), item.getValue());
            }
            return htmlDocument;
        }

        public String parseHtmlDoc(String htmlDocument) {
            StringBuilder resultedDocument = new StringBuilder();
            Stack<Tags> tag = new Stack<>();
            tag.push(Tags.TEXT_BETWEEN_TAGS);
            char temp;
            for (int i = 0; i < htmlDocument.length(); i++) {
                temp = htmlDocument.charAt(i);
                if (temp == '<' && htmlDocument.charAt(i + 1) == 's' && htmlDocument.charAt(i + 2) == 'c' &&
                        htmlDocument.charAt(i + 3) == 'r') {
                    tag.push(Tags.SCRIPT_TAG);
                    continue;
                }
                if (temp == '<' && htmlDocument.charAt(i + 1) == '/' && htmlDocument.charAt(i + 2) == 's'
                        && htmlDocument.charAt(i + 3) == 'c') {
                    tag.pop();
                    i = i + 8;
                    continue;
                }
                if (tag.peek() == Tags.SCRIPT_TAG) {
                    continue;
                }
                if (temp == '<' && htmlDocument.charAt(i + 1) == 's' && htmlDocument.charAt(i + 2) == 't' &&
                        htmlDocument.charAt(i + 3) == 'y') {
                    tag.push(Tags.STYLE_TAG);
                    continue;
                }
                if (temp == '<' && htmlDocument.charAt(i + 1) == '/' && htmlDocument.charAt(i + 2) == 's' &&
                        htmlDocument.charAt(i + 3) == 't') {
                    tag.pop();
                    i = i+ 7;
                    continue;
                }
                if (tag.peek() == Tags.STYLE_TAG) {
                    continue;
                }
                if (temp == '<' && (tag.peek() != Tags.SCRIPT_TAG || tag.peek() != Tags.STYLE_TAG)) {
                    tag.push(Tags.OTHER_TAG);
                    continue;
                }
                if (temp == '>' && tag.peek() == Tags.OTHER_TAG) {
                    tag.pop();
                    continue;
                }
                if (tag.peek() == Tags.TEXT_BETWEEN_TAGS) resultedDocument.append(temp);
            }
            return (resultedDocument.toString()).replaceAll("[\\s]{2,}", " ");
        }
    }
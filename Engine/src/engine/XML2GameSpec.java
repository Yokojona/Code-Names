package engine;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XML2GameSpec {
    public static GameSpec ParseFile(String file_path) {
        Document doc = File2Document(file_path);
        if (doc == null) {return null;}
        String[] game_words = getWords(doc, "ECN-Game-Words");
        String[] black_words = getWords(doc, "ECN-Black-Words");

        Element boardElement = (Element) doc.getElementsByTagName("ECN-Board").item(0);
        int cards_count = Integer.parseInt(boardElement.getAttribute("cards-count"));
        int black_cards_count = Integer.parseInt(boardElement.getAttribute("black-cards-count"));

        Element layoutElement = (Element) boardElement.getElementsByTagName("ECN-Layout").item(0);
        int rows = Integer.parseInt(layoutElement.getAttribute("rows"));
        int columns = Integer.parseInt(layoutElement.getAttribute("columns"));

        int numOfTeams = 2;
        String[] team_names = new String[numOfTeams];
        int[] team_cards_count = new int[numOfTeams];
        for (int i = 0; i < numOfTeams; i++) {
            fillTeam(doc, i ,team_names, team_cards_count);
        }

        return new GameSpec(file_path, game_words, black_words,
                cards_count, black_cards_count, rows, columns,
                team_names, team_cards_count);
    }

    private static Document File2Document(String file_path) {
        if (!file_path.endsWith(".xml")) {return null;}
        File xmlFile = new File(file_path);
        if (!xmlFile.exists()) {return null;}
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            return null;
        }
        Document doc;
        try {
            doc = dBuilder.parse(xmlFile);
        } catch (SAXException | IOException e) {
            return null;
        }
        doc.getDocumentElement().normalize();
        return doc;
    }

    private static String[] getWords(Document doc, String tagName) {
        NodeList wordsNode = doc.getElementsByTagName(tagName);
        return wordsNode.item(0).getTextContent().trim().split("\\s+");
    }

    private static void fillTeam(Document doc, int i, String[] team_names, int[] team_cards_count) {
        NodeList teamList = doc.getElementsByTagName("ECN-Team" + (i + 1));
        Element teamElement = (Element) teamList.item(0);
        team_names[i] = teamElement.getAttribute("name");
        team_cards_count[i] = Integer.parseInt(teamElement.getAttribute("cards-count"));
    }
}

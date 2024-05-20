package engine.jaxb;

import dto.GameSpec;
import engine.jaxb.generated.ECNGame;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Utilizes JAXB to parse XML files into Java object representations.
 */
public class JaxbParser {

    /**
     * Parses the XML file at the given file path into a GameSpec object.
     *
     * This method reads an XML file using JAXB, mapping the contents to a custom ECNGame class
     * which is assumed to match the XML structure as defined by the associated XSD.
     *
     * @param file_path The file path to the XML document to be parsed.
     * @return A GameSpec object populated with data extracted from the XML file, or null if an error occurs.
     */
    public static GameSpec ParseFile(String file_path) {
        JAXBContext jaxbContext = null;
        Unmarshaller unmarshaller = null;
        ECNGame ecnGame = null;

        try {
            jaxbContext = JAXBContext.newInstance(ECNGame.class);
            unmarshaller = jaxbContext.createUnmarshaller();
            // Casting is safe assuming the XML structure strictly follows the ECNGame class structure
            ecnGame = (ECNGame) unmarshaller.unmarshal(new File(file_path));
        } catch (JAXBException e) {
            e.printStackTrace();  // Optionally log the error or handle it accordingly
            return null;  // Return null to indicate failure in parsing
        }

        // Initialize arrays for teams, assuming two teams as a starting point
        int numOfTeams = 2; // For now, adding more teams must update this logic
        String[] team_names = new String[numOfTeams];
        int[] team_cards_count = new int[numOfTeams];

        // Extracting team names and card counts from the parsed XML data
        team_names[0] = ecnGame.getECNTeam1().getName();
        team_cards_count[0] = ecnGame.getECNTeam1().getCardsCount();
        team_names[1] = ecnGame.getECNTeam2().getName();
        team_cards_count[1] = ecnGame.getECNTeam2().getCardsCount();

        // Constructing a new GameSpec object to return, populated with the data from the XML
        return new GameSpec(file_path,
                ecnGame.getECNWords().getECNGameWords().trim().split("\\s+"),
                ecnGame.getECNWords().getECNBlackWords().trim().split("\\s+"),
                ecnGame.getECNBoard().getCardsCount(),
                ecnGame.getECNBoard().getBlackCardsCount(),
                ecnGame.getECNBoard().getECNLayout().getRows(),
                ecnGame.getECNBoard().getECNLayout().getColumns(),
                team_names,
                team_cards_count);
    }
}


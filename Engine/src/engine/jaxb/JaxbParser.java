package engine.jaxb;

import dto.GameSpec;
import engine.jaxb.generated.ECNGame;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxbParser {
    public static GameSpec ParseFile(String file_path) {
        JAXBContext jaxbContext = null;
        Unmarshaller unmarshaller = null;
        ECNGame ecnGame = null;
        try {
            jaxbContext = JAXBContext.newInstance(ECNGame.class);
            unmarshaller = jaxbContext.createUnmarshaller();
            ecnGame = (ECNGame) unmarshaller.unmarshal(new File(file_path));
        } catch (JAXBException e) {
            return null;
        }
        int numOfTeams = 2; //for later adding more teams
        String[] team_names = new String[numOfTeams];
        int[] team_cards_count = new int[numOfTeams];
        team_names[0] = ecnGame.getECNTeam1().getName();
        team_cards_count[0] = ecnGame.getECNTeam1().getCardsCount();
        team_names[1] = ecnGame.getECNTeam2().getName();
        team_cards_count[1] = ecnGame.getECNTeam2().getCardsCount();
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

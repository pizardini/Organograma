import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Organograma {
    public static void main(String[] args) throws IOException{
        Map<String, Setor> org = new HashMap<>();
        List<String> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("organograma.txt"));

        String line;
        String setorRaiz = reader.readLine();

        while ((line = reader.readLine()) != null && !line.equals("FIM")) {
            String[] parts = line.split(" ");

            if (parts.length != 2) {
                continue;
            }

            String subordinadoName = parts[0];
            String responsavelName = parts[1];

            Setor subordinate = org.computeIfAbsent(subordinadoName, Setor::new);
            Setor responsible = org.computeIfAbsent(responsavelName, Setor::new);

            responsible.addSubordinate(subordinate);
        }

        reader.close();

        result.add(setorRaiz);

        for (String name : result) {
            System.out.println(name);
        }
    }
}


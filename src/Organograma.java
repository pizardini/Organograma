import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Organograma {
    public static void main(String[] args) throws IOException, LimitException {
        Map<String, Setor> org = new HashMap<>();
        List<String> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("organograma.txt"));

        String line;
        String setorRaiz = reader.readLine();
        String busca = null;

        while ((line = reader.readLine()) != null && !line.equals("FIM")) {
            String[] parts = line.split(" ");

            if (parts.length == 1) {
                busca = line;
                continue;
            }

            String subordinadoName = parts[0];
            String responsavelName = parts[1];

            if (parts[0].length() > 40 || parts[1].length() > 40) {
                throw new LimitException("Nome com mais de 40 caracteres");
            }

            Setor subordinate = org.computeIfAbsent(subordinadoName, Setor::new);
            Setor responsible = org.computeIfAbsent(responsavelName, Setor::new);

            responsible.addSubordinate(subordinate);
        }

        reader.close();

        Setor setorBuscado = org.get(busca);
        if (setorBuscado != null) {
            result.add(setorBuscado.getName());
            visitarSetores(setorBuscado, result);
        }

        for (String nome : result) {
            System.out.println(nome);
        }
    }


    public static void visitarSetores(Setor setor, List<String> resultado) {
        List<Setor> subordinados = setor.getSubordinates();
        Collections.sort(subordinados, Comparator.comparing(Setor::getName));

        for (Setor subordinado : subordinados) {
            resultado.add(subordinado.getName());
            visitarSetores(subordinado, resultado);
        }
    }
}


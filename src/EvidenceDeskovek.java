import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EvidenceDeskovek {
    public static ArrayList<Deskovka> seznamDeskovek = new ArrayList<Deskovka>();
    public EvidenceDeskovek(){
        cteniZeSouboru();
        vypisDeskovek();
    }

    public int getVelikostSeznamu(){
        return seznamDeskovek.size();
    }
    public void cteniZeSouboru() {
        try (
                FileReader fileReader = new FileReader("text.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                Scanner scanner = new Scanner(bufferedReader);
                )
        {
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] split = line.split(";");
                boolean mameKoupenou = false;
                if (split[1] == "ano"){
                    mameKoupenou = true;
                }
                else{
                    mameKoupenou = false;
                }
                seznamDeskovek.add(new Deskovka(split[0], mameKoupenou, Integer.parseInt(split[2])));
            }
        }
        catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public void vypisDeskovek(){
        for (Deskovka deskovka : seznamDeskovek){
            System.out.println(deskovka.getNazevDeskovky() + " " + deskovka.isMameKoupenou() + " " + deskovka.getOblibenost());
        }
    }

    public ArrayList<Deskovka> getSeznamDeskovek(){
        return seznamDeskovek;
    }
}

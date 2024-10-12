package setup;

import core.Carnival;
import core.store.Prize;

import java.io.*;

public class FileIO {

    public void update() {
        {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            writer.write("Tickets: " + Carnival.getTickets());
            for (Prize p : Carnival.getInventory().getInventory()) {
                writer.write("\n" + p.getName());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

    public void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Tickets: ")) {
                    String t = line.substring(9).trim(); // Extract the ticket count
                    Carnival.setTickets(Integer.parseInt(t));
                } else {
                    Carnival.getInventory().addPrize(new Prize(line.trim()));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
